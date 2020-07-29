package drizzs.magicnmonsters.client.overlay;

import drizzs.magicnmonsters.client.draw.IDrawable;
import drizzs.magicnmonsters.client.draw.UiTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

import static drizzs.magicnmonsters.MagicMon.MOD_ID;
import static drizzs.magicnmonsters.capability.MagicPowerCap.getMagicPower;
import static drizzs.magicnmonsters.capability.MagicPowerCap.getMaxMagicPower;

public class MagicPowerOverlay {

    private static final IDrawable EMPTY_MAGIC_BAR = new UiTexture(new ResourceLocation(MOD_ID, "textures/gui/empty_bar.png"),120,20).getFullArea();
    private static final IDrawable FULL_MAGIC_BAR = new UiTexture(new ResourceLocation(MOD_ID, "textures/gui/full_bar.png"),120,20).getFullArea();

    public static MagicPowerOverlay OVERLAY = new MagicPowerOverlay();

    private final PlayerEntity player;
    private final Minecraft mc;

    private MagicPowerOverlay(){
        player = Minecraft.getInstance().player;
        mc = Minecraft.getInstance();
    }

    private PlayerEntity getPlayer() {
        return player;
    }

    public void buildOverlay(){
        int magicBarX = mc.getMainWindow().getScaledWidth()/2 - 60;
        int magicBarY = 10;
        EMPTY_MAGIC_BAR.draw(magicBarX,magicBarY,120,10);
        int magicPower = getMagicPower(getPlayer());
        int maxPower = getMaxMagicPower(getPlayer());
        FULL_MAGIC_BAR.drawPartial(magicBarX,magicBarY,120,10,0, 0, (float)magicPower/maxPower, 1);
    }
}
