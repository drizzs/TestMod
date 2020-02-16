package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.EARTH_EGG_TILE;
import static spectralmc.elepets.util.ElePetsTags.EARTHEGG;

public class EarthEggTile extends BaseEggTile {

    public EarthEggTile() {
        super(EARTH_EGG_TILE.get(), ParticleTypes.RAIN);
    }

    @Override
    public boolean getChallengeBoolean() {
        return isSurrounded(EARTHEGG);
    }
}
