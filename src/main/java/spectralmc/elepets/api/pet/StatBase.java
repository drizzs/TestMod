package spectralmc.elepets.api.pet;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;
import spectralmc.elepets.api.ElePetsRegistries;
import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.PetMaturity;
import spectralmc.elepets.api.pet.nature.PetNature;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.util.ElePetsConfig;

import java.util.function.Supplier;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.MATURITY;

public class StatBase extends PetBase implements IPetStats, INBTSerializable<CompoundNBT> {

    private int attackValue;
    private int defenceValue;
    private int speedValue;
    private int healthValue;

    private int baseAttack;
    private int baseDefense;
    private int baseSpeed;
    private int baseHealth;
    private int baseMeleeResist;
    private int baseRangedResist;

    private int meleeResistValue;
    private int rangedResistValue;

    private int maxLevel = setMaxLevel();

    private int level = 1;
    private int experience = 0;
    private int experienceToNextLevel;

    private int maxHP = 0;
    private int currentHP = setCurrentHP();
    private int damageTaken = 0;

    //For Setting Specifics into Pets
    public StatBase(ResourceLocation id, String name, PetType type, PetGender gender, PetDiet diet, PetNature nature, int baseAttack, int baseDefense, int baseSpeed, int baseHealth, int baseMeleeResist, int baseRangedResist, int rarity, boolean canBreed) {
        super(id, name, type, gender, diet, nature, rarity, canBreed);
        //Base Stats Passing through for the purpose of accessing this information in the future
        //Each Pet has Unique Stats
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.baseHealth = baseHealth;

        this.baseMeleeResist = baseMeleeResist;
        this.baseRangedResist = baseRangedResist;
    }

    public StatBase(ResourceLocation id, String name, PetType type, PetDiet diet, int baseAttack, int baseDefense, int baseSpeed, int baseHealth, int baseMeleeResist, int baseRangedResist, int rarity, boolean canBreed) {
        super(id, name, type, diet,rarity, canBreed);
        //Base Stats Passing through for the purpose of accessing this information in the future
        //Each Pet has Unique Stats
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.baseHealth = baseHealth;

        this.baseMeleeResist = baseMeleeResist;
        this.baseRangedResist = baseRangedResist;
    }

    //Stat Getters

    @Override
    public int getAttackStat() {
        setAttackStat();
        return attackValue;
    }

    @Override
    public int getDefenceStat() {
        return defenceValue;
    }

    @Override
    public int getSpeedStat() {
        return speedValue;
    }

    @Override
    public int getHealthStat() {
        return healthValue;
    }

    @Override
    public int getBaseAttack() {
        return baseAttack;
    }

    @Override
    public int getBaseDefense() {
        return baseDefense;
    }

    @Override
    public int getBaseSpeed() {
        return baseSpeed;
    }

    @Override
    public int getBaseHealth() {
        return baseHealth;
    }

    @Override
    public int getBaseMeleeResist() {
        return baseMeleeResist;
    }

    @Override
    public int getBaseRangedResist() {
        return baseRangedResist;
    }

    @Override
    public int getMeleeResistValue() {
        return meleeResistValue;
    }

    @Override
    public int getRangedResistValue() {
        return rangedResistValue;
    }

    //Applies stat logic to each stat

    private void setAttackStat() {
        /*
        int levelModifier = getCurrentLevel() * ((getPetType().get().getAttackModifier() + getPetMaturity().getGrowthOrder()) * (getPetNature().getAttackModifier()/100)) + 1;
        */
        int baseStats = getBaseAttack() + (getPetType().getAttackModifier() * 2);
        attackValue = baseStats;
    }

    private void setDefenseStat() {
        int levelModifier = getCurrentLevel() * ((getPetType().getDefenceModifier() + getPetMaturity().getGrowthOrder()) * (getPetNature().getDefenceModifier() / 100)) + 1;
        int baseStats = getBaseDefense() + (getPetType().getDefenceModifier() * 2);
        defenceValue = baseStats + levelModifier;
    }

