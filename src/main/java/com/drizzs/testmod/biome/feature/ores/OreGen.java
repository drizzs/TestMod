package com.drizzs.testmod.biome.feature.ores;


import com.drizzs.testmod.TestMod;
import com.drizzs.testmod.blocks.BlockRegistry;
import com.drizzs.testmod.util.config.ConfigHandler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class OreGen
{


    private static Biome biome;

    @SubscribeEvent
    public static void onFeatureRegistryEvent(RegistryEvent.Register<Feature<?>> event) {
        ForgeRegistries.BIOMES.forEach(biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockRegistry.testore.getDefaultState(),
                        ConfigHandler.COMMON.maxTestOreVeinSize.get()), Placement.COUNT_RANGE, new CountRangeConfig(ConfigHandler.COMMON.chanceToSpawnTestOre.get(),
                        ConfigHandler.COMMON.minTestOreSpawnHeight.get(), 0, ConfigHandler.COMMON.maxTestOreSpawnHeight.get()))));

    }




}
