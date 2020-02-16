package spectralmc.elepets.client.ui.util.drawable;

import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * Represents an object that can be drawn onto a UI.
 *
 * @author phantamanta44
 */
public interface UiDrawable {

    /**
     * Draws this onto a UI.
     *
     * @param x      The x-coordinate to draw at.
     * @param y      The y-coordinate to draw at.
     * @param width  The width of the region to draw in.
     * @param height The height of the region to draw in.
     */
    default void draw(double x, double y, double width, double height) {
        drawPartial(x, y, width, height, 0F, 0F, 1F, 1F);
    }

    /**
     * Draws this onto a UI.
     *
     * @param pos  The position to draw at.
     * @param dims The dimensions of the region to draw in.
     */
    default void draw(PlanarPoint pos, PlanarRect dims) {
        draw(pos.getX(), pos.getY(), dims.getWidth(), dims.getHeight());
    }

    /**
     * Draws part of this onto a UI.
     *
     * @param x      The x-coordinate to draw at.
     * @param y      The y-coordinate to draw at.
     * @param width  The width of the region to draw in.
     * @param height The height of the region to draw in.
     * @param x1     The fraction to start drawing at horizontally.
     * @param y1     The fraction to start drawing at vertically.
     * @param x2     The fraction to end drawing at horizontally.
     * @param y2     The fraction to end drawing at vertically.
     */
    void drawPartial(double x, double y, double width, double height, float x1, float y1, float x2, float y2);

    /**
     * Draws part of this onto a UI.
     *
     * @param pos  The position to draw at.
     * @param dims The dimensions of the region to draw in.
     * @param x1   The fraction to start drawing at horizontally.
     * @param y1   The fraction to start drawing at vertically.
     * @param x2   The fraction to end drawing at horizontally.
     * @param y2   The fraction to end drawing at vertically.
     */
    default void drawPartial(PlanarPoint pos, PlanarRect dims, float x1, float y1, float x2, float y2) {
        drawPartial(pos.getX(), pos.getY(), dims.getWidth(), dims.getHeight(), x1, y1, x2, y2);
    }

}
