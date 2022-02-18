package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.inventories.tag.TagColor;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.util.concurrent.atomic.AtomicBoolean;

public class ColorInventory extends CustomGui {
    public ColorInventory() {
        super(2, "§c§lEndoSkull §8» §d§lCouleur du Tag");
        for (TagColor value : TagColor.values()) {
            setItem(value.getSlot(), new CustomItemStack(value.getSkull()).setName("§" + value.getColor() + value.getDisplayName()), player -> {
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
                        player1.sendMessage("§aVotre tag a bien été défini");
                    });
                    return AnvilGUI.Response.close();
                }).plugin(Main.getInstance()).title("Texte du tag").text("Tag").open(player);
            });
        }
    }
}
