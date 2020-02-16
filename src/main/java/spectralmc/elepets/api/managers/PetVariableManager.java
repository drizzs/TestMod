package spectralmc.elepets.api.managers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.*;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spectralmc.elepets.api.ElePetsRegistries;
import spectralmc.elepets.api.pet.diet.PetDiet;
import spectralmc.elepets.api.pet.gender.PetGender;
import spectralmc.elepets.api.pet.maturity.PetMaturity;
import spectralmc.elepets.api.pet.nature.PetNature;
import spectralmc.elepets.api.pet.type.PetType;
import spectralmc.elepets.api.serializer.IPetVariables;
import spectralmc.elepets.api.serializer.VariableType;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spectralmc.elepets.api.serializer.SerializerRegistryHandler.TYPE;

public class PetVariableManager extends JsonReloadListener {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private Map<VariableType<?>, Map<ResourceLocation, IPetVariables>> variables = ImmutableMap.of();
    private boolean someVariablesErrored;

    public PetVariableManager() {
        super(GSON, "pet_variables");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonObject> splashList, IResourceManager resourceManagerIn, IProfiler profilerIn) {
        this.someVariablesErrored = false;
        Map<VariableType<?>, ImmutableMap.Builder<ResourceLocation, IPetVariables>> map = Maps.newHashMap();
        for (Entry<ResourceLocation, JsonObject> entry : splashList.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();
            if (resourcelocation.getPath().startsWith("_"))
                continue; //Forge: filter anything beginning with "_" as it's used for metadata.

            try {
                if (!net.minecraftforge.common.crafting.CraftingHelper.processConditions(entry.getValue(), "conditions")) {
                    LOGGER.info("Skipping loading variables {} as it's conditions were not met", resourcelocation);
                    continue;
                }
                IPetVariables ivariables = deserializeRecipe(resourcelocation, entry.getValue());
                if (ivariables == null) {
                    LOGGER.info("Skipping loading variables {} as it's serializer returned null", resourcelocation);
                    continue;
                }
                map.computeIfAbsent(ivariables.getType(), (variable) -> {
                    return ImmutableMap.builder();
                }).put(resourcelocation, ivariables);
            } catch (IllegalArgumentException | JsonParseException jsonparseexception) {
                LOGGER.error("Parsing error loading variables {}", resourcelocation, jsonparseexception);
            }
        }

        this.variables = map.entrySet().stream().collect(ImmutableMap.toImmutableMap((Entry::getKey), (variables) -> {
            return variables.getValue().build();
        }));
        LOGGER.info("Loaded {} variables", (int) map.size());
    }

    public <T extends IPetVariables> Optional<T> getVariable(VariableType<T> variableType, IPetVariables variable) {
        return this.getVariables(variableType).values().stream().flatMap((variables) -> {
            return Util.streamOptional(variableType.matches(variable,variables));
        }).findFirst();
    }

    public <T extends IPetVariables> List<T> getVariableList(VariableType<T> variableType, IPetVariables variable) {
        return this.getVariables(variableType).values().stream().flatMap((variables) -> {
            return Util.streamOptional(variableType.matches(variable,variables));
        }).sorted(Comparator.comparing(IPetVariables::getName)).collect(Collectors.toList());
    }

    private <T extends IPetVariables> Map<ResourceLocation, IPetVariables> getVariables(VariableType<T> variableType) {
        return this.variables.getOrDefault(variableType, Collections.emptyMap());
    }

    public Optional<? extends IPetVariables> getVariable(ResourceLocation variableID) {
        return this.variables.values().stream().map((variable) -> {
            return variable.get(variableID);
        }).filter(Objects::nonNull).findFirst();
    }

    public Collection<IPetVariables> getVariables() {
        return this.variables.values().stream().flatMap((p_215376_0_) -> {
            return p_215376_0_.values().stream();
        }).collect(Collectors.toSet());
    }

