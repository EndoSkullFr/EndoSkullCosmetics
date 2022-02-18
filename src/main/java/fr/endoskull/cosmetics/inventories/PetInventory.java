package fr.endoskull.cosmetics.inventories;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.endoskull.api.spigot.utils.CustomGui;
import fr.endoskull.api.spigot.utils.CustomItemStack;
import fr.endoskull.cosmetics.Main;
import fr.endoskull.cosmetics.redis.PetData;
import fr.endoskull.cosmetics.utils.Pets;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class PetInventory extends CustomGui {
    public PetInventory(UUID uuid) {
        super(3, "§c§lEndoSkull §8» §d§lPets");
        int i = 9;
        for (Pets value : Pets.values()) {
            setItem(i, new CustomItemStack(getSkull(value.getSkull())).setName(value.getDisplayName()), player -> {
                value.getAction().click(player);
                new PetData(player.getUniqueId()).setPet(value.toString());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
                player.sendMessage("§eEndoSkull §8>> §7Vous avez fait spawn le pet " + value.getDisplayName());
            });
            i++;
        }
        PetData petData = new PetData(uuid);
        if (petData.getPet() != null) {
            setItem(26, new CustomItemStack(Material.BARRIER).setName("§cRetirer le pet"), player -> {
                player.closeInventory();
                new PetData(player.getUniqueId()).setPet(null);
                player.playSound(player.getLocation(), Sound.CLICK, 1f, 1f);
                player.sendMessage("§eEndoSkull §8>> §7Vous avez fait despawn votre pet");
                Main main = Main.getInstance();
                if (main.getPets().containsKey(player.getUniqueId())) {
                    main.getPets().get(player.getUniqueId()).remove();
                    main.getPets().remove(player.getUniqueId());
                }
            });
        }

    }

    private static ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url.isEmpty())
            return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);

        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
            error.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }
}
