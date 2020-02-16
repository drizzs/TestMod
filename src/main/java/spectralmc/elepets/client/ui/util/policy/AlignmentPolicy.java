package spectralmc.elepets.client.ui.util.policy;

/**
 * Defines a policy dictating the position of an object on an ordered line segment.
 *
 * @author phantamanta44
 */
public enum AlignmentPolicy {

    /**
     * This policy dictates that an object should be positioned at the start of the line.
     */
    BEGINNING(0F),

    /**
     * This policy dictates that an object should be positioned at the midpoint of the line.
     */
    MIDDLE(0.5F),

    /**
     * This policy dictates that an object should be positioned at the end of the line.
     */
    END(1F);

    /**
     * The fraction along the line at which an object should be positioned.
     */
    public final float fraction;

    /**
     * Creates a policy for placing an object at a given fraction along a line.
     *
     * @param fraction The fraction along the line at which an object should be positioned.
     */
    AlignmentPolicy(float fraction) {
        this.fraction = fraction;
    }

    /**
     * Positions an object with nonzero width on a line segment using this policy.
     *
     * @param space The width of the line segment.
     * @param width The width of the object.
     * @return The aligned position of the object's left bound.
     */
    public int placeObject(int space, int width) {
        return Math.round(fraction * (space - width));
    }

    /**
     * Positions a point on a line segment using this alignment policy.
     * Equivalent to caling {@link #placeObject(int, int)} with a width of zero.
     *
     * @param space The width of the line segment.
     * @return The aligned position of the point.
     */
    public int placePoint(int space) {
        return Math.round(fraction * space);
    }

}
