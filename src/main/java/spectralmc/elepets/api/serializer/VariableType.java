package spectralmc.elepets.api.serializer;

import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Optional;

public class VariableType<T extends IPetVariables> extends ForgeRegistryEntry<VariableType<?>> {

    public VariableType() {
    }

    public Optional<T> matches(IPetVariables variable, IPetVariables targetVariable) {
        return variable.getType().equals(targetVariable.getType()) ? Optional.of((T)variable) : Optional.empty();
    }
}
