package fr.endoskull.cosmetics.utils;

import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.commands.ParticlesCommand;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticlesTask extends BukkitRunnable {
    private Main main;
    private int step = 0;

    public ParticlesTask(Main main) {
        this.main = main;
        for (Particles value : Particles.values()) {
            Bukkit.getPluginManager().addPermission(new Permission("endoskull.particles." + value.toString().toLowerCase()));
        }
    }

    @Override
    public void run() {
        for (Player player : main.getParticles().keySet()) {
            Particles particle = main.getParticles().get(player);
            List<Location> locations = new ArrayList<>();
            Location loc = player.getLocation();
            if (particle == Particles.FLY) {
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 48) {
                    double offset = (Math.pow(Math.E, MathL.cos(t)) - 2 * MathL.cos(t * 4) - Math.pow(MathL.sin(t / 12), 5)) / 2;
                    double x = MathL.sin(t) * offset;
                    double y = MathL.cos(t) * offset;
                    Vector v = VectorUtils.rotateAroundAxisY(new Vector(x, y, -0.3), -Math.toRadians(loc.getYaw()));
                    locations.add(loc.clone().add(v.getX(), v.getY() + 1, v.getZ()));
                }
                for (Location location : locations) {
                    ParticleUtils.sendParticle(EnumParticle.FLAME, location, player, 1, 0, 0, 0);
                }
            }
            if (particle == Particles.SPIRAL) {

                int spiral = 2;
                int whirlStep = 20;
                double modulo = step % whirlStep;
                if (modulo == 0) modulo = 1d/(whirlStep*2);
                for (int i = 0; i < spiral; i++) {
                    for (double t = 0; t < Math.PI; t += Math.PI / 8) {
                        double v = t / Math.PI;
                        double calcul = (t + ((Math.PI * 2/spiral)*i)) - ((modulo / whirlStep * (Math.PI * 2)));
                        locations.add(loc.clone().add(MathL.cos(calcul) * v * 2, 0.05, MathL.sin(calcul) * v * 2));
                    }
                }
                for (Location location : locations) {
                    ParticleUtils.sendParticle(EnumParticle.FLAME, location, player, 1, 0, 0, 0);
                }
            }
            if (particle == Particles.WHIRLWIND) {
                int whirlStep = 20;
                double modulo = step % whirlStep;
                if (modulo == 0) modulo = 1d/(whirlStep*2);
                for (double t = 0; t < Math.PI * 6; t += Math.PI / 48) {
                    double distance = t / (Math.PI * (6 + (Math.PI/2)));
                    locations.add(loc.clone().add(MathL.cos(t - ((modulo / whirlStep * (Math.PI * 2)))) * distance, 0.05 * t, MathL.sin(t - ((modulo / whirlStep * (Math.PI * 2)))) * distance));
                }
                for (Location location : locations) {
                    ParticleUtils.sendParticle(EnumParticle.SMOKE_NORMAL, location, player, 1, 0, 0, 0);
                }
            }

            if (particle == Particles.SCANNER) {
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 10) {
                    for (double i = 0.1; i < 0.7; i+=0.15) {
                        ParticleUtils.sendRedstoneParticle(loc.clone().add(MathL.cos(t)*i, Math.sin(step * 10 * Math.PI / 360d) + 1, MathL.sin(t)*i), player, 0.001f, 1, 0);
                    }
                }
            }
            if (particle == Particles.WHISP) {
                Vector v1 = VectorUtils.rotateAroundAxisY(new Vector(MathL.cos(step * Math.PI / 20)*0.7, 0, MathL.sin(step * Math.PI / 20)*0.7), -Math.toRadians(loc.getYaw()));
                Vector v2 = VectorUtils.rotateAroundAxisY(new Vector(MathL.cos(step * Math.PI / 20)*-0.7, 0, MathL.sin(step * Math.PI / 20)*0.7), -Math.toRadians(loc.getYaw()));
                ParticleUtils.sendParticle(EnumParticle.FLAME, loc.clone().add(v1.getX(), v1.getY() + 1, v1.getZ()), player, 1, 0, 0, 0);
                ParticleUtils.sendParticle(EnumParticle.FLAME, loc.clone().add(v2.getX(), v2.getY() + 1, v2.getZ()), player, 1, 0, 0, 0);
                //ParticleUtils.sendParticle(EnumParticle.FLAME, loc.clone().add(MathL.cos(step * Math.PI / 20)*0.5, 1, MathL.sin(step * Math.PI / 20)*0.5), player, 1, 0, 0, 0);
                //ParticleUtils.sendParticle(EnumParticle.FLAME, loc.clone().add(MathL.cos(step * Math.PI / 20)*-0.5, 1, MathL.sin(step * Math.PI / 20)*0.5), player, 1, 0, 0, 0);
            }

            if (particle == Particles.HELICOPTER) {
                int helicoStep = 20;
                if (!player.getAllowFlight()) helicoStep = 10;
                double modulo = step % helicoStep;
                if (modulo == 0) modulo = 1d/(helicoStep*2);
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 2) {
                    for (double i = 0; i < 0.8; i += 0.2) {
                        ParticleUtils.sendRedstoneParticle(loc.clone().add(MathL.cos(t + ((modulo / helicoStep * (Math.PI * 2))))*i, 2, MathL.sin(t + ((modulo / helicoStep * (Math.PI * 2))))*i), player, 1, 1, 0);
                    }
                }
            }

            if (particle == Particles.REACTOR) {
                int reactorStep = 20;
                double modulo = step % reactorStep;
                if (modulo == 0) modulo = 1d/(reactorStep*2);
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 2) {
                    for (double i = 0.1; i < 0.45; i += 0.1) {
                        Vector v = VectorUtils.rotateAroundAxisY(new Vector(MathL.cos(t + ((modulo / reactorStep * (Math.PI * 2))))*i, 1.2 + MathL.sin(t + ((modulo / reactorStep * (Math.PI * 2))))*i, -0.4), -Math.toRadians(loc.getYaw()));
                        ParticleUtils.sendRedstoneParticle(loc.clone().add(v.getX(), v.getY(), v.getZ()), player, 0.001f, 1, 1);
                    }
                }
                for (double t = 0; t < Math.PI * 2; t += Math.PI / 10) {
                    Vector v = VectorUtils.rotateAroundAxisY(new Vector(MathL.cos(t)*0.5, 1.2 + MathL.sin(t)*0.5, -0.35), -Math.toRadians(loc.getYaw()));
                    ParticleUtils.sendRedstoneParticle(loc.clone().add(v.getX(), v.getY(), v.getZ()), player, 0.001f, 0, 0);
                }
            }

            if (particle == Particles.ANGE) {
                int spiral = 2;
                int angeStep = 20;
                double modulo = step % angeStep;
                if (modulo == 0) modulo = 1d/(angeStep*2);
                for (int i = 0; i < spiral; i++) {
                    for (double t = 0; t < Math.PI; t += Math.PI / 8) {
                        float radius = 1.5f;
                        float angle = 0f;
                        double x = (radius * Math.sin(angle));
                        double z = (radius * Math.cos(angle));
                        angle += 0.1;
                        locations.add(loc.clone().add(x, 0.05, z));
                    }
                }
                for (Location location : locations) {
                    ParticleUtils.sendParticle(EnumParticle.FLAME, location, player, 1, 0, 0, 0);
                }
            }
        }
        step++;
    }
}
