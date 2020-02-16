package spectralmc.elepets.common.blocks.eggtiles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleType;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.INBTSerializable;
import spectralmc.elepets.api.ElePetsRegistries;
import spectralmc.elepets.api.managers.PetManager;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.PetBase;
import spectralmc.elepets.api.pet.StatBase;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.common.entity.BreedingPetEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.PET;
import static spectralmc.elepets.common.content.ElePetsEntityRegistry.TEST_PET;

public class BaseEggTile extends TileEntity implements ITickableTileEntity, INBTSerializable<CompoundNBT> {

    private final PetVariableManager manager = new PetVariableManager();
    private final PetManager petManager = new PetManager();
    public int maxEggTime;
    public int timer;
    protected int challenge;
    private PetType type;
    private final ParticleType<?> particle;
    private UUID id;

    public BaseEggTile(TileEntityType<?> tileEntityTypeIn, ParticleType<?> particleType) {
        super(tileEntityTypeIn);
        this.particle = particleType;
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (getMaxEggTime() > 0) {
                if (getChallengeBoolean()) {
                    ++timer;
                }
                if (isEggCooked()) {
                    babySpawnEvent();
                }
            }
        }
    }

    public void setControllingPlayer(UUID id) {
        this.id = id;
    }

    //Sets Egg Type from the Block
    public void setEggType(PetType type) {
        this.type = type;
    }

    public int getCurrentEggTime() {
        return timer;
    }

    public int getMaxEggTime() {
        return maxEggTime;
    }

    public PetType getPetType() {
        return type;
    }

    public ParticleType<?> getParticle() {
        return particle;
    }

    //Checks to see if the egg is ready to hatch
    public boolean isEggCooked() {
        return getCurrentEggTime() == getMaxEggTime();
    }

    //The Challenge to be met in order for the timer to activate
    public boolean getChallengeBoolean() {
        return getCurrentEggTime() != getMaxEggTime();
    }

    //The total time till it hatches

    public void setMaxEggTime(int maxEggTime) {
        this.maxEggTime = maxEggTime;
    }

    //Russian Roulette of Pet Selection, Attaches the Pet Variables to the Entity when its spawned!
    //Randomized based on rarity 1-5 for now
    private PetBase petSelector() {
        List<PetBase> rarity1 = new ArrayList<>();
        List<PetBase> rarity2 = new ArrayList<>();
        List<PetBase> rarity3 = new ArrayList<>();
        List<PetBase> rarity4 = new ArrayList<>();
        List<PetBase> rarity5 = new ArrayList<>();

        getPetManager().getVariables().stream().filter(variables -> {return variables.getType().equals(PET.get());}).map(variables -> {return (StatBase) variables;}).filter(variable -> {return variable.getPetType().equals(this.getPetType());}).filter(variables -> {return variables.getRarity() == 1;}).forEach(rarity1::add);
        getPetManager().getVariables().stream().filter(variables -> {return variables.getType().equals(PET.get());}).map(variables -> {return (StatBase) variables;}).filter(variable -> {return variable.getPetType().equals(this.getPetType());}).filter(variables -> {return variables.getRarity() == 2;}).forEach(rarity2::add);
        getPetManager().getVariables().stream().filter(variables -> {return variables.getType().equals(PET.get());}).map(variables -> {return (StatBase) variables;}).filter(variable -> {return variable.getPetType().equals(this.getPetType());}).filter(variables -> {return variables.getRarity() == 3;}).forEach(rarity3::add);
        getPetManager().getVariables().stream().filter(variables -> {return variables.getType().equals(PET.get());}).map(variables -> {return (StatBase) variables;}).filter(variable -> {return variable.getPetType().equals(this.getPetType());}).filter(variables -> {return variables.getRarity() == 4;}).forEach(rarity4::add);
        getPetManager().getVariables().stream().filter(variables -> {return variables.getType().equals(PET.get());}).map(variables -> {return (StatBase) variables;}).filter(variable -> {return variable.getPetType().equals(this.getPetType());}).filter(variables -> {return variables.getRarity() == 5;}).forEach(rarity5::add);

        Random random = world.rand;
        int rarityOfBaby = random.nextInt(101);
        int randomLevel1 = rarity1.size() - 1;
        PetBase babyPet = rarity1.get(randomLevel1);
        if (rarityOfBaby > 95) {
            int randomLevel5 = rarity5.size() - 1;
            babyPet = rarity5.get(randomLevel5);
        } else if (rarityOfBaby > 85) {
            int randomLevel4 = rarity4.size() - 1;
            babyPet = rarity4.get(randomLevel4);
        } else if (rarityOfBaby > 70) {
            int randomLevel3 = rarity3.size() - 1;
            babyPet = rarity3.get(randomLevel3);
        } else if (rarityOfBaby > 55) {
            int randomLevel2 = rarity2.size() - 1;
            babyPet = rarity2.get(randomLevel2);
        }

        world.destroyBlock(pos, false);
        return babyPet;
    }

    //Spawns the Entity
    public void babySpawnEvent() {
        PetBase babyPet = petSelector();
        BreedingPetEntity babyEntity = new BreedingPetEntity(TEST_PET.get(), world);
        babyEntity.setPetBase((StatBase) babyPet);
        babyEntity.setHealth(50);
        babyEntity.getPetBase().setPetGender(world);
        babyEntity.getPetBase().setMaturity(getManager().getMaturityFromString("baby"));
        babyEntity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
        babyEntity.setControllingPlayerUUID(id);
        world.addEntity(babyEntity);
    }

    protected List<BlockState> getAboveStates(int aboveBlocks) {
        List<BlockState> stateList = new ArrayList<>();
        for (int x = 1; x <= aboveBlocks; ++x) {
            stateList.add(world.getBlockState(pos.up(x)));
        }
        return stateList;
    }

    //Get Cardinal Positions (not up or down)
    public List<BlockPos> getDirectionalPositions() {
        List<BlockPos> posList = new ArrayList<>();
        posList.add(pos.east());
        posList.add(pos.west());
        posList.add(pos.south());
        posList.add(pos.north());
        return posList;
    }

    public int getChallengeGoal() {
        return challenge;
    }

    //Checks the blocks around you (not above or under)
    public boolean isSurrounded(Tag<Block> tag) {
        int check = 0;
        for (BlockPos targetPos : getDirectionalPositions()) {
            if (world.getBlockState(targetPos).isIn(tag)) {
                check++;
            } else {
                System.out.print("not fire");
            }
        }
        return check >= 4;
    }

    //Compares the biome you're in to the biome you want to check for!
    public boolean worldBiomeTypeChecker(Biome.Category category) {
        return world.getBiome(pos).getCategory().equals(category);
    }

    //Serializer and Deserializer for the Variables so that if the chunks unload the eggs dont reset!
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.challenge = compound.getInt("challenge");
        this.timer = compound.getInt("timer");
        this.maxEggTime = compound.getInt("maxEggTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("challenge", challenge);
        compound.putInt("timer", timer);
        compound.putInt("maxEggTime", maxEggTime);
        return compound;
    }

    public void setChallengeGoal() {
        ++challenge;
    }

    public PetVariableManager getManager() {
        return manager;
    }

    public PetManager getPetManager() {
        return petManager;
    }
}
