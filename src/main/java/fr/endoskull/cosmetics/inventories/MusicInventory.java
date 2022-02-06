package fr.endoskull.cosmetics.inventories;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class MusicInventory extends CustomGui {
    private static HashMap<UUID, RadioSongPlayer> songPlayer = new HashMap<>();
    public MusicInventory(Player p) {
        super(3, "§cEndoSkull §8» §fMusique");
        File musicFolder = new File(Main.getInstance().getDataFolder(), "musics");
        if (!musicFolder.exists()) {
            setItem(0, new CustomItemStack(Material.BARRIER).setName("§cPas de dossier musiques"));
            return;
        }
        if (musicFolder.isDirectory()) {
            int i = 0;
            for (String s : musicFolder.list()) {
                Song song = NBSDecoder.parse(new File(Main.getInstance().getDataFolder(), "musics/" + s));
                setItem(i, new CustomItemStack(Material.GOLD_RECORD).setName(song.getTitle()).setLore("\n" + song.getDescription()), player -> {
                    if (songPlayer.containsKey(player.getUniqueId())) {
                        songPlayer.get(player.getUniqueId()).destroy();
                    }
                    RadioSongPlayer rsp = new RadioSongPlayer(song);
                    rsp.addPlayer(player);
                    rsp.setPlaying(true);
                    songPlayer.put(player.getUniqueId(), rsp);
                });
                i++;
            }
        } else {
            setItem(0, new CustomItemStack(Material.BARRIER).setName("§cPas un dossier"));
        }
    }
}