    private void setSpeedStat() {
        int levelModifier = getCurrentLevel() * ((getPetType().getSpeedModifier() + getPetMaturity().getGrowthOrder()) * (getPetNature().getSpeedModifier() / 100)) + 1;
        int baseStats = getBaseSpeed() + (getPetType().getSpeedModifier() * 2);
        speedValue = baseStats + levelModifier;
    }

    protected void setHealthStat() {
        int levelModifier = getCurrentLevel() * ((getPetType().getHealthModifier() + getPetMaturity().getGrowthOrder()) * (getPetNature().getHealthModifier() / 100)) + 1;
        int baseStats = getBaseHealth() + (getPetType().getHealthModifier() * 2);
        healthValue = baseStats + levelModifier;
    }

    protected void setMeleeResistStat() {
        meleeResistValue = getPetType().getMeleeResistanceModifier() + getPetNature().getMeleeResistanceModifier() + getBaseMeleeResist();
    }

    protected void setRangedResistStat() {
        rangedResistValue = getPetType().getRangeResistanceModifier() + getPetNature().getRangedResistanceModifier() + getBaseRangedResist();
    }

    private int setMaxLevel() {
        return ElePetsConfig.COMMON.MAXLEVEL.get();
    }

    @Override
    public int getDamageTaken() {
        return damageTaken;
    }

    @Override
    public int getCurrentHP() {
        return getMaxHP() - getDamageTaken();
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getCurrentLevel() {
        return level;
    }

    @Override
    public int getCurrentXp() {
        return experience;
    }

    //Setters for HP Max and Current
    private void setMaxHP() {
        maxHP = getHealthStat() + getPetType().getHealthModifier() + (getPetMaturity().getGrowthOrder() * 50) + (getPetNature().getHealthModifier() / 10);
    }

    public int setCurrentHP() {
        return getMaxHP() - getDamageTaken();
    }

    //LevelsUp by 1 and Checks for Evolution
    public void levelUp() {
        if (canLevel()) {
            ++level;
            checkMaturity();
        } else {
            //ToDo:: Add a message that says your pet is max level should only matter for forced levelling
        }
    }

    //Sets Level for the purpose of commands and Checks for Evolutions
    public void setLevel(int level) {
        if (level > getMaxLevel()) {
            //TODO: add message that says you cant set a pet level above the max level
            this.level = getMaxLevel();
        } else {
            this.level = level;
        }
        checkMaturity();
    }

    //Checks Whether or not this dragon needs to grow up
    protected void checkMaturity() {
        if (getCurrentLevel() == getPetMaturity().getAgeToMature()) {
            addAge();
            setMaturity(growth());
        }
    }

    private PetMaturity growth(){
        return getManager().getVariables().stream().filter(variables -> { return variables.getType().equals(MATURITY.get());}).map(variables -> {return (PetMaturity) variables;}).filter(variables -> { return variables.getGrowthOrder() == getAge();}).findFirst().orElse(getManager().getMaturityFromString("adult"));
    }

    //Checks to see if you're above max at max level
    public boolean canLevel() {
        return getCurrentLevel() < getMaxLevel();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("currentLevel", this.level);
        nbt.putInt("experienceTotal", this.experience);
        nbt.putInt("toNextLevel", this.experienceToNextLevel);

        nbt.putInt("baseAttack", this.baseAttack);
        nbt.putInt("baseDefence", this.baseDefense);
        nbt.putInt("baseSpeed", this.baseSpeed);
        nbt.putInt("baseHealth", this.baseHealth);

        nbt.putInt("baseRangedResist", this.baseRangedResist);
        nbt.putInt("baseMeleeResist", this.baseMeleeResist);

        nbt.putInt("damageTaken", this.damageTaken);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.level = nbt.getInt("currentLevel");
        this.experience = nbt.getInt("experienceTotal");
        this.experienceToNextLevel = nbt.getInt("toNextLevel");

        this.baseAttack = nbt.getInt("baseAttack");
        this.baseDefense = nbt.getInt("baseDefence");
        this.baseSpeed = nbt.getInt("baseSpeed");
        this.baseHealth = nbt.getInt("baseHealth");

        this.baseMeleeResist = nbt.getInt("baseMeleeResist");
        this.baseRangedResist = nbt.getInt("baseRangedResist");

        this.damageTaken = nbt.getInt("damageTaken");
    }
}
