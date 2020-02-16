package spectralmc.elepets.client.ui.util.geometry;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.util.math.MathHelper;

/**
 * Represents a simple linear transformation between two coordinate systems on the plane.
 *
 * @author phantamanta44
 */
public class PlanarTrans {

    /**
     * The identity transformation.
     */
    public static final PlanarTrans IDENTITY = new PlanarTrans(1F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 1F);

    /**
     * The components of the 3-dimensional matrix making up this transformation.
     */
    private final float m11, m12, m13, m21, m22, m23, m31, m32, m33;

    /**
     * Creates a new planar transformation with the given transformation matrix entries.
     *
     * @param m11 Element (1,1)
     * @param m12 Element (1,2)
     * @param m13 Element (1,3)
     * @param m21 Element (2,1)
     * @param m22 Element (2,2)
     * @param m23 Element (2,3)
     * @param m31 Element (3,1)
     * @param m32 Element (3,2)
     * @param m33 Element (3,3)
     */
    public PlanarTrans(float m11, float m12, float m13, float m21, float m22, float m23, float m31, float m32, float m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    /**
     * Computes the inverse of this transform, if it exists.
     *
     * @return The inverse of the transform.
     * @throws java.util.NoSuchElementException If the transform is not invertible.
     */
    public PlanarTrans computeInverse() {
        throw new UnsupportedOperationException(); // TODO impl planar transform inversion
    }

    /**
     * Composes this transformation with another.
     *
     * @param next The transformation to apply after this one.
     * @return The new composed transformation.
     */
    public PlanarTrans compose(PlanarTrans next) {
        return new PlanarTrans(
                m11 * next.m11 + m12 * next.m21 + m13 * next.m31,
                m11 * next.m12 + m12 * next.m22 + m13 * next.m32,
                m11 * next.m13 + m12 * next.m23 + m13 * next.m33,
                m21 * next.m11 + m22 * next.m21 + m23 * next.m31,
                m21 * next.m12 + m22 * next.m22 + m23 * next.m32,
                m21 * next.m13 + m22 * next.m23 + m23 * next.m33,
                m31 * next.m11 + m32 * next.m21 + m33 * next.m31,
                m31 * next.m12 + m32 * next.m22 + m33 * next.m32,
                m31 * next.m13 + m32 * next.m23 + m33 * next.m33);
    }

    /**
     * Applies this transformation to a point.
     *
     * @param point The point to transform.
     * @return The new transformed point.
     */
    public PlanarPoint apply(PlanarPoint point) {
        int x = point.getX(), y = point.getY();
        return new PlanarPoint(Math.round(m11 * x + m12 * y + m13), Math.round(m21 * x + m22 * y + m23));
    }

    /**
     * Produces a Blaze3D-compatible representation of this transform.
     *
     * @return A Blaze3D transformation matrix.
     */
    public Matrix4f asBlaze3d() {
        return new Matrix4f(new float[] {
                m11, m12, 0F, m13,
                m21, m22, 0F, m23,
                0F, 0F, 1F, 0F,
                m31,  m32, 0F, m33
        });
    }

    /**
     * Applies this transformation to the current OpenGL transformation matrix stack.
     */
    public void applyGl() {
        RenderSystem.multMatrix(asBlaze3d());
    }

    /**
     * A builder for creating planar transformations.
     */
    public static class Builder {

        /**
         * The components of the 3-dimensional matrix for the transformation to be built.
         */
        private float m11, m12, m13, m21, m22, m23, m31, m32, m33;

        /**
         * Creates a builder with a given initial transform.
         *
         * @param archetype The transform to copy.
         */
        public Builder(PlanarTrans archetype) {
            this.m11 = archetype.m11;
            this.m12 = archetype.m12;
            this.m13 = archetype.m13;
            this.m21 = archetype.m21;
            this.m22 = archetype.m22;
            this.m23 = archetype.m23;
            this.m31 = archetype.m31;
            this.m32 = archetype.m32;
            this.m33 = archetype.m33;
        }

        /**
         * Creates a builder initialized to the identity.
         */
        public Builder() {
            this(PlanarTrans.IDENTITY);
        }

        /**
         * Applies a translational transform.
         *
         * @param x The translation on the x-axis.
         * @param y The translation on the y-axis.
         * @return This same builder.
         */
        public Builder translate(float x, float y) {
            float n11 = m11 + x * m31, n12 = m12 + x * m32, n13 = m13 + x * m33;
            float n21 = m21 + y * m31, n22 = m22 + y * m32, n23 = m23 + y * m33;
            m11 = n11;
            m12 = n12;
            m13 = n13;
            m21 = n21;
            m22 = n22;
            m23 = n23;
            return this;
        }

        /**
         * Applies a rotational transform.
         *
         * @param angle The angle to rotate by, counterclockwise, in radians.
         * @return This same builder.
         */
        public Builder rotate(float angle) {
            float cos = MathHelper.cos(angle), sin = MathHelper.sin(angle);
            float n11 = cos * m11 + -sin * m21, n12 = cos * m12 + -sin * m22, n13 = cos * m13 + -sin * m23;
            float n21 = sin * m11 + cos * m21, n22 = sin * m12 + cos * m22, n23 = sin * m13 + cos * m23;
            m11 = n11;
            m12 = n12;
            m13 = n13;
            m21 = n21;
            m22 = n22;
            m23 = n23;
            return this;
        }

        /**
         * Applies a scaling transform.
         *
         * @param x The scaling factor on the x-axis.
         * @param y The scaling factor on the y-axis.
         * @return This same builder.
         */
        public Builder scale(float x, float y) {
            m11 *= x;
            m12 *= x;
            m13 *= x;
            m21 *= y;
            m22 *= y;
            m23 *= y;
            return this;
        }

        /**
         * Creates a new planar transformation using the parameters of this builder.
         *
         * @return The new transformation.
         */
        public PlanarTrans build() {
            return new PlanarTrans(m11, m12, m13, m21, m22, m23, m31, m32, m33);
        }

    }

}
