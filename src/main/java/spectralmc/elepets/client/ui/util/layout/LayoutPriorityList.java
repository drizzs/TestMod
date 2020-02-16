package spectralmc.elepets.client.ui.util.layout;

import java.util.*;
import java.util.stream.Stream;

/**
 * A collection type that allows both iteration in insertion order and in priority order based on an element'
 * {@link LayoutPriority}.
 *
 * @author phantamanta44
 */
public class LayoutPriorityList<T> {

    /**
     * The list maintaining an ordering of all the elements in the collection.
     */
    private final List<T> totalOrdering = new ArrayList<>();

    /**
     * The table maintaining sub-orderings for each priority level.
     */
    private final Map<LayoutPriority, List<T>> priorities = new EnumMap<>(LayoutPriority.class);

    /**
     * Adds an element at the given priority level.
     *
     * @param element  The element to add.
     * @param priority The priority of the added element.
     */
    public void add(T element, LayoutPriority priority) {
        totalOrdering.add(element);
        priorities.computeIfAbsent(priority, k -> new ArrayList<>()).add(element);
    }

    /**
     * Adds an element with {@link LayoutPriority#NORMAL} priority.
     *
     * @param element The element to add.
     */
    public void add(T element) {
        add(element, LayoutPriority.NORMAL);
    }

    /**
     * Retrieves the total number of elements in this collection.
     *
     * @return The element count.
     */
    public int getCount() {
        return totalOrdering.size();
    }

    /**
     * Produces a stream of the elements in insertion order.
     *
     * @return The stream of elements.
     */
    public Stream<T> streamTotal() {
        return totalOrdering.stream();
    }

    /**
     * Produces a stream of the elements in priority order.
     *
     * @return The stream of elements.
     */
    public Stream<T> streamPrioritized() {
        return LayoutPriority.ORDER.stream()
                .map(priorities::get)
                .filter(Objects::nonNull)
                .flatMap(List::stream);
    }

}
