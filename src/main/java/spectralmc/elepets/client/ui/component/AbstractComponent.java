package spectralmc.elepets.client.ui.component;

import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * A basic implementation of the core functionality of {@link UiComponent}.
 *
 * @author phantamanta44
 */
public abstract class AbstractComponent implements UiComponent {

    /**
     * The current laid-out dimensions of the component, or {@code null} if the component is not yet laid-out.
     */
    protected PlanarRect dimensions = null;

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        dimensions = region;
        return dimensions;
    }

    @Override
    public PlanarRect getDimensions() {
        NotLaidOutException.ensure(dimensions != null, this);
        return dimensions;
    }

}
