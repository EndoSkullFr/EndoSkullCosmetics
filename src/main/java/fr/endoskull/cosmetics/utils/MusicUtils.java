package fr.endoskull.cosmetics.utils;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.cosmetics.Main;
import org.bukkit.entity.Player;

import java.io.File;

public class MusicUtils {

    public static void selectMusic(Player player, Musics music) {
        String s = music == null ? "" : music.toString().toLowerCase();
        Account account = AccountProvider.getAccount(player.getUniqueId());
        account.setProperty("cosmetics/music", s);
    }
    public static boolean isMusicSelected(Player player, Musics musics) {
        Account account = AccountProvider.getAccount(player.getUniqueId());
        return account.getProperty("cosmetics/music", "").equalsIgnoreCase(musics.toString().toLowerCase());
    }
    public static boolean hasMusicSelected(Player player) {
        Account account = AccountProvider.getAccount(player.getUniqueId());
        return !account.getProperty("cosmetics/music", "").equalsIgnoreCase("");
    }
    public static Musics getMusicSelected(Player player) {
        Account account = AccountProvider.getAccount(player.getUniqueId());
        return Musics.valueOf(account.getProperty("cosmetics/music", "").toUpperCase());
    }

    public static void playMusic(Player player, Musics music) {
        Song song = NBSDecoder.parse(new File(Main.getInstance().getDataFolder(), "musics/" + music.getFile()));
        if (Main.getInstance().getSongPlayer().containsKey(player.getUniqueId())) {
            Main.getInstance().getSongPlayer().get(player.getUniqueId()).destroy();
        }
        RadioSongPlayer rsp = new RadioSongPlayer(song);
        rsp.addPlayer(player);
        rsp.setPlaying(true);
        rsp.setRepeatMode(RepeatMode.ALL);
        Main.getInstance().getSongPlayer().put(player.getUniqueId(), rsp);
        player.sendMessage("§eEndoSkull §8>> §7Musique en cours de lecture §a" + music.getName());
    }
}
