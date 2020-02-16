package spectralmc.elepets.common.content;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import spectralmc.elepets.common.items.CrystalItem;
import spectralmc.elepets.testing.TestEntitySpawner;
import spectralmc.elepets.testing.TestEntitySpawner2;

import static spectralmc.elepets.ElePets.ITEM_GROUP;
import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsItems {
    public static final DeferredRegister<Item> ELEPETS_ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> CAPTURE_CRYSTAL_EMPTY = ELEPETS_ITEMS.register("empty_crystal", () -> new Item(new Item.Properties().maxStackSize(16).group(ITEM_GROUP)));
    public static final RegistryObject<Item> CAPTURE_CRYSTAL_FILLED = ELEPETS_ITEMS.register("filled_crystal", CrystalItem::new);

    public static final RegistryObject<Item> TEST_SPAWNER = ELEPETS_ITEMS.register("test_spawner", TestEntitySpawner::new);
    public static final RegistryObject<Item> TEST_SPAWNER1 = ELEPETS_ITEMS.register("test_spawner2", TestEntitySpawner2::new);

    public static void register(IEventBus eventBus) {
        ELEPETS_ITEMS.register(eventBus);
    }

}
