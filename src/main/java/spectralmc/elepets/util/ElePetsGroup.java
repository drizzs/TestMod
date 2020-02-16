package spectralmc.elepets.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.DARK_EGG_ITEM;

public class ElePetsGroup extends ItemGroup {

    public ElePetsGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(DARK_EGG_ITEM.get());
    }


}