package fr.endoskull.cosmetics.utils;

import fr.endoskull.api.commons.lang.MessageUtils;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Particles {
    WHISP(new ItemStack(Material.TORCH), CosmeticsMessage.P_WHISP, CosmeticsMessage.P_WHISP_DESC, 1000),
    SCANNER(new ItemStack(Material.GLASS), CosmeticsMessage.P_SCANNER, CosmeticsMessage.P_SCANNER_DESC, 1000),
    SPIRAL(new ItemStack(Material.LEASH), CosmeticsMessage.P_SPIRAL, CosmeticsMessage.P_SPIRAL_DESC, 1000),
    WHIRLWIND(new ItemStack(Material.HOPPER), CosmeticsMessage.P_WHIRLWIND, CosmeticsMessage.P_WHIRLWIND_DESC, 1000),
    SPEED(new ItemStack(Material.GOLD_BOOTS), CosmeticsMessage.P_SPEED, CosmeticsMessage.P_SPEED_DESC, 2000),
    JUMP(new ItemStack(Material.DIAMOND_BOOTS), CosmeticsMessage.P_JUMP, CosmeticsMessage.P_JUMP_DESC, 2000),
    HELICOPTER(new ItemStack(Material.NETHER_STAR), CosmeticsMessage.P_HELICOPTER, CosmeticsMessage.P_HELICOPTER_DESC, 10000),
    REACTOR(new ItemStack(Material.PISTON_BASE), CosmeticsMessage.P_REACTOR, CosmeticsMessage.P_REACTOR_DESC, 10000),
    DOUBLE_JUMP(new ItemStack(Material.TNT), CosmeticsMessage.P_DOUBLE_JUMP, CosmeticsMessage.P_DOUBLE_JUMP_DESC, 10000),
    FLY(new ItemStack(Material.FEATHER), CosmeticsMessage.P_FLY, CosmeticsMessage.P_FLY_DESC, 50000),
    INFINITE_JUMP(new CustomItemStack(Material.LEATHER_BOOTS).setLeatherColor(Color.BLACK), CosmeticsMessage.P_INFINITE_JUMP, CosmeticsMessage.P_INFINITE_JUMP_DESC, 50000);

    private ItemStack item;
    private MessageUtils name;
    private MessageUtils desc;
    private int price;

    Particles(ItemStack item, MessageUtils name, MessageUtils desc, int price) {
        this.item = item;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public ItemStack getItem() {
        return item;
    }

    public MessageUtils getName() {
        return name;
    }

    public MessageUtils getDesc() {
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
