package spectralmc.elepets.testing.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import spectralmc.elepets.common.entity.PetEntity;
import spectralmc.elepets.testing.model.TestModel;

import static spectralmc.elepets.ElePets.MOD_ID;

public class TestRenderer extends MobRenderer<PetEntity, TestModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/test_pet.png");

    public TestRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TestModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(PetEntity entity) {
        return TEXTURE;
    }
}
