package spectralmc.elepets.api.pet.gender;

import spectralmc.elepets.api.serializer.IPetVariables;

public interface IGender extends IPetVariables {

    boolean canBreed();

    boolean canBreedUniSex();

    boolean canBreedWithDragon(PetGender otherDragon);

}
