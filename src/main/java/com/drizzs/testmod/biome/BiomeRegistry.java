package com.drizzs.testmod.biome;

import com.drizzs.testmod.TestMod;
import com.drizzs.testmod.biome.overworld.SurfaceTestBiome;
import com.drizzs.testmod.biome.overworld.TestBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BiomeRegistry
{

    public static Biome testbiome;
    public static Biome surfacetestbiome;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event)
    {
        testbiome = registerBiome(new TestBiome(), BiomeManager.BiomeType.WARM , "testbiome", 50, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SPOOKY);

       //SurfaceBuilder Test Biome
        surfacetestbiome = registerBiome(new SurfaceTestBiome(), BiomeManager.BiomeType.WARM , "surfacetestbiome", 50, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SPOOKY);

    }

    public static Biome registerBiome (Biome biome, BiomeManager.BiomeType biometype, String name, int weight, BiomeDictionary.Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addBiome(biometype, new BiomeManager.BiomeEntry(biome, weight));
        BiomeDictionary.addTypes(biome, types);
        System.out.println(name + "registered");
        return biome;

    }

}
