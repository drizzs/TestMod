package com.drizzs.testmod.blocks.featureblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestFeature extends BushBlock {

    public TestFeature(Properties properties) {
        super(properties);
    }



    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        entity.attackEntityFrom(DamageSource.SWEET_BERRY_BUSH, 1.0F);
    }



}
