package com.drizzs.testmod.world.surfacebuilders;

import com.drizzs.testmod.blocks.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class Surfaces {

    public static SurfaceBuilderConfig TESTSURFACE;

    public Surfaces() {
    }

    static {
        TESTSURFACE = new SurfaceBuilderConfig(BlockRegistry.basic_block.getDefaultState(), Blocks.LAVA.getDefaultState(), Blocks.FURNACE.getDefaultState());

    }
}