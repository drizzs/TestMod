package spectralmc.elepets.common.tags;


import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagCollection;
import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.api.pet.type.PetType;

import java.util.Optional;

public class TypeTags {

    public static TagCollection<String> collection = new TagCollection<>((collection) -> {
        return Optional.empty();
    }, "type", false, "");

    public static int generation;

    public static void setCollection(TagCollection<String> collectionIn) {
        collection = collectionIn;
        ++generation;
    }

    public static TagCollection<String> getCollection() {
        return collection;
    }

    public static int getGeneration() {
        return generation;
    }

    private static Tag<String> makeWrapperTag(String id) {
        return new TypeWrapper(new ResourceLocation(id));
    }

}
