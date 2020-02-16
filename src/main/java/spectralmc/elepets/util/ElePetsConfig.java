package spectralmc.elepets.util;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ElePetsConfig {

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {

        public final ForgeConfigSpec.IntValue RESISTANCEMODIFIER;
        public final ForgeConfigSpec.IntValue SUPERWEAKNESSMODIFIER;
        public final ForgeConfigSpec.IntValue WEAKNESSMODIFIER;
        public final ForgeConfigSpec.IntValue MAXLEVEL;

        public final ForgeConfigSpec.BooleanValue WEATHERCONTROL;
        public final ForgeConfigSpec.BooleanValue PETBIOMESPAWN;

        public Common(ForgeConfigSpec.Builder builder) {
            RESISTANCEMODIFIER = builder
                    .comment("Sets the damage modifier for Resistances (number is a percentage), This will reduce damage by X % - Default 50% Reduced Damage -")
                    .defineInRange("ResistanceModifier", 50, 1, 100);
            SUPERWEAKNESSMODIFIER = builder
                    .comment("Sets the damage modifier for Super Weaknesses (number is a percentage), This will increase damage by X % - Default 50% extra Damage -")
                    .defineInRange("WeaknessModifier", 50, 1, 100);
            WEAKNESSMODIFIER = builder
                    .comment("Sets the damage modifier for Weaknesses (number is a percentage), This will increase damage by X % - Default 25% extra Damage -")
                    .defineInRange("SuperWeaknessModifier", 25, 1, 100);
            MAXLEVEL = builder
                    .comment("Sets the max level of the pets in the game - Default 100 -")
                    .defineInRange("SuperWeaknessModifier", 100, 1, 300);
            WEATHERCONTROL = builder
                    .comment("This Config Controls the mods ability to Control the Weather, False will set all weather to default and not allow the mod to change it!")
                    .define("WeatherControl", true);
            PETBIOMESPAWN = builder
                    .comment("Allows pets to spawn in their respective Biome types EI Ice in Snow Biomes, Water in Oceans. - Default : True -")
                    .define("WeatherControl", true);
        }
    }
}
