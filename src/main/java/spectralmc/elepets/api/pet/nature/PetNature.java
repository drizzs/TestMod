package spectralmc.elepets.api.pet.nature;

import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.IPetVariables;
import spectralmc.elepets.api.serializer.VariableType;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.NATURE;
import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.NATURE_SERIALIZER;

public class PetNature implements INature {

    private final ResourceLocation id;
    private final String name;
    private final int attackModifier;
    private final int defenseModifier;
    private final int speedModifier;
    private final int healthModifier;
    private final int meleeResistanceModifier;
    private final int rangedResistanceModifier;

    public PetNature(ResourceLocation id, String name, int attackModifier, int defenseModifier, int speedModifier, int healthModifier, int meleeResistanceModifier, int rangedResistanceModifier) {
        this.id = id;
        this.name = name;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.speedModifier = speedModifier;
        this.healthModifier = healthModifier;
        this.meleeResistanceModifier = meleeResistanceModifier;
        this.rangedResistanceModifier = rangedResistanceModifier;
    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean matchNature(String name) {
        return matchPetVariable(name);
    }

    @Override
    public boolean matchPetVariable(String name) {
        return name.equals(this.name);
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return NATURE_SERIALIZER.get();
    }

    @Override
    public VariableType<?> getType() {
        return NATURE.get();
    }

    @Override
    public int getAttackModifier() {
        if (attackModifier < 0) {
            return 0;
        } else return Math.min(attackModifier, 200);
    }

    @Override
    public int getDefenceModifier() {
        if (defenseModifier < 0) {
            return 0;
        } else return Math.min(defenseModifier, 200);
    }

    @Override
    public int getSpeedModifier() {
        if (speedModifier < 0) {
            return 0;
        } else return Math.min(speedModifier, 200);
    }

    @Override
    public int getHealthModifier() {
        if (healthModifier < 0) {
            return 0;
        } else return Math.min(healthModifier, 200);
    }

    @Override
    public int getMeleeResistanceModifier() {
        if (meleeResistanceModifier < 0) {
            return 0;
        } else return Math.min(meleeResistanceModifier, 200);
    }

    @Override
    public int getRangedResistanceModifier() {
        if (rangedResistanceModifier < 0) {
            return 0;
        } else return Math.min(rangedResistanceModifier, 200);
    }

}
