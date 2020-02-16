package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.NORMAL_EGG_TILE;

public class NormalEggTile extends BaseEggTile{

    public NormalEggTile() {
        super(NORMAL_EGG_TILE.get(), ParticleTypes.PORTAL);
    }

}
