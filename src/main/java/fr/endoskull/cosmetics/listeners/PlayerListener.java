package fr.endoskull.cosmetics.listeners;

import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Account account = AccountProvider.getAccount(player.getUniqueId());
        if (account.getProperty("cosmetics/hideparticles", "false").equals("true")) {
            Main.getInstance().getHidingParticles().add(player);
        }
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            if (player == null || !player.isOnline()) return;
            if (!account.getProperty("cosmetics/particle", "").equals("")) {
                ParticleUtils.applyParticle(player, Particles.valueOf(account.getProperty("cosmetics/particle")));
            }
            if (MusicUtils.hasMusicSelected(player)) {
                Musics music = MusicUtils.getMusicSelected(player);
                MusicUtils.playMusic(player, music);
            }
        }, 10);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Main.getInstance().getParticles().remove(player);
        if (Main.getInstance().getSongPlayer().containsKey(player.getUniqueId())) {
            Main.getInstance().getSongPlayer().get(player.getUniqueId()).destroy();
            Main.getInstance().getSongPlayer().remove(player.getUniqueId());
        }
        Main.getInstance().getHidingParticles().remove(player);
    }

}
