package spectralmc.elepets.client.ui.component.impl;

import spectralmc.elepets.client.ui.component.AbstractDimensionableComponent;
import spectralmc.elepets.client.ui.util.drawable.UiDrawable;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.dimension.DimensionerAspectRatio;

/**
 * A dimensionable component that displays some drawable object as an image.
 * It is recommended that this component be used with an aspect-ratio-conserving dimensioning policy.
 *
 * @author phantamanta44
 * @see DimensionerAspectRatio
 */
public class ComponentImage extends AbstractDimensionableComponent {

    /**
     * The image to be displayed.
     */
    private final UiDrawable image;

    /**
     * Creates a new image component.
     *
     * @param image The image to display.
     */
    public ComponentImage(UiDrawable image) {
        this.image = image;
    }

    @Override
    public void renderComponent(FocusContext focusCtx, float partialTicks) {
        image.draw(PlanarPoint.ORIGIN, dimensions);
    }

}
