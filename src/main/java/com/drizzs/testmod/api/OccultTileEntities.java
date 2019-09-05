package com.drizzs.testmod.api;

import com.drizzs.testmod.block.tile.RitualFire;
import com.drizzs.testmod.tileentity.RitualFireTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class OccultTileEntities
{
    @ObjectHolder("occult:ritualfire")
    public static RitualFire RITUALFIRE;

    @ObjectHolder("occult:ritualfire")
    public static TileEntityType<RitualFireTileEntity> RITUALTILE;


}
