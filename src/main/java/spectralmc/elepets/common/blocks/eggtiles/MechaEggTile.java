package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.MECHA_EGG_TILE;

public class MechaEggTile extends BaseEggTile {

    public MechaEggTile() {
        super(MECHA_EGG_TILE.get(), ParticleTypes.BUBBLE_POP);
    }

    @Override
    public boolean getChallengeBoolean() {
        return getChallengeGoal() >= 4;
    }



}
