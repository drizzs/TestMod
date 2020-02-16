package spectralmc.elepets.client.ui.util.policy;

import java.util.Collection;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;

/**
 * Defines a policy dictating the way space should be distributed between elements on a line.
 *
 * @author phantamanta44
 */
@SuppressWarnings("DuplicatedCode")
public enum JustificationPolicy {

    /**
     * Positions elements at the start of the line. All space is placed at the end of the line.
     */
    START {
        @Override
        public <T> void placeObjects(int space, Collection<T> elements,
                                     ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter) {
            int pos = 0;
            for (T element : elements) {
                posSetter.accept(element, pos);
                pos += widthGetter.applyAsInt(element);
            }
        }
    },

    /**
     * Positions elements at the end of the line. All space is placed at the start of the line.
     */
    END {
        @Override
        public <T> void placeObjects(int space, Collection<T> elements,
                                     ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter) {
            // compute remaining space after all elements
            for (T element : elements) {
                space -= widthGetter.applyAsInt(element);
            }
            // place elements
            int pos = space;
            for (T element : elements) {
                posSetter.accept(element, pos);
                pos += widthGetter.applyAsInt(element);
            }
        }
    },

    /**
     * Distributes space between the elements, but not at the start or end of the line.
     */
    SPACE_BETWEEN {
        @Override
        public <T> void placeObjects(int space, Collection<T> elements,
                                     ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter) {
            // compute remaining space after all elements
            for (T element : elements) {
                space -= widthGetter.applyAsInt(element);
            }
            // compute distributed space
            space /= elements.size() - 1;
            // distribute space between all elements
            int pos = 0;
            for (T element : elements) {
                posSetter.accept(element, pos);
                pos += widthGetter.applyAsInt(element) + space;
            }
        }
    },

    /**
     * Distributes space around the start and end of the line, causing the elements to flow to the center.
     */
    SPACE_AROUND {
        @Override
        public <T> void placeObjects(int space, Collection<T> elements,
                                     ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter) {
            // compute remaining space after all elements
            for (T element : elements) {
                space -= widthGetter.applyAsInt(element);
            }
            // place elements
            int pos = space / 2;
            for (T element : elements) {
                posSetter.accept(element, pos);
                pos += widthGetter.applyAsInt(element);
            }
        }
    },

    /**
     * Distributes space between the elements as well as the start and end of the line.
     */
    SPACE_EVENLY {
        @Override
        public <T> void placeObjects(int space, Collection<T> elements,
                                     ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter) {
            // compute remaining space after all elements
            for (T element : elements) {
                space -= widthGetter.applyAsInt(element);
            }
            // compute distributed space
            space /= elements.size() + 1;
            // distribute space between all elements
            int pos = space;
            for (T element : elements) {
                posSetter.accept(element, pos);
                pos += widthGetter.applyAsInt(element) + space;
            }
        }
    };

    /**
     * Distributes elements with nonzero width onto a line segment using this policy.
     *
     * @param space       The width of the line segment.
     * @param elements    The objects to place.
     * @param widthGetter A function mapping an object to its width.
     * @param posSetter   A function consuming the position at which to place an object.
     * @param <T>         The type of a placeable object.
     */
    public abstract <T> void placeObjects(int space, Collection<T> elements,
                                          ToIntFunction<T> widthGetter, ObjIntConsumer<T> posSetter);

    /**
     * Distributes points onto a line segment using this policy.
     *
     * @param space     The width of the line segment.
     * @param elements  The objects to place.
     * @param posSetter A function consuming the position at which to place an object.
     * @param <T>       The type of a placeable object.
     */
    public <T> void placePoints(int space, Collection<T> elements, ObjIntConsumer<T> posSetter) {
        placeObjects(space, elements, e -> 0, posSetter);
    }

}
