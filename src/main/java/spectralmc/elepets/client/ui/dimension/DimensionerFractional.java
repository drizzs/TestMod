package spectralmc.elepets.client.ui.dimension;

import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * A dimensioning policy that expands components to fill a fraction of the parent region.
 *
 * @author phantamanta44
 */
public class DimensionerFractional implements Dimensioner {

    /**
     * A dimensioning policy dictating that a component fills the entirety of its available space.
     */
    public static final DimensionerFractional FILL = new DimensionerFractional(1F, 1F);

    /**
     * The scaling factor on the x-axis.
     */
    private final float scaleX;

    /**
     * The scaling factor on the y-axis.
     */
    private final float scaleY;

    /**
     * Creates a new dimensioning policy that fills a given fraction of its parent.
     *
     * @param scaleX The fraction on the x-axis.
     * @param scaleY The fraction on the y-axis.
     */
    public DimensionerFractional(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public PlanarRect requestDimensions(PlanarRect region) {
        return region.scale(scaleX, scaleY);
    }

}
