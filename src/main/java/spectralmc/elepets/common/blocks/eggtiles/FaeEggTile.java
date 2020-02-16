package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.biome.Biome;
import spectralmc.elepets.common.entity.BreedingPetEntity;

import java.util.List;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.FAE_EGG_TILE;

public class FaeEggTile extends BaseEggTile {

    private static final EntityPredicate splashSearch = (new EntityPredicate()).setDistance(5.0D).allowInvulnerable().allowFriendlyFire().setLineOfSiteRequired();
    private AxisAlignedBB boundingBox;

    public FaeEggTile() {
        super(FAE_EGG_TILE.get(), ParticleTypes.END_ROD);
        this.boundingBox = new AxisAlignedBB(-3, -3, -3, 3, 3, 3);
    }

    @Override
    public boolean getChallengeBoolean() {
        getRegen();
        return getChallengeGoal() == 2 && worldBiomeTypeChecker(Biome.Category.MUSHROOM);
    }

    private void getRegen() {
        boolean activated = false;
        List<PotionEntity> entities = this.world.getEntitiesWithinAABB(EntityType.POTION, boundingBox, EntityPredicates.NOT_SPECTATING);
        for (PotionEntity potions : entities) {
            if (potions.getItem().equals(Items.SPLASH_POTION.getDefaultInstance())) {
                List<EffectInstance> instances = PotionUtils.getEffectsFromStack(potions.getItem());
                for (EffectInstance instance : instances) {
                    if (instance.getEffectName().equals(Effects.REGENERATION.toString())) {
                        if (!world.isRemote && !activated) {
                            setChallengeGoal();
                            activated = true;
                        }
                    }
                }
            }
        }
    }
}
