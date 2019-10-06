package com.drizzs.testmod.blocks;

import com.drizzs.testmod.TestMod;
import com.drizzs.testmod.blocks.featureblocks.TestFeature;
import com.drizzs.testmod.util.TestItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry
{

    public static Block basic_block;
    public static Block testore;
    public static Block testfeature;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {

        basic_block = registerBlock(new Block((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "basic_block");

        testore = registerBlock(new Block((Block.Properties.create(Material.ROCK).hardnessAndResistance(0.6F).sound(SoundType.STONE))), "testore");


        testfeature = registerBlock(new TestFeature((Block.Properties.create(Material.PLANTS).noDrops().doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT))), "test_feature");


    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(TestItemGroup.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlock(Block block, BlockItem itemBlock, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null)
        {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }


}
