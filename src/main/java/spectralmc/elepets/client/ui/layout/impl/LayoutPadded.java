package spectralmc.elepets.client.ui.layout.impl;

import com.google.common.base.Preconditions;
import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.AbstractLayout;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.layout.CandidateChild;

import java.util.stream.Stream;

/**
 * A layout containing exactly one element and allows its dimensions to be padded.
 *
 * @author phantamanta44
 */
public class LayoutPadded extends AbstractLayout {

    /**
     * The child component. May not necessarily be laid-out.
     *
     * @see #childPlaced
     */
    private final UiComponent child;

    /**
     * The amount of padding on each margin of the child component.
     */
    private final float padLeft, padTop, padRight, padBottom;

    /**
     * Whether the padding is in percentages or not.
     */
    private final boolean percents;

    /**
     * The laid-out child component, or {@code null} if not yet laid-out.
     */
    private ChildComponent childPlaced = null;

    /**
     * Creates a new padded layout for the given child component.
     *
     * @param child     The child component.
     * @param padLeft   The padding on the left.
     * @param padTop    The padding on the top.
     * @param padRight  The padding on the right.
     * @param padBottom The padding on the bottom.
     * @param percents  Whether the padding should be expressed as a percentage of available space or not.
     * @throws IllegalArgumentException If percentages are enabled any any padding amount exceeds 100%.
     */
    public LayoutPadded(UiComponent child, float padLeft, float padTop, float padRight, float padBottom, boolean percents) {
        if (percents) {
            Preconditions.checkState(padLeft <= 1F && padTop <= 1F && padRight <= 1F && padBottom <= 1F,
                    "Padding percentages must be less than 100%!");
        }
        this.child = child;
        this.padLeft = padLeft;
        this.padTop = padTop;
        this.padRight = padRight;
        this.padBottom = padBottom;
        this.percents = percents;
    }

    @Override
    public Stream<? extends ChildComponent> streamChildren() {
        NotLaidOutException.ensure(childPlaced != null, this);
        return Stream.of(childPlaced);
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        // compute padding amounts
        int left, right, top, bottom;
        if (percents) {
            left = Math.round(padLeft * region.getWidth());
            right = Math.round(padRight * region.getWidth());
            top = Math.round(padTop * region.getHeight());
            bottom = Math.round(padBottom * region.getHeight());
        } else {
            left = Math.round(padLeft);
            right = Math.round(padRight);
            top = Math.round(padTop);
            bottom = Math.round(padBottom);
        }
        // lay out the child in the padded region
        PlanarRect childDims = child.layOutComponent(region.offsetDimensions(-(left + right), -(top + bottom)));
        childPlaced = new CandidateChild(child).setPositionX(left).setPositionY(top).build();
        dimensions = childDims.offsetDimensions(left + right, top + bottom);
        return dimensions;
    }

}
