package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.WATER_EGG_TILE;
import static spectralmc.elepets.util.ElePetsTags.WATEREGG;

public class WaterEggTile extends BaseEggTile {
    public WaterEggTile() {
        super(WATER_EGG_TILE.get(), ParticleTypes.BUBBLE_COLUMN_UP);
    }


    @Override
    public boolean getChallengeBoolean() {
        int check = 0;
        for(BlockState state : getAboveStates(3)){
            if(state.getBlock().isIn(WATEREGG)){
                ++check;
            }
        }
        return isSurrounded(WATEREGG) && check == 3;
    }
}
