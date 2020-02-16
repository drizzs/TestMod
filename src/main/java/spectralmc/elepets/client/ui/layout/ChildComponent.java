package spectralmc.elepets.client.ui.layout;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.util.geometry.PlanarTrans;

/**
 * Represents a component that is positioned within a layout.
 *
 * @author phantamanta44
 * @see UiLayout
 * @see UiComponent
 */
public interface ChildComponent {

    /**
     * Retrieves the child component.
     *
     * @return The child component.
     */
    UiComponent getComponent();

    /**
     * Retrieves the transform from the parent layout's local coordinate system to the child component's.
     *
     * @return The parent-to-child transform.
     */
    PlanarTrans getTransform();

    /**
     * A straightforwards implementation of {@link ChildComponent}.
     */
    class Impl implements ChildComponent {

        /**
         * The child component.
         */
        private final UiComponent component;

        /**
         * The parent-to-child transform.
         */
        private final PlanarTrans transform;

        /**
         * Creates a simple child component instance for the given child and parent-to-child transform.
         *
         * @param component The child component.
         * @param transform The parent-to-child transform.
         */
        public Impl(UiComponent component, PlanarTrans transform) {
            this.component = component;
            this.transform = transform;
        }

        @Override
        public UiComponent getComponent() {
            return component;
        }

        @Override
        public PlanarTrans getTransform() {
            return transform;
        }

    }

}
