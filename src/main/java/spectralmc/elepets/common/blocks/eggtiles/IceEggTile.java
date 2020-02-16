package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.ICE_EGG_TILE;

public class IceEggTile extends BaseEggTile {

    public IceEggTile() {
        super(ICE_EGG_TILE.get(), ParticleTypes.ITEM_SNOWBALL);
    }

    @Override
    public boolean getChallengeBoolean() {
        return pos.getY() > 80 && worldBiomeTypeChecker(Biome.Category.ICY);
    }
}
