package com.drizzs.testmod.blocks;

import com.drizzs.testmod.blocks.crucible.CrucibleTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TileEntityRegistry {


    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        event.getRegistry().register(TileEntityType.Builder.create(CrucibleTile:: new, TileEntities.CRUCIBLE).build(null).setRegistryName("crucible"));

    }
}
