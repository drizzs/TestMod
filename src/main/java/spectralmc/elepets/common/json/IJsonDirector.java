package spectralmc.elepets.common.json;

import net.minecraft.util.ResourceLocation;

public interface IJsonDirector<T> {
    void put(ResourceLocation resourceLocation, T value);

    void clear();
}