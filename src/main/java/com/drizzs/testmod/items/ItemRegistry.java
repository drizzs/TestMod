package com.drizzs.testmod.items;

import com.drizzs.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry
{

    public static Item test_icon;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {

        test_icon = registerItem(new Item(new Item.Properties().group(ItemGroup.MISC)),"test_item");

    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }



}
