package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.api.spigot.utils.Languages;
import fr.endoskull.cosmetics.utils.CosmeticsMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CosmeticsInventory extends CustomGui {
    public CosmeticsInventory(Player p) {
        super(3, Languages.getLang(p).getMessage(CosmeticsMessage.GUI_COSMETICS), p);
        Languages lang = Languages.getLang(p);
        setItem(11, new CustomItemStack(Material.NAME_TAG).setName(lang.getMessage(CosmeticsMessage.TAGS)).setLore(lang.getMessage(CosmeticsMessage.TAGS_DESC)), player -> {
            player.performCommand("tag");
        });
        setItem(13, new CustomItemStack(Material.BLAZE_POWDER).setName(lang.getMessage(CosmeticsMessage.PARTICLES)), player -> {
            player.performCommand("particles");
        });
        setItem(15, new CustomItemStack(Material.GOLD_RECORD).setName(lang.getMessage(CosmeticsMessage.MUSICS)).setLore(lang.getMessage(CosmeticsMessage.MUSICS_DESC)), player -> {
            player.performCommand("music");
        });
    }
}
