package spectralmc.elepets.api.database;

public class PlayerInfo {

    private int lifeTimeWins;
    private int lifeTimeLosses;
    private int currentTeamWins;
    private int currentTeamLosses;
    private long currency;
    private boolean hasGoneToStarterZone;

    public PlayerInfo(int lifeTimeWins, int lifeTimeLosses, int currentTeamWins, int currentTeamLosses, int currency, boolean hasGoneToStarterZone) {
        this.lifeTimeWins = lifeTimeWins;
        this.lifeTimeLosses = lifeTimeLosses;
        this.currentTeamWins = currentTeamWins;
        this.currentTeamLosses = currentTeamLosses;
        this.currency = currency;
        this.hasGoneToStarterZone = hasGoneToStarterZone;
    }

    public long getCurrency() {
        return currency;
    }

    public void addCurrency(long amount) {
        if (amount > 0) {
            long currentAmount = this.getCurrency();
            this.currency = currentAmount + amount;
        }
    }

    public void removeCurrency(long amount) {
        if (amount > 0) {
            long currentAmount = this.getCurrency();
            this.currency = currentAmount - amount;
        }
    }

    public int getCurrentTeamWins() {
        return currentTeamWins;
    }

    public void addCurrentTeamWin() {
        ++currentTeamWins;
    }

    public void setCurrentTeamWins(int amount) {
        currentTeamWins = amount;
    }

    public void resetCurrentTeamWins() {
        currentTeamWins = 0;
    }

    public int getCurrentTeamLosses() {
        return currentTeamLosses;
    }

    public void addCurrentTeamLosses() {
        ++currentTeamLosses;
    }

    public void setCurrentTeamLosses(int amount) {
        currentTeamLosses = amount;
    }

    public void resetCurrentTeamLosses() {
        currentTeamLosses = 0;
    }

    public int getLifeTimeWins() {
        return lifeTimeWins;
    }

    public void addLifeTimeWins() {
        ++lifeTimeWins;
    }

    public void setLifeTimeWins(int amount) {
        lifeTimeWins = amount;
    }

    public void resetLifeTimeWins() {
        lifeTimeWins = 0;
    }

    public int getLifeTimeLosses() {
        return lifeTimeLosses;
    }

    public void addLifeTimeLosses() {
        ++lifeTimeLosses;
    }

    public void setLifeTimeLosses(int amount) {
        lifeTimeLosses = amount;
    }

    public void resetLifeTimeLosses() {
        lifeTimeLosses = 0;
    }

    public boolean hasGoneToStarterZone() {
        return hasGoneToStarterZone;
    }

    public void setHasGoneToStarterZone(boolean hasGonetoStartingArea) {
        hasGoneToStarterZone = hasGonetoStartingArea;
    }

}
