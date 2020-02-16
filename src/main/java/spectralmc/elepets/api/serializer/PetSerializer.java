package spectralmc.elepets.api.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.StatBase;
import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.type.PetType;

import javax.annotation.Nullable;

public class PetSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<IPetSerializer<?>> implements IPetSerializer<StatBase> {

    private final PetVariableManager manager = new PetVariableManager();

    @Override
    public StatBase read(ResourceLocation petID, JsonObject json) {
        String name = JSONUtils.getString(json, "name");
        String type = JSONUtils.getString(json, "petType");
        PetType petType = manager.getTypeFromString(type);
        String diet = JSONUtils.getString(json, "petDiet");
        PetDiet petDiet = manager.getDietFromString(diet);
        int baseAttack = JSONUtils.getInt(json, "baseAttack");
        int baseDefense = JSONUtils.getInt(json, "baseDefense");
        int baseSpeed = JSONUtils.getInt(json, "baseSpeed");
        int baseHealth = JSONUtils.getInt(json, "baseHealth");
        int baseMeleeResist = JSONUtils.getInt(json, "baseMeleeResist");
        int baseRangedResist = JSONUtils.getInt(json, "baseRangedResist");
        int rarity = JSONUtils.getInt(json, "rarity");
        boolean canBreed = JSONUtils.getBoolean(json, "canBreed");
        return new StatBase(petID, name, petType, petDiet,baseAttack,baseDefense,baseSpeed,baseHealth,baseMeleeResist,baseRangedResist,rarity,canBreed);
    }

    @Nullable
    @Override
    public StatBase read(ResourceLocation petID, PacketBuffer buffer) {
        String name = buffer.readString();
        String type = buffer.readString();
        PetType petType = manager.getTypeFromString(type);
        String diet = buffer.readString();
        PetDiet petDiet = manager.getDietFromString(diet);
        int baseAttack = buffer.readInt();
        int baseDefense = buffer.readInt();
        int baseSpeed = buffer.readInt();
        int baseHealth = buffer.readInt();
        int baseMeleeResist = buffer.readInt();
        int baseRangedResist = buffer.readInt();
        int rarity = buffer.readInt();
        boolean canBreed = buffer.readBoolean();
        return new StatBase(petID, name, petType, petDiet,baseAttack,baseDefense,baseSpeed,baseHealth,baseMeleeResist,baseRangedResist,rarity,canBreed);
    }

    @Override
    public void write(PacketBuffer buffer, StatBase pet) {
        buffer.writeString(pet.getName());
        buffer.writeString(pet.getPetType().getName());
        buffer.writeString(pet.getDiet().getName());
        buffer.writeInt(pet.getBaseAttack());
        buffer.writeInt(pet.getBaseDefense());
        buffer.writeInt(pet.getBaseSpeed());
        buffer.writeInt(pet.getBaseHealth());
        buffer.writeInt(pet.getBaseMeleeResist());
        buffer.writeInt(pet.getBaseRangedResist());
        buffer.writeInt(pet.getRarity());
        buffer.writeBoolean(pet.canBreed());
    }
}
