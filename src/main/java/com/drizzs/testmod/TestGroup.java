package com.drizzs.testmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.drizzs.testmod.content.Items.TESTITEM;

public class TestGroup extends ItemGroup {

    public static final TestGroup TEST_GROUP = new TestGroup(ItemGroup.GROUPS.length, "test_group");

    private TestGroup(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(TESTITEM.get());
    }

}
