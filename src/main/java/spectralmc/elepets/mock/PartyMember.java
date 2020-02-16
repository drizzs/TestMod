package spectralmc.elepets.mock;

import net.minecraft.util.ResourceLocation;
import spectralmc.elepets.ElePets;
import spectralmc.elepets.client.ui.util.drawable.UiDrawable;
import spectralmc.elepets.client.ui.util.drawable.UiTexture;

import java.util.Arrays;
import java.util.List;

// mock type for a party member because that stuff isn't implemented yet
public class PartyMember {

    public static final List<PartyMember> SAMPLE = Arrays.asList(
            new PartyMember(
                    new UiTexture(new ResourceLocation(ElePets.MOD_ID, "textures/gui/hud/not_dragon.png"), 48, 80).getFullRegion(),
                    0x2196F3),
            null,
            null,
            null,
            null,
            null
    );

    public final UiDrawable crystalSprite;
    public final int typeColour;

    public PartyMember(UiDrawable crystalSprite, int typeColour) {
        this.crystalSprite = crystalSprite;
        this.typeColour = typeColour;
    }

}
