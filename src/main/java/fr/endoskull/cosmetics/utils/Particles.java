package fr.endoskull.cosmetics.utils;

import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Particles {
    WHISP(new ItemStack(Material.TORCH), "Feux follets", "Deux petites flammes qui tournent autour de vous", 1000),
    SCANNER(new ItemStack(Material.GLASS), "Scanner", "Faîtes vous scanner par un cercle vert montant et descandant. Serez-vous l'imposteur ?", 1000),
    SPIRAL(new ItemStack(Material.LEASH), "Spirale", "Créez des spirales de feux sur le sol", 1000),
    WHIRLWIND(new ItemStack(Material.HOPPER), "Tourbillon", "Créez un effet de tourbillon sur le sol à vos pieds", 1000),
    SPEED(new ItemStack(Material.GOLD_BOOTS), "Chaussures de course", "Grâce à ses chaussures vous pourrez courir plus vite (speed 5)", 2000),
    JUMP(new ItemStack(Material.DIAMOND_BOOTS), "Bottes accordéons", "Faîtes des sauts tel un kangourou à l'aide de ses bottes accordéons (jump boost 5)", 2000),
    HELICOPTER(new ItemStack(Material.NETHER_STAR), "Hélicoptère", "Des hélices au dessus de votre tête qui vous permettent de vous envoler en faisant un double saut", 10000),
    REACTOR(new ItemStack(Material.PISTON_BASE), "Réacteur", "Accrochez ce réacteur dans votre dos pour vous propulsez en avant lorsque que vous appuyez sur la touche de sneak en étant au sol", 10000),
    DOUBLE_JUMP(new ItemStack(Material.TNT), "Semelles explosives", "Obtenez la capacité de faire des doubles sauts en utilisant ces chaussures à semelle explosive", 10000),
    FLY(new ItemStack(Material.FEATHER), "Ailes de feu", "Envolez vous dans le ciel avec des ailes de feu mais attention à ne pas trop vous approcher du soleil sinon vous finirez brûlé comme Icare", 50000),
    INFINITE_JUMP(new CustomItemStack(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK), "Tatanes ténébreuses", "Rassemblez le pouvoir mystique de ses chaussure pour débloquer l'abilité de faire des doubles sauts à l'infini", 50000),
    ANGEENFER(new ItemStack(Material.GOLD_NUGGET), "Ange de l'enfer", "Créez un cercle maléfique au dessus de vous comme si vous étiez un ange !", 2000);
    private ItemStack item;
    private String name;
    private String desc;
    private int price;

    Particles(ItemStack item, String name, String desc, int price) {
        this.item = item;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public static String split(String message) {
        if (message.length() < 30) return message;
        if (!message.contains(" ")) return message;
        String[] messages = message.split(" ");
        String newMessage = "";
        int i = 0;
        for (String s : messages) {
            if (i > 30) {
                i = 0;
                newMessage += "\n";
            }
            newMessage += s + " ";
            i += s.length();
        }
        return "§7" + newMessage.replace("\n", "\n§7");
    }
}
