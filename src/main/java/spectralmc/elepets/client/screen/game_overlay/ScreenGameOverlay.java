package spectralmc.elepets.client.screen.game_overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.ElePetsKeybinds;
import spectralmc.elepets.client.ui.LayoutManager;
import spectralmc.elepets.client.ui.dimension.DimensionerFractional;
import spectralmc.elepets.client.ui.layout.impl.LayoutAnchored;
import spectralmc.elepets.client.ui.layout.impl.LayoutLineup;
import spectralmc.elepets.client.ui.layout.impl.LayoutPadded;
import spectralmc.elepets.client.ui.layout.impl.LayoutTransforming;
import spectralmc.elepets.client.ui.util.animation.AnimatedFloat;
import spectralmc.elepets.client.ui.util.animation.InterpolationFunction;
import spectralmc.elepets.client.ui.util.drawable.UiDrawable;
import spectralmc.elepets.client.ui.util.drawable.UiTexture;
import spectralmc.elepets.client.ui.util.geometry.PlanarAxis;
import spectralmc.elepets.client.ui.util.policy.AlignmentPolicy;
import spectralmc.elepets.client.ui.util.policy.JustificationPolicy;
import spectralmc.elepets.mock.PartyMember;

/**
 * The game overlay.
 * Not strictly a screen, but it's close enough.
 *
 * @author phantamanta44
 */
public class ScreenGameOverlay {

    /**
     * The spritesheet for the hotkey bard.
     */
    private static final UiTexture TEX_HOTKEY_BAR
            = new UiTexture(new ResourceLocation(ElePets.MOD_ID, "textures/gui/hud/hotkey_bar.png"), 216, 72);

    /**
     * The hotkey icon for the "open party menu" hotkey.
     */
    private static final UiDrawable SPR_HOTKEY_BAR_PARTY = TEX_HOTKEY_BAR.getRegion(0, 0, 72, 72);

    /**
     * The hotkey icon for the "open tamer card" hotkey.
     */
    private static final UiDrawable SPR_HOTKEY_BAR_CARD = TEX_HOTKEY_BAR.getRegion(72, 0, 72, 72);

    /**
     * The hotkey icon for the "open dragondex" hotkey.
     */
    private static final UiDrawable SPR_HOTKEY_BAR_DEX = TEX_HOTKEY_BAR.getRegion(144, 0, 72, 72);

    /**
     * The singleton instanceof {@link ScreenGameOverlay}.
     */
    public static final ScreenGameOverlay INSTANCE = new ScreenGameOverlay();

    /**
     * Whether the party bar is shown or not.
     */
    private boolean partyBarShown = true;

    /**
     * The animated value for the horizontal position on the screen.
     * Used to hide the party bar when it's toggled off.
     */
    private final AnimatedFloat partyBarOffset = new AnimatedFloat(0, InterpolationFunction.SINE);

    /**
     * The layout manager handling the in-game HUD.
     */
    private LayoutManager layoutManager;

    /**
     * Initializes the in-game overlay.
     */
    private ScreenGameOverlay() {
        rebuildLayout();
    }

    /**
     * Draws the Elepets HUD.
     *
     * @param window       The game window.
     * @param partialTicks The client partial ticks.
     */
    public void render(MainWindow window, float partialTicks) {
        layoutManager.layOutAndRender(window.getScaledWidth(), window.getScaledHeight(), partialTicks);
    }

    /**
     * Sets the visibility state of the party bar.
     *
     * @param shown Whether the party bar should be shown or not.
     */
    public void setPartyBarShown(boolean shown) {
        this.partyBarShown = shown;
        if (shown) {
            partyBarOffset.animateToNow(0F, 600L);
        } else {
            partyBarOffset.animateToNow(-1F, 600L);
        }
    }

    /**
     * Checks whether the party bar is currently shown or not.
     *
     * @return The visibility of the party bar.
     */
    public boolean isPartyBarShown() {
        return partyBarShown;
    }

    /**
     * Rebuilds the entire HUD layout.
     */
    private void rebuildLayout() {
        // root layout
        LayoutAnchored lRoot = new LayoutAnchored();

        // party bar
        LayoutLineup lParty = new LayoutLineup(PlanarAxis.Y)
                .setJustificationPolicy(JustificationPolicy.SPACE_AROUND)
                .setAlignmentPolicy(AlignmentPolicy.BEGINNING);
        lParty.setDimensioningPolicy(new DimensionerFractional(0.5F, 0.55F));
        for (int i = 0; i < PartyMember.SAMPLE.size(); i++) {
            int index = i;
            lParty.addChild(new ComponentPartyMember(() -> PartyMember.SAMPLE.get(index), index));
        }
        LayoutTransforming lPartyCont = new LayoutTransforming(lParty, focusCtx -> {
            ComponentPartyMember partyMember = (ComponentPartyMember)lParty.streamChildren()
                    .findFirst().orElseThrow(IllegalStateException::new).getComponent();
            RenderSystem.translatef(partyBarOffset.getValueNow() * partyMember.getDimensions().getWidth() * 2F, 0F, 0F);
        });
        LayoutPadded lPartyPadded = new LayoutPadded(lPartyCont, 0.01F, 0F, 0F, 0F, true);
        lRoot.addChild(lPartyPadded, AlignmentPolicy.BEGINNING, AlignmentPolicy.MIDDLE);

        // hotkey bar
        LayoutLineup lHotkeys = new LayoutLineup(PlanarAxis.X)
                .setJustificationPolicy(JustificationPolicy.END);
        lHotkeys.setDimensioningPolicy(new DimensionerFractional(0.5F, 0.075F));
        lHotkeys.addChild(new ComponentHotkeyIcon(SPR_HOTKEY_BAR_PARTY, ElePetsKeybinds.TOGGLE_PARTY_HUD));
        lHotkeys.addChild(new ComponentHotkeyIcon(SPR_HOTKEY_BAR_CARD, ElePetsKeybinds.OPEN_CARD_UI));
        lHotkeys.addChild(new ComponentHotkeyIcon(SPR_HOTKEY_BAR_DEX, ElePetsKeybinds.OPEN_DEX_UI));
        LayoutTransforming lHotkeysCont = new LayoutTransforming(lHotkeys, focusCtx ->
                RenderSystem.translatef(0F, -partyBarOffset.getValueNow() * (8 + lHotkeys.getDimensions().getHeight()), 0F));
        LayoutPadded lHotkeysPadded = new LayoutPadded(lHotkeysCont, 0, 0, 8, 8, false);
        lRoot.addChild(lHotkeysPadded, AlignmentPolicy.END, AlignmentPolicy.END);

        // done!
        this.layoutManager = new LayoutManager(lRoot);
    }

}
