package spectralmc.elepets.client.ui.dimension;

import com.google.common.base.Preconditions;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * A dimensioning policy that allows component to grow or shrink while maintaining an aspect ratio.
 *
 * @author phantamanta44
 */
public class DimensionerAspectRatio implements Dimensioner {

    /**
     * The rectangle defining this dimensioning policy's fixed aspect ratio.
     */
    private final PlanarRect ratio;

    /**
     * Creates a new aspect-ratio dimensioning policy.
     *
     * @param ratio A rectangle defining the aspect ratio.
     * @throws IllegalArgumentException If the given rectangle has zero volume.
     */
    public DimensionerAspectRatio(PlanarRect ratio) {
        Preconditions.checkArgument(ratio.computeVolume() > 0, "Aspect ratio rectangle must have nonzero volume!");
        this.ratio = ratio;
    }

    @Override
    public PlanarRect requestDimensions(PlanarRect region) {
        // find the bounding dimension and scale to it
        return ratio.scale(Math.min(
                region.getWidth() / (float)ratio.getWidth(), region.getHeight() / (float)ratio.getHeight()));
    }

}
