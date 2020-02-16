package spectralmc.elepets.util.math;

/**
 * Helper functions for hashing things with reasonable collision resistance.
 *
 * @author phantamanta44
 */
public class HashUtils {

    /**
     * Computes a hash code for two {@code int}s.
     * Note that the order of the passed values matters!
     *
     * @param a The first value.
     * @param b The second value.
     * @return The hash code.
     */
    public static int hashIntPair(int a, int b) {
        return Integer.hashCode(a) * ((1 << 17) - 1) + Integer.hashCode(b);
    }

}
