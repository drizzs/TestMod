package spectralmc.elepets.client.ui.layout.impl;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.AbstractDimensionableLayout;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.layout.CandidateChild;
import spectralmc.elepets.client.ui.util.policy.AlignmentPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A dimensionable layout that places children at anchoring points.
 *
 * @author phantamanta44
 */
public class LayoutAnchored extends AbstractDimensionableLayout {

    /**
     * The list of child components. May not necessarily be laid out.
     *
     * @see #childrenPlaced
     */
    private final List<AnchoredChild> children = new ArrayList<>();

    /**
     * The list of laid-out child components, or {@code null} if not yet laid out.
     */
    private List<ChildComponent> childrenPlaced = null;

    /**
     * Adds a child component to this layout anchored with the given policies.
     *
     * @param component The child component.
     * @param alignX    The anchoring policy along the x-axis.
     * @param alignY    The anchoring policy along the y-axis.
     * @return This same {@link LayoutAnchored}.
     */
    public LayoutAnchored addChild(UiComponent component, AlignmentPolicy alignX, AlignmentPolicy alignY) {
        children.add(new AnchoredChild(component, alignX, alignY));
        return this;
    }

    @Override
    public Stream<? extends ChildComponent> streamChildren() {
        NotLaidOutException.ensure(childrenPlaced != null, this);
        return childrenPlaced.stream();
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        super.layOutComponent(region);
        List<ChildComponent> placed = new ArrayList<>();
        for (AnchoredChild child : children) {
            PlanarRect childBounds = child.component.layOutComponent(region);
            CandidateChild cand = new CandidateChild(child.component);
            cand.setPositionX(child.alignX.placeObject(region.getWidth(), childBounds.getWidth()));
            cand.setPositionY(child.alignY.placeObject(region.getHeight(), childBounds.getHeight()));
            placed.add(cand.build());
        }
        childrenPlaced = placed;
        return dimensions;
    }

    /**
     * Represents a component anchored at a position within an anchorable layout.
     */
    private static class AnchoredChild {

        /**
         * The UI component this refers to.
         */
        private final UiComponent component;

        /**
         * The anchoring policy along the x-axis.
         */
        private final AlignmentPolicy alignX;

        /**
         * The anchoring policy along the y-axis.
         */
        private final AlignmentPolicy alignY;

        /**
         * Creates a new anchored child with the given properties.
         *
         * @param component The child component.
         * @param alignX    The anchor along the x-axis.
         * @param alignY    The anchor along the y-axis.
         */
        AnchoredChild(UiComponent component, AlignmentPolicy alignX, AlignmentPolicy alignY) {
            this.component = component;
            this.alignX = alignX;
            this.alignY = alignY;
        }

    }

}
