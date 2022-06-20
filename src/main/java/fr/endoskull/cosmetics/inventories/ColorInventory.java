package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.api.spigot.utils.Languages;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.CosmeticsMessage;
import fr.endoskull.cosmetics.utils.TagColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class ColorInventory extends CustomGui {
    public ColorInventory(Player p) {
        super(2, Languages.getLang(p).getMessage(CosmeticsMessage.GUI_TAG), p);
        Languages lang = Languages.getLang(p);
        for (TagColor value : TagColor.values()) {
            setItem(value.getSlot(), new CustomItemStack(value.getSkull()).setName("§" + value.getColor() + lang.getMessage(value.getDisplayName())), player -> {
                player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
                AtomicBoolean succes = new AtomicBoolean(false);

                new AnvilGUI.Builder().onClose(player1 -> {
                    if (succes.get()) return;
                    player1.playSound(player1.getLocation(), Sound.VILLAGER_NO, 40, 40);
                }).onComplete((player1, tag) -> {
                    succes.set(true);
                    if (tag.length() > 3) {
                        tag = tag.substring(0, 3);
                    }
                    String finalTag = tag;
                    Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player1.getName() + " meta setsuffix \"&7[&" + value.getColor() + finalTag + "&7]\"");
                        player1.sendMessage(lang.getMessage(CosmeticsMessage.TAG_CHANGE));
                    });
                    return AnvilGUI.Response.close();
                }).plugin(Main.getInstance()).title(lang.getMessage(CosmeticsMessage.TAG_ANVIL)).text("Tag").open(player);
            });
        }
    }
}
