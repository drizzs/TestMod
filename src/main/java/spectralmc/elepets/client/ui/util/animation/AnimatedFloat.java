package spectralmc.elepets.client.ui.util.animation;

/**
 * A float value that can be animated over a period of time.
 *
 * @author phantamanta44
 */
public class AnimatedFloat {

    /**
     * The interpolation function for animation.
     */
    private final InterpolationFunction interpFunc;

    /**
     * The value set before the current animation.
     */
    private float prevValue;

    /**
     * The target value for the current animation.
     */
    private float targetValue;

    /**
     * The duration of the current animation.
     */
    private long duration = 1;

    /**
     * The start time for the current animation.
     */
    private long animationStartTime = -1L;

    /**
     * Creates an animated float with the given interpolation function.
     *
     * @param initialValue The initial value.
     * @param interpFunc   The interpolation function for animating the value.
     */
    public AnimatedFloat(float initialValue, InterpolationFunction interpFunc) {
        this.interpFunc = interpFunc;
        this.prevValue = this.targetValue = initialValue;
    }

    /**
     * Animates the value to a new target over some duration.
     *
     * @param targetValue The target value.
     * @param duration    The duration of the animation.
     * @param now         The current time, in epoch milliseconds.
     */
    public void animateTo(float targetValue, long duration, long now) {
        prevValue = getValue(now);
        this.targetValue = targetValue;
        this.duration = Math.max(duration, 1);
        animationStartTime = now;
    }

    /**
     * Animates the value to a new target over some duration.
     * Acquires the current time using {@link System#currentTimeMillis()}.
     *
     * @param targetValue The target value.
     * @param duration    The duration of the animation.
     */
    public void animateToNow(float targetValue, long duration) {
        animateTo(targetValue, duration, System.currentTimeMillis());
    }

    /**
     * Retrieves the current animated value.
     *
     * @param now The current time, in epoch milliseconds.
     * @return The interpolated value.
     */
    public float getValue(long now) {
        return prevValue + (targetValue - prevValue)
                * interpFunc.apply(Math.min((now - animationStartTime) / (float)duration, 1F));
    }

    /**
     * Retrieves the current animated value.
     * Acquires the current time using {@link System#currentTimeMillis()}.
     *
     * @return The interpolated value.
     */
    public float getValueNow() {
        return getValue(System.currentTimeMillis());
    }

}
