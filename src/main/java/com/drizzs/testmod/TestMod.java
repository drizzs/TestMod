package com.drizzs.testmod;

import com.drizzs.testmod.content.Blocks;
import com.drizzs.testmod.content.Items;
import com.drizzs.testmod.world.TestWorldType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.drizzs.testmod.TestMod.MOD_ID;


@Mod(MOD_ID)
public class TestMod
{

    public static TestMod instance;

    public static final String MOD_ID = "testmod";
    public static WorldType TESTWORLDTYPE = new TestWorldType();
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public TestMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        //Loads your Deferred Registry through ModEventBus
        Blocks.register(bus);
        Items.register(bus);
    }



}
