package spectralmc.elepets.client.ui.util.geometry;

import com.google.common.base.Preconditions;
import spectralmc.elepets.client.ui.util.policy.AxialPolicy;
import spectralmc.elepets.util.math.HashUtils;

/**
 * Represents a rectangle on the plane.
 * The rectangle only has width and height, and is not positioned.
 *
 * @author phantamanta44
 */
public class PlanarRect {

    /**
     * A zero-by-zero rectangle.
     */
    public static final PlanarRect ZERO = new PlanarRect(0, 0);

    /**
     * An infinitely (or reasonably close to it) large rectangle.
     */
    public static final PlanarRect INFINITE = new PlanarRect(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * The width of the rectangle.
     */
    private final int width;

    /**
     * The height of the rectangle.
     */
    private final int height;

    /**
     * Creates a new rectangle with a given set of dimensions.
     *
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @throws IllegalArgumentException If either given dimension is negative.
     */
    public PlanarRect(int width, int height) {
        Preconditions.checkArgument(width >= 0, "Rectangle width must be non-negative!");
        Preconditions.checkArgument(height >= 0, "Rectangle height must be non-negative!");
        this.width = width;
        this.height = height;
    }

    /**
     * Retrieves the width of the rectangle.
     *
     * @return The rectangle's width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the rectangle.
     *
     * @return The rectangle's height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves the dimension of the rectangle on a given axis.
     *
     * @param axis The axis to get the dimension along.
     * @return The dimension on the axis.
     */
    public int getDimension(PlanarAxis axis) {
        return axis == PlanarAxis.X ? width : height;
    }

    /**
     * Computes the volume of the rectangle.
     *
     * @return The rectangle's volume.
     */
    public int computeVolume() {
        return width * height;
    }

    /**
     * Checks if a point lies in this rectangle, given an origin position for the top-left corner.
     *
     * @param point  The point to test.
     * @param origin The position of the rectangle's top-left corner.
     * @return Whether the point lies in the positioned rectangle or not.
     */
    public boolean contains(PlanarPoint point, PlanarPoint origin) {
        int dx = point.getX() - origin.getX(), dy = point.getY() - origin.getY();
        return dx >= 0 && dx <= width && dy >= 0 && dy <= height;
    }

    /**
     * Checks if a point lies in this rectangle when placed at the origin.
     *
     * @param point The point to test.
     * @return Whether the point lies in this rectangle placed at the origin or not.
     */
    public boolean contains(PlanarPoint point) {
        return contains(point, PlanarPoint.ORIGIN);
    }

    /**
     * Scales this rectangle by a factor along each dimension, rounding results to the nearest integer.
     *
     * @param factorX The horizontal scaling factor.
     * @param factorY The vertical scaling factor.
     * @return A new scaled rectangle.
     * @throws IllegalArgumentException If a scaling factor is negative.
     * @throws IllegalStateException    If the scaled rectangle would have illegal dimensions.
     */
    public PlanarRect scale(float factorX, float factorY) {
        Preconditions.checkArgument(factorX >= 0, "Scaling factors must be non-negative!");
        Preconditions.checkArgument(factorY >= 0, "Scaling factors must be non-negative!");
        return new PlanarRect(Math.round(width * factorX), Math.round(height * factorY));
    }

    /**
     * Scales this rectangle by a factor, rounding each dimension to the nearest integer.
     *
     * @param factor The scaling factor.
     * @return A new scaled rectangle.
     * @throws IllegalArgumentException If the scaling factor is negative.
     * @throws IllegalStateException    If the scaled rectangle would have illegal dimensions.
     */
    public PlanarRect scale(float factor) {
        return scale(factor, factor);
    }

    /**
     * Computes a new rectangle with the dimension along the given axis set to a new amount.
     *
     * @param axis   The axis to resize.
     * @param amount The quantity to set the dimension to.
     * @return A new resized rectangle.
     * @throws IllegalStateException If the resized rectangle would have illegal dimensions.
     */
    public PlanarRect withDimension(PlanarAxis axis, int amount) {
        return axis == PlanarAxis.X ? new PlanarRect(amount, height) : new PlanarRect(width, amount);
    }

    /**
     * Computes a new rectangle with the dimension along the given axis offset by an amount.
     *
     * @param axis   The axis to resize.
     * @param amount The quantity to offset the dimension by.
     * @return A new resized rectangle.
     * @throws IllegalStateException If the resized rectangle would have illegal dimensions.
     */
    public PlanarRect offsetDimension(PlanarAxis axis, int amount) {
        return withDimension(axis, getDimension(axis) + amount);
    }

    /**
     * Computes a new rectangle with each dimension offset by some amount.
     *
     * @param offsetX The horizontal offset.
     * @param offsetY The vertical offset.
     * @return A new resized rectangle.
     * @throws IllegalStateException If the resized rectangle would have illegal dimensions.
     */
    public PlanarRect offsetDimensions(int offsetX, int offsetY) {
        return new PlanarRect(width + offsetX, height + offsetY);
    }

    /**
     * Computes a new rectangle by scaling downwards to fit an enclosing rectangle.
     * Note that the computed rectangle may not fit within the enclosing rectangle if the given scaling policy is not
     * sufficiently flexible to allow it.
     *
     * @param enclosing   The enclosing rectangle to contract to.
     * @param scalePolicy The scaling policy for the contraction.
     * @return A new contracted rectangle.
     * @see AxialPolicy
     */
    public PlanarRect contractTo(PlanarRect enclosing, AxialPolicy scalePolicy) {
        return new PlanarRect(
                scalePolicy.permitsX ? Math.min(width, enclosing.width) : width,
                scalePolicy.permitsY ? Math.min(height, enclosing.height) : height);
    }

    /**
     * Computes a new rectangle by scaling upwards to fill an enclosing rectangle.
     * Note that the computed rectangle may not completely cover the enclosing rectangle if the given scaling policy is
     * not sufficiently flexible to allow it.
     *
     * @param enclosing   The enclosing rectangle to expand to.
     * @param scalePolicy The scaling policy for the expansion.
     * @return A new expanded rectangle.
     * @see AxialPolicy
     */
    public PlanarRect expandTo(PlanarRect enclosing, AxialPolicy scalePolicy) {
        return new PlanarRect(
                scalePolicy.permitsX ? Math.max(width, enclosing.width) : width,
                scalePolicy.permitsY ? Math.max(height, enclosing.height) : height);
    }

    @Override
    public int hashCode() {
        return HashUtils.hashIntPair(width, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlanarRect)) {
            return false;
        }
        PlanarRect o = (PlanarRect)obj;
        return width == o.width && height == o.height;
    }

    @Override
    public String toString() {
        return String.format("[%d x %d]", width, height);
    }

}
