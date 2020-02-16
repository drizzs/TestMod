package spectralmc.elepets.api.serializer;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import spectralmc.elepets.api.ElePetsRegistries;
import spectralmc.elepets.api.pet.PetBase;
import spectralmc.elepets.api.pet.StatBase;
import spectralmc.elepets.api.pet.diet.DietSerializer;
import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.gender.GenderSerializer;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.MaturitySerializer;
import spectralmc.elepets.api.pet.maturity.PetMaturity;
import spectralmc.elepets.api.pet.nature.NatureSerializer;
import spectralmc.elepets.api.pet.nature.PetNature;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.api.pet.type.TypeSerializer;

import static spectralmc.elepets.ElePets.MOD_ID;

public class SerializerRegistryHandler {

    public static final DeferredRegister<IPetSerializable<?>> PET_SERIALIZABLE = new DeferredRegister<>(ElePetsRegistries.PET_SERIALIZABLE_A, MOD_ID);
    public static final DeferredRegister<VariableType<?>> PET_VARIABLE = new DeferredRegister<>(ElePetsRegistries.PET_VARIABLES_A, MOD_ID);
    public static final DeferredRegister<IPetSerializer<?>> PET_SERIALIZER = new DeferredRegister<>(ElePetsRegistries.PET_SERIALIZABLE_B, MOD_ID);
    public static final DeferredRegister<PetSerializerType<?>> PET_TYPE = new DeferredRegister<>(ElePetsRegistries.PET_VARIABLES_B, MOD_ID);

    public static final RegistryObject<IPetSerializable<PetDiet>> DIET_SERIALIZER = PET_SERIALIZABLE.register("diet_serializer", DietSerializer::new);
    public static final RegistryObject<IPetSerializable<PetGender>> GENDER_SERIALIZER = PET_SERIALIZABLE.register("gender_serializer", GenderSerializer::new);
    public static final RegistryObject<IPetSerializable<PetMaturity>> MATURITY_SERIALIZER = PET_SERIALIZABLE.register("maturity_serializer", MaturitySerializer::new);
    public static final RegistryObject<IPetSerializable<PetNature>> NATURE_SERIALIZER = PET_SERIALIZABLE.register("nature_serializer", NatureSerializer::new);
    public static final RegistryObject<IPetSerializable<PetType>> TYPE_SERIALIZER = PET_SERIALIZABLE.register("type_serializer", TypeSerializer::new);

    public static final RegistryObject<VariableType<PetDiet>> DIET = PET_VARIABLE.register("diet", () -> new VariableType<PetDiet>() {});
    public static final RegistryObject<VariableType<PetGender>> GENDER = PET_VARIABLE.register("gender", () -> new VariableType<PetGender>() {});
    public static final RegistryObject<VariableType<PetMaturity>> MATURITY = PET_VARIABLE.register("maturity", () -> new VariableType<PetMaturity>() {});
    public static final RegistryObject<VariableType<PetNature>> NATURE = PET_VARIABLE.register("nature", () -> new VariableType<PetNature>() {});
    public static final RegistryObject<VariableType<PetType>> TYPE = PET_VARIABLE.register("type", () -> new VariableType<PetType>() {});

    public static final RegistryObject<IPetSerializer<StatBase>> PET_SERIAL = PET_SERIALIZER.register("pet_serial", PetSerializer::new);

    public static final RegistryObject<PetSerializerType<StatBase>> PET = PET_TYPE.register("pet_serial", ()-> new PetSerializerType<StatBase>(){});


    public static void register(IEventBus eventBus) {
        PET_SERIALIZABLE.register(eventBus);
        PET_VARIABLE.register(eventBus);
        PET_SERIALIZER.register(eventBus);
        PET_TYPE.register(eventBus);
    }
}
