package spectralmc.elepets.client.ui.layout.impl;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.AbstractLayout;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.geometry.PlanarAxis;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.layout.CandidateChild;
import spectralmc.elepets.client.ui.util.layout.LayoutPriority;
import spectralmc.elepets.client.ui.util.layout.LayoutPriorityList;
import spectralmc.elepets.client.ui.util.policy.AlignmentPolicy;
import spectralmc.elepets.client.ui.util.policy.JustificationPolicy;
import spectralmc.elepets.util.functional.StreamOps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A layout that places children sequentially in a row or column.
 * Children with higher priority will have their bounds laid out first.
 *
 * @author phantamanta44
 */
public class LayoutLinear extends AbstractLayout {

    /**
     * The axis along which children are laid out.
     */
    private final PlanarAxis axis;

    /**
     * The list of child components. May not necessarily be laid out.
     *
     * @see #childrenPlaced
     */
    private final LayoutPriorityList<UiComponent> children = new LayoutPriorityList<>();

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
     * Creates a new linear layout along the given axis.
     *
     * @param axis The axis to lay children out on.
     */
    public LayoutLinear(PlanarAxis axis) {
        this.axis = axis;
    }

    /**
     * Sets the justification policy used to position elements along the axis.
     * Defaults to {@link JustificationPolicy#SPACE_BETWEEN}.
     *
     * @param policy The policy to use.
     * @return This same {@link LayoutLinear}.
     */
    public LayoutLinear setJustificationPolicy(JustificationPolicy policy) {
        this.justification = policy;
        return this;
    }

    /**
     * Sets the alignment policy used to position elements perpendicular to the axis.
     * Defaults to {@link AlignmentPolicy#MIDDLE}.
     *
     * @param policy The policy to use.
     * @return This same {@link LayoutLinear}.
     */
    public LayoutLinear setAlignmentPolicy(AlignmentPolicy policy) {
        this.alignment = policy;
        return this;
    }

    /**
     * Adds a child component to this layout.
     * Higher priority children have their bounds laid out first.
     *
     * @param component The child component.
     * @param priority  The priority with which the child should be laid out.
     * @return This same {@link LayoutLinear}.
     */
    public LayoutLinear addChild(UiComponent component, LayoutPriority priority) {
        children.add(component, priority);
        return this;
    }

    /**
     * Adds a child component to this layout with normal priority.
     * Higher priority children have their bounds laid out first.
     *
     * @param component The child component.
     * @return This same {@link LayoutLinear}.
     */
    public LayoutLinear addChild(UiComponent component) {
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
        PlanarAxis axisOpp = axis.getOpposite();
        // compute layout bounds of all children
        List<CandidateChild> cands = new ArrayList<>();
        AtomicReference<PlanarRect> remHolder = new AtomicReference<>(region);
        PlanarRect space = StreamOps.foldl(children.streamPrioritized(), PlanarRect.ZERO, (curSpace, comp) -> {
            // compute child bounds
            PlanarRect remaining = remHolder.get();
            PlanarRect childBounds = comp.layOutComponent(remaining);
            // add new candidate child
            cands.add(new CandidateChild(comp));
            // reduce remaining space
            int childAxial = childBounds.getDimension(axis);
            remHolder.set(remaining.offsetDimension(axis, -childAxial));
            // increase consumed space
            return curSpace.offsetDimension(axis, childAxial)
                    .withDimension(axisOpp, Math.max(curSpace.getDimension(axisOpp), childBounds.getDimension(axisOpp)));
        });
        // place the children
        justification.placeObjects(
                space.getDimension(axis), cands, CandidateChild.dimGetter(axis), CandidateChild.posSetter(axis));
        for (CandidateChild cand : cands) {
            cand.setPosition(axisOpp, alignment.placeObject(
                    space.getDimension(axisOpp), cand.getComponent().getDimensions().getDimension(axisOpp)));
        }
        // update the laid-out children list
        childrenPlaced = cands.stream().map(CandidateChild::build).collect(Collectors.toList());
        dimensions = space;
        return space;
    }

}
