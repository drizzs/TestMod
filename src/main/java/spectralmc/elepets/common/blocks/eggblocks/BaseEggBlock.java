package spectralmc.elepets.common.blocks.eggblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.common.blocks.eggtiles.BaseEggTile;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.TYPE;

public class BaseEggBlock extends Block {

    private final PetVariableManager manager = new PetVariableManager();

    private final int eggMinSpawnRate;

    private final int eggMaxSpawnRate;

    private final String type;

    public BaseEggBlock(Properties properties, int eggMinSpawnRate, int eggMaxSpawnRate, String type) {
        super(properties);
        this.eggMaxSpawnRate = eggMaxSpawnRate;
        this.eggMinSpawnRate = eggMinSpawnRate;
        this.type = type;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        TileEntity egg = world.getTileEntity(pos);
        if(egg instanceof BaseEggTile){
            Random rand = world.rand;
            int random = getEggMinSpawnRate() + rand.nextInt(getEggMaxSpawnRate() - getEggMinSpawnRate() + 1);
            ((BaseEggTile) egg).setMaxEggTime(random);
            ((BaseEggTile) egg).setEggType(getManager().getTypeFromString("normal"));
            ((BaseEggTile) egg).setControllingPlayer(placer.getUniqueID());
            getManager().getVariables().forEach(variables -> {LOGGER.info(variables.getName());});
        }
    }

    public int getEggMaxSpawnRate() {
        return eggMaxSpawnRate;
    }

    public int getEggMinSpawnRate() {
        return eggMinSpawnRate;
    }

    public String getType() {
        return type;
    }

    public PetVariableManager getManager() {
        return manager;
    }
}
