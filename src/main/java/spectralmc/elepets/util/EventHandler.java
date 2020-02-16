package spectralmc.elepets.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Timer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import spectralmc.elepets.common.blocks.eggtiles.SpiritEggTile;

import static spectralmc.elepets.ElePets.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onDeathEvent(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        BlockPos pos = entity.getPosition();
        World world = entity.world;
        for (int x = -10; x < 10; ++x) {
            for (int y = -10; y < 10; ++y) {
                for (int z = -10; z < 10; ++z) {
                    TileEntity egg = world.getTileEntity(pos.add(x, y, z));
                    if (egg instanceof SpiritEggTile) {
                        ((SpiritEggTile) egg).setChallengeGoal();
                    }
                }
            }
        }

                /*if (entity instanceof EndermanEntity) {
            if (Objects.equals(((EndermanEntity) entity).getHeldBlockState(), DARK_EGG.get().getDefaultState())) {
                endermanSpawn(pos, world);
            }
        } */
    }

    public void endermanSpawn(BlockPos pos, World world) {
        EndermanEntity entity = new EndermanEntity(EntityType.ENDERMAN, world);
        entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(entity);
    }

}
