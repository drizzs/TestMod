package com.drizzs.testmod.items;

import com.drizzs.testmod.blocks.BaseRegistryAdapter;
import com.drizzs.testmod.util.config.ConfigHandler;
import com.drizzs.testmod.world.IslandPiece;
import com.drizzs.testmod.world.IslandStructure;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import static com.drizzs.testmod.TestMod.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MOD_ID)
public class ItemRegistry {

    @ObjectHolder("testmod:test_icon")
    public static Item test_icon;

    public static Structure<NoFeatureConfig> ISLANDFEATURE;

    public static IStructurePieceType ISLANDPIECE;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("test_icon"));
    }

    @SubscribeEvent
    public static void registerFeature(RegistryEvent.Register<Feature<?>> event) {
        BaseRegistryAdapter<Feature<?>> registry = new BaseRegistryAdapter<>(event.getRegistry());
        ISLANDPIECE = Registry.register(Registry.STRUCTURE_PIECE, registry.getResource("piece"), IslandPiece::new);
        event.getRegistry().register(new IslandStructure(NoFeatureConfig::deserialize).setRegistryName("island"));
    }

    public static void applyFeatures() {
        ConfiguredFeature<?> ISLAND_FEATURE = Biome.createDecoratedFeature(ISLANDFEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG);
        FlatGenerationSettings.FEATURE_STAGES.put(ISLAND_FEATURE, GenerationStage.Decoration.SURFACE_STRUCTURES);
        FlatGenerationSettings.STRUCTURES.put("testmod:island", new ConfiguredFeature[] { ISLAND_FEATURE });
        FlatGenerationSettings.FEATURE_CONFIGS.put(ISLAND_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG);

        if (ConfigHandler.COMMON.OVERWORLDISLANDFEATURE.get()) {
            for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
                    addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, ISLANDFEATURE);
                }

            }
        }
    }

    private static void addStructure(Biome biome, GenerationStage.Decoration stage, Structure<NoFeatureConfig> structure) {
        biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
        biome.addFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
    }


}
