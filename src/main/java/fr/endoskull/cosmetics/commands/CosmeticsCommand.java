package fr.endoskull.cosmetics.commands;

import fr.endoskull.cosmetics.inventories.CosmeticsInventory;
import fr.endoskull.cosmetics.inventories.ParticlesInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CosmeticsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVous devez être un joueur pour éxécuter cette commande");
            return false;
        }
        Player player = (Player) sender;
        new CosmeticsInventory().open(player);
        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);
        return false;
    }
}
