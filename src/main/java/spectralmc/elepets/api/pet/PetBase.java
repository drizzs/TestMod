package spectralmc.elepets.api.pet;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import spectralmc.elepets.api.ElePetsRegistries;
import spectralmc.elepets.api.managers.PetVariableManager;
import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.PetMaturity;
import spectralmc.elepets.api.pet.nature.PetNature;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.api.serializer.IPetSerializable;
import spectralmc.elepets.api.serializer.VariableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.NATURE;

public class PetBase implements IPetBase {

    private final ResourceLocation id;
    private final String name;
    protected final PetType type;
    private final PetDiet diet;
    protected PetNature nature;
    protected PetGender gender;

    private PetMaturity maturity;

    private final boolean canBreed;

    private int age = 0;

    private final int rarity;

    private final PetVariableManager manager = new PetVariableManager();

    public PetBase(ResourceLocation id, String name, PetType type, PetGender gender, PetDiet diet, PetNature nature, int rarity, boolean canBreed) {
        this(id, name ,type, diet, rarity, canBreed);
        this.gender = gender;
        this.nature = nature;
    }

    public PetBase(ResourceLocation id, String name, PetType type, PetDiet diet, int rarity, boolean canBreed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.diet = diet;
        //checks if this can lay eggs
        this.canBreed = canBreed;

        //Controls spawn from eggs and natural spawns
        this.rarity = rarity;

    }

    @Override
    public ResourceLocation getLocation() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matchPetVariable(String name) {
        return false;
    }

    @Override
    public IPetSerializable<?> getSerializer() {
        return null;
    }

    @Override
    public VariableType<?> getType() {
        return null;
    }

    //Randomizes Nature
    private PetNature setRandomPetNature(World world) {
        Random random = world.rand;
        List<PetNature> natureList = new ArrayList<>();
        getManager().getVariables().stream().filter(variables -> {return variables.getType().equals(NATURE.get());}).map(variables -> {return (PetNature)variables;}).forEach(natureList::add);
        int randomInt = random.nextInt(natureList.size());
        return natureList.get(randomInt);
    }

    //Randomizes Gender based on % of Male Population for each type
    public PetGender setPetGender(World world) {
        if(gender == null) {
            Random random = world.rand;
            if (getPetType().canBeNeutral()) {
                return getManager().getGenderFromString("neutral");
            } else {
                int randint = random.nextInt(10);
                if (randint == 10 % getPetType().getMalePercent()) {
                    return getManager().getGenderFromString("male");
                } else {
                    return getManager().getGenderFromString("female");
                }
            }
        } else{
            return gender;
        }
    }

    public void setPetGender(PetGender gender) {
        this.gender = gender;
    }

    public void setMaturity(PetMaturity maturity) {
        this.maturity = maturity;
    }

    public void addAge() {
        age++;
    }

    public int getAge() {
        return age;
    }

    //Getters
    @Override
    public PetNature getPetNature() {
        return nature;
    }

    @Override
    public PetGender getPetGender() {
        return gender;
    }

    @Override
    public PetDiet getDiet() {
        return diet;
    }

    @Override
    public PetType getPetType() {
        return type;
    }

    public PetMaturity getPetMaturity() {
        return maturity;
    }

    public int getRarity() {
        return rarity;
    }

    //Checks if pet can lay eggs at all
    public boolean canBreed() {
        return canBreed;
    }

    public PetVariableManager getManager() {
        return manager;
    }
}
