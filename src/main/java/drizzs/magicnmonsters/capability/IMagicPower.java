package drizzs.magicnmonsters.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMagicPower extends INBTSerializable<CompoundNBT> {

    int getMagicPower();
    int getMaxPower();

    int addMagicPower();
    int removeMagicPower();

    int addMagicPower(int magic);
    int removeMagicPower(int magic);

    void setMagicPower(int magic);

    void resetMagicPower();

}
