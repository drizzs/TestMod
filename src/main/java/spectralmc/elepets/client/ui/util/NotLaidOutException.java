package spectralmc.elepets.client.ui.util;

import spectralmc.elepets.client.ui.component.UiComponent;

/**
 * Thrown when an operation is performed on a non-laid-out component that requires it to be laid out.
 *
 * @author phantamanta44
 */
public class NotLaidOutException extends RuntimeException {

    /**
     * The component that this exception applies to.
     */
    private final UiComponent component;

    /**
     * Creates a new exception referring to the given component.
     *
     * @param component The component that was operated on.
     */
    public NotLaidOutException(UiComponent component) {
        super("UI component is not laid out yet: " + component);
        this.component = component;
    }

    /**
     * Retrieves the component that this exception is referring to.
     *
     * @return The component that was operated on.
     */
    public UiComponent getComponent() {
        return component;
    }

    /**
     * Checks a precondition for being laid out, throwing an exception if it fails.
     *
     * @param test      The result of the check.
     * @param component The component in question.
     * @throws NotLaidOutException If the check failed.
     */
    public static void ensure(boolean test, UiComponent component) {
        if (!test) {
            throw new NotLaidOutException(component);
        }
    }

}
