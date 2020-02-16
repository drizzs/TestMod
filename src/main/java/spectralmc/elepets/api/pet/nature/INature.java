package spectralmc.elepets.api.pet.nature;

import spectralmc.elepets.api.serializer.IPetVariables;

public interface INature extends IPetVariables {

    int getAttackModifier();

    int getDefenceModifier();

    int getSpeedModifier();

    int getHealthModifier();

    int getMeleeResistanceModifier();

    int getRangedResistanceModifier();

}
