package spectralmc.elepets.util.render;

import com.mojang.blaze3d.systems.RenderSystem;

/**
 * Helper functions for working with colours.
 *
 * @author phantamanta44
 */
public class ColourUtils {

    /**
     * Extracts a component from an integer colour representation (e.g. ARGB).
     *
     * @param colour The colour integer to extract from.
     * @param shift  The number of components to shift right; in ARGB, you would pass {@code 2} to extract red.
     * @return The extracted component.
     */
    public static int extractComponent(int colour, int shift) {
        return (colour >> (shift * 8)) & 0xFF;
    }

    /**
     * Sets the OpenGL colour state to an RGB colour.
     * Note that this function specifically handles RGB and not ARGB!
     *
     * @param colour The RGB colour to set. The 8 most significant bits are ignored.
     * @param alpha  The alpha to set, on the range [0, 1].
     */
    public static void setGlColour(int colour, float alpha) {
        RenderSystem.color4f(
                extractComponent(colour, 2) / 255F,
                extractComponent(colour, 1) / 255F,
                extractComponent(colour, 0) / 255F,
                alpha);
    }

    /**
     * Sets the OpenGL colour state to an RGB colour with full opacity.
     * Note that this function specifically handles RGB and not ARGB!
     *
     * @param colour The RGB colour to set. The 8 most significant bits are ignored.
     */
    public static void setGlColour(int colour) {
        setGlColour(colour, 1F);
    }

}
