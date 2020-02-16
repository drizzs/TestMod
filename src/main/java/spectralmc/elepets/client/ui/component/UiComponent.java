package spectralmc.elepets.client.ui.component;

import spectralmc.elepets.client.ui.util.FocusingEventResult;
import spectralmc.elepets.client.ui.layout.UiLayout;
import spectralmc.elepets.client.ui.util.NotLaidOutException;
import spectralmc.elepets.client.ui.util.focus.FocusContext;
import spectralmc.elepets.client.ui.util.geometry.PlanarPoint;
import spectralmc.elepets.client.ui.util.geometry.PlanarRect;

/**
 * Represents some component that can be placed in a UI layout.
 * Each component has a "local coordinate system" which has its origin at the top-left corner of its bounding rectangle.
 *
 * @author phantamanta44
 * @see UiLayout
 */
public interface UiComponent {

    /**
     * Lays out this component within the bounds of an enclosing region.
     *
     * @param region The enclosing region's dimensions.
     * @return The laid-out dimensions for the component within the region.
     * @throws IllegalArgumentException If the component cannot be laid out in the given dimensions.
     */
    PlanarRect layOutComponent(PlanarRect region);

    /**
     * Gets the current laid-out dimensions of this component.
     *
     * @return The dimensions of the component.
     * @throws NotLaidOutException If the component is not yet laid out.
     */
    PlanarRect getDimensions();

    /**
     * Renders this component with the given dimensions.
     * The rendering coordinate system is the component's local coordinate system.
     *
     * @param focusCtx     The window's focus context.
     * @param partialTicks The client partial ticks.
     * @throws NotLaidOutException If the component is not yet laid out.
     */
    void renderComponent(FocusContext focusCtx, float partialTicks);

    /**
     * Requests that this component handles a mouse click event.
     *
     * @param focusCtx The window's focus context.
     * @param mousePos The position where the click occurred in the local coordinate system.
     * @return The result of the event.
     */
    default FocusingEventResult onClick(FocusContext focusCtx, PlanarPoint mousePos) {
        return FocusingEventResult.REJECTED;
    }

    /**
     * Requests that this component handles a mouse drag event.
     *
     * @param focusCtx The window's focus context.
     * @param mousePos The position where the drag occurred in the local coordinate system.
     * @return The result of the event.
     */
    default FocusingEventResult onDrag(FocusContext focusCtx, PlanarPoint mousePos) {
        return FocusingEventResult.REJECTED;
    }

}
