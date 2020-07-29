package drizzs.magicnmonsters;

import drizzs.magicnmonsters.capability.MagicPowerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static drizzs.magicnmonsters.MagicMon.MOD_ID;
import static drizzs.magicnmonsters.capability.MagicPowerCap.addMagicPower;
import static drizzs.magicnmonsters.capability.MagicPowerCap.removeMagicPower;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(MOD_ID, "magic_power"), new MagicPowerProvider());
        }
    }

    @SubscribeEvent
    public static void playerRightClick(PlayerInteractEvent.RightClickEmpty event){
        PlayerEntity player = event.getPlayer();
        if(player.getActiveItemStack().isEmpty()) {
            removeMagicPower(player, 50);
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
       PlayerEntity player = event.player;
       if(event.player.world.getGameTime() % 20 ==0){
           addMagicPower(player);
       }
    }
}
