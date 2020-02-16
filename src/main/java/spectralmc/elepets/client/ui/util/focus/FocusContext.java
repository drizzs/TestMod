package spectralmc.elepets.client.ui.util.focus;

import com.google.common.base.Objects;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import java.util.List;

/**
 * Represents a context in which a UI component can be focused.
 *
 * @author phantamanta44
 */
public class FocusContext {

    /**
     * The list of all focus targets in this context.
     */
    private final List<FocusTarget> focusTargets;

    /**
     * A mapping from a focus target to its index in {@link #focusTargets}.
     */
    private final Object2IntMap<FocusTarget> indexMap;

    /**
     * The currently focused target, or {@code null} if nothing is focused.
     */
    private FocusTarget focused = null;

    /**
     * Creates a new focus context with the given list of targets.
     *
     * @param focusTargets The list of all focus targets.
     */
    public FocusContext(List<FocusTarget> focusTargets) {
        this.focusTargets = focusTargets;
        this.indexMap = new Object2IntOpenHashMap<>();
        for (int i = 0; i < focusTargets.size(); i++) {
            indexMap.put(focusTargets.get(i), i);
        }
    }

    /**
     * Gets the list of all focus targets in this context.
     *
     * @return The list of focus targets.
     */
    public List<FocusTarget> getFocusTargets() {
        return focusTargets;
    }

    /**
     * Retrieves the currently focused target, or {@code null} if nothing is focused.
     *
     * @return The current focus target, if any.
     */
    public FocusTarget getFocused() {
        return focused;
    }

    /**
     * Checks whether a focus target is focused or not.
     *
     * @param target The focus target to check.
     * @return Whether the target is focused or not.
     */
    public boolean isFocused(FocusTarget target) {
        return Objects.equal(target, focused);
    }

    /**
     * Sets the currently-focused target.
     *
     * @param target The new focus target.
     */
    public void setFocus(FocusTarget target) {
        focused = target;
    }

    /**
     * Cycles to the next available focus target, or otherwise to the first focus target.
     */
    public void cycleFocus() {
        if (focused == null) {
            focused = focusTargets.get(0);
        } else {
            int index = indexMap.getInt(focused);
            focused = focusTargets.get((index >= focusTargets.size() - 1) ? 0 : (index + 1));
        }
    }

}
