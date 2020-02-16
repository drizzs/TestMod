package spectralmc.elepets.client.ui.util.drawable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Represents a texture resource for objects that can be drawn onto a UI.
 * Used to produce {@link UiDrawable} instances from a spritesheet texture.
 *
 * @author phantamanta44
 */
public class UiTexture {

    /**
     * The location of the texture.
     */
    private final ResourceLocation textureResource;

    /**
     * The width of the texture. Stored as a {@code float} for convenience.
     */
    private final float width;

    /**
     * The height of the texture. Stored as a {@code float} for convenience.
     */
    private final float height;

    /**
     * Creates a new texture resource.
     *
     * @param textureResource The location of the texture.
     * @param width           The width of the texture.
     * @param height          The height of the texture.
     */
    public UiTexture(ResourceLocation textureResource, int width, int height) {
        this.textureResource = textureResource;
        this.width = width;
        this.height = height;
    }

    /**
     * Binds the texture resource to the rendering engine.
     */
    public void bindTexture() {
        Minecraft.getInstance().textureManager.bindTexture(textureResource);
    }

    /**
     * Extracts the entire texture as a {@link UiDrawable}.
     *
     * @return The full drawable region.
     */
    public UiDrawable getFullRegion() {
        return new Region(0F, 0F, 1F, 1F);
    }

    /**
     * Extracts a region of the texture resource as a {@link UiDrawable}.
     *
     * @param x         The x-coordinate of the region in the texture.
     * @param y         The y-coordinate of the region in the texture.
     * @param regWidth  The width of the region in the texture.
     * @param regHeight The height of the region in the texture.
     * @return The extracted drawable region.
     */
    public UiDrawable getRegion(int x, int y, int regWidth, int regHeight) {
        return new Region(x / width, y / height, regWidth / width, regHeight / height);
    }

    /**
     * Represents a drawable region of a {@link UiTexture}.
     */
    private class Region implements UiDrawable {

        /**
         * The U/V coordinates of this region in the texture.
         */
        private final float u, v;

        /**
         * The dimensions of this region in U/V coordinate space.
         */
        private final float du, dv;

        /**
         * Creates a new region.
         *
         * @param u  The u-coordinate of the region.
         * @param v  The v-coordinate of the region.
         * @param du The width of the region in U/V space.
         * @param dv The height of the region in U/V space.
         */
        Region(float u, float v, float du, float dv) {
            this.u = u;
            this.v = v;
            this.du = du;
            this.dv = dv;
        }

        @Override
        public void drawPartial(double x, double y, double width, double height, float x1, float y1, float x2, float y2) {
            bindTexture();
            // compute coordinates for rendering based on dimension fractions
            double xi = x + width * x1, xf = x + width * x2, yi = y + height * y1, yf = y + height * y2;
            float ui = u + du * x1, uf = u + du * x2, vi = v + dv * y1, vf = v + dv * y2;
            // do the render
            Tessellator tess = Tessellator.getInstance();
            BufferBuilder buf = tess.getBuffer();
            buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buf.pos(xi, yi, 0D).tex(ui, vi).endVertex();
            buf.pos(xi, yf, 0D).tex(ui, vf).endVertex();
            buf.pos(xf, yf, 0D).tex(uf, vf).endVertex();
            buf.pos(xf, yi, 0D).tex(uf, vi).endVertex();
            tess.draw();
        }

    }

}
