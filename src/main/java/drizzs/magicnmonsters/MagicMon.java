package drizzs.magicnmonsters;


import drizzs.magicnmonsters.capability.IMagicPower;
import drizzs.magicnmonsters.capability.MagicPower;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;


@Mod(MagicMon.MOD_ID)
public class MagicMon {
	public static final String MOD_ID = "magicmon";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public MagicMon() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}

	private void setup(final FMLCommonSetupEvent event) {

		CapabilityManager.INSTANCE.register(IMagicPower.class, new Capability.IStorage<IMagicPower>() {
			@Nullable
			@Override
			public INBT writeNBT(Capability<IMagicPower> capability, IMagicPower instance, Direction side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<IMagicPower> capability, IMagicPower instance, Direction side, INBT nbt) {
				instance.deserializeNBT((CompoundNBT) nbt);
			}
		}, MagicPower::new);
	}
}
