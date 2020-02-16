package spectralmc.elepets.common.entity.goal;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import spectralmc.elepets.common.entity.BreedingPetEntity;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class PetBreedGoal extends Goal {
    private static final EntityPredicate loveSearch = (new EntityPredicate()).setDistance(8.0D).allowInvulnerable().allowFriendlyFire().setLineOfSiteRequired();
    protected final BreedingPetEntity petEntity;
    private final Class<? extends BreedingPetEntity> mateClass;
    protected final World world;
    protected BreedingPetEntity targetMate;
    private int spawnBabyDelay;
    private final double moveSpeed;

    public PetBreedGoal(BreedingPetEntity petEntity, double speedIn) {
        this(petEntity, speedIn, petEntity.getClass());
    }

    public PetBreedGoal(BreedingPetEntity petEntity, double speed, Class<? extends BreedingPetEntity> mateClass) {
        this.petEntity = petEntity;
        this.world = petEntity.world;
        this.mateClass = mateClass;
        this.moveSpeed = speed;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        if (!this.petEntity.isSearchingForLove()) {
            return false;
        } else {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    @Nullable
    private BreedingPetEntity getNearbyMate() {
        List<BreedingPetEntity> list = this.world.getTargettableEntitiesWithinAABB(this.mateClass, loveSearch, this.petEntity, this.petEntity.getBoundingBox().grow(8.0D));
        double d0 = Double.MAX_VALUE;
        BreedingPetEntity petEntity1 = null;
        for (BreedingPetEntity pet : list) {
            if (this.petEntity.isValidPartner(pet) && this.petEntity.getDistanceSq(pet) < d0) {
                petEntity1 = pet;
                d0 = this.petEntity.getDistanceSq(pet);
            }
        }
        return petEntity1;
    }

    public boolean shouldContinueExecuting() {
        return this.targetMate.isAlive() && this.targetMate.isSearchingForLove() && this.spawnBabyDelay < 60;
    }

    public void resetTask() {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    @Override
    public void tick() {
        this.petEntity.getLookController().setLookPositionWithEntity(this.targetMate, 10.0F, (float) this.petEntity.getVerticalFaceSpeed());
        this.petEntity.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;
        if (this.spawnBabyDelay >= 60 && this.petEntity.getDistanceSq(this.targetMate) < 9.0D) {
            this.spawnBaby();
        }
    }

    protected void spawnBaby() {
        petEntity.dropEggItem(targetMate);
    }

}
