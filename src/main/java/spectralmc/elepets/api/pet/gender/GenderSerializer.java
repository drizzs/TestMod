package spectralmc.elepets.api.pet.gender;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;

import javax.annotation.Nullable;

public class GenderSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializable<?>>  implements IPetSerializable<PetGender> {

    @Override
    public PetGender read(ResourceLocation genderID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        boolean canBreed = JSONUtils.getBoolean(json, "canBreed");
        boolean canBreedUniSex = JSONUtils.getBoolean(json, "canBreedUniSex");
        return new PetGender(genderID,name,canBreed,canBreedUniSex);
    }

    @Nullable
    @Override
    public PetGender read(ResourceLocation genderID, PacketBuffer buffer) {
        String name = buffer.readString();
        boolean canBreed = buffer.readBoolean();
        boolean canBreedUniSex = buffer.readBoolean();
        return new PetGender(genderID,name,canBreed,canBreedUniSex);
    }

    @Override
    public void write(PacketBuffer buffer, PetGender gender) {
        buffer.writeString(gender.getName());
        buffer.writeBoolean(gender.canBreed());
        buffer.writeBoolean(gender.canBreedUniSex());
    }

}
