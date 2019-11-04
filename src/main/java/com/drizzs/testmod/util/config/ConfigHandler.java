package com.drizzs.testmod.util.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Objects;

public class ConfigHandler {

    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

        public static final CommonConfig COMMON;
        public static final ForgeConfigSpec COMMON_SPEC;


    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }




    public static class ClientConfig {

        public final ForgeConfigSpec.BooleanValue fogControl;

        ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("FogControl");
            fogControl = builder
                    .comment("Controls if fog is on or not.")
                    .define("fogControl", false);
            builder.pop();

        }
    }


    public static class CommonConfig {

        public final ForgeConfigSpec.IntValue minTestOreSpawnHeight;
        public final ForgeConfigSpec.IntValue maxTestOreSpawnHeight;
        public final ForgeConfigSpec.IntValue chanceToSpawnTestOre;
        public final ForgeConfigSpec.IntValue maxTestOreVeinSize;

        public final ForgeConfigSpec.IntValue testFeatureWeight;

        public final ForgeConfigSpec.BooleanValue spawnBiomes;
        public final ForgeConfigSpec.BooleanValue registerConfigBiome;
        public final ForgeConfigSpec.IntValue configBiomeWeight;


        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("TestOreGen");
            minTestOreSpawnHeight = builder
                    .comment("The minimum height to spawn Test Ore at.")
                    .defineInRange("minTestOreSpawnHeight", 1, 0, 256);
            maxTestOreSpawnHeight = builder
                    .comment("The maximum height to spawn Test Ore at.")
                    .defineInRange("maxTestOreSpawnHeight", 73, 0, 256);
            chanceToSpawnTestOre = builder
                    .comment("Controls the chance to spawn Test Ore in world generation.")
                    .defineInRange("chanceToSpawnTestOre", 20, 1, 100);
            maxTestOreVeinSize = builder
                    .comment("The maximum number of ores per vein. Will Spawn half of number indicated. 10 = 5 ore.")
                    .defineInRange("maxTestOreVeinSize", 10, 1, 100);
            builder.pop();

            builder.push("FeatureGen");
            testFeatureWeight = builder
                    .comment("Spawn Weight of Test Feature")
                    .defineInRange("testFeatureWeight", 10, 1, 500);
            builder.pop();

            builder.push("BiomeJunk");
            registerConfigBiome = builder
                    .comment("Set True to spawn Custom Biomes in the default provider")
                    .define("registerConfigBiome", true);
            spawnBiomes = builder
                    .comment("Set True to spawn Custom Biomes in the default provider")
                    .define("spawnBiomes", true);
            configBiomeWeight = builder
                    .comment("Spawn Weight of Test Feature")
                    .defineInRange("configBiomeWeight", 10, 1, 500);
            builder.pop();

        }

    }


}
