package spectralmc.elepets.api.pet.type;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;

import javax.annotation.Nullable;

public class TypeSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializable<?>> implements IPetSerializable<PetType> {

    @Override
    public PetType read(ResourceLocation typeID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        int attackModifier = JSONUtils.getInt(json, "attackModifier");
        int defenseModifier = JSONUtils.getInt(json, "defenseModifier");
        int speedModifier = JSONUtils.getInt(json, "speedModifier");
        int healthModifier = JSONUtils.getInt(json, "healthModifier");
        int meleeResistance = JSONUtils.getInt(json, "meleeResistance");
        int rangedResistance = JSONUtils.getInt(json, "rangedResistance");
        int typeColor = JSONUtils.getInt(json, "typeColor");
        int percentBabyIsMale = JSONUtils.getInt(json, "percentBabyIsMale");
        boolean canBeNeutral = JSONUtils.getBoolean(json, "canBeNeutral");

        return new PetType(typeID, name, attackModifier, defenseModifier, speedModifier, healthModifier, meleeResistance, rangedResistance, typeColor, canBeNeutral, percentBabyIsMale);
    }

    @Nullable
    @Override
    public PetType read(ResourceLocation typeID, PacketBuffer buffer) {
        String name = buffer.readString();
        int attackModifier = buffer.readInt();
        int defenseModifier = buffer.readInt();
        int speedModifier = buffer.readInt();
        int healthModifier = buffer.readInt();
        int meleeResistance = buffer.readInt();
        int rangedResistance = buffer.readInt();
        int typeColor = buffer.readInt();
        int percentBabyIsMale = buffer.readInt();
        boolean canBeNeutral = buffer.readBoolean();

        return new PetType(typeID, name, attackModifier, defenseModifier, speedModifier, healthModifier, meleeResistance, rangedResistance, typeColor, canBeNeutral, percentBabyIsMale);
    }

    @Override
    public void write(PacketBuffer buffer, PetType type) {
        buffer.writeString(type.getName());
        buffer.writeInt(type.getAttackModifier());
        buffer.writeInt(type.getDefenceModifier());
        buffer.writeInt(type.getSpeedModifier());
        buffer.writeInt(type.getHealthModifier());
        buffer.writeInt(type.getMeleeResistanceModifier());
        buffer.writeInt(type.getRangeResistanceModifier());
        buffer.writeInt(type.getTypeColor());
        buffer.writeInt(type.getMalePercent());
        buffer.writeBoolean(type.canBeNeutral());
    }

}
