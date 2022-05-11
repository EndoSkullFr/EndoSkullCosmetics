package fr.endoskull.cosmetics.inventories;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.MusicUtils;
import fr.endoskull.cosmetics.utils.Musics;
import fr.endoskull.cosmetics.utils.ParticleUtils;
import fr.endoskull.cosmetics.utils.Particles;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.File;

public class MusicInventory extends CustomGui {
    private static int[] glassSlot = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
    private static int[] slots = {10,11,12,13,14,15,16, 19,20,21,22,23,24,25, 28,29,30,31,32,33,34};
    public MusicInventory(Player p) {
        super(5, "§c§lEndoSkull §8» §d§lMusique");
        p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1, 1);
        for (int i : glassSlot) {
            setItem(i, CustomItemStack.getPane(2));
        }
        int i = 0;
        Musics selected = null;
        if (MusicUtils.hasMusicSelected(p)) {
            selected = MusicUtils.getMusicSelected(p);
        }
        for (Musics value : Musics.values()) {
            if (selected != null && selected == value) {
                setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + value.getName()).setGlow());
            } else {
                setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + value.getName()), player -> {
                    MusicUtils.selectMusic(player, value);
                    MusicUtils.playMusic(player, value);
                    player.closeInventory();
                });
            }
            i++;
        }
        if (Main.getInstance().getSongPlayer().containsKey(p.getUniqueId())) {
            setItem(40, new CustomItemStack(Material.REDSTONE_BLOCK).setName("§cArrêter la musique"), player -> {
                MusicUtils.selectMusic(player, null);
                if (Main.getInstance().getSongPlayer().containsKey(player.getUniqueId())) {
                    Main.getInstance().getSongPlayer().get(player.getUniqueId()).destroy();
                    Main.getInstance().getSongPlayer().remove(player.getUniqueId());
                }
                player.sendMessage("§eEndoSkull §8>> §7Vous avez enlever la musique");
                new MusicInventory(player).open(player);
            });
        }
    }
}
