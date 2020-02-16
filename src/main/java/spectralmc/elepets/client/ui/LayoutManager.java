package spectralmc.elepets.client.ui;

import com.google.common.base.Preconditions;
import net.minecraft.client.gui.IGuiEventListener;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.layout.UiLayout;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.focus.FocusTarget;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

/**
 * Represents and manages a window region into which layouts can be rendered.
 *
 * @author phantamanta44
 */
public class LayoutManager implements IGuiEventListener {

    /**
     * The root of this layout manager's UI component tree.
     */
    private final UiComponent rootComponent;

    /**
     * The last known dimensions of the window region for this layout manager.
     */
    private PlanarRect windowRegion = PlanarRect.ZERO;

    /**
     * The focus context for this window, or {@code null} if not yet laid-out.
     */
    private FocusContext focusCtx = null;

    /**
     * Whether the layout manager is valid or not.
     * Essentially, represents whether the layout is still alive and usable or not.
     */
    private boolean valid = true;

    /**
     * Creates a new layout manager with the given root component.
     *
     * @param rootComponent The root UI component.
     */
    public LayoutManager(UiComponent rootComponent) {
        this.rootComponent = rootComponent;
    }

    /**
     * Invalidates the layout manager, marking it as unusable for the future.
     */
    public void invalidate() {
        valid = false;
    }

    /**
     * Checks whether this layout manager is still valid or not.
     *
     * @return The validity of this layout manager.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Ensures that the layout manager is valid.
     *
     * @throws IllegalStateException If this layout manager is invalidated.
     */
    private void checkValid() {
        Preconditions.checkState(valid, "Layout manager has been invalidated!");
    }

    /**
     * Lays out the window.
     *
     * @param winWidth  The width of the window region.
     * @param winHeight The height of the window region.
     * @throws IllegalStateException If this layout manager is invalidated.
     */
    public void layOut(int winWidth, int winHeight) {
        checkValid();
        // only lay out if the window dimensions differ
        // may fail in the edge case where the initial dimensions are 0x0, but that's extremely unlikely
        if (winWidth != windowRegion.getWidth() || winHeight != windowRegion.getHeight()) {
            // lay out component tree
            windowRegion = new PlanarRect(winWidth, winHeight);
            try {
                rootComponent.layOutComponent(windowRegion);
            } catch (Exception e) {
                ElePets.LOGGER.warn("Failed to lay out window!", e);
            }
            // collect focus targets and rebuild focus context
            List<FocusTarget> focusTargets = new ArrayList<>();
            traverse(c -> {
                if (c instanceof FocusTarget) {
                    focusTargets.add((FocusTarget)c);
                }
            });
            focusCtx = new FocusContext(focusTargets);
        }
    }

    /**
     * Renders the layout into the window.
     *
     * @param partialTicks The client partial ticks.
     * @throws IllegalStateException If this layout manager is invalidated.
     */
    public void render(float partialTicks) {
        checkValid();
        Preconditions.checkState(focusCtx != null, "The window needs to be laid out before it can be rendered!");
        rootComponent.renderComponent(focusCtx, partialTicks);
    }

    /**
     * Lays out and renders the component tree.
     *
     * @param winWidth     The window region's width.
     * @param winHeight    The window region's height.
     * @param partialTicks The client partial ticks.
     * @throws IllegalStateException If this layout manager is invalidated.
     */
    public void layOutAndRender(int winWidth, int winHeight, float partialTicks) {
        layOut(winWidth, winHeight);
        render(partialTicks);
    }

    /**
     * Traverses this layout manager's component tree depth-first.
     *
     * @param visitor A visitor function applied at each component in the traversal.
     */
    public void traverse(Consumer<UiComponent> visitor) {
        // FILO buffer allows depth-first traversal
        Deque<UiComponent> stack = new ArrayDeque<>();
        stack.push(rootComponent);
        while (!stack.isEmpty()) {
            UiComponent comp = stack.pop();
            visitor.accept(comp);
            if (comp instanceof UiLayout) {
                ((UiLayout)comp).streamChildren().forEach(c -> stack.push(c.getComponent()));
            }
        }
    }

    // TODO pass gui events to component tree

}
