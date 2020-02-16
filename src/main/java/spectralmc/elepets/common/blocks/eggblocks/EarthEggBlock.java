package spectralmc.elepets.common.blocks.eggblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import spectralmc.elepets.common.blocks.eggtiles.EarthEggTile;

import javax.annotation.Nullable;

public class EarthEggBlock extends BaseEggBlock {

    public EarthEggBlock() {
        super(Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0), 500, 600, "earth");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EarthEggTile();
    }
}
