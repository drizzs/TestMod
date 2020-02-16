package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.DARK_EGG_TILE;

public class DarkEggTile extends BaseEggTile {

    private EndermanEntity entity = null;

    public DarkEggTile() {
        super(DARK_EGG_TILE.get(), ParticleTypes.SMOKE);
    }
/*
    @Override
    public void tick() {
        if (!world.isRemote) {
            if (getMaxEggTime() > 0) {
                if (getChallengeBoolean()) {
                    ++timer;
                    ++challenge;
                }
                if (getChallengeGoal() == 80) {
                    endermanSpawn();

                }
                if (getChallengeGoal() == 200) {
                    endermanEggPickup();
                }
                if (getChallengeGoal() == 200) {
                    endermanKill();
                }
            }
        }
    }

    public void endermanSpawn() {
        entity = new EndermanEntity(EntityType.ENDERMAN, world);
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(entity);
    }

    public void endermanEggPickup() {
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        entity.setHeldBlockState(this.getBlockState());
    }

    private void endermanKill(){
        entity.onKillCommand();
    }

 */

    @Override
    public boolean getChallengeBoolean() {
        return !world.isDaytime();
    }
}
