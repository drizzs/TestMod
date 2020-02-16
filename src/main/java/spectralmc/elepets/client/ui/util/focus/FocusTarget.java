package spectralmc.elepets.client.ui.util.focus;

import spectralmc.elepets.client.ui.component.UiComponent;

/**
 * Represents a UI component that's capable of being focused.
 *
 * @author phantamanta44
 */
public interface FocusTarget extends UiComponent {

    /**
     * Called when this focus target gains focus.
     *
     * @param focusCtx The window's focus context.
     */
    default void onFocusAcquired(FocusContext focusCtx) {
        // NO-OP
    }

    /**
     * Called when this focus target loses focus.
     *
     * @param focusCtx The window's focus context.
     */
    default void onFocusLost(FocusContext focusCtx) {
        // NO-OP
    }

    /**
     * Sends this focus target a key press event.
     *
     * @param focusCtx  The window's focus context.
     * @param keyCode   The key code for the pressed key.
     * @param modifiers The modifier key mask for the event.
     * @return Whether this focus handler consumed the event or not.
     */
    default boolean onKeyDown(FocusContext focusCtx, int keyCode, int modifiers) {
        focusCtx.cycleFocus();
        return true;
    }

}
