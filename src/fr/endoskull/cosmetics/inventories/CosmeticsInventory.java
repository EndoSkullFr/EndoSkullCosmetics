package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Material;

public class CosmeticsInventory extends CustomGui {
    public CosmeticsInventory() {
        super(3, "§dEndoSkull Cosmétiques");
        setItem(11, new CustomItemStack(Material.NAME_TAG).setName("§6Tags"), player -> {
            player.performCommand("tag");
        });
        setItem(13, new CustomItemStack(Material.BLAZE_POWDER).setName("§6Particules"), player -> {
            player.performCommand("particles");
        });
        setItem(15, new CustomItemStack(Material.MONSTER_EGG).setName("§6Pets"), player -> {
            player.performCommand("pets");
        });
    }
}
