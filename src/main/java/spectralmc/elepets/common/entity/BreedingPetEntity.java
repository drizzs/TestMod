package spectralmc.elepets.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import spectralmc.elepets.common.blocks.eggblocks.BaseEggBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static spectralmc.elepets.common.content.ElePetsEggRegistry.EGG_BLOCK;

public class BreedingPetEntity extends BasePetEntity {

    protected static final DataParameter<Byte> OWNED = EntityDataManager.createKey(BreedingPetEntity.class, DataSerializers.BYTE);

    private int lookingForLove;
    private UUID controllingPlayer;

    public BreedingPetEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    //Handles the Timer and Particles
    @Override
    public void livingTick() {
        super.livingTick();
        if (this.lookingForLove > 0) {
            --this.lookingForLove;
            if (this.lookingForLove % 10 == 0) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OWNED, (byte) 0);
    }

    //Compares Items to the DietLists For This Pet
    public boolean isBreedingItem(ItemStack stack) {
        if (world.isRemote) return false;
        return getPetBase().getDiet().isDietItem(stack);
    }

    //Drops Egg Item based on Type, Randomizes for multiple types.
    public void dropEggItem(BreedingPetEntity entity) {
        List<Item> eggs = new ArrayList<>();
        for (RegistryObject<Block> blockObject : EGG_BLOCK.getEntries()) {
            Block block = blockObject.get();
            if (block instanceof BaseEggBlock) {
                if (((BaseEggBlock) block).getType().equals(getPetBase().getPetType()) || ((BaseEggBlock) block).getType().equals(entity.getPetBase().getPetType())) {
                    eggs.add(block.asItem());
                }
            }
        }
        int totalEggs = eggs.size();
        int randEgg = rand.nextInt(totalEggs);

        Item item = eggs.get(randEgg);
        ItemStack stack = item.getDefaultInstance();
        stack.setCount(1);
        ItemEntity itemEntity = new ItemEntity(world, getPosX() - 1, getPosY() + 1, getPosZ() - 1, stack);

        world.addEntity(itemEntity);
        resetLoveSearch();
    }

    //Handles the interaction with the food and sets the love!
    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (this.isBreedingItem(itemstack)) {
            if (player.getUniqueID().equals(getControllingPlayer()))
                if (!this.world.isRemote && this.canBreed()) {
                    this.consumeItem(player, itemstack);
                    setLookingForLove(600);
                    player.func_226292_a_(hand, true);
                    return true;
                }
        }
        return false;
    }

    public void setControllingPlayer(PlayerEntity controllingPlayer) {
        this.controllingPlayer = controllingPlayer.getUniqueID();
    }

    public void setControllingPlayerUUID(UUID id) {
        this.controllingPlayer = id;
    }

    public UUID getControllingPlayer() {
        return controllingPlayer;
    }

    public void setLookingForLove(int loveSearchTime) {
        lookingForLove = loveSearchTime;
    }

    //Checks to see if the other entity can breed with this entity!
    public boolean isValidPartner(BreedingPetEntity entity) {
        if (entity == this) {
            return false;
        } else if (entity.getClass() != this.getClass()) {
            return false;
        } else if (entity.getPetGender() == getVariableManager().getGenderFromString("none")) {
            return false;
        } else {
            return this.isSearchingForLove() && entity.isSearchingForLove() && entity.getPetGender() != this.getPetGender();
        }
    }

    //Checks the pet to see if it can breed
    //Enigma dragons(some cannot breed)
    public boolean canBreed() {
        return this.getPetBase().canBreed();
    }

    //Simple consumes the item if player is not in creative
    protected void consumeItem(PlayerEntity player, ItemStack stack) {
        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }
    }

    //Returns whether or not the pet is searching for love!
    public boolean isSearchingForLove() {
        return this.lookingForLove > 0;
    }

    //Resets the search and turns off the love
    public void resetLoveSearch() {
        this.lookingForLove = 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 18) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.HEART, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D, this.getPosZRandom(1.0D), d0, d1, d2);
            }
        } else {
            super.handleStatusUpdate(id);
        }
    }

    //Find Owner Entity from UUID
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getControllingPlayer();
            return uuid == null ? null : this.world.getPlayerByUuid(uuid);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    //Sets Owner ID
    private void setOwnerId(UUID id) {
        controllingPlayer = id;
    }

    //Sets Custom AI
    public void setOwned(boolean owned) {
        byte b0 = this.dataManager.get(OWNED);
        if (owned) {
            this.dataManager.set(OWNED, (byte) (b0 | 4));
        } else {
            this.dataManager.set(OWNED, (byte) (b0 & -5));
        }
        //TODO Setup Custom Tamed AI

    }

    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        return true;
    }

    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && super.canAttack(target);
    }

    public boolean isOwner(LivingEntity entityIn) {
        return entityIn == this.getOwner();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.getControllingPlayer() == null) {
            compound.putString("OwnerUUID", "");
        } else {
            compound.putString("OwnerUUID", this.getControllingPlayer().toString());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        String id;
        if (compound.contains("OwnerUUID", 8)) {
            id = compound.getString("OwnerUUID");
        } else {
            String s1 = compound.getString("Owner");
            id = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }

        if (!id.isEmpty()) {
            try {
                this.setOwnerId(UUID.fromString(id));
                this.setOwned(true);
            } catch (Throwable var4) {
                this.setOwned(false);
            }
        }
    }

    public void onDeath(DamageSource cause) {
        if (!world.isRemote) {
            PlayerEntity entity = world.getPlayerByUuid(getControllingPlayer());
            Entity murderer = cause.getTrueSource();
            if (entity != null) {
                if (isOwner(entity)) {
                    if (murderer != null) {
                        if (murderer instanceof BreedingPetEntity) {
                            murderer.onKillEntity(this);
                        }
                    }
                    this.dead = true;
                    this.getCombatTracker().reset();
                    this.world.setEntityState(this, (byte) 3);
                    //TODO: Set Death Pose

                    //TODO: Add Link to GUI Inventory where this Pet is Stored and Set it to Broken

                }
            } else {
                //TODO: Add GUI Question to Capture if you're holding a crystal.

                super.onDeath(cause);
            }
        }
    }

}
