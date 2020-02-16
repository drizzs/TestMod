package spectralmc.elepets.common.blocks.eggblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import spectralmc.elepets.common.blocks.eggtiles.FaeEggTile;

import javax.annotation.Nullable;

import static net.minecraft.potion.Effects.REGENERATION;

public class FaeEggBlock extends BaseEggBlock {

    public FaeEggBlock() {
        super(Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0), 500, 600, "fae");
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FaeEggTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult p_225533_6_) {
        TileEntity entity = world.getTileEntity(pos);
        if (entity instanceof FaeEggTile) {
            ItemStack stack = player.getHeldItem(hand);
            if (stack.getItem() instanceof PotionItem) {
                for (EffectInstance effectInstance : PotionUtils.getEffectsFromStack(stack)) {
                    if (effectInstance.getEffectName().equals(REGENERATION.getName())) {
                        ((FaeEggTile) entity).setChallengeGoal();
                        stack.shrink(1);
                        ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                        bottle.setCount(1);
                        player.addItemStackToInventory(bottle);
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        return ActionResultType.FAIL;
    }

}
