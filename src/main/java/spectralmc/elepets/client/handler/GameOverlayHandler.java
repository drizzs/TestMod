package spectralmc.elepets.client.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.screen.game_overlay.ScreenGameOverlay;

/**
 * Event handler that handles drawing the in-game HUD for Elepets.
 *
 * @author phantamanta44
 */
@Mod.EventBusSubscriber(value = { Dist.CLIENT }, modid = ElePets.MOD_ID)
public class GameOverlayHandler {

    /**
     * Event listener: handles the in-game overlay rendering event.
     * If the event is for drawing text, then the Elepets HUD is drawn.
     *
     * @param event The rendering event.
     */
    @SubscribeEvent
    public static void onRenderHud(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            ScreenGameOverlay.INSTANCE.render(event.getWindow(), event.getPartialTicks());
        }
    }

}
