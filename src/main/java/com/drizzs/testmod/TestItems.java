package com.drizzs.testmod;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TestItems {
    public static Item test_icon;
    public static Item amalgam;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        amalgam = registerItem(new Item(new Item.Properties().group(TestItemGroup.instance)), "amalgam");


        test_icon = registerItem(new Item(new Item.Properties()), "test_icon");

    }

    public static Item registerItem(Item item, String name)
    {
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

}
