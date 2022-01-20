package fr.endoskull.cosmetics;

import fr.endoskull.cosmetics.commands.CosmeticsCommand;
import fr.endoskull.cosmetics.commands.ParticlesCommand;
import fr.endoskull.cosmetics.commands.PetsCommand;
import fr.endoskull.cosmetics.commands.TagCommand;
import fr.endoskull.cosmetics.listeners.PetListener;
import fr.endoskull.cosmetics.utils.CustomEntityType;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main instance;
    private HashMap<UUID, LivingEntity> pets = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        getCommand("pets").setExecutor(new PetsCommand());
        getCommand("particules").setExecutor(new ParticlesCommand());
        getCommand("cosmetics").setExecutor(new CosmeticsCommand());
        getCommand("tag").setExecutor(new TagCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PetListener(this), this);

        CustomEntityType.registerAllEntities();
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
}
