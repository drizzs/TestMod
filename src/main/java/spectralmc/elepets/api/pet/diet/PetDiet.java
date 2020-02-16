package spectralmc.elepets.api.pet.diet;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.IPetVariables;
import spectralmc.elepets.api.serializer.VariableType;

import java.util.ArrayList;
import java.util.List;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.DIET;
import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.DIET_SERIALIZER;

public class PetDiet implements IDiet {

    private final ResourceLocation id;
    private final String name;
    private final Tag<Item> dietList;

    public PetDiet(ResourceLocation id, String name, Tag<Item> dietList) {
        this.id = id;
        this.name = name;
        this.dietList = dietList;
    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean matchDiet(String name){
        return matchPetVariable(name);
    }

    @Override
    public boolean matchPetVariable(String name) {
        return name.equals(this.name);
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return DIET_SERIALIZER.get();
    }

    @Override
    public VariableType<?> getType() {
        return DIET.get();
    }

    @Override
    public boolean isDietItem(ItemStack stack) {
        return Ingredient.fromTag(dietList).test(stack);
    }

    @Override
    public Tag<Item> getDietTag() {
        return dietList;
    }

}
