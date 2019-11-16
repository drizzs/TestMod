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



        ClientConfig(ForgeConfigSpec.Builder builder) {


        }
    }


    public static class CommonConfig {

        public final ForgeConfigSpec.BooleanValue OVERWORLDISLANDFEATURE;

        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.push("TestOreGen");
            OVERWORLDISLANDFEATURE = builder
                    .comment("If True, This will spawn this colour grass in the world. Set Grass Feature to false to turn all grass off! DOES NOT WORK")
                    .define("OverworldIslandFeature", true);
           builder.pop();


        }

    }


}
