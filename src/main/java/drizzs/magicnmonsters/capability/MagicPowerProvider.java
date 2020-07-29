package drizzs.magicnmonsters.capability;

import drizzs.magicnmonsters.capability.IMagicPower;
import drizzs.magicnmonsters.capability.MagicPower;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static drizzs.magicnmonsters.capability.MagicPowerCap.MAGIC_POWER_CAPABILITY;

public class MagicPowerProvider implements ICapabilitySerializable<CompoundNBT> {

    private final IMagicPower magicPower;
    private final LazyOptional<IMagicPower> magicPowerProvider;

    public MagicPowerProvider() {
        this(new MagicPower());
    }

    public MagicPowerProvider(IMagicPower sf) {
        this.magicPower = sf;
        this.magicPowerProvider = LazyOptional.of(() -> sf);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == MAGIC_POWER_CAPABILITY ? magicPowerProvider.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return magicPower.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        magicPower.deserializeNBT(nbt);
    }
}
