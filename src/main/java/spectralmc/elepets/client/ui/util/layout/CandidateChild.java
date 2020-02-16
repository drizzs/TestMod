package spectralmc.elepets.client.ui.util.layout;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.geometry.PlanarAxis;
import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.util.geometry.PlanarTrans;

import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;

/**
 * A builder class for a simple {@link ChildComponent} that is only translated.
 *
 * @author phantamanta44
 */
public class CandidateChild {

    /**
     * Produces a function mapping a {@link CandidateChild} to a dimension of its wrapped component.
     *
     * @param axis The axis of the dimension to get.
     * @return The dimension getter.
     */
    public static ToIntFunction<CandidateChild> dimGetter(PlanarAxis axis) {
        return c -> c.getComponent().getDimensions().getDimension(axis);
    }

    /**
     * Produces a function for setting the position of a {@link CandidateChild} along a given axis.
     *
     * @param axis The axis of the position to set.
     * @return The position setter.
     */
    public static ObjIntConsumer<CandidateChild> posSetter(PlanarAxis axis) {
        return (c, d) -> c.setPosition(axis, d);
    }

    /**
     * The child component.
     */
    private final UiComponent component;

    /**
     * The x-coordinate of the child component in the parent's local coordinate system.
     */
    private int posX = 0;

    /**
     * The y-coordinate of the child component in the parent's local coordinate system.
     */
    private int posY = 0;

    /**
     * Creates a new candidate child wrapping the given component.
     *
     * @param component The child component.
     */
    public CandidateChild(UiComponent component) {
        this.component = component;
    }

    /**
     * Retrieves the wrapped component.
     *
     * @return The child component.
     */
    public UiComponent getComponent() {
        return component;
    }

    /**
     * Sets the x-coordinate of the child component in the parent's local coordinate system.
     *
     * @param x The x-coordinate.
     * @return This same {@link CandidateChild}.
     */
    public CandidateChild setPositionX(int x) {
        this.posX = x;
        return this;
    }

    /**
     * Sets the y-coordinate of the child component in the parent's local coordinate system.
     *
     * @param y The y-coordinate.
     * @return This same {@link CandidateChild}.
     */
    public CandidateChild setPositionY(int y) {
        this.posY = y;
        return this;
    }

    /**
     * Sets the position of the child component on a given axis in the parent's local coordinate system.
     *
     * @param axis The axis to set the position along.
     * @param pos  The position along the axis.
     */
    public void setPosition(PlanarAxis axis, int pos) {
        if (axis == PlanarAxis.X) {
            setPositionX(pos);
        } else {
            setPositionY(pos);
        }
    }

    /**
     * Sets the position of the child component in the parent's local coordinate system.
     *
     * @param pos The position in the parent.
     */
    public void setPosition(PlanarPoint pos) {
        setPositionX(pos.getX());
        setPositionY(pos.getY());
    }

    /**
     * Builds a {@link ChildComponent} using the built translation.
     *
     * @return The new child component.
     */
    public ChildComponent build() {
        return new ChildComponent.Impl(component, new PlanarTrans.Builder().translate(posX, posY).build());
    }

}
