package spectralmc.elepets.client.screen.game_overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import spectralmc.elepets.client.ui.component.AbstractComponent;
import spectralmc.elepets.client.ui.dimension.Dimensioner;
import spectralmc.elepets.client.ui.dimension.DimensionerAspectRatio;
import spectralmc.elepets.client.ui.util.drawable.UiDrawable;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A component that displays an icon labeled with a hotkey.
 *
 * @author phantamanta44
 */
public class ComponentHotkeyIcon extends AbstractComponent {

    /**
     * A pattern for extracting the first character of a word in a string.
     */
    private static final Pattern KEY_NAME_PAT = Pattern.compile("(?:^|\\s+)(\\S\\d*)");

    /**
     * The dimensioning policy for hotkey icons.
     */
    private static final Dimensioner DIM_POLICY = new DimensionerAspectRatio(new PlanarRect(72, 72));

    /**
     * The icon to draw.
     */
    private final UiDrawable icon;

    /**
     * The hotkey with which to label the icon.
     */
    private final KeyBinding hotkey;

    /**
     * Creates a new hotkey icon with the given icon image and hotkey.
     *
     * @param icon   The icon to display.
     * @param hotkey The hotkey to display.
     */
    public ComponentHotkeyIcon(UiDrawable icon, KeyBinding hotkey) {
        this.icon = icon;
        this.hotkey = hotkey;
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        return dimensions = DIM_POLICY.requestDimensions(region);
    }

    @Override
    public void renderComponent(FocusContext focusCtx, float partialTicks) {
        RenderSystem.enableBlend();
        // draw the icon
        icon.draw(PlanarPoint.ORIGIN, dimensions);
        // parse hotkey name
        Matcher hotkeyNameMatcher = KEY_NAME_PAT.matcher(hotkey.getLocalizedName());
        StringBuilder hotkeyNameBuilder = new StringBuilder();
        while (hotkeyNameMatcher.find()) {
            hotkeyNameBuilder.append(hotkeyNameMatcher.group(1).toUpperCase());
        }
        String hotkeyName = hotkeyNameBuilder.toString();
        // draw hotkey name, scaled by icon size
        FontRenderer fr = Minecraft.getInstance().fontRenderer;
        RenderSystem.pushMatrix();
        float textScale = dimensions.getHeight() / 24F;
        RenderSystem.translatef(
                dimensions.getWidth() - fr.getStringWidth(hotkeyName) * textScale,
                dimensions.getHeight() - fr.FONT_HEIGHT * textScale,
                0F);
        RenderSystem.scalef(textScale, textScale, 1F);
        fr.drawStringWithShadow(hotkeyName, 0F, 0F, 0xFFFFFF);
        RenderSystem.popMatrix();
    }

}
