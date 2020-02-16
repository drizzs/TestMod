package spectralmc.elepets.client.ui.dimension;

import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.policy.AxialPolicy;

/**
 * A dimensioning policy that imposes exact dimensions on a component.
 *
 * @author phantamanta44
 */
public class DimensionerExact implements Dimensioner {

    /**
     * The exact dimensions imposed by this policy.
     */
    private final PlanarRect dimensions;

    /**
     * The policy dictating the ability of this policy's dimensions to shrink to fit a container.
     */
    private final AxialPolicy shrinkPolicy;

    /**
     * Creates a new exact dimensioning policy with the given dimensions.
     *
     * @param dimensions   The exact dimensions imposed by the policy.
     * @param shrinkPolicy A policy dictating which axes are allowed to shrink to fit a smaller container.
     */
    public DimensionerExact(PlanarRect dimensions, AxialPolicy shrinkPolicy) {
        this.dimensions = dimensions;
        this.shrinkPolicy = shrinkPolicy;
    }

    @Override
    public PlanarRect requestDimensions(PlanarRect region) {
        return dimensions.contractTo(region, shrinkPolicy);
    }

}
