package com.drizzs.testmod.biome.feature;

import com.drizzs.testmod.blocks.BlockRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TestGrassFeature extends Feature<NoFeatureConfig>
{

    public TestGrassFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }


    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState state = BlockRegistry.testfeature.getDefaultState();

        for (BlockState state1 = world.getBlockState(pos); (state1.isAir(world, pos) || state1.isIn(BlockTags.LEAVES)) && pos.getY() > 0; state1 = world.getBlockState(pos))
        {
            pos = pos.down();
        }

        int i = 0;

        for (int j = 0; j < 128; ++j)
        {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(blockpos) && state.isValidPosition(world, blockpos))
            {
                world.setBlockState(blockpos, state, 2);
                ++i;
            }
        }

        return i > 0;
    }

}
