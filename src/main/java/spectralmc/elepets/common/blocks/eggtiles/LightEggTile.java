package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;

import static spectralmc.elepets.common.content.ElePetsBlocks.UNLIT_PLACEHOLDER;
import static spectralmc.elepets.common.content.ElePetsEggRegistry.LIGHT_EGG_TILE;
import static spectralmc.elepets.util.ElePetsTags.LIGHTEGG;

public class LightEggTile extends BaseEggTile {

    public LightEggTile() {
        super(LIGHT_EGG_TILE.get(), ParticleTypes.FLASH);
    }

    @Override
    public boolean getChallengeBoolean() {
        if(getChallengeGoal() != 15 ){
            consumeNearbyLight();
            return false;
        }else return getChallengeGoal() >= 15 && world.isDaytime();
    }

    private void consumeNearbyLight(){
        for(int x = -2; x < 2; ++x){
            for(int y = -2; y < 2; ++y){
                for(int z = -2; z < 2; ++z){
                    BlockPos targetPos = pos.add(x,y,z);
                    BlockState targetState = world.getBlockState(targetPos);
                    if(world.isDaytime() && targetState.isIn(LIGHTEGG)){
                        world.destroyBlock(targetPos,false);
                        world.setBlockState(targetPos, UNLIT_PLACEHOLDER.get().getDefaultState());
                        setChallengeGoal();
                        return;
                    }
                }
            }
        }
    }
}
