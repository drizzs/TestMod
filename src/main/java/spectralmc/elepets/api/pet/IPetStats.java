package spectralmc.elepets.api.pet;

public interface IPetStats {

    int getAttackStat();

    int getDefenceStat();

    int getSpeedStat();

    int getHealthStat();

    int getDamageTaken();

    int getCurrentHP();

    int getMaxHP();

    int getMaxLevel();

    int getCurrentLevel();

    int getCurrentXp();

    int getMeleeResistValue();

    int getRangedResistValue();

    int getBaseAttack();

    int getBaseDefense();

    int getBaseSpeed();

    int getBaseHealth();

    int getBaseMeleeResist();

    int getBaseRangedResist();

}
