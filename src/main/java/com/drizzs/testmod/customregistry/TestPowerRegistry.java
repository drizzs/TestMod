package com.drizzs.testmod.customregistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class TestPowerRegistry {
    static {
        init();
    }

    public static IForgeRegistry<TestPowerType> TESTPOWER = RegistryManager.ACTIVE.getRegistry(TestPowerType.class);

    private static void init() {
        new RegistryBuilder<TestPowerType>()
                .setName(new ResourceLocation("testmod", "testpower"))
                .setType(TestPowerType.class)
                .setDefaultKey(new ResourceLocation("testmod", "empty"))
                .create();
    }
}