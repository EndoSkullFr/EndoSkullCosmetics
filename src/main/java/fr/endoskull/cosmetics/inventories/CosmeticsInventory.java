package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Material;

public class CosmeticsInventory extends CustomGui {
    public CosmeticsInventory() {
        super(3, "§c§lEndoSkull §8» §d§lCosmétiques");
        setItem(11, new CustomItemStack(Material.NAME_TAG).setName("§6Tags").setLore("\n§7Requiert le grade §bHéro"), player -> {
            player.performCommand("tag");
        });
        setItem(13, new CustomItemStack(Material.BLAZE_POWDER).setName("§6Particules"), player -> {
            player.performCommand("particles");
        });
        setItem(15, new CustomItemStack(Material.GOLD_RECORD).setName("§6Musique").setLore("\n§7Requiert le grade §eVIP"), player -> {
            player.performCommand("music");
        });
    }
}
