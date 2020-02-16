package spectralmc.elepets.api.pet.maturity;

import spectralmc.elepets.api.serializer.IPetVariables;

public interface IMaturity extends IPetVariables {

    int getAgeToMature();

    int getGrowthOrder();

}