    public Stream<ResourceLocation> getKeys() {
        return this.variables.values().stream().flatMap((variables) -> {
            return variables.keySet().stream();
        });
    }

    /**
     * Deserializes a recipe object from json data.
     */
    public static IPetVariables deserializeRecipe(ResourceLocation variableId, JsonObject json) {
        String s = JSONUtils.getString(json, "type");
        return Objects.requireNonNull(ElePetsRegistries.PET_SERIALIZABLE_A.getValue(new ResourceLocation(s))).read(variableId,json);
    }

    @OnlyIn(Dist.CLIENT)
    public void func_223389_a(Iterable<IPetVariables> petVariables) {
        this.someVariablesErrored = false;
        Map<VariableType<?>, Map<ResourceLocation, IPetVariables>> map = Maps.newHashMap();
        petVariables.forEach((variable) -> {
            Map<ResourceLocation, IPetVariables> map1 = map.computeIfAbsent(variable.getType(), (variables) -> {
                return Maps.newHashMap();
            });
            IPetVariables ivariable = map1.put(variable.getLocation(), variable);
            if (ivariable != null) {
                throw new IllegalStateException("Duplicate variables ignored with ID " + variable.getLocation());
            }
        });
        this.variables = ImmutableMap.copyOf(map);
    }

    //GetType
    public PetType getTypeFromString(String name){
        return this.getVariables().stream().filter(this::checkType).map(this::castType).filter(variable -> {return matchType(variable,name);}).findFirst().orElse(null);
    }

    //TypeChecks
    private boolean checkType(IPetVariables variables) {
        return variables.getType() == TYPE.get();
    }

    private PetType castType(IPetVariables variables){
        return (PetType) variables;
    }

    private boolean matchType(PetType type, String name){
        return type.getName().equals(name);
    }

    //GetDiet
    public PetDiet getDietFromString(String name){
        return this.getVariables().stream().filter(this::checkDiet).map(this::castDiet).filter(variable -> {return matchDiet(variable,name);}).findFirst().orElse(null);
    }

    //DietChecks
    private boolean checkDiet(IPetVariables variables) {
        return variables.getType() == TYPE.get();
    }

    private PetDiet castDiet(IPetVariables variables){
        return (PetDiet) variables;
    }

    private boolean matchDiet(PetDiet type, String name){
        return type.getName().equals(name);
    }

    //GetGender
    public PetGender getGenderFromString(String name){
        return this.getVariables().stream().filter(this::checkGender).map(this::castGender).filter(variable -> {return matchGender(variable,name);}).findFirst().orElse(null);
    }

    //GenderChecks
    private boolean checkGender(IPetVariables variables) {
        return variables.getType() == TYPE.get();
    }

    private PetGender castGender(IPetVariables variables){
        return (PetGender) variables;
    }

    private boolean matchGender(PetGender type, String name){
        return type.getName().equals(name);
    }

    //GetMaturity
    public PetMaturity getMaturityFromString(String name){
        return this.getVariables().stream().filter(this::checkMaturity).map(this::castMaturity).filter(variable -> {return matchMaturity(variable,name);}).findFirst().orElse(null);
    }

    //MaturityChecks
    private boolean checkMaturity(IPetVariables variables) {
        return variables.getType() == TYPE.get();
    }

    private PetMaturity castMaturity(IPetVariables variables){
        return (PetMaturity) variables;
    }

    private boolean matchMaturity(PetMaturity type, String name){
        return type.getName().equals(name);
    }

    //GetNature
    public PetNature getNatureFromString(String name){
        return this.getVariables().stream().filter(this::checkNature).map(this::castNature).filter(variable -> {return matchNature(variable,name);}).findFirst().orElse(null);
    }

    //NatureChecks
    private boolean checkNature(IPetVariables variables) {
        return variables.getType() == TYPE.get();
    }

    private PetNature castNature(IPetVariables variables){
        return (PetNature) variables;
    }

    private boolean matchNature(PetNature type, String name){
        return type.getName().equals(name);
    }
}
