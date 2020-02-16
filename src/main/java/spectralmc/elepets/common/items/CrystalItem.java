package spectralmc.elepets.common.items;


import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import spectralmc.elepets.api.pet.StatBase;

public class CrystalItem extends Item implements INBTSerializable<CompoundNBT> {

    private StatBase storedPet;

    private boolean broken = isPetBroken();

    public CrystalItem() {
        super(new Item.Properties().maxStackSize(1));
    }

    public StatBase getStoredPet() {
        return storedPet;
    }

    public void setStoredPet(StatBase storedPet) {
        this.storedPet = storedPet;
    }

    public boolean isPetBroken(){
        int currentHp = 0;
        if(getStoredPet() != null){
            currentHp = getStoredPet().getCurrentHP();
        }
        return currentHp <= 0;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getStoredPet().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getStoredPet().serializeNBT();
    }
}
