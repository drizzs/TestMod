package spectralmc.elepets.api.pet.diet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;
import spectralmc.elepets.api.serializer.IPetVariables;

public interface IDiet extends IPetVariables {

    boolean isDietItem(ItemStack stack);

    Tag<Item> getDietTag();

}
