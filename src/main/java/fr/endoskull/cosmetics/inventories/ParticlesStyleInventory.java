package fr.endoskull.cosmetics.inventories;

import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ParticlesStyleInventory extends CustomGui {
    public ParticlesStyleInventory() {
        super(6, "§c§lEndoSkull §8» §d§lParticules");
        /*int i = 0;
        Integer[] glassSlot = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        for (int i1 : glassSlot) {
            setItem(i1, CustomItemStack.getPane(3).setName("§r"));
        }
        for (ParticleStyle style : PlayerParticles.getInstance().getManager(ParticleStyleManager.class).getStyles()) {
            while (Arrays.asList(glassSlot).contains(i)) i++;
            setItem(i, new CustomItemStack(style.getGuiIconMaterial()).setName("§a" + ParticleUtils.formatName(style.getName())), player -> {
                ParticleEffect effect = null;
                for (ParticlePair activePlayerParticle : PlayerParticlesAPI.getInstance().getActivePlayerParticles(player)) {
                    effect = activePlayerParticle.getEffect();
                    break;
                }
                if (effect == null) {
                    effect = ParticleEffect.getEnabledEffects().get(new Random().nextInt(ParticleEffect.getEnabledEffects().size()));
                }
                PlayerParticlesAPI.getInstance().resetActivePlayerParticles(player);
                PlayerParticlesAPI.getInstance().addActivePlayerParticle(player, effect, style);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            });
            i++;
        }
        setItem(53, CustomItemStack.getBackGuiItem(), player -> {
            new ParticlesInventory(player).open(player);
            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1, 1);
        });*/
    }
}
