package fr.endoskull.cosmetics.listeners;

import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.MathL;
import fr.endoskull.cosmetics.utils.ParticleUtils;
import fr.endoskull.cosmetics.utils.Particles;
import fr.endoskull.cosmetics.utils.ParticlesTask;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ParticleListener implements Listener {
    private Main main;
    public ParticleListener(Main main) {
        this.main = main;
    }

    private List<Player> trail = new ArrayList<>();
    private List<Player> reactor = new ArrayList<>();

    @EventHandler
    public void onFly(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (!e.isFlying()) return;
        if (player.getVehicle() != null) return;
        if (fr.bebedlastreat.endoskullnpc.Main.getInstance().getJumping().containsKey(player)) return;
        if (main.getParticles().get(player) == Particles.DOUBLE_JUMP) {
            e.setCancelled(true);
            player.playSound(player.getLocation(), Sound.EXPLODE, 1f, 1f);
            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
            //ParticleUtils.sendParticle(EnumParticle.EXPLOSION_LARGE, player.getLocation(), player, 5, 0.5f, 0.5f, 0.5f);
            for (double t = 0; t < Math.PI * 2; t += Math.PI / 10) {
                Location loc = player.getLocation().clone();
                loc.add(MathL.cos(t)/2, 0, MathL.sin(t)/2);
                ParticleUtils.sendParticle(EnumParticle.SMOKE_LARGE, loc, player, 1, 0, 0, 0);
            }
            player.setAllowFlight(false);
        }
        if (main.getParticles().get(player) == Particles.HELICOPTER) {
            e.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BAT_LOOP, 1f, 1f);
            player.setVelocity(player.getLocation().getDirection().setY(2));
            player.setAllowFlight(false);
        }
        if (main.getParticles().get(player) == Particles.INFINITE_JUMP) {
            e.setCancelled(true);
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1f, 1f);
            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
            //ParticleUtils.sendParticle(EnumParticle.EXPLOSION_LARGE, player.getLocation(), player, 5, 0.5f, 0.5f, 0.5f);
            for (double t = 0; t < Math.PI * 2; t += Math.PI / 10) {
                Location loc = player.getLocation().clone();
                loc.add(MathL.cos(t)/2, 0, MathL.sin(t)/2);
                ParticleUtils.sendParticle(EnumParticle.CRIT_MAGIC, loc, player, 1, 0, 0, 0);
                if (!trail.contains(player)) trail.add(player);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getVehicle() != null) return;
        if (!main.getParticles().containsKey(player)) return;
        if (fr.bebedlastreat.endoskullnpc.Main.getInstance().getJumping().containsKey(player)) return;
        if (main.getParticles().get(player) == Particles.DOUBLE_JUMP) {
            if (player.isOnGround()) {
                player.setAllowFlight(true);
            }
        }
        if (main.getParticles().get(player) == Particles.HELICOPTER) {
            if (player.isOnGround()) {
                player.setAllowFlight(true);
            }
        }
        if (main.getParticles().get(player) == Particles.INFINITE_JUMP && trail.contains(player)) {
            ParticleUtils.sendParticle(EnumParticle.CRIT_MAGIC, player.getLocation().clone().add(0, 1, 0), player, 1, 0, 0, 0);
            if (player.isOnGround()) {
                trail.remove(player);
            }
        }
    }

    @EventHandler
    public void onSpeedBoots(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!main.getParticles().containsKey(player)) return;
        if (fr.bebedlastreat.endoskullnpc.Main.getInstance().getJumping().containsKey(player)) return;
        if (main.getParticles().get(player) == Particles.SPEED && e.getTo().distance(e.getFrom()) > 0) {
            ParticleUtils.sendParticle(EnumParticle.FLAME, player.getLocation(), player, 1, 0, 0, 0);
        }
    }

    private List<Player> jumpings = new ArrayList<>();

    @EventHandler
    public void onJumpBoots(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!main.getParticles().containsKey(player)) return;
        if (fr.bebedlastreat.endoskullnpc.Main.getInstance().getJumping().containsKey(player)) return;
        if (main.getParticles().get(player) == Particles.JUMP) {
            if (player.isOnGround()) {
                jumpings.remove(player);
            } else {
                if (!jumpings.contains(player) && player.getVelocity().getY() > 0.9) {
                    jumpings.add(player);
                    for (double t = 0; t < Math.PI * 6; t += Math.PI / 10) {
                        ParticleUtils.sendRedstoneParticle(player.getLocation().clone().add(MathL.cos(t)*0.3, t/(Math.PI*10), MathL.sin(t)*0.3), player, 0.001f, 1, 1);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        if (reactor.contains(player)) return;
        if (!main.getParticles().containsKey(player)) return;
        if (fr.bebedlastreat.endoskullnpc.Main.getInstance().getJumping().containsKey(player)) return;
        if (main.getParticles().get(player) == Particles.REACTOR && !e.isSneaking()) {
            if (player.isOnGround()) {
                player.setVelocity(player.getLocation().getDirection().multiply(5).setY(0.5));
                player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1f ,1f);
            }
        }
    }

    @EventHandler
    public void onVelocity(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (e.getTo().distance(e.getFrom()) > 0.1) {
            ParticlesTask.getMoving().add(player);
        } else {
            ParticlesTask.getMoving().remove(player);
        }
    }
}
