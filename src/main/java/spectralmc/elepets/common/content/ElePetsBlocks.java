package spectralmc.elepets.common.content;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsBlocks {
    public static final DeferredRegister<Block> ELEPETS_BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> UNLIT_PLACEHOLDER = ELEPETS_BLOCKS.register("unlit_placeholder", () -> new Block(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.5F)));

    public static void register(IEventBus eventBus) {
        ELEPETS_BLOCKS.register(eventBus);
    }
}
