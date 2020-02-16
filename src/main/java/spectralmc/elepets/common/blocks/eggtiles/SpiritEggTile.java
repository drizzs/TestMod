package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.SPIRIT_EGG_TILE;

public class SpiritEggTile extends BaseEggTile {
    public SpiritEggTile() {
        super(SPIRIT_EGG_TILE.get(), ParticleTypes.SMOKE);
    }

    @Override
    public boolean getChallengeBoolean() {
        return getChallengeGoal() >= 15;
    }

}
