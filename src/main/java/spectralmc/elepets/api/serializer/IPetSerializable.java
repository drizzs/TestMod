package spectralmc.elepets.api.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public interface IPetSerializable<T extends IPetVariables> extends IForgeRegistryEntry<IPetSerializable<?>> {

    T read(ResourceLocation petVariableID, JsonObject json);

    @Nullable
    T read(ResourceLocation petVariableID, PacketBuffer buffer);

    void write(PacketBuffer buffer, T petVariable);

}
