package fr.endoskull.cosmetics.commands;

import fr.endoskull.cosmetics.inventories.MusicInventory;
import fr.endoskull.cosmetics.inventories.PetInventory;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MusicCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVous devez être un joueur pour éxécuter cette commande");
            return false;
        }
        Player player = (Player) sender;
        new MusicInventory(player).open(player);
        return false;
    }
}
