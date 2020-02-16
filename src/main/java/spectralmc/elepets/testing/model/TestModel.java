package spectralmc.elepets.testing.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import spectralmc.elepets.common.entity.PetEntity;

public class TestModel extends EntityModel<PetEntity> {

    public ModelRenderer body;

    public TestModel(){
        body = new ModelRenderer(this,0,0);
        body.addBox(-3,-3,-3,6,6,6);
    }

    @Override
    public void render(PetEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {


    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
