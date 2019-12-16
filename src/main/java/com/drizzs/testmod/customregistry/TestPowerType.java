package com.drizzs.testmod.customregistry;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import static com.drizzs.testmod.customregistry.TestPowerRegistryEvent.TEST_POWER;

public class TestPowerType extends ForgeRegistryEntry<TestPowerType> {

    private final int colour;

    public TestPowerType(int colour) {
        this.colour = colour;
    }

    public int getPowerColour() {
        return colour;
    }

    public TestPowerType getTypeFromName(String name) {
        TestPowerType testType = null;

        for (RegistryObject<TestPowerType> type : TEST_POWER.getEntries()) {
            if (type.getId().toString().equals(name)) {
                testType = type.get();
            }
        }
        return testType;
    }
}
