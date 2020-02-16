package spectralmc.elepets.util.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;

/**
 * Helper functions for dispatching simple 2D renders into a UI.
 *
 * @author phantamanta44
 */
public class GuiRenders {

    /**
     * Draws the outline of a rectangle.
     *
     * @param x1        The left x-coordinate.
     * @param y1        The top y-coordinate.
     * @param x2        The right x-coordinate.
     * @param y2        The bottom y-coordinate.
     * @param lineWidth The width of the outline.
     * @param colour    The colour of the outline in ARGB.
     */
    public static void strokeRect(int x1, int y1, int x2, int y2, int lineWidth, int colour) {
        AbstractGui.fill(x1, y1, x2, y1 + lineWidth, colour);
        AbstractGui.fill(x1, y1, x1 + lineWidth, y2, colour);
        AbstractGui.fill(x2 - lineWidth, y1, x2, y2, colour);
        AbstractGui.fill(x1, y2 - lineWidth, x2, y2, colour);
        RenderSystem.enableBlend();
    }

}
