package spectralmc.elepets.client.ui.util.geometry;

/**
 * Represents an axis on a plane.
 *
 * @author phantamanta44
 */
public enum PlanarAxis {

    /**
     * The horizontal axis.
     */
    X(1, 0),

    /**
     * The vertical axis.
     */
    Y(0, 1);

    /**
     * The x-component of a unit basis vector along this axis.
     */
    public final int x;

    /**
     * The y-component of a unit basis vector along this axis.
     */
    public final int y;

    /**
     * Creates a new axis with the given unit basis vector.
     *
     * @param x The x-component of the basis vector.
     * @param y The y-component of the basis vector.
     */
    PlanarAxis(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the opposite planar axis from this one.
     *
     * @return The opposite axis.
     */
    public PlanarAxis getOpposite() {
        switch (this) {
            case X:
                return Y;
            case Y:
                return X;
        }
        throw new IllegalStateException();
    }

}
