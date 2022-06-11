package fr.endoskull.cosmetics.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Musics {
    //BABY_SHARK(new ItemStack(Material.MONSTER_EGG), "Baby Shark (sérieusement ?)", "baby shark.nbs"),
    COFFIN_DANCE(new ItemStack(Material.SKULL_ITEM), "Coffin Dance", "coffin dance.nbs"),
    MARIO_BROS(new ItemStack(Material.RABBIT_FOOT), "Mario Bros", "mario bros.nbs"),
    MARIO_GALAXY(new ItemStack(Material.ENDER_PORTAL_FRAME), "Super Mario Galaxy", "mario galaxy.nbs"),
    MARIO_KART(new ItemStack(Material.MINECART), "Mario Kart 64 Raceway", "mario kart.nbs", 49),
    MARIO_WORLD(new ItemStack(Material.GRASS), "Super Mario world", "mario world.nbs"),
    MEGALOVANIA(new ItemStack(Material.BONE), "Megalovania", "megalovania.nbs"),
    RUSH_E(new ItemStack(Material.GLASS_BOTTLE), "Rush E", "rush e.nbs", 220),
    SMASH_BROS(new ItemStack(Material.ARROW), "Super Smash Bros Ultimate", "smash bros ultimate.nbs"),
    LEGEND_ZELDA(new ItemStack(Material.DIAMOND_SWORD), "The Legend Of Zelda", "the legend of zelda.nbs"),
    //OCARINA_OF_TIME(new ItemStack(Material.NOTE_BLOCK), "The Legend of Zelda: Ocarina of Time", "baby shark.nbs"),
    POKEMON(new ItemStack(Material.EGG), "Pokemon Rouge et Bleu", "Pokemon Red-Blue Title.nbs"),
    RASPUTIN(new ItemStack(Material.GOLD_INGOT), "Rasputin", "rasputin.nbs", 1050),
    SOVIET_UNION(new ItemStack(Material.WOOD_HOE), "Soviet National Anthem URSS", "soviet_national_anthem_urss.nbs", 20),
    JOJO(new ItemStack(Material.BLAZE_POWDER), "Giorno's Theme", "jojo.nbs"),
    MII_CHANNEL(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), "Mii Channel", "mii channel.nbs"),
    NGGYU(new ItemStack(Material.ANVIL), "Never Gonna Give You Up", "nggyu.nbs"),
    PIRATES_CARAIBES(new ItemStack(Material.IRON_SWORD), "He's a Pirate", "pirates des caraibes.nbs"),
    STAR_WARS(new ItemStack(Material.BEACON, 1), "Star Wars - Dark Vador Theme", "star wars.nbs"),
    ONE_PIECE(new ItemStack(Material.BOAT), "One Piece - We are", "une_piece_we_are.nbs"),
    TETRIS(new ItemStack(Material.WOOD_STAIRS), "Tetris", "tetris.nbs"),
    GHOSTBUSTERS(new ItemStack(Material.MONSTER_EGG, 1, (byte) 56), "Ghostbusters", "ghostbusters.nbs", 200);

    private ItemStack item;
    private String name;
    private String file;
    private int startingTick = 0;

    Musics(ItemStack item, String name, String file) {
        this.item = item;
        this.name = name;
        this.file = file;
    }

    Musics(ItemStack item, String name, String file, int startingTick) {
        this.item = item;
        this.name = name;
        this.file = file;
        this.startingTick = startingTick;
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

    public int getStartingTick() {
        return startingTick;
    }
}
