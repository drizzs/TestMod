package drizzs.magicnmonsters.client.draw;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UiTexture {
	private final ResourceLocation TEXTURE;
	private final float x;
	private final float y;

	public UiTexture(ResourceLocation texture, float x, float y) {
		TEXTURE = texture;
		this.x = x;
		this.y = y;
	}

	public void bindTexture() {
		Minecraft.getInstance().textureManager.bindTexture(TEXTURE);
	}
	
	public IDrawable getFullArea() {
		return new Area(0, 0, 1, 1);
	}
	
	public IDrawable getArea(int x, int y, int w, int h) {
		return new Area(x / w, y / h, w / this.x, h / this.y);
		
	}

	private class Area implements IDrawable {

		private final float v, u;
		private final float dv, du;

		public Area(float u, float v, float du, float dv) {
			this.v = v;
			this.u = u;
			this.du = du;
			this.dv = dv;
		}

		@Override
		public void drawPartial(double x, double y, double width, double height, float x1, float y1, float x2,
				float y2) {
			bindTexture();
			double xi = x + width * x1, xf = x + width * x2, yi = y + height * y1, yf = y + height * y2;
			float ui = u + du * x1, uf = u + du * x2, vi = v + dv * y1, vf = v + dv * y2;
			BufferBuilder buf = Tessellator.getInstance().getBuffer();
			buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			buf.pos(xi, yi, 0D).tex(ui, vi).endVertex();
			buf.pos(xi, yf, 0D).tex(ui, vf).endVertex();
			buf.pos(xf, yf, 0D).tex(uf, vf).endVertex();
			buf.pos(xf, yi, 0D).tex(uf, vi).endVertex();
			buf.finishDrawing();
			RenderSystem.enableAlphaTest();
			WorldVertexBufferUploader.draw(buf);
		}

	}
}
