package com.drizzs.testmod.blocks;

import com.drizzs.testmod.TestMod;
import com.drizzs.testmod.util.TestItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


// Event Bus Subscriber, This activates your SubscribeEvents!

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry
{
    //Callable Object for your Block Connected to the registry name through @ObjectHolder
    @ObjectHolder("testmod:basic_block")
    public static Block basic_block;

    @ObjectHolder("testmod:customblock")
    public static Block customblock;


    //SubscribeEvents can only have 1 Parameter, Mod.EventBusSubscriber is Required to make this work!

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        //Register your Blocks here!
        event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("basic_block"));

        event.getRegistry().register(new CustomBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE)).setRegistryName("customblock"));

    }

    // Required to make an Item for your Block. Not all Blocks need an Item!
    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event)
    {
        //Register your BlockItems here!
        event.getRegistry().register(new BlockItem(basic_block, new Item.Properties().group(TestItemGroup.instance)).setRegistryName("basic_block"));

        event.getRegistry().register(new BlockItem(customblock, new Item.Properties().group(TestItemGroup.instance)).setRegistryName("customblock"));
    }

}
