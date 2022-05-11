package fr.endoskull.cosmetics.listeners;

import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.utils.Pets;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.IMerchant;
import net.minecraft.server.v1_8_R3.InventoryMerchant;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Field;
import java.util.UUID;

public class PetListener implements Listener {

    private Main main;

    public PetListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClickPet(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) e.getRightClicked();
            for (UUID uuid : main.getPets().keySet()) {
                if (main.getPets().get(uuid).equals(livingEntity)) {
                    e.setCancelled(true);
                }
            }
            if (main.getPets().containsKey(player.getUniqueId())) {
                if (main.getPets().get(player.getUniqueId()).equals(livingEntity)) {
                    livingEntity.setPassenger(player);
                }
            }
        }
    }

    @EventHandler
    public void villDisableTrade(InventoryOpenEvent e) {
        if(e.getInventory().getType() == InventoryType.MERCHANT) {

            Player player = (Player) e.getPlayer();

            try {
                Field merchant = InventoryMerchant.class.getDeclaredField("merchant");
                merchant.setAccessible(true);
                IMerchant merch = (IMerchant) merchant.get((InventoryMerchant)((((CraftInventory) e.getInventory()).getInventory())));
                if (((EntityLiving)merch).getCustomName().startsWith("§6Villageois de")) {
                    e.setCancelled(true);
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            } //This is your IMerchant

        }
    }

    /*@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PetData petData = new PetData(player.getUniqueId());
        if (petData.getPet() != null) {
            Pets pet = Pets.valueOf(petData.getPet());
            Bukkit.getScheduler().runTaskLater(main, () -> pet.getAction().click(player), 5L);
        }
    }*/

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (main.getPets().containsKey(player.getUniqueId())) {
            main.getPets().get(player.getUniqueId()).remove();
            main.getPets().remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) return;
        LivingEntity livingEntity = (LivingEntity) e.getEntity();
        for (UUID uuid : main.getPets().keySet()) {
            if (main.getPets().get(uuid).equals(livingEntity)) e.setCancelled(true);
        }
    }
}
