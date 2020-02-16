package spectralmc.elepets.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import static spectralmc.elepets.ElePets.MOD_ID;

public class ElePetsTags {

    public static final Tag<Item> HERBIVORE = dietTag("herbivore");
    public static final Tag<Item> CARNIVORE = dietTag("carnivore");
    public static final Tag<Item> OMNIVORE = dietTag("omnivore");
    public static final Tag<Item> PESCATARIAN = dietTag("pescetarian");
    public static final Tag<Item> METALLIVORE = dietTag("metallivore");

    private static Tag<Item> dietTag(String name) {
        return new ItemTags.Wrapper(new ResourceLocation(MOD_ID + ":" + "diet/" + name));
    }

    public static final Tag<Block> FIREEGG = eggTags("warm_blanket");
    public static final Tag<Block> WATEREGG = eggTags("wet_blanket");
    public static final Tag<Block> EARTHEGG = eggTags("hard_blanket");
    public static final Tag<Block> LIGHTEGG = eggTags("bright_blanket");

    private static Tag<Block> eggTags(String name) {
        return new BlockTags.Wrapper(new ResourceLocation(MOD_ID + ":" + "egg/" + name));
    }

}
