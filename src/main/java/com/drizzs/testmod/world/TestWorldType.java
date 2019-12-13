package com.drizzs.testmod.world;

import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class TestWorldType extends WorldType {

    public TestWorldType() {
        super("test_world");
    }

    public ChunkGenerator<?> createChunkGenerator(World world) {
        ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkgeneratortype4 = ChunkGeneratorType.SURFACE;
        OverworldGenSettings overworldgensettings1 = chunkgeneratortype4.createSettings();
        overworldgensettings1.setDefaultBlock(Blocks.STONE.getDefaultState());
        overworldgensettings1.setDefaultFluid(Blocks.WATER.getDefaultState());
        BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> bpt = BiomeProviderType.VANILLA_LAYERED;
        OverworldBiomeProviderSettings overworldbiomeprovidersettings1 = (bpt.createSettings()).setGeneratorSettings(new OverworldGenSettings()).setWorldInfo(world.getWorldInfo());
        BiomeProvider provider = bpt.create(overworldbiomeprovidersettings1);
        return chunkgeneratortype4.create(world, provider, overworldgensettings1);
    }

    public <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> getBiomeLayer(IAreaFactory<T> parentLayer, OverworldGenSettings chunkSettings, LongFunction<C> contextFactory) {
        parentLayer = (new BiomeLayer(this.getWorldType(), chunkSettings)).apply((IExtendedNoiseRandom)contextFactory.apply(200L), parentLayer);
        parentLayer = AddBambooForestLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1001L), parentLayer);
        parentLayer = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, parentLayer, 2, contextFactory);
        parentLayer = EdgeBiomeLayer.INSTANCE.apply((IExtendedNoiseRandom)contextFactory.apply(1000L), parentLayer);
        return parentLayer;
    }
}