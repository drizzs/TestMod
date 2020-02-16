package spectralmc.elepets.client.ui.util;

import spectralmc.elepets.client.ui.util.focus.FocusTarget;

/**
 * Represents the result of handling an event whose consumption is capable of passing focus onto a target.
 *
 * @author phantamanta44
 */
public class FocusingEventResult {

    /**
     * An event-handling result where the event was not consumed and focus should not be passed.
     */
    public static FocusingEventResult REJECTED = new FocusingEventResult(Result.REJECTED, null);

    /**
     * An event-handling result where the event was consumed but focus should not be passed.
     */
    public static FocusingEventResult CONSUMED = new FocusingEventResult(Result.CONSUMED, null);

    /**
     * Creates a new event-handling result that hands focus to the given focus target.
     *
     * @param target The new focus target.
     * @return The new event-handling result.
     */
    public static FocusingEventResult consumed(FocusTarget target) {
        return new FocusingEventResult(Result.CONSUMED, target);
    }

    /**
     * The result of handling the event.
     */
    private final Result result;

    /**
     * The target that focus should be passed to, or {@code null} if not applicable.
     */
    private final FocusTarget focusTarget;

    /**
     * Creates a new event result with the given result and focus target.
     *
     * @param result      The result of consuming the event.
     * @param focusTarget The new focus target, or {@code null} if not applicable.
     */
    public FocusingEventResult(Result result, FocusTarget focusTarget) {
        this.result = result;
        this.focusTarget = focusTarget;
    }

    /**
     * Gets the event consumption result for this event result.
     *
     * @return The consumption result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Gets the target that focus should be passed to, or {@code null} if not applicable.
     *
     * @return The new focus target.
     */
    public FocusTarget getFocusTarget() {
        return focusTarget;
    }

    /**
     * Represents whether an event was consumed or not.
     */
    public enum Result {

        /**
         * Represents the case where an event is not consumed.
         */
        REJECTED,

        /**
         * Represents the case where an event is consumed.
         */
        CONSUMED

    }

}
