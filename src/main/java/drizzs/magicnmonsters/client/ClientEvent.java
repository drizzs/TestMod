package drizzs.magicnmonsters.client;

import drizzs.magicnmonsters.client.overlay.MagicPowerOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static drizzs.magicnmonsters.MagicMon.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MOD_ID)
public class ClientEvent {

    @SubscribeEvent
    public static void onRenderHud(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            MagicPowerOverlay.OVERLAY.buildOverlay();
        }
    }

}
