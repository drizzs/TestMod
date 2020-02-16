package spectralmc.elepets.common.content;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import spectralmc.elepets.common.entity.PetEntity;

import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsEntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = new DeferredRegister<>(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<PetEntity>> TEST_PET = ENTITY_TYPE.register("test_pet", () -> EntityType.Builder.create(PetEntity::new, EntityClassification.CREATURE).size(1, 1).setShouldReceiveVelocityUpdates(false).build("test_pet"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}
