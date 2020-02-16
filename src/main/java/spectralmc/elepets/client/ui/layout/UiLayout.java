package spectralmc.elepets.client.ui.layout;

import spectralmc.elepets.client.ui.component.UiComponent;
import spectralmc.elepets.client.ui.util.NotLaidOutException;

import java.util.stream.Stream;

/**
 * Represents some UI layout in which UI components can be placed.
 * Note that a layout is itself a component, so it can be added as a child of another layout.
 *
 * @author phantamanta44
 * @see UiComponent
 */
public interface UiLayout extends UiComponent {

    /**
     * Produces a stream of this layout's laid-out child components.
     *
     * @return A stream of child components.
     * @throws NotLaidOutException If this layout is not yet laid out.
     */
    Stream<? extends ChildComponent> streamChildren();

}
