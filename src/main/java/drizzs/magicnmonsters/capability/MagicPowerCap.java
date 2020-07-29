package drizzs.magicnmonsters.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

public class MagicPowerCap {

    @CapabilityInject(IMagicPower.class)
    public static Capability<IMagicPower> MAGIC_POWER_CAPABILITY;

    public static final Direction DEFAULT_FACING = null;

    public static LazyOptional<IMagicPower> getPlayerMagicPower(final PlayerEntity player) {
        return player.getCapability(MAGIC_POWER_CAPABILITY, DEFAULT_FACING);
    }

    public static int getMagicPower(final PlayerEntity player){
        return getPlayerMagicPower(player).map(IMagicPower::getMagicPower).orElse(0);
    }

    public static int addMagicPower(final PlayerEntity player){
        return getPlayerMagicPower(player).map(IMagicPower::addMagicPower).orElse(0);
    }

    public static int getMaxMagicPower(final PlayerEntity player){
        return getPlayerMagicPower(player).map(IMagicPower::getMaxPower).orElse(0);
    }

    public static int removeMagicPower(final PlayerEntity player, int remove){
        return getPlayerMagicPower(player).map(power -> power.removeMagicPower(remove)).orElse(0);
    }
}
