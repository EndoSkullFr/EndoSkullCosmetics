package fr.endoskull.cosmetics.commands;

import fr.endoskull.api.commons.lang.MessageUtils;
import fr.endoskull.api.spigot.utils.Languages;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.inventories.ColorInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Languages.getLang(sender).getMessage(MessageUtils.Global.CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        new ColorInventory(player).open();
        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
        return false;
    }
}
