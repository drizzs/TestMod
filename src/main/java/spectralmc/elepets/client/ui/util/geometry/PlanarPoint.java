package spectralmc.elepets.client.ui.util.geometry;

import spectralmc.elepets.util.math.HashUtils;

/**
 * Represents a point positioned on a plane.
 * Note that this point does not make any specification about which coordinate system it lies in.
 *
 * @author phantamanta44
 */
public class PlanarPoint {

    /**
     * A planar point placed at the origin.
     */
    public static final PlanarPoint ORIGIN = new PlanarPoint(0, 0);

    /**
     * The x-coordinate of this point.
     */
    private final int x;

    /**
     * The y-coordinate of this point.
     */
    private final int y;

    /**
     * Creates a new point at a given position on the plane.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public PlanarPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the x-coordinate of this point.
     *
     * @return The point's x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of this point.
     *
     * @return The point's y-coordinate.
     */
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return HashUtils.hashIntPair(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlanarPoint)) {
            return false;
        }
        PlanarPoint o = (PlanarPoint)obj;
        return x == o.x && y == o.y;
    }

    @Override
    public String toString() {
        return String.format("<%d,%d>", x, y);
    }

}
