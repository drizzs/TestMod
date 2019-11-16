package com.drizzs.testmod.world;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum IslandVariant implements IStringSerializable {
    BLACK(0, Blocks.GRASS_BLOCK.getDefaultState(), Blocks.WATER.getDefaultState(),Blocks.GRASS.getDefaultState());

    private final int index;
    private final BlockState grassBlock;
    private final BlockState lakeFluid;
    private final BlockState tallGrass;

    IslandVariant(int index, BlockState grassBlock, BlockState lakeFluid, BlockState tallGrass) {
        this.index = index;
        this.grassBlock = grassBlock;
        this.tallGrass = tallGrass;
        this.lakeFluid = lakeFluid;
    }

    public BlockState getGrassBlock() {
        return this.grassBlock;
    }

    public BlockState getLakeFluid() {
        return this.lakeFluid;
    }

    public BlockState getTallGrass() {
        return this.tallGrass;
    }

    @Override
    public String getName() {
        return this.toString();
    }

    public int getIndex() {
        return this.index;
    }

    public static IslandVariant getVariantFromIndex(int index) {
        if (index == 0) {
            return BLACK;
        }
        throw new IllegalStateException("Unexpected variant: " + index);
    }
}