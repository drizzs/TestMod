package spectralmc.elepets.api.serializer;

import net.minecraft.util.ResourceLocation;

public interface IPetVariables {

    ResourceLocation getLocation();

    String getName();

    boolean matchPetVariable(String name);

    IPetSerializable<?> getSerializer();

    VariableType<?> getType();

}
