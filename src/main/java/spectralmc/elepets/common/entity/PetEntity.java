package spectralmc.elepets.common.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import spectralmc.elepets.common.entity.goal.PetBreedGoal;


public class PetEntity extends BreedingPetEntity {

    public PetEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(7, new PetBreedGoal(this, 1.0D));
    }

}
