package spectralmc.elepets.common.blocks.eggblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import spectralmc.elepets.common.blocks.eggtiles.FireEggTile;

import javax.annotation.Nullable;

public class FireEggBlock extends BaseEggBlock {

    public FireEggBlock() {
        super(Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0), 500, 600, "fire");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FireEggTile();
    }
}
