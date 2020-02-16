package spectralmc.elepets.api.database;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public interface IPlayerInfo extends INBTSerializable<CompoundNBT> {

    long getCurrency(UUID id);

    void addCurrency(UUID id, long amount);

    void removeCurrency(UUID id, long amount);

    int getCurrentTeamWins(UUID id);

    void addCurrentTeamWin(UUID id);

    void setCurrentTeamWins(UUID id, int amount);

    void resetCurrentTeamWins(UUID id);

    int getCurrentTeamLosses(UUID id);

    void addCurrentTeamLosses(UUID id);

    void setCurrentTeamLosses(UUID id, int amount);

    void resetCurrentTeamLosses(UUID id);

    int getLifeTimeWins(UUID id);

    void addLifeTimeWins(UUID id);

    void setLifeTimeWins(UUID id, int amount);

    void resetLifeTimeWins(UUID id);

    int getLifeTimeLosses(UUID id);

    void addLifeTimeLosses(UUID id);

    void setLifeTimeLosses(UUID id, int amount);

    void resetLifeTimeLosses(UUID id);

    boolean hasGoneToStarterZone(UUID id);

    void setHasGoneToStarterZone(UUID id, boolean hasGonetoStartingArea);

}
