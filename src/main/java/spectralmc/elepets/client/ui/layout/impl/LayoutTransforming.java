package spectralmc.elepets.client.ui.layout.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.AbstractLayout;
import spectralmc.elepets.client.ui.layout.ChildComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.client.ui.util.layout.CandidateChild;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A layout containing exactly one element that allows that element's rendering to be transformed.
 *
 * @author phantamanta44
 */
public class LayoutTransforming extends AbstractLayout {

    /**
     * The child component. May not necessarily be laid-out.
     *
     * @see #childPlaced
     */
    private final UiComponent child;

    /**
     * The rendering transformation function.
     */
    private final Consumer<FocusContext> renderTransformer;

    /**
     * The laid-out child component, or {@code null} if not yet laid-out.
     */
    private ChildComponent childPlaced = null;

    /**
     * Creates a new transforming layout for the given child component.
     *
     * @param child             The child component.
     * @param renderTransformer The function that applies the render transformation.
     */
    public LayoutTransforming(UiComponent child, Consumer<FocusContext> renderTransformer) {
        this.child = child;
        this.renderTransformer = renderTransformer;
    }

    @Override
    public Stream<? extends ChildComponent> streamChildren() {
        NotLaidOutException.ensure(childPlaced != null, this);
        return Stream.of(childPlaced);
    }

    @Override
    public PlanarRect layOutComponent(PlanarRect region) {
        PlanarRect childDims = child.layOutComponent(region);
        childPlaced = new CandidateChild(child).build();
        return dimensions = childDims;
    }

    @Override
    protected void renderChild(ChildComponent child, FocusContext focusCtx, float partialTicks) {
        RenderSystem.pushMatrix();
        renderTransformer.accept(focusCtx);
        super.renderChild(child, focusCtx, partialTicks);
        RenderSystem.popMatrix();
    }

}
