package com.drizzs.testmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;

public class TestItemGroup extends ItemGroup
{

    public static final TestItemGroup instance = new TestItemGroup(ItemGroup.GROUPS.length, "testgroup");

    private TestItemGroup(int index, String label)
    {
        super(index, label);
    }

    @Nonnull
    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(TestItems.test_icon);
    }


}
