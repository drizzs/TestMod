package spectralmc.elepets.api.pet.maturity;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;

import javax.annotation.Nullable;

public class MaturitySerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializable<?>>  implements IPetSerializable<PetMaturity> {

    @Override
    public PetMaturity read(ResourceLocation maturityID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        int ageToMature = JSONUtils.getInt(json, "ageToMature");
        int statModifier = JSONUtils.getInt(json, "growthOrder");
        return new PetMaturity(maturityID, name, statModifier, ageToMature);
    }

    @Nullable
    @Override
    public PetMaturity read(ResourceLocation maturityID, PacketBuffer buffer) {
        String name = buffer.readString();
        int growthOrder = buffer.readInt();
        int ageToMature = buffer.readInt();
        return new PetMaturity(maturityID, name, growthOrder, ageToMature);
    }

    @Override
    public void write(PacketBuffer buffer, PetMaturity maturity) {
        buffer.writeString(maturity.getName());
        buffer.writeInt(maturity.getGrowthOrder());
        buffer.writeInt(maturity.getAgeToMature());
    }

}
