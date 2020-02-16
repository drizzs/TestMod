package spectralmc.elepets.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.screen.game_overlay.ScreenGameOverlay;

import java.util.stream.Stream;

/**
 * Key bindings for Elepets.
 *
 * @author phantamanta44
 */
@Mod.EventBusSubscriber(value = { Dist.CLIENT }, modid = ElePets.MOD_ID)
public class ElePetsKeybinds {

    /**
     * The key binding category for Elepets bindings.
     */
    private static final String CAT_ELEPETS = "key.categories.elepets";

    /**
     * The key binding for toggling the party HUD.
     */
    public static final KeyBinding TOGGLE_PARTY_HUD = new KeyBinding("key.elepets.toggle_party_hud",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_I, CAT_ELEPETS);

    /**
     * The key binding for opening the tamer card UI.
     */
    public static final KeyBinding OPEN_CARD_UI = new KeyBinding("key.elepets.open_card_ui",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_U, CAT_ELEPETS);

    /**
     * The key binding for opening the dragondex.
     */
    public static final KeyBinding OPEN_DEX_UI = new KeyBinding("key.elepets.open_dex_ui",
            KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_O, CAT_ELEPETS);

    /**
     * Registers all Elepets key bindings with the {@link ClientRegistry}.
     */
    public static void registerBindings() {
        Stream.of(
                TOGGLE_PARTY_HUD, OPEN_CARD_UI, OPEN_DEX_UI
        ).forEach(ClientRegistry::registerKeyBinding);
    }

    /**
     * Event listener: handles key binding presses.
     *
     * @param event The input event.
     */
    @SubscribeEvent
    public static void onInput(InputEvent event) {
        // TODO handle keybinds
        // keybinds are only meaningful in-game, so ignore them if the client player is null
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (TOGGLE_PARTY_HUD.isPressed()) {
            ScreenGameOverlay gameOverlay = ScreenGameOverlay.INSTANCE;
            gameOverlay.setPartyBarShown(!gameOverlay.isPartyBarShown());
        } else if (OPEN_CARD_UI.isPressed()) {
            player.sendMessage(
                    new StringTextComponent(OPEN_CARD_UI.getLocalizedName() + ": Tamer Card to be implemented soon!")
                            .applyTextStyle(TextFormatting.RED));
        } else if (OPEN_DEX_UI.isPressed()) {
            player.sendMessage(
                    new StringTextComponent(OPEN_DEX_UI.getLocalizedName() + ": DragonDex to be implemented soon!")
                            .applyTextStyle(TextFormatting.RED));
        }
    }

}
