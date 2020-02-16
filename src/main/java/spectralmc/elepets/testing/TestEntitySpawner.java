package spectralmc.elepets.testing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spectralmc.elepets.api.managers.PetManager;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.PetBase;
import spectralmc.elepets.api.pet.StatBase;
import spectralmc.elepets.common.entity.PetEntity;

import static spectralmc.elepets.ElePets.ITEM_GROUP;
import static spectralmc.elepets.common.content.ElePetsEntityRegistry.TEST_PET;

public class TestEntitySpawner extends Item {

    private final PetManager manager = new PetManager();
    private final PetVariableManager variableManager = new PetVariableManager();

    public TestEntitySpawner() {
        super(new Item.Properties().maxStackSize(16).group(ITEM_GROUP));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos().up();
        StatBase petBase = manager.getPetFromString("normal1");
        if (petBase != null) {
            PetEntity entity = new PetEntity(TEST_PET.get(), world);
            entity.setPetBase(petBase);
            entity.getPetBase().setMaturity(variableManager.getMaturityFromString("adult"));
            entity.getPetBase().setPetGender(variableManager.getGenderFromString("female"));
            entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
            entity.setControllingPlayer(player);
            world.addEntity(entity);

            if (!world.isRemote) {
                world.addEntity(entity);
            }
        }
        return ActionResultType.SUCCESS;
    }

}
