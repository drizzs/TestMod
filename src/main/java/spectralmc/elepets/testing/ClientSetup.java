package spectralmc.elepets.testing;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import spectralmc.elepets.common.content.ElePetsEntityRegistry;
import spectralmc.elepets.testing.renderer.TestRenderer;

import static spectralmc.elepets.ElePets.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ElePetsEntityRegistry.TEST_PET.get(), TestRenderer::new);
    }

}
