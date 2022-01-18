package fr.endoskull.cosmetics.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVous devez être un joueur pour éxécuter cette commande");
            return false;
        }
        Player player = (Player) sender;
    	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	StringBuilder sb = new StringBuilder();
    	Random random = new Random();
    	 int length = 7;
    	for(int i = 0; i < length; i++) {
    	int index = random.nextInt(alphabet.length());
    	char randomChar = alphabet.charAt(index);
    	sb.append(randomChar);
    	
    	    }
    	
    	String randomUsername = sb.toString();
    	player.setDisplayName(randomUsername);
    	player.setCustomName(randomUsername);
    	sender.sendMessage("�c[EndoSkull] Ton nouveau pseudo est : �e" + randomUsername);
		return false;
    }
    
}
