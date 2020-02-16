package spectralmc.elepets.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import spectralmc.elepets.api.database.PlayerInfoCap;
import spectralmc.elepets.api.database.IPlayerInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerInfoStorageProvider implements ICapabilitySerializable<CompoundNBT> {

    private final IPlayerInfo playerInfo;
    private final LazyOptional<IPlayerInfo> playerInfoLazyOptional;

    public PlayerInfoStorageProvider() {
        this(new PlayerInfoStorage());
    }

    public PlayerInfoStorageProvider(IPlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        this.playerInfoLazyOptional = LazyOptional.of(() -> playerInfo);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == PlayerInfoCap.PLAYER_INFO_CAP ? playerInfoLazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return playerInfo.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        playerInfo.deserializeNBT(nbt);
    }

}
