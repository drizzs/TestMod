package com.drizzs.testmod.items;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.drizzs.testmod.TestGroup.TEST_GROUP;
import static com.drizzs.testmod.TestMod.MOD_ID;

public class Items {

    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> TESTITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().group(TEST_GROUP)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
