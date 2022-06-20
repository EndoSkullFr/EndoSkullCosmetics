package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.commons.account.Account;
import fr.endoskull.api.commons.account.AccountProvider;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.api.spigot.utils.Languages;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.CosmeticsMessage;
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
        super(5, Languages.getLang(p).getMessage(CosmeticsMessage.GUI_PARTICLES), p);
        Languages lang = Languages.getLang(p);
        p.playSound(p.getLocation(), Sound.WOOD_CLICK, 1, 1);
        for (int i : glassSlot) {
            setItem(i, CustomItemStack.getPane(3));
        }
        setItem(4, new CustomItemStack(Material.EYE_OF_ENDER).setName(Main.getInstance().getHidingParticles().contains(p) ? lang.getMessage(CosmeticsMessage.SHOW_PARTICLES) : lang.getMessage(CosmeticsMessage.HIDE_PARTICLES)), player -> {
            if (Main.getInstance().getHidingParticles().contains(player)) {
                Main.getInstance().getHidingParticles().remove(player);
                Account account = AccountProvider.getAccount(player.getUniqueId());
                account.setProperty("cosmetics/hideparticles", "false");
                player.sendMessage(lang.getMessage(CosmeticsMessage.YOU_SHOW_PARTICLES));
                new ParticlesInventory(player).open(player);
            } else {
                Main.getInstance().getHidingParticles().add(player);
                Account account = AccountProvider.getAccount(player.getUniqueId());
                account.setProperty("cosmetics/hideparticles", "true");
                player.sendMessage(lang.getMessage(CosmeticsMessage.YOU_HIDE_PARTICLES));
                new ParticlesInventory(player).open(player);
            }
        });
        int i = 0;
        for (Particles value : Particles.values()) {
            if (ParticleUtils.isParticleSelected(p, value)) {
                setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + lang.getMessage(value.getName())).setLore("\n" + Particles.split(lang.getMessage(value.getDesc())) + lang.getMessage(CosmeticsMessage.PARTICLE_SELECTED)).setGlow());
            } else {
                if (ParticleUtils.hasParticle(p, value)) {
                    setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + lang.getMessage(value.getName())).setLore("\n" + Particles.split(lang.getMessage(value.getDesc())) + lang.getMessage(CosmeticsMessage.PARTICLE_SELECT)), player -> {
                        Account account = AccountProvider.getAccount(player.getUniqueId());
                        account.setProperty("cosmetics/particle", value.toString());
                        ParticleUtils.applyParticle(player, value);
                        player.sendMessage(lang.getMessage(CosmeticsMessage.YOU_SELECT_PARTICLE).replace("{particle}", lang.getMessage(value.getName())));
                        new ParticlesInventory(player).open(player);
                    });
                } else {
                    setItem(slots[i], new CustomItemStack(value.getItem()).setName("§a" + lang.getMessage(value.getName())).setLore("\n" + Particles.split(lang.getMessage(value.getDesc()))  + lang.getMessage(CosmeticsMessage.PARTICLE_PRICE).replace("{price}", String.valueOf(value.getPrice()))), player -> {
                        new ConfirmGui(player, value).open(player);
                    });
                }
            }
            i++;
        }

        if (Main.getInstance().getParticles().containsKey(p)) {
            setItem(40, new CustomItemStack(Material.REDSTONE_BLOCK).setName(lang.getMessage(CosmeticsMessage.REMOVE_PARTICLE)), player -> {
                ParticleUtils.selectParticle(player, null);
                ParticleUtils.applyParticle(player, null);
                player.sendMessage(lang.getMessage(CosmeticsMessage.YOU_REMOVE_PARTICLE));
                new ParticlesInventory(player).open(player);
            });
        }
    }

    private static class ConfirmGui extends CustomGui {
        public ConfirmGui(Player p, Particles particle) {
            super(3, Languages.getLang(p).getMessage(CosmeticsMessage.GUI_PARTICLE_CONFIRM), p);
            Languages lang = Languages.getLang(p);
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
            setItem(13, new CustomItemStack(particle.getItem()).setName("§a" + lang.getMessage(particle.getName())).setLore("\n" + Particles.split(lang.getMessage(particle.getDesc()))  + lang.getMessage(CosmeticsMessage.PARTICLE_PRICE).replace("{price}", String.valueOf(particle.getPrice()))));

            setItem(11, new CustomItemStack(Material.STAINED_CLAY, 1, (byte) 5).setName(lang.getMessage(CosmeticsMessage.PARTICLE_BUY)), player -> {
                Account account = AccountProvider.getAccount(player.getUniqueId());
                if (account.getSolde() < particle.getPrice()) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    player.sendMessage(lang.getMessage(CosmeticsMessage.PARTICLE_LESS_MONEY));
                    return;
                }
                account.removeMoney(particle.getPrice());
                ParticleUtils.addParticle(player, particle);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
                player.sendMessage(lang.getMessage(CosmeticsMessage.YOU_BUY_PARTICLE).replace("{particle}", lang.getMessage(particle.getName())));
                new ParticlesInventory(player).open(player);
            });
            setItem(15, new CustomItemStack(Material.STAINED_CLAY, 1, (byte) 14).setName(lang.getMessage(CosmeticsMessage.CANCEL)), player -> {
                new ParticlesInventory(player).open(player);
            });
        }
    }
}
