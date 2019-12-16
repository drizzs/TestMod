package com.drizzs.testmod.customregistry;

import com.drizzs.testmod.customregistry.powertypes.SuperPowerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import static com.drizzs.testmod.TestMod.MOD_ID;

public class TestPowerRegistryEvent {

    public static final DeferredRegister<TestPowerType> TEST_POWER = new DeferredRegister<>(TestPowerRegistry.TESTPOWER, MOD_ID);

    public static final RegistryObject<TestPowerType> SUPER_POWER = TEST_POWER.register("super_power",
            SuperPowerType::new);

    public static void register(IEventBus eventBus) {
        TEST_POWER.register(eventBus);
    }

}
