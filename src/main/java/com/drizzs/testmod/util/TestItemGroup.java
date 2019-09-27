package com.drizzs.testmod.util;

import com.drizzs.testmod.items.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TestItemGroup extends ItemGroup {

    public static final TestItemGroup instance = new TestItemGroup(ItemGroup.GROUPS.length, "testmod");

    private TestItemGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemRegistry.test_icon);
    }


}