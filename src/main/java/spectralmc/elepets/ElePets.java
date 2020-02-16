package spectralmc.elepets;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spectralmc.elepets.api.serializer.SerializerRegistryHandler;
import spectralmc.elepets.client.ElePetsKeybinds;
import spectralmc.elepets.common.content.*;
import spectralmc.elepets.testing.ClientSetup;
import spectralmc.elepets.util.ElePetsGroup;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import spectralmc.elepets.api.database.IPlayerInfo;
import spectralmc.elepets.common.capability.PlayerInfoStorage;
import spectralmc.elepets.common.capability.PlayerInfoStorageProvider;

import javax.annotation.Nullable;

import static spectralmc.elepets.ElePets.MOD_ID;

@Mod(MOD_ID)
public class ElePets {
    public static final String MOD_ID = "elepets";
    public static Logger LOGGER = LogManager.getLogger();

    public static final ElePetsGroup ITEM_GROUP = new ElePetsGroup(ItemGroup.GROUPS.length, "elepets_group");

    public ElePets() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);

        ElePetsEggRegistry.register(bus);
        ElePetsItems.register(bus);
        ElePetsEntityRegistry.register(bus);
        ElePetsBlocks.register(bus);
        SerializerRegistryHandler.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.addGenericListener(World.class, ElePets::attachCapability);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IPlayerInfo.class, new Capability.IStorage<IPlayerInfo>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<IPlayerInfo> capability, IPlayerInfo instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IPlayerInfo> capability, IPlayerInfo instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, PlayerInfoStorage::new);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        ElePetsKeybinds.registerBindings(); // FIXME this probably fails on a dedicated server
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }

    private static void attachCapability(AttachCapabilitiesEvent<World> event) {
        World world = event.getObject();
        if (world.getDimension().getType() == DimensionType.OVERWORLD) {
            event.addCapability(new ResourceLocation(MOD_ID, "player_info_storage"), new PlayerInfoStorageProvider());
        }
    }
}
