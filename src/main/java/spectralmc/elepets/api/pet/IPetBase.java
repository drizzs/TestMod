package spectralmc.elepets.api.pet;

import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.PetMaturity;
import spectralmc.elepets.api.pet.nature.PetNature;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.api.serializer.IPetVariables;

public interface IPetBase extends IPetVariables {

    PetType getPetType();

    PetDiet getDiet();

    PetMaturity getPetMaturity();

    PetGender getPetGender();

    PetNature getPetNature();

}
