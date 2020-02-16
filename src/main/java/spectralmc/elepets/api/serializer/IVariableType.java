package spectralmc.elepets.api.serializer;

import java.util.Optional;

public interface IVariableType<T extends IPetVariables> {

    Optional<T> matches(IPetVariables variable, IPetVariables targetVariable);

}
