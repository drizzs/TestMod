package com.drizzs.testmod.biome.feature;

import com.drizzs.testmod.TestMod;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID,bus= Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder("testmod")
public class FeatureRegistry
{

    public static Feature<NoFeatureConfig> testgrassfeature;


    @SubscribeEvent
    public static void registerFeature(RegistryEvent.Register<Feature<?>> event)
    {
        testgrassfeature = registerFeature("testgrassfeature", new TestGrassFeature(NoFeatureConfig::deserialize));

    }

    private static<C extends IFeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature)
    {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, name, feature));

    }



}
