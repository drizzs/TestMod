package spectralmc.elepets.client.ui.util.animation;

import net.minecraft.util.math.MathHelper;

/**
 * A function for interpolating between two numbers.
 *
 * @author phantamanta44
 */
public enum InterpolationFunction {

    /**
     * Linearly interpolates between two values.
     */
    LINEAR {
        @Override
        public float apply(float t) {
            return t;
        }
    },

    /**
     * Interpolates along the spline formed by half of a cosine cycle.
     */
    SINE {
        @Override
        public float apply(float t) {
            return (1F - MathHelper.cos(t * (float)Math.PI)) / 2F;
        }
    };

    /**
     * Maps a linear progression fraction to the equivalent progression on the interpolation curve.
     *
     * @param t The progression parameter.
     * @return The interpolated progression.
     */
    public abstract float apply(float t);

}
