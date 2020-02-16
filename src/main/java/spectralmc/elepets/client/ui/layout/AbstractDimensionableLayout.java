package spectralmc.elepets.client.ui.layout;

import spectralmc.elepets.client.ui.dimension.Dimensioner;
import spectralmc.elepets.client.ui.dimension.DimensionerFractional;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * A basic implementation of {@link UiLayout} that supports setting custom dimensioning policies.
 *
 * @author phantamanta44
 */
public abstract class AbstractDimensionableLayout extends AbstractLayout {

    /**
     * The dimensioning policy used by this layout. Defaults to {@link DimensionerFractional#FILL}.
     */
    private Dimensioner dimensioningPolicy = DimensionerFractional.FILL;

    /**
     * Retrieves the dimensioning policy used by this layout.
     *
     * @return The dimensioning policy.
     */
    public Dimensioner getDimensioningPolicy() {
        return dimensioningPolicy;
    }

    /**
     * Sets the dimensioning policy used by this layout.
     *
     * @param dimensioningPolicy The dimensioning policy to use.
     */
    public void setDimensioningPolicy(Dimensioner dimensioningPolicy) {
        this.dimensioningPolicy = dimensioningPolicy;
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        dimensions = dimensioningPolicy.requestDimensions(region);
        return dimensions;
    }

}
