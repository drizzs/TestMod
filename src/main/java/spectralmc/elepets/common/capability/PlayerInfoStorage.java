package spectralmc.elepets.common.capability;

import net.minecraft.nbt.CompoundNBT;
import spectralmc.elepets.api.database.IPlayerInfo;
import spectralmc.elepets.api.database.PlayerInfo;

import java.util.*;

public class PlayerInfoStorage implements IPlayerInfo {

    private final Map<UUID, PlayerInfo> playerInfoMap;

    public PlayerInfoStorage() {
        playerInfoMap = new HashMap<>();
    }

    public PlayerInfo getPlayerInfo(UUID id) {
        if (playerInfoMap.get(id) == null) {
            playerInfoMap.put(id, new PlayerInfo(0, 0, 0, 0, 0, false));
        }
        return playerInfoMap.get(id);
    }

    @Override
    public long getCurrency(UUID id) {
        return getPlayerInfo(id).getCurrency();
    }

    @Override
    public void addCurrency(UUID id, long amount) {
        getPlayerInfo(id).addCurrency(amount);
    }

    @Override
    public void removeCurrency(UUID id, long amount) {
        getPlayerInfo(id).removeCurrency(amount);
    }

    @Override
    public int getCurrentTeamWins(UUID id) {
        return getPlayerInfo(id).getCurrentTeamWins();
    }

    @Override
    public void addCurrentTeamWin(UUID id) {
        getPlayerInfo(id).addCurrentTeamWin();
    }

    @Override
    public void setCurrentTeamWins(UUID id, int amount) {
        getPlayerInfo(id).setCurrentTeamWins(amount);
    }

    @Override
    public void resetCurrentTeamWins(UUID id) {
        getPlayerInfo(id).resetCurrentTeamWins();
    }

    @Override
    public int getCurrentTeamLosses(UUID id) {
        return getPlayerInfo(id).getCurrentTeamLosses();
    }

    @Override
    public void addCurrentTeamLosses(UUID id) {
        getPlayerInfo(id).addCurrentTeamLosses();
    }

    @Override
    public void setCurrentTeamLosses(UUID id, int amount) {
        getPlayerInfo(id).setCurrentTeamLosses(amount);
    }

    @Override
    public void resetCurrentTeamLosses(UUID id) {
        getPlayerInfo(id).resetCurrentTeamLosses();
    }

    @Override
    public int getLifeTimeWins(UUID id) {
        return getPlayerInfo(id).getLifeTimeWins();
    }

    @Override
    public void addLifeTimeWins(UUID id) {
        getPlayerInfo(id).addLifeTimeWins();
    }

    @Override
    public void setLifeTimeWins(UUID id, int amount) {
        getPlayerInfo(id).setLifeTimeWins(amount);
    }

    @Override
    public void resetLifeTimeWins(UUID id) {
        getPlayerInfo(id).resetLifeTimeWins();
    }

    @Override
    public int getLifeTimeLosses(UUID id) {
        return getPlayerInfo(id).getLifeTimeLosses();
    }

    @Override
    public void addLifeTimeLosses(UUID id) {
        getPlayerInfo(id).addLifeTimeLosses();
    }

    @Override
    public void setLifeTimeLosses(UUID id, int amount) {
        getPlayerInfo(id).setLifeTimeLosses(amount);
    }

    @Override
    public void resetLifeTimeLosses(UUID id) {
        getPlayerInfo(id).resetLifeTimeLosses();
    }

    @Override
    public boolean hasGoneToStarterZone(UUID id) {
        return getPlayerInfo(id).hasGoneToStarterZone();
    }

    @Override
    public void setHasGoneToStarterZone(UUID id, boolean hasGonetoStartingArea) {
        getPlayerInfo(id).setHasGoneToStarterZone(hasGonetoStartingArea);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        int index = 0;
        for (UUID id : playerInfoMap.keySet()) {
            nbt.putString("id" + index, id.toString());
            nbt.putLong(id.toString() + " currency", getCurrency(id));
            nbt.putInt(id.toString() + " currentTeamWins", getCurrentTeamWins(id));
            nbt.putInt(id.toString() + " currentTeamLosses", getCurrentTeamLosses(id));
            nbt.putInt(id.toString() + " currentTeamWins", getLifeTimeWins(id));
            nbt.putInt(id.toString() + " currentTeamLosses", getLifeTimeLosses(id));
            nbt.putBoolean(id.toString() + " hasGoneToStarter", hasGoneToStarterZone(id));
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        List<UUID> idList = new ArrayList<>();
        int index;
        int index2 = 1;
        for (index = 0; index < index2; ) {
            String stringID = nbt.getString("id" + index);
            if (!stringID.isEmpty()) {
                UUID id = UUID.fromString(nbt.getString("id" + index));
                idList.add(id);
                index2++;
            }
            index++;
        }
        int size = idList.size();
        for (int x = 0; x < size; ++x) {
            UUID id = idList.get(x);
            PlayerInfo playerInfo = new PlayerInfo(nbt.getInt(id.toString() + " currentTeamWins"), nbt.getInt(id.toString() + " currentTeamLosses"), nbt.getInt(id.toString() + " currentTeamWins"), nbt.getInt(id.toString() + " currentTeamLosses"), nbt.getInt(id.toString() + " currency"), nbt.getBoolean(id.toString() + " hasGoneToStarter"));
            playerInfoMap.put(id, playerInfo);
        }
    }

}
