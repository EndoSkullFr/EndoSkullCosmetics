package fr.endoskull.cosmetics.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ParticleUtils {

    public static boolean hasParticle(Player player, Particles particle) {
        if (player.hasPermission("endoskull.particles." + particle.toString().toLowerCase())) return true;
        Account account = AccountProvider.getAccount(player.getUniqueId());
        return account.getProperty("cosmetics/particles/" + particle.toString().toLowerCase(), "false").equalsIgnoreCase("true");
    }

    public static void addParticle(Player player, Particles particle) {
        Account account = AccountProvider.getAccount(player.getUniqueId());
        account.setProperty("cosmetics/particles/" + particle.toString().toLowerCase(), "true");
    }

    public static void selectParticle(Player player, Particles particle) {
        String s = particle == null ? "" : particle.toString().toLowerCase();
        Account account = AccountProvider.getAccount(player.getUniqueId());
        account.setProperty("cosmetics/particle", s);
    }
    public static boolean isParticleSelected(Player player, Particles particle) {
        Account account = AccountProvider.getAccount(player.getUniqueId());
        return account.getProperty("cosmetics/particle", "").equalsIgnoreCase(particle.toString().toLowerCase());
    }

    public static void applyParticle(Player player, Particles particles) {
        player.setFlying(false);
        player.setAllowFlight(false);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        if (particles == null) {
            Main.getInstance().getParticles().remove(player);
            return;
        }
        Main.getInstance().getParticles().put(player, particles);

        switch (particles) {
            case SPEED:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
                player.getInventory().setBoots(new CustomItemStack(Material.GOLD_BOOTS).setName("§a" + Particles.SPEED.getName()).setUnbreakable());
                break;
            case JUMP:
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 4));
                player.getInventory().setBoots(new CustomItemStack(Material.DIAMOND_BOOTS).setName("§a" + Particles.JUMP.getName()).setUnbreakable());
                break;
            case DOUBLE_JUMP:
                player.setAllowFlight(true);
                player.getInventory().setBoots(new CustomItemStack(Material.LEATHER_BOOTS).setName("§a" + Particles.DOUBLE_JUMP.getName()).setUnbreakable().setLeatherColor(Color.RED));
                break;
            case INFINITE_JUMP:
                player.setAllowFlight(true);
                player.getInventory().setBoots(new CustomItemStack(Material.LEATHER_BOOTS).setName("§a" + Particles.INFINITE_JUMP.getName()).setUnbreakable().setLeatherColor(Color.BLACK));
                break;
            case HELICOPTER:
                player.setAllowFlight(true);
                player.getInventory().setHelmet(new CustomItemStack(Material.LEATHER_HELMET).setName("§a" + Particles.HELICOPTER.getName()).setUnbreakable().setLeatherColor(Color.RED));
                player.getInventory().setChestplate(new CustomItemStack(Material.LEATHER_CHESTPLATE).setName("§a" + Particles.HELICOPTER.getName()).setUnbreakable().setLeatherColor(Color.RED));
                player.getInventory().setLeggings(new CustomItemStack(Material.LEATHER_LEGGINGS).setName("§a" + Particles.HELICOPTER.getName()).setUnbreakable().setLeatherColor(Color.RED));
                player.getInventory().setBoots(new CustomItemStack(Material.LEATHER_BOOTS).setName("§a" + Particles.HELICOPTER.getName()).setUnbreakable().setLeatherColor(Color.BLUE));
                break;
            case FLY:
                player.setAllowFlight(true);
                player.setFlying(true);
                break;
        }
    }

    public static void sendParticle(EnumParticle particle, Location location, Player player, int amount, float x, float y, float z) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            if (pls.canSee(player)) {
                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                        particle, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), x, y, z, 0, amount);
                ((CraftPlayer) pls).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public static void sendRedstoneParticle(Location location, Player player, float red, float green, float blue) {
        for (Player pls : Bukkit.getOnlinePlayers()) {
            if (pls.canSee(player)) {
                PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                        EnumParticle.REDSTONE, true, (float) location.getX(), (float) location.getY(), (float) location.getZ(), red, green, blue, 1, 0);
                ((CraftPlayer) pls).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }
}
