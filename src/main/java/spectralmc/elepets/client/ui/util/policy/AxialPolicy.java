package spectralmc.elepets.client.ui.util.policy;

import spectralmc.elepets.client.ui.util.geometry.PlanarAxis;

/**
 * Defines a policy dictating whether an operation applies to either axis of a plane.
 *
 * @author phantamanta44
 */
public enum AxialPolicy {

    /**
     * This policy dictates that the operation cannot be performed on either axis.
     */
    NONE(false, false),

    /**
     * This policy dictates that the operation can only be applied on the x-axis.
     */
    HORIZONTAL(true, false),

    /**
     * This policy dictates that the operation can only be applied on the y-axis.
     */
    VERTICAL(false, true),

    /**
     * This policy dictates that the operation can be applied on both axes.
     */
    BOTH(true, true);

    /**
     * Whether this policy permits operating on the x-axis or not.
     */
    public final boolean permitsX;

    /**
     * Whether this policy permits operating on the y-axis or not.
     */
    public final boolean permitsY;

    /**
     * Creates a policy with the given axial policies.
     *
     * @param permitsX Whether horizontal operations are permitted or not.
     * @param permitsY Whether vertical operations are permitted or not.
     */
    AxialPolicy(boolean permitsX, boolean permitsY) {
        this.permitsX = permitsX;
        this.permitsY = permitsY;
    }

    /**
     * Checks whether this policy permits operating on a given axis or not.
     *
     * @param axis The axis to check.
     * @return Whether operations are permitted or not.
     */
    public boolean permitsAxis(PlanarAxis axis) {
        switch (axis) {
            case X:
                return permitsX;
            case Y:
                return permitsY;
        }
        throw new IllegalArgumentException("Unknown axis: " + axis);
    }

}
