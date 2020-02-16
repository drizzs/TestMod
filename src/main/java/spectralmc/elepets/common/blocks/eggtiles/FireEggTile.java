package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.FIRE_EGG_TILE;
import static spectralmc.elepets.util.ElePetsTags.FIREEGG;

public class FireEggTile extends BaseEggTile {

    public FireEggTile() {
        super(FIRE_EGG_TILE.get(), ParticleTypes.LAVA);
    }

    @Override
    public boolean getChallengeBoolean() {
        return isSurrounded(FIREEGG) && worldBiomeTypeChecker(Biome.Category.NETHER);
    }
}
