package spectralmc.elepets.api.pet.type;

import spectralmc.elepets.api.serializer.IPetVariables;

public interface IType extends IPetVariables {

    boolean canBeNeutral();

    int getMalePercent();

    int getAttackModifier();

    int getDefenceModifier();

    int getSpeedModifier();

    int getHealthModifier();

    int getMeleeResistanceModifier();

    int getRangeResistanceModifier();

    double getTypeResistanceModifier(PetType opposingPetType);

    double getTypeWeaknessModifier(PetType opposingPetType);

    int getTypeColor();

    PetType getPetType();

    void addWeakness(PetType type);

    void addResistance(PetType type);

    void addSuperWeakness(PetType type);

}
