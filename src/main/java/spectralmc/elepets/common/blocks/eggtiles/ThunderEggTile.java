package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import spectralmc.elepets.util.ElePetsConfig;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.THUNDER_EGG_TILE;

public class ThunderEggTile extends BaseEggTile {

    public ThunderEggTile() {
        super(THUNDER_EGG_TILE.get(), ParticleTypes.FLASH);
    }

    @Override
    public boolean getChallengeBoolean() {
        if (!world.getWorldInfo().isThundering() && ElePetsConfig.COMMON.WEATHERCONTROL.get()) {
            world.getWorldInfo().setThundering(true);
            world.getWorldInfo().setThunderTime(getMaxEggTime());
            world.getWorldInfo().setRaining(true);
            world.getWorldInfo().setRainTime(getMaxEggTime());
        }
        if (timer == getMaxEggTime() - 10) {
            LightningBoltEntity entity = new LightningBoltEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), true);
            ((ServerWorld) this.world).addLightningBolt(entity);
        }
        return getMaxEggTime() != getCurrentEggTime();
    }
}
