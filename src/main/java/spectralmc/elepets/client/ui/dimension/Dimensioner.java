package spectralmc.elepets.client.ui.dimension;

import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.component.UiComponent;

/**
 * Represents a policy for dimensioning a UI component.
 *
 * @author phantamanta44
 * @see UiComponent
 */
public interface Dimensioner {

    /**
     * Requests dimensions for a component within the bounds of an enclosing region.
     *
     * @param region The enclosing region's dimensions.
     * @return The preferred dimensions for the component within the region.
     */
    PlanarRect requestDimensions(PlanarRect region);

}
