package fr.endoskull.cosmetics;

import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import fr.endoskull.cosmetics.commands.*;
import fr.endoskull.cosmetics.inventories.MusicInventory;
import fr.endoskull.cosmetics.listeners.ParticleListener;
import fr.endoskull.cosmetics.listeners.PetListener;
import fr.endoskull.cosmetics.listeners.PlayerListener;
import fr.endoskull.cosmetics.utils.CustomEntityType;
import fr.endoskull.cosmetics.utils.Musics;
import fr.endoskull.cosmetics.utils.Particles;
import fr.endoskull.cosmetics.utils.ParticlesTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;
    private final HashMap<UUID, LivingEntity> pets = new HashMap<>();
    private final HashMap<Player, Particles> particles = new HashMap<>();
    private HashMap<UUID, RadioSongPlayer> songPlayer = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        getCommand("pets").setExecutor(new PetsCommand());
        getCommand("particules").setExecutor(new ParticlesCommand());
        getCommand("cosmetics").setExecutor(new CosmeticsCommand());
        getCommand("tag").setExecutor(new TagCommand());
        getCommand("music").setExecutor(new MusicCommand());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new ParticleListener(this), this);

        CustomEntityType.registerAllEntities();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ParticlesTask(this), 20, 2);

        for (Musics value : Musics.values()) {
            Main.getInstance().saveResource("musics/" + value.getFile(), false);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        for (UUID uuid : pets.keySet()) {
            pets.get(uuid).remove();
        }
        super.onDisable();
    }

    public static Main getInstance() {
        return instance;
    }

    public HashMap<UUID, LivingEntity> getPets() {
        return pets;
    }

    public HashMap<Player, Particles> getParticles() {
        return particles;
    }

    public HashMap<UUID, RadioSongPlayer> getSongPlayer() {
        return songPlayer;
    }

}
