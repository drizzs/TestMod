package spectralmc.elepets.api.database;

import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import spectralmc.elepets.api.database.IPlayerInfo;

import java.util.UUID;

public class PlayerInfoCap {

    public static final Direction DEFAULT_FACING = null;

    //This is the capability that is called from the lazyOptional.
    @CapabilityInject(IPlayerInfo.class)
    public static Capability<IPlayerInfo> PLAYER_INFO_CAP;


    public static LazyOptional<IPlayerInfo> getPlayerInfo(World world) {
        return world.getCapability(PLAYER_INFO_CAP, DEFAULT_FACING);
    }


    //Static Cap Handlers for wins of the Current Team you have!
    public static int getCurrentTeamWins(World world,UUID id){
        return getPlayerInfo(world).map(player ->  player.getCurrentTeamWins(id)).orElse(0);
    }

    public static void addCurrentTeamWins(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.addCurrentTeamWin(id));
    }

    public static void setCurrentTeamWins(World world, UUID id, int amount){
        getPlayerInfo(world).ifPresent(player -> player.setCurrentTeamWins(id,amount));
    }

    public static void resetCurrentTeamWins(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.resetCurrentTeamWins(id));
    }


    //Static Cap Handlers for losses of the Current Team you have!
    public static int getCurrentTeamLosses(World world,UUID id){
        return getPlayerInfo(world).map(player ->  player.getCurrentTeamLosses(id)).orElse(0);
    }

    public static void addCurrentTeamLosses(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.addCurrentTeamLosses(id));
    }

    public static void setCurrentTeamLosses(World world, UUID id, int amount){
        getPlayerInfo(world).ifPresent(player -> player.setCurrentTeamLosses(id,amount));
    }

    public static void resetCurrentTeamLosses(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.resetCurrentTeamWins(id));
    }


    //Static Cap Handlers for total wins through the life of your game!
    public static int getLifeTimeWins(World world,UUID id){
        return getPlayerInfo(world).map(player ->  player.getLifeTimeWins(id)).orElse(0);
    }

    public static void addLifeTimeWins(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.addLifeTimeWins(id));
    }

    public static void setLifeTimeWins(World world, UUID id, int amount){
        getPlayerInfo(world).ifPresent(player -> player.setLifeTimeWins(id,amount));
    }

    public static void resetLifeTimeWins(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.resetLifeTimeWins(id));
    }


    //Static Cap Handlers for total losses through the life of your game!
    public static int getLifeTimeLosses(World world,UUID id){
        return getPlayerInfo(world).map(player ->  player.getLifeTimeLosses(id)).orElse(0);
    }

    public static void addLifeTimeLosses(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.addLifeTimeLosses(id));
    }

    public static void setLifeTimeLosses(World world, UUID id, int amount){
        getPlayerInfo(world).ifPresent(player -> player.setLifeTimeLosses(id,amount));
    }

    public static void resetLifeTimeLosses(World world, UUID id){
        getPlayerInfo(world).ifPresent(player -> player.resetLifeTimeLosses(id));
    }

    public static boolean hasGoneToStarterZone(World world, UUID id){
        return getPlayerInfo(world).map(player ->  player.hasGoneToStarterZone(id)).orElse(false);
    }

    public static void setHasGoneToStarterZone(World world, UUID id, boolean hasGoneToStarterZone){
        getPlayerInfo(world).ifPresent(player -> player.setHasGoneToStarterZone(id,hasGoneToStarterZone));
    }

}
