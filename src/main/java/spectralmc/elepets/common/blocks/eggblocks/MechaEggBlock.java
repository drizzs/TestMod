package spectralmc.elepets.common.blocks.eggblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import spectralmc.elepets.common.blocks.eggtiles.MechaEggTile;

import javax.annotation.Nullable;

public class MechaEggBlock extends BaseEggBlock {

    public MechaEggBlock() {
        super(Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0), 500, 600, "mecha");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MechaEggTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        TileEntity egg = world.getTileEntity(pos);
        if (egg instanceof MechaEggTile) {
            ((MechaEggTile) egg).setChallengeGoal();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
