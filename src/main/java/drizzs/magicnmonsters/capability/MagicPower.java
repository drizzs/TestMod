package drizzs.magicnmonsters.capability;

import net.minecraft.nbt.CompoundNBT;

public class MagicPower implements IMagicPower{

    private int magicPower;
    private final int maxCapacity;

    public MagicPower(){
        magicPower = 1000;
        maxCapacity = 1000;
    }

    @Override
    public int getMagicPower() {
        return magicPower;
    }

    @Override
    public int getMaxPower() {
        return maxCapacity;
    }

    @Override
    public int addMagicPower() {
        magicPower = Math.min(getMaxPower(), ++magicPower);
        return magicPower;
    }

    @Override
    public int removeMagicPower() {
        magicPower = Math.max(0, --magicPower);
        return magicPower;
    }

    @Override
    public int addMagicPower(int magic) {
        magicPower = Math.min(getMaxPower(), magicPower + magic);
        return magicPower;
    }

    @Override
    public int removeMagicPower(int magic) {
        magicPower = Math.max(0, magicPower - magic);
        return magicPower;
    }

    @Override
    public void setMagicPower(int magic) {
        magicPower = Math.min(Math.max(0, magic),getMaxPower());
    }

    @Override
    public void resetMagicPower() {
        magicPower = getMaxPower();
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("power",getMagicPower());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setMagicPower(nbt.getInt("power"));
    }

}
