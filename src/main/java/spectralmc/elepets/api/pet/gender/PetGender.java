package spectralmc.elepets.api.pet.gender;

import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.IPetVariables;
import spectralmc.elepets.api.serializer.VariableType;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.GENDER;
import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.GENDER_SERIALIZER;

public class PetGender implements IGender {

    private final ResourceLocation id;
    private final String name;
    private final boolean canBreed;
    private final boolean canBreedUniSex;

    public PetGender(ResourceLocation id, String name, boolean canBreed, boolean canBreedUniSex) {
        this.id = id;
        this.name = name;
        this.canBreed = canBreed;
        this.canBreedUniSex = canBreedUniSex;
    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean matchGender(String name) {
        return matchPetVariable(name);
    }

    @Override
    public boolean matchPetVariable(String name) {
        return name.equals(this.name);
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return GENDER_SERIALIZER.get();
    }

    @Override
    public VariableType<?> getType() {
        return GENDER.get();
    }

    @Override
    public boolean canBreed() {
        return canBreed;
    }

    @Override
    public boolean canBreedUniSex() {
        return canBreedUniSex;
    }

    @Override
    public boolean canBreedWithDragon(PetGender otherDragon) {
        if (canBreed && canBreedUniSex) {
            return true;
        } else if (canBreed) {
            return !this.equals(otherDragon);
        }
        return false;
    }

}
