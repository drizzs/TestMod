package spectralmc.elepets.common.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.StatBase;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.PetMaturity;

public class BasePetEntity extends CreatureEntity {

    //This Class Handles Pet Maturity and Gender.

    protected PetMaturity petMaturity;

    private PetVariableManager variableManager = new PetVariableManager();

    protected StatBase petBase;

    protected BasePetEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public boolean hasAlternativeGenderModels() {
        return false;
    }

    protected void onChildSpawnFromEgg(PlayerEntity playerIn, PetMaturity maturity) {
    }

    protected void onGrowth(PlayerEntity playerIn, PetMaturity maturity) {
    }

    //TODO: AddModelSelector
    public void modelSelector() {
        if (getPetMaturity().equals(getVariableManager().getMaturityFromString("adult"))) {
            if (hasAlternativeGenderModels() && !getPetGender().equals(getVariableManager().getGenderFromString("male"))) {

            } else {

            }
        } else if (getPetMaturity().equals(getVariableManager().getMaturityFromString("teen"))) {
            if (hasAlternativeGenderModels() && !getPetGender().equals(getVariableManager().getGenderFromString("male"))) {

            } else {

            }
        }  else {
            if (hasAlternativeGenderModels() && !getPetGender().equals(getVariableManager().getGenderFromString("male"))) {

            } else {

            }
        }
    }

    @Override
    public boolean isChild() {
        return false;
    }

    public PetMaturity getPetMaturity() {
        return petMaturity;
    }

    public PetGender getPetGender() {
        return getPetBase().getPetGender();
    }

    public StatBase getPetBase() {
        return petBase;
    }

    public void setPetBase(StatBase pet) {
        petBase = pet;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        getPetBase().deserializeNBT(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(getPetBase().serializeNBT());
    }

    public PetVariableManager getVariableManager() {
        return variableManager;
    }
}
