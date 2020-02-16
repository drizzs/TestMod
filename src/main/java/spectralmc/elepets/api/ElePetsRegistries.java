package spectralmc.elepets.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.IPetSerializer;
import spectralmc.elepets.api.serializer.PetSerializerType;
import spectralmc.elepets.api.serializer.VariableType;

import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsRegistries {

    static {
        init();
    }

    public static IForgeRegistry<IPetSerializable<?>> PET_SERIALIZABLE_A = RegistryManager.ACTIVE.getRegistry(IPetSerializable.class);
    public static IForgeRegistry<VariableType<?>> PET_VARIABLES_A = RegistryManager.ACTIVE.getRegistry(VariableType.class);
    public static IForgeRegistry<IPetSerializer<?>> PET_SERIALIZABLE_B = RegistryManager.ACTIVE.getRegistry(IPetSerializer.class);
    public static IForgeRegistry<PetSerializerType<?>> PET_VARIABLES_B = RegistryManager.ACTIVE.getRegistry(PetSerializerType.class);

    @SuppressWarnings("unchecked")
    private static void init() {

        makeRegistry(new ResourceLocation(MOD_ID, "1_variableserializer"), IPetSerializable.class).create();
        makeRegistry(new ResourceLocation(MOD_ID, "2_petvariables"), VariableType.class).create();
        makeRegistry(new ResourceLocation(MOD_ID, "3_petserializer"), IPetSerializer.class).create();
        makeRegistry(new ResourceLocation(MOD_ID, "4_pet"), PetSerializerType.class).create();

    }

    private static <T extends IForgeRegistryEntry<T>> RegistryBuilder<T> makeRegistry(ResourceLocation name, Class<T> type) {
        return new RegistryBuilder<T>()
                .setName(name)
                .setType(type);
    }

}
