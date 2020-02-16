package spectralmc.elepets.client.screen.game_overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.ui.component.AbstractComponent;
import spectralmc.elepets.client.ui.dimension.Dimensioner;
import spectralmc.elepets.client.ui.dimension.DimensionerAspectRatio;
import spectralmc.elepets.client.ui.util.drawable.UiDrawable;
import spectralmc.elepets.client.ui.util.drawable.UiTexture;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.mock.PartyMember;
import spectralmc.elepets.util.render.ColourUtils;

import java.util.function.Supplier;

/**
 * A party member crystal on the in-game HUD.
 *
 * @author phantamanta44
 */
public class ComponentPartyMember extends AbstractComponent {

    /**
     * The distance, as a percentage of width, by which alternating crystals should be offset horizontally.
     */
    private static final float OVERLAP_X = 0.2F;

    /**
     * The distance, as a percentage of height, by which crystals should overlap vertically.
     */
    private static final float OVERLAP_Y = 0.15F;

    /**
     * The dimensioning policy for party member crystals.
     */
    private static final Dimensioner DIM_POLICY
            = new DimensionerAspectRatio(new PlanarRect(48, Math.round(80F * (1F - OVERLAP_Y * 2F))));

    /**
     * The spritesheet texture for party member crystals.
     */
    private static final UiTexture TEX_PARTY_CRYSTAL
            = new UiTexture(new ResourceLocation(ElePets.MOD_ID, "textures/gui/hud/party_crystal.png"), 96, 80);

    /**
     * The sprite for the party member crystal outline.
     */
    private static final UiDrawable SPR_PARTY_CRYSTAL_OUTLINE = TEX_PARTY_CRYSTAL.getRegion(0, 0, 48, 80);

    /**
     * The sprite for the party member crystal glowing effect layer.
     */
    private static final UiDrawable SPR_PARTY_CRYSTAL_GLOW = TEX_PARTY_CRYSTAL.getRegion(48, 0, 48, 80);

    /**
     * A function for retrieving the party member in this crystal's slot.
     */
    private final Supplier<PartyMember> partyMemberGetter;

    /**
     * Whether the crystal should be offset from the center line or not.
     */
    private final boolean doOffsetX;

    /**
     * Creates a new party member crystal for the given party slot.
     *
     * @param partyMemberGetter A getter function for retrieving the party member in this crystal's slot.
     * @param index             The index of this crystal in the party member list.
     */
    public ComponentPartyMember(Supplier<PartyMember> partyMemberGetter, int index) {
        this.partyMemberGetter = partyMemberGetter;
        this.doOffsetX = index % 2 == 0;
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        return dimensions = DIM_POLICY.requestDimensions(region);
    }

    @Override
    public void renderComponent(FocusContext focusCtx, float partialTicks) {
        int width = dimensions.getWidth(), height = Math.round(dimensions.getHeight() / (1F - OVERLAP_Y * 2F));
        float offsetX = doOffsetX ? (OVERLAP_X * width * 2F) : 0F, offsetY = -OVERLAP_Y * height;
        PartyMember member = partyMemberGetter.get();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
        RenderSystem.disableAlphaTest();
        if (member != null) {
            member.crystalSprite.draw(offsetX, offsetY, width, height);
            ColourUtils.setGlColour(member.typeColour);
            SPR_PARTY_CRYSTAL_GLOW.draw(offsetX, offsetY, width, height);
            RenderSystem.color4f(1F, 1F, 1F, 1F);
        }
        SPR_PARTY_CRYSTAL_OUTLINE.draw(offsetX, offsetY, width, height);
        RenderSystem.enableAlphaTest();
    }

}
