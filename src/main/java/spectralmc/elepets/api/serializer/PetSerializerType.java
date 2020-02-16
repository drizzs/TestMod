package spectralmc.elepets.api.serializer;

import net.minecraftforge.registries.ForgeRegistryEntry;
import spectralmc.elepets.api.pet.IPetBase;

import java.util.Optional;

public class PetSerializerType<T extends IPetBase> extends ForgeRegistryEntry<PetSerializerType<?>> {

    public PetSerializerType() {
    }

    public Optional<T> matches(IPetBase petBase, IPetBase targetPetBase) {
        return petBase.getType().equals(targetPetBase.getType()) ? Optional.of((T)petBase) : Optional.empty();
    }
}
