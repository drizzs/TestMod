package spectralmc.elepets.api.pet.type;

import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.VariableType;
import spectralmc.elepets.util.ElePetsConfig;

import java.util.ArrayList;
import java.util.List;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.TYPE;
import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.TYPE_SERIALIZER;

public class PetType implements IType {

    private final ResourceLocation id;
    private final String name;
    private final int attackModifier;
    private final int defenseModifier;
    private final int speedModifier;
    private final int healthModifier;
    private final int meleeResistance;
    private final int rangedResistance;

    private final boolean canBeNeutral;
    private final int percentBabyIsMale;

    private final int typeColor;

    public List<PetType> weaknesses = new ArrayList<>();
    public List<PetType> superweaknesses = new ArrayList<>();
    public List<PetType> resistance = new ArrayList<>();

    public PetType(ResourceLocation id, String name, int attackModifier, int defenseModifier, int speedModifier, int healthModifier, int meleeResistance, int rangedResistance, int typeColor, boolean canBeNeutral, int percentBabyIsMale) {
        this.id = id;
        this.name = name;
        this.attackModifier = attackModifier;
        this.defenseModifier = defenseModifier;
        this.speedModifier = speedModifier;
        this.healthModifier = healthModifier;
        this.meleeResistance = meleeResistance;
        this.rangedResistance = rangedResistance;
        this.typeColor = typeColor;
        this.canBeNeutral = canBeNeutral;
        this.percentBabyIsMale = percentBabyIsMale;
    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean matchType(String name){
        return matchPetVariable(name);
    }

    @Override
    public boolean matchPetVariable(String name) {
        return name.equals(this.name);
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return TYPE_SERIALIZER.get();
    }

    @Override
    public VariableType<?> getType() {
        return TYPE.get();
    }

    @Override
    public boolean canBeNeutral() {
        return canBeNeutral;
    }

    @Override
    public int getMalePercent() {
        return percentBabyIsMale;
    }

    @Override
    public int getAttackModifier() {
        return attackModifier;
    }

    @Override
    public int getDefenceModifier() {
        return defenseModifier;
    }

    @Override
    public int getSpeedModifier() {
        return speedModifier;
    }

    @Override
    public int getHealthModifier() {
        return healthModifier;
    }

    @Override
    public int getMeleeResistanceModifier() {
        return meleeResistance;
    }

    @Override
    public int getRangeResistanceModifier() {
        return rangedResistance;
    }

    @Override
    public double getTypeResistanceModifier(PetType opposingPetType) {
        if (resistance.contains(opposingPetType)) {
            return ElePetsConfig.COMMON.RESISTANCEMODIFIER.get() / 100D;
        }
        return 0;
    }

    @Override
    public double getTypeWeaknessModifier(PetType opposingPetType) {
        if (weaknesses.contains(opposingPetType)) {
            return ElePetsConfig.COMMON.WEAKNESSMODIFIER.get() / 100D;
        } else if (superweaknesses.contains(opposingPetType)) {
            return ElePetsConfig.COMMON.SUPERWEAKNESSMODIFIER.get() / 100D;
        }
        return 0;
    }

    @Override
    public int getTypeColor() {
        return typeColor;
    }

    @Override
    public PetType getPetType() {
        return this;
    }

    @Override
    public void addWeakness(PetType type) {
        weaknesses.add(type);
    }

    @Override
    public void addResistance(PetType type) {
        weaknesses.add(type);
    }

    @Override
    public void addSuperWeakness(PetType type) {
        weaknesses.add(type);
    }

}
