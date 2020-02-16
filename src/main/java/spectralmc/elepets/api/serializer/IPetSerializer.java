package spectralmc.elepets.api.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public interface IPetSerializer<T extends IPetVariables> extends IForgeRegistryEntry<IPetSerializer<?>> {

    T read(ResourceLocation petVariableID, JsonObject json);

    @Nullable
    T read(ResourceLocation petVariableID, PacketBuffer buffer);

    void write(PacketBuffer buffer, T petVariable);

}
