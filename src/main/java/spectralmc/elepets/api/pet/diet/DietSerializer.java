package spectralmc.elepets.api.pet.diet;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;

import javax.annotation.Nullable;

public class DietSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializable<?>>  implements IPetSerializable<PetDiet> {

    @Override
    public PetDiet read(ResourceLocation dietID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        String tag = JSONUtils.getString(json, "dietTag");
        Tag<Item> dietTag = new ItemTags.Wrapper(new ResourceLocation(tag));
        return new PetDiet(dietID, name, dietTag);
    }

    @Nullable
    @Override
    public PetDiet read(ResourceLocation dietID, PacketBuffer buffer) {
        String name = buffer.readString();
        String tag = buffer.readString();
        Tag<Item> dietTag = new ItemTags.Wrapper(new ResourceLocation(tag));
        return new PetDiet(dietID, name, dietTag);
    }

    @Override
    public void write(PacketBuffer buffer, PetDiet diet) {
        buffer.writeString(diet.getName());
        buffer.writeString(diet.getDietTag().getId().getPath());
    }
}
