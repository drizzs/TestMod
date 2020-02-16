package spectralmc.elepets.api.pet.maturity;

import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.VariableType;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.MATURITY;
import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.MATURITY_SERIALIZER;

public class PetMaturity implements IMaturity {

    private final ResourceLocation id;
    private final String name;
    private final int growthOrder;
    private final int ageToMature;

    public PetMaturity(ResourceLocation id, String name, int growthOrder, int ageToMature) {
        this.id = id;
        this.name = name;
        this.growthOrder = growthOrder;
        this.ageToMature = ageToMature;
    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean matchMaturity(String name) {
        return matchPetVariable(name);
    }

    @Override
    public boolean matchPetVariable(String name) {
        return name.equals(this.name);
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return MATURITY_SERIALIZER.get();
    }

    @Override
    public VariableType<?> getType() {
        return MATURITY.get();
    }

    public int getAgeToMature() {
        return ageToMature;
    }

    public int getGrowthOrder() {
        return growthOrder;
    }

}
