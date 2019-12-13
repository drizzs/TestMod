package com.drizzs.testmod.content;


import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiFunction;

import static com.drizzs.testmod.TestMod.MOD_ID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Dimensions {
    public static final ResourceLocation Test_Dimension = new ResourceLocation(MOD_ID + ":test_dimension");
    public static ModDimension test_dimension = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return OverworldDimension::new;
        }
    }.setRegistryName(Test_Dimension);

    public static ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> generatorType = new ChunkGeneratorType<>(OverworldChunkGenerator::new, false, OverworldGenSettings::new);

    public static BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeProviderType = new BiomeProviderType<>(OverworldBiomeProvider::new, OverworldBiomeProviderSettings::new);

    @SubscribeEvent
    public static void onDimensionModRegistry(final RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().register(test_dimension);
        DimensionManager.registerDimension(Test_Dimension, test_dimension, null, true);
    }

}