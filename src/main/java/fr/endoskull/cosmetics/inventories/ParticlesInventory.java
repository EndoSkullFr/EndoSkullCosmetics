package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.MusicUtils;
import fr.endoskull.cosmetics.utils.ParticleUtils;
import fr.endoskull.cosmetics.utils.Particles;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ParticlesInventory extends CustomGui {
    private static int[] glassSlot = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
    private static int[] slots = {10,11,12,13,14,15,16, 19,20,21,22,23,24,25, 28,29,30,31,32,33,34};
    public ParticlesInventory(Player p) {
        super(5, "§c§lEndoSkull §8» §d§lParticules");
        p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1, 1);
        for (int i : glassSlot) {
            setItem(i, CustomItemStack.getPane(3));
        }
        int i = 0;
        for (Particles value : Particles.values()) {
            if (ParticleUtils.isParticleSelected(p, value)) {
                setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + value.getName()).setLore("\n" + Particles.split(value.getDesc()) + "\n\n§a§l➜ Sélectionné ✔").setGlow());
            } else {
                if (ParticleUtils.hasParticle(p, value)) {
                    setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + value.getName()).setLore("\n" + Particles.split(value.getDesc()) + "\n\n§a➜ Cliquez pour sélectionner"), player -> {
                        Account account = AccountProvider.getAccount(player.getUniqueId());
                        account.setProperty("cosmetics/particle", value.toString());
                        ParticleUtils.applyParticle(player, value);
                        player.sendMessage("§eEndoSkull §8>> §7Vous avez sélectionné la particule §e" + value.getName());
                        new ParticlesInventory(player).open(player);
                    });
                } else {
                    setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + value.getName()).setLore("\n" + Particles.split(value.getDesc())  + "\n\n§e➜ Prix: " + value.getPrice()), player -> {
                        new ConfirmGui(player, value).open(player);
                    });
                }
            }
            i++;
        }

        if (Main.getInstance().getParticles().containsKey(p)) {
            setItem(40, new CustomItemStack(Material.REDSTONE_BLOCK).setName("§cEnlever la particule"), player -> {
                ParticleUtils.selectParticle(player, null);
                ParticleUtils.applyParticle(player, null);
                player.sendMessage("§eEndoSkull §8>> §7Vous avez enlever la particule");
                new ParticlesInventory(player).open(player);
            });
        }
    }

    private static class ConfirmGui extends CustomGui {
        public ConfirmGui(Player p, Particles particle) {
            super(3, "§c§lEndoSkull §8» §a§lConfirmation");
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
            setItem(13, new CustomItemStack(particle.getItem()).setName("§a" + particle.getName()).setLore("\n" + Particles.split(particle.getDesc())  + "\n\n§e➜ Prix: " + particle.getPrice()));

            setItem(11, new CustomItemStack(Material.STAINED_CLAY, 1, (byte) 5).setName("§aAcheter"), player -> {
                Account account = AccountProvider.getAccount(player.getUniqueId());
                if (account.getSolde() < particle.getPrice()) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    player.sendMessage("§eEndoSkull §8>> §cVous n'avez pas assez de money");
                    return;
                }
                account.removeMoney(particle.getPrice());
                ParticleUtils.addParticle(player, particle);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                player.sendMessage("§eEndoSkull §8>> §7Vous avez acheté la particule §e" + particle.getName());
                new ParticlesInventory(player).open(player);
            });
            setItem(15, new CustomItemStack(Material.STAINED_CLAY, 1, (byte) 14).setName("§cAnnuler"), player -> {
                new ParticlesInventory(player).open(player);
            });
        }
    }
}
