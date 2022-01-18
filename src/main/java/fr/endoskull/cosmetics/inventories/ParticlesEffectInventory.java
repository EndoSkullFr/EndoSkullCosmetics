package fr.endoskull.cosmetics.inventories;

import dev.esophose.playerparticles.PlayerParticles;
import dev.esophose.playerparticles.api.PlayerParticlesAPI;
import dev.esophose.playerparticles.manager.ParticleStyleManager;
import dev.esophose.playerparticles.particles.ParticleEffect;
import dev.esophose.playerparticles.particles.ParticlePair;
import dev.esophose.playerparticles.styles.ParticleStyle;
import dev.esophose.playerparticles.styles.ParticleStyleOrbit;
import dev.esophose.playerparticles.util.ParticleUtils;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Random;

public class ParticlesEffectInventory extends CustomGui {
    public ParticlesEffectInventory() {
        super(6, "§dEndoSkull Particules");
        int i = 0;
        Integer[] glassSlot = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        for (int i1 : glassSlot) {
            setItem(i1, CustomItemStack.getPane(3).setName("§r"));
        }
        for (ParticleEffect enabledEffect : ParticleEffect.getEnabledEffects()) {
            while (Arrays.asList(glassSlot).contains(i)) i++;
            setItem(i, new CustomItemStack(enabledEffect.getGuiIconMaterial()).setName("§a" + ParticleUtils.formatName(enabledEffect.getName())), player -> {
                ParticleStyle style = null;
                for (ParticlePair activePlayerParticle : PlayerParticlesAPI.getInstance().getActivePlayerParticles(player)) {
                    style = activePlayerParticle.getStyle();
                    break;
                }
                if (style == null) {
                    style = (ParticleStyle) PlayerParticles.getInstance().getManager(ParticleStyleManager.class).getStyles().toArray()[new Random().nextInt(PlayerParticles.getInstance().getManager(ParticleStyleManager.class).getStyles().toArray().length)];
                }
                PlayerParticlesAPI.getInstance().resetActivePlayerParticles(player);
                PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, enabledEffect, style);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            });
            i++;
        }
        setItem(53, CustomItemStack.getBackGuiItem(), player -> {
            new ParticlesInventory(player).open(player);
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
        });
    }
}
