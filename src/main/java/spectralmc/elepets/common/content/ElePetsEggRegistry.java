package spectralmc.elepets.common.content;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import spectralmc.elepets.common.blocks.eggblocks.*;
import spectralmc.elepets.common.blocks.eggtiles.*;

import static spectralmc.elepets.ElePets.ITEM_GROUP;
import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsEggRegistry {
    public static final DeferredRegister<Item> EGG_ITEM = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> EGG_BLOCK = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> EGG_TILE = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    //Egg Blocks

    public static final RegistryObject<Block> NORMAL_EGG = EGG_BLOCK.register("normal_egg", NormalEggBlock::new);
    public static final RegistryObject<Block> FIRE_EGG = EGG_BLOCK.register("fire_egg", FireEggBlock::new);
    public static final RegistryObject<Block> WATER_EGG = EGG_BLOCK.register("water_egg", WaterEggBlock::new);
    public static final RegistryObject<Block> EARTH_EGG = EGG_BLOCK.register("earth_egg", EarthEggBlock::new);
    public static final RegistryObject<Block> ICE_EGG = EGG_BLOCK.register("ice_egg", IceEggBlock::new);
    public static final RegistryObject<Block> THUNDER_EGG = EGG_BLOCK.register("thunder_egg", ThunderEggBlock::new);
    public static final RegistryObject<Block> MECHA_EGG = EGG_BLOCK.register("mecha_egg", MechaEggBlock::new);
    public static final RegistryObject<Block> SPIRIT_EGG = EGG_BLOCK.register("spirit_egg", SpiritEggBlock::new);
    public static final RegistryObject<Block> LIGHT_EGG = EGG_BLOCK.register("light_egg", LightEggBlock::new);
    public static final RegistryObject<Block> DARK_EGG = EGG_BLOCK.register("dark_egg", DarkEggBlock::new);
    public static final RegistryObject<Block> FAE_EGG = EGG_BLOCK.register("fae_egg", FaeEggBlock::new);

    //Egg Items
    public static final RegistryObject<Item> NORMAL_EGG_ITEM = EGG_ITEM.register("normal_egg", () -> new BlockItem(NORMAL_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> FIRE_EGG_ITEM = EGG_ITEM.register("fire_egg", () -> new BlockItem(FIRE_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> WATER_EGG_ITEM = EGG_ITEM.register("water_egg", () -> new BlockItem(WATER_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> EARTH_EGG_ITEM = EGG_ITEM.register("earth_egg", () -> new BlockItem(EARTH_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> ICE_EGG_ITEM = EGG_ITEM.register("ice_egg", () -> new BlockItem(ICE_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> THUNDER_EGG_ITEM = EGG_ITEM.register("thunder_egg", () -> new BlockItem(THUNDER_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> MECHA_EGG_ITEM = EGG_ITEM.register("mecha_egg", () -> new BlockItem(MECHA_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> SPIRIT_EGG_ITEM = EGG_ITEM.register("spirit_egg", () -> new BlockItem(SPIRIT_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> LIGHT_EGG_ITEM = EGG_ITEM.register("light_egg", () -> new BlockItem(LIGHT_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> DARK_EGG_ITEM = EGG_ITEM.register("dark_egg", () -> new BlockItem(DARK_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));
    public static final RegistryObject<Item> FAE_EGG_ITEM = EGG_ITEM.register("fae_egg", () -> new BlockItem(FAE_EGG.get(), new Item.Properties().maxStackSize(1).group(ITEM_GROUP)));

    // Egg Tiles

    public static final RegistryObject<TileEntityType<?>> NORMAL_EGG_TILE = EGG_TILE.register("normal_egg",
            () -> TileEntityType.Builder.create(NormalEggTile::new, NORMAL_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> FIRE_EGG_TILE = EGG_TILE.register("fire_egg",
            () -> TileEntityType.Builder.create(FireEggTile::new, FIRE_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> WATER_EGG_TILE = EGG_TILE.register("water_egg",
            () -> TileEntityType.Builder.create(WaterEggTile::new, WATER_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> EARTH_EGG_TILE = EGG_TILE.register("earth_egg",
            () -> TileEntityType.Builder.create(EarthEggTile::new, EARTH_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> ICE_EGG_TILE = EGG_TILE.register("ice_egg",
            () -> TileEntityType.Builder.create(IceEggTile::new, ICE_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> THUNDER_EGG_TILE = EGG_TILE.register("thunder_egg",
            () -> TileEntityType.Builder.create(ThunderEggTile::new, THUNDER_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> MECHA_EGG_TILE = EGG_TILE.register("mecha_egg",
            () -> TileEntityType.Builder.create(MechaEggTile::new, MECHA_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> SPIRIT_EGG_TILE = EGG_TILE.register("spirit_egg",
            () -> TileEntityType.Builder.create(SpiritEggTile::new, SPIRIT_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> LIGHT_EGG_TILE = EGG_TILE.register("light_egg",
            () -> TileEntityType.Builder.create(LightEggTile::new, LIGHT_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> DARK_EGG_TILE = EGG_TILE.register("dark_egg",
            () -> TileEntityType.Builder.create(DarkEggTile::new, DARK_EGG.get()).build(null));

    public static final RegistryObject<TileEntityType<?>> FAE_EGG_TILE = EGG_TILE.register("fae_egg",

            () -> TileEntityType.Builder.create(FaeEggTile::new, FAE_EGG.get()).build(null));



    public static void register(IEventBus eventBus) {
        EGG_BLOCK.register(eventBus);
        EGG_ITEM.register(eventBus);
        EGG_TILE.register(eventBus);
    }
}
