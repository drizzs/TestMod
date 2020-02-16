package spectralmc.elepets.client.ui.layout.impl;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.AbstractDimensionableLayout;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.geometry.PlanarAxis;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.layout.CandidateChild;
import spectralmc.elepets.client.ui.util.policy.AlignmentPolicy;
import spectralmc.elepets.client.ui.util.policy.JustificationPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A layout that evenly distributes space along a row or column to its children.
 * Good for displaying a line of components of equal size.
 *
 * @author phantamanta44
 */
public class LayoutLineup extends AbstractDimensionableLayout {

    /**
     * The axis along which children are laid out.
     */
    private final PlanarAxis axis;

    /**
     * The list of child components. May not necessarily be laid out.
     *
     * @see #childrenPlaced
     */
    private final List<UiComponent> children = new ArrayList<>();

    /**
     * The justification policy for positioning components along the axis.
     */
    private JustificationPolicy justification = JustificationPolicy.SPACE_BETWEEN;

    /**
     * The alignment policy for positioning components perpendicular to the axis.
     */
    private AlignmentPolicy alignment = AlignmentPolicy.MIDDLE;

    /**
     * The list of laid-out child components, or {@code null} if not yet laid out.
     */
    private List<ChildComponent> childrenPlaced = null;

    /**
     * Creates a new lineup layout along the given axis.
     *
     * @param axis The axis to lay children out on.
     */
    public LayoutLineup(PlanarAxis axis) {
        this.axis = axis;
    }

    /**
     * Sets the justification policy used to position elements along the axis.
     * Defaults to {@link JustificationPolicy#SPACE_BETWEEN}.
     *
     * @param policy The policy to use.
     * @return This same {@link LayoutLineup}.
     */
    public LayoutLineup setJustificationPolicy(JustificationPolicy policy) {
        this.justification = policy;
        return this;
    }

    /**
     * Sets the alignment policy used to position elements perpendicular to the axis.
     * Defaults to {@link AlignmentPolicy#MIDDLE}.
     *
     * @param policy The policy to use.
     * @return This same {@link LayoutLineup}.
     */
    public LayoutLineup setAlignmentPolicy(AlignmentPolicy policy) {
        this.alignment = policy;
        return this;
    }

    /**
     * Adds a child component to this layout.
     *
     * @param component The child component.
     * @return This same {@link LayoutLineup}.
     */
    public LayoutLineup addChild(UiComponent component) {
        children.add(component);
        return this;
    }

    @Override
    public Stream<? extends ChildComponent> streamChildren() {
        NotLaidOutException.ensure(childrenPlaced != null, this);
        return childrenPlaced.stream();
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        super.layOutComponent(region);
        PlanarAxis axisOpp = axis.getOpposite();
        // compute region distribution
        PlanarRect childRegion = dimensions.withDimension(axis,
                (int)Math.floor(dimensions.getDimension(axis) / (float)children.size()));
        // lay out children
        List<CandidateChild> cands = new ArrayList<>();
        for (UiComponent comp : children) {
            // compute child bounds
            comp.layOutComponent(childRegion);
            cands.add(new CandidateChild(comp));
        }
        // place the children
        justification.placeObjects(
                dimensions.getDimension(axis), cands, CandidateChild.dimGetter(axis), CandidateChild.posSetter(axis));
        for (CandidateChild cand : cands) {
            cand.setPosition(axisOpp, alignment.placeObject(
                    dimensions.getDimension(axisOpp), cand.getComponent().getDimensions().getDimension(axisOpp)));
        }
        // update the laid-out children list
        childrenPlaced = cands.stream().map(CandidateChild::build).collect(Collectors.toList());
        return dimensions;
    }

}
