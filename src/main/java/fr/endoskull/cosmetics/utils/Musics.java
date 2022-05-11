package fr.endoskull.cosmetics.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Musics {
    POKEMON(new ItemStack(Material.EGG), "Pokemon Rouge et Bleu", "Pokemon Red-Blue Title.nbs");

    private ItemStack item;
    private String name;
    private String file;

    Musics(ItemStack item, String name, String file) {
        this.item = item;
        this.name = name;
        this.file = file;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }
}
