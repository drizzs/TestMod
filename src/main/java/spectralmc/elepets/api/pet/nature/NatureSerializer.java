package spectralmc.elepets.api.pet.nature;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;

import javax.annotation.Nullable;

public class NatureSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializable<?>>  implements IPetSerializable<PetNature> {

    @Override
    public PetNature read(ResourceLocation natureID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        int attackModifier = JSONUtils.getInt(json,"attackModifier");
        int defenseModifier = JSONUtils.getInt(json,"defenseModifier");
        int speedModifier = JSONUtils.getInt(json,"speedModifier");
        int healthModifier = JSONUtils.getInt(json,"healthModifier");
        int meleeResistanceModifier = JSONUtils.getInt(json,"meleeResistanceModifier");
        int rangedResistanceModifier = JSONUtils.getInt(json,"rangedResistanceModifier");
        return new PetNature(natureID, name, attackModifier, defenseModifier, speedModifier, healthModifier, meleeResistanceModifier, rangedResistanceModifier);
    }

    @Nullable
    @Override
    public PetNature read(ResourceLocation natureID, PacketBuffer buffer) {
        String name = buffer.readString();
        int attackModifier = buffer.readInt();
        int defenseModifier = buffer.readInt();
        int speedModifier = buffer.readInt();
        int healthModifier = buffer.readInt();
        int meleeResistanceModifier = buffer.readInt();
        int rangedResistanceModifier = buffer.readInt();
        return new PetNature(natureID, name, attackModifier, defenseModifier, speedModifier, healthModifier, meleeResistanceModifier, rangedResistanceModifier);
    }

    @Override
    public void write(PacketBuffer buffer, PetNature nature) {
        buffer.writeString(nature.getName());
        buffer.writeInt(nature.getAttackModifier());
        buffer.writeInt(nature.getDefenceModifier());
        buffer.writeInt(nature.getSpeedModifier());
        buffer.writeInt(nature.getHealthModifier());
        buffer.writeInt(nature.getMeleeResistanceModifier());
        buffer.writeInt(nature.getRangedResistanceModifier());
    }
}
