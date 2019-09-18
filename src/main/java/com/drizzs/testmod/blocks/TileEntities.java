package com.drizzs.testmod.blocks;

import com.drizzs.testmod.blocks.crucible.CrucibleBlock;
import com.drizzs.testmod.blocks.crucible.CrucibleTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntities {

    @ObjectHolder("testmod:crucible")
    public static CrucibleBlock CRUCIBLE;

    @ObjectHolder("testmod:crucible")
    public static TileEntityType<CrucibleTile> CRUCIBLETILE;

}
