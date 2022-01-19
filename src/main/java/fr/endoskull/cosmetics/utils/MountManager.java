package fr.endoskull.cosmetics.utils;

import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.pets.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MountManager {
    public static double maxHealth = 1.2d;
    public static float mountSpeed = 0.15f;
    public static float villagerSpeed = 0.05f;

    public static void ridePig(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideablePig(world, player), player);
    }
    public static void rideCow(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableCow(world), player);
    }
    public static void rideMushroom(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableMushroom(world), player);
    }
    public static void rideSheep(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableSheep(world), player);
    }
    public static void rideChicken(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableChicken(world), player);
    }
    public static void rideWolf(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableWolf(world), player);
    }
    public static void rideCat(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableCat(world), player);
    }
    public static void rideRabbit(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableRabbit(world), player);
    }
    public static void rideVillager(Player player) {
        Location loc = player.getLocation();
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        make(new RideableVillager(world), player);
    }

    public static void make(EntityLiving nmsEntity, Player player) {
        if (!canSummonMount(player.getLocation())) {
            player.sendMessage("§eEndoSkull §8>> §cVous avez besoin de plus d'espace pour faire apparaître votre pet");
            return;
        }
        if (Main.getInstance().getPets().containsKey(player.getUniqueId())) {
            Main.getInstance().getPets().get(player.getUniqueId()).remove();
        }
        LivingEntity mount = (LivingEntity) nmsEntity.getBukkitEntity();
        mount.setCustomName(Pets.getByEntityType(mount.getType()).getDisplayName() + " de "+ player.getName());
        mount.setCustomNameVisible(true);
        followPlayer(player, mount, 1.75);
        if (mount instanceof Ageable) ((Ageable) mount).setBaby();

        Location loc = player.getLocation();
        World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        nmsEntity.setPosition(loc.getX(), loc.getY() + 0.3, loc.getZ());
        nmsWorld.addEntity(nmsEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        //mount.setPassenger(player);
        mount.setHealth(maxHealth);
        Main.getInstance().getPets().put(player.getUniqueId(), mount);
    }
    /*public static boolean shouldDie(EntityLiving mount, Player rider) {
        if (mount.passenger == null || !(mount.passenger instanceof EntityHuman)) {
            mount.die();
            return true;
        }
        return false;
    }*/

    public static boolean canSummonMount(Location loc) {
        org.bukkit.World world = loc.getWorld();
        Block block = loc.getBlock();
        for (int x = loc.getBlockX() - 1; x <= loc.getBlockX() + 1; x++) {
            for (int y = loc.getBlockY(); y <= loc.getBlockY() + 1; y++) {
                for (int z = loc.getBlockX() - 1; z <= loc.getBlockX() + 1; z++) {
                    block = world.getBlockAt(x, y, z);
                    if (block.getType().isSolid()) return false;
                }
            }
        }
        return true;
    }

    public static void followPlayer(Player player, LivingEntity entity, double d) {
        final LivingEntity e = entity;
        final Player p = player;
        final float f = (float) d;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
            if (player.getLocation().distance(entity.getLocation()) > 10) entity.teleport(player);

            ((EntityInsentient) ((CraftEntity) e).getHandle()).getNavigation().a(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), f);
        }, 20, 20);
    }

}
