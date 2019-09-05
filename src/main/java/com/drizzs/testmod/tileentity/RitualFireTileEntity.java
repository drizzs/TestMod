package com.drizzs.testmod.tileentity;

import com.drizzs.testmod.api.IPressureReceiver;
import com.drizzs.testmod.recipes.RitualFireRecipes;
import com.drizzs.testmod.tileentity.tileinventory.BaseTileInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;

import static com.drizzs.testmod.api.OccultTileEntities.RITUALTILE;

public class RitualFireTileEntity extends BaseTileInventory implements IPressureReceiver, ITickableTileEntity {

    private static final String TAG_RITUALFIRE_SPAWNED = "RitualFireSpawned";

    public static Map<ResourceLocation, RitualFireRecipes> ritualFireRecipes = Collections.emptyMap();

    public int pressureToGet = 0;
    private int pressure = 0;
    private boolean isactive = false;
    private boolean pentacleOn = false;

    public RitualFireTileEntity() {
        super(RITUALTILE);
    }

    public boolean isFireActive(boolean active)
    {
        return isactive;
    }

    @Override
    public int getSizeInventory() {
        return 32;
    }

    @Override
    protected SimpleItemStackHandler createItemHandler() {
        return new SimpleItemStackHandler(this, false) {
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }

    public boolean doesPentacleExist(boolean exist)
    {
        return pentacleOn;
    }

    @Override
    public int getCurrentPressure() {
        return pressure;
    }

    @Override
    public boolean isFull() {
        return pressure >= pressureToGet;
    }

    @Override
    public void receivePressure(int pressure) {
        this.pressure = Math.min(this.pressure + pressure, pressureToGet);
    }

    @Override
    public boolean canReceivePressure() {
        return false;
    }


    public int getTargetPressure() {
        return pressureToGet;
    }

    @Override
    public void tick() {




    }
}
