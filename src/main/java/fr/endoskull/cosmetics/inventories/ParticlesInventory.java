package fr.endoskull.cosmetics.inventories;

import dev.esophose.playerparticles.api.PlayerParticlesAPI;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ParticlesInventory extends CustomGui {
    public ParticlesInventory(Player p) {
        super(3, "§c§lEndoSkull §8» §d§lParticules");
        setItem(12, new CustomItemStack(Material.FIREWORK).setName("§6Choisir l'effet"), player -> {
            new ParticlesEffectInventory().open(player);
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
        });
        setItem(14, new CustomItemStack(Material.NETHER_STAR).setName("§6Choisir le style"), player -> {
            new ParticlesStyleInventory().open(player);
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
        });
        if (p != null) {
            if (PlayerParticlesAPI.getInstance().getActivePlayerParticles(p).size() > 0) {
                setItem(26, new CustomItemStack(Material.REDSTONE_BLOCK).setName("§cRetirer vos particules"), player -> {
                    PlayerParticlesAPI.getInstance().resetActivePlayerParticles(player);
                    new ParticlesInventory(player).open(player);
                    player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                });
            }
        }
    }
}
