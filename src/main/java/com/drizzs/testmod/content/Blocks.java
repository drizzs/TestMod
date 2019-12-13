package com.drizzs.testmod.content;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

import static com.drizzs.testmod.TestGroup.TEST_GROUP;
import static com.drizzs.testmod.TestMod.MOD_ID;

public class Blocks {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);
    public static Map<RegistryObject<Block>, String> blocklist = Maps.newHashMap();

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(Block.Properties.create(Material.ROCK)
            .harvestLevel(1)
            .sound(SoundType.STONE)
            .hardnessAndResistance(5.0F, 6.0F)
    ));

    public static void register(IEventBus bus) {
        blocklist.put(TEST_BLOCK,"test_block");

        for(RegistryObject<Block> block: blocklist.keySet()){
            ITEMS.register(blocklist.get(block),() -> new BlockItem(block.get(), new Item.Properties().group(TEST_GROUP)));
        }

        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

}
