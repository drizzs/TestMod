package spectralmc.elepets.util.functional;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Helper functions for interacting with the stream API in a functional manner.
 *
 * @author phantamanta44
 */
public class StreamOps {

    /**
     * Performs a sequential left-fold operation on a stream.
     *
     * @param stream      The stream to fold.
     * @param identity    The identity accumulator.
     * @param accumulator The accumulating function.
     * @param <A>         The type of the accumulator.
     * @param <B>         The type of the stream's element.
     * @return The accumulated value.
     */
    public static <A, B> A foldl(Stream<B> stream, A identity, BiFunction<A, B, A> accumulator) {
        Iterator<B> iter = stream.iterator();
        while (iter.hasNext()) {
            identity = accumulator.apply(identity, iter.next());
        }
        return identity;
    }

}
