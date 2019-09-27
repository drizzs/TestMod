package com.drizzs.testmod.world.surfacebuilders;

import com.drizzs.testmod.TestMod;
import com.drizzs.testmod.world.surfacebuilders.overworld.TestSurface;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder("testmod")
public class SurfaceBuilderRegistry
{

    public static final SurfaceBuilder<SurfaceBuilderConfig> testsurface = new TestSurface();

    private static IForgeRegistry<SurfaceBuilder<?>> registry;

    @SubscribeEvent
    public static void onRegisterSurface(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        if (registry == null) {
            registry = event.getRegistry();
        }
        registerSurfaceBuilder(testsurface, "testsurface");

    }


    public static SurfaceBuilder<?> registerSurfaceBuilder(SurfaceBuilder<?> surface, String name) {
        if (registry == null) {
            throw new NullPointerException("surface registry aint workin!");
        } else {
            surface.setRegistryName("testmod", name);
            registry.register(surface);
            return surface;
        }
    }


}
