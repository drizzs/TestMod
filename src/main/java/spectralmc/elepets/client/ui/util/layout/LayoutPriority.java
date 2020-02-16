package spectralmc.elepets.client.ui.util.layout;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Represents a priority for a UI component to be laid out in a layout.
 * What this means depends on the context in which the component is being laid out.
 *
 * @author phantamanta44
 */
public enum LayoutPriority {

    /**
     * Indicates that a component has a lower priority level.
     */
    LOW,

    /**
     * Indicates that a component has a normal priority level.
     */
    NORMAL,

    /**
     * Indicates that a component has a higher priority level.
     */
    HIGH;

    /**
     * An immutable list of the layout priorities in priority order.
     */
    public static final List<LayoutPriority> ORDER = ImmutableList.of(HIGH, NORMAL, LOW);

}
