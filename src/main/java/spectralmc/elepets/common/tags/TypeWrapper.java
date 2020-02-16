package spectralmc.elepets.common.tags;

import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;

public class TypeWrapper extends Tag<String> {

    private int lastKnownGeneration = -1;
    private Tag<String> cachedTag;

    public TypeWrapper(ResourceLocation resourceLocationIn) {
        super(resourceLocationIn);
    }

    /**
     * Returns true if this set contains the specified element.
     */
    public boolean contains(String itemIn) {
        if (this.lastKnownGeneration != TypeTags.generation) {
            this.cachedTag = TypeTags.collection.getOrCreate(this.getId());
            this.lastKnownGeneration = TypeTags.generation;
        }

        return this.cachedTag.contains(itemIn);
    }

    public Collection<String> getAllElements() {
        if (this.lastKnownGeneration != TypeTags.generation) {
            this.cachedTag = TypeTags.collection.getOrCreate(this.getId());
            this.lastKnownGeneration = TypeTags.generation;
        }

        return this.cachedTag.getAllElements();
    }

    public Collection<Tag.ITagEntry<String>> getEntries() {
        if (this.lastKnownGeneration != TypeTags.generation) {
            this.cachedTag = TypeTags.collection.getOrCreate(this.getId());
            this.lastKnownGeneration = TypeTags.generation;
        }

        return this.cachedTag.getEntries();
    }
}
