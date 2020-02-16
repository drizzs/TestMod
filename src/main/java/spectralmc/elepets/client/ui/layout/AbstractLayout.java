package spectralmc.elepets.client.ui.layout;

import com.mojang.blaze3d.systems.RenderSystem;
import spectralmc.elepets.client.ui.component.AbstractComponent;
import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.util.FocusingEventResult;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;
import spectralmc.elepets.util.render.GuiRenders;
import net.minecraft.client.Minecraft;

import java.util.function.Function;

/**
 * A basic implementation of the core functionality of a {@link UiLayout}.
 * Applicable events are automatically propagated to children.
 *
 * @author phantamanta44
 */
public abstract class AbstractLayout extends AbstractComponent implements UiLayout {

    @Override
    public void renderComponent(FocusContext focusCtx, float partialTicks) {
        streamChildren().forEach(c -> {
            RenderSystem.pushMatrix();
            c.getTransform().applyGl();
            if (Minecraft.getInstance().gameSettings.showDebugInfo) {
                // render outlines for children if debug HUD is open
                PlanarRect cDims = c.getComponent().getDimensions();
                // compute colour from hash code so components have distinct outline colours
                GuiRenders.strokeRect(
                        0, 0, cDims.getWidth(), cDims.getHeight(), 2, 0xFF000000 | c.getComponent().hashCode());
            }
            renderChild(c, focusCtx, partialTicks);
            RenderSystem.popMatrix();
        });
    }

    /**
     * Renders a single child component of this layout.
     *
     * @param child        The child component.
     * @param focusCtx     The window's focusing context.
     * @param partialTicks The client partial ticks.
     */
    protected void renderChild(ChildComponent child, FocusContext focusCtx, float partialTicks) {
        child.getComponent().renderComponent(focusCtx, partialTicks);
    }

    @Override
    public FocusingEventResult onClick(FocusContext focusCtx, PlanarPoint mousePos) {
        return propagateFocusingEvent(c -> {
            UiComponent comp = c.getComponent();
            PlanarPoint childMousePos = c.getTransform().apply(mousePos);
            return comp.getDimensions().contains(childMousePos) ? comp.onClick(focusCtx, childMousePos) : null;
        });
    }

    @Override
    public FocusingEventResult onDrag(FocusContext focusCtx, PlanarPoint mousePos) {
        return propagateFocusingEvent(c -> {
            UiComponent comp = c.getComponent();
            PlanarPoint childMousePos = c.getTransform().apply(mousePos);
            return comp.getDimensions().contains(childMousePos) ? comp.onDrag(focusCtx, childMousePos) : null;
        });
    }

    /**
     * Attempts to propagate a focusing event to all applicable children.
     * The propagation ends as soon as any one child consumes the event.
     *
     * @param propagator A propagation function that passes an event to a child or returns null if it's not applicable.
     * @return The result of the propagation.
     */
    protected FocusingEventResult propagateFocusingEvent(Function<ChildComponent, FocusingEventResult> propagator) {
        return streamChildren()
                .map(propagator)
                .filter(c -> c != null && c.getResult() != FocusingEventResult.Result.REJECTED)
                .findFirst().orElse(FocusingEventResult.REJECTED);
    }

}
