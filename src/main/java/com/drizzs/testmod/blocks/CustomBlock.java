package com.drizzs.testmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class CustomBlock extends Block {

    public static final BooleanProperty WET = BooleanProperty.create("wet");

    public CustomBlock(Properties properties) {
        super(properties);

        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(WET, false));
    }

    @Override
    @Deprecated
    public BlockState updatePostPlacement(BlockState state1, Direction direction, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2) {
        if (direction != Direction.UP) {
            return super.updatePostPlacement(state1, direction, state2, world, pos1, pos2);
        } else {
            Block block = state2.getBlock();
            return (BlockState)state1.with(WET, world.getWorldInfo().isRaining());
        }
    }

    @Override
    public void tick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.isRemote) {
            if (!world.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
                if (world.getWorldInfo().isRaining()) {
                    BlockState blockstate = this.getDefaultState();
                            world.setBlockState(pos, blockstate.with(WET, world.getWorldInfo().isRaining()));
                }

        }
        super.tick(state, world, pos, random);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> state) {
        state.add(new IProperty[]{WET});
    }


}
