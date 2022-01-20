package fr.endoskull.cosmetics.utils;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public enum Pets {
    PIG("§dCochon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=", EntityType.PIG, MountManager::ridePig),
    COW("§eVache", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19", EntityType.COW, MountManager::rideCow),
    MUSHROOM("§cChampimeuh", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmI1Mjg0MWYyZmQ1ODllMGJjODRjYmFiZjllMWMyN2NiNzBjYWM5OGY4ZDZiM2RkMDY1ZTU1YTRkY2I3MGQ3NyJ9fX0=", EntityType.MUSHROOM_COW, MountManager::rideMushroom),
    SHEEP("§eMouton", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19", EntityType.SHEEP, MountManager::rideSheep),
    CHICKEN("§ePoulet", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYzODQ2OWE1OTljZWVmNzIwNzUzNzYwMzI0OGE5YWIxMWZmNTkxZmQzNzhiZWE0NzM1YjM0NmE3ZmFlODkzIn19fQ==", EntityType.CHICKEN, MountManager::rideChicken),
    WOLF("§eLoup", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGMzZGQ5ODRiYjY1OTg0OWJkNTI5OTQwNDY5NjRjMjI3MjVmNzE3ZTk4NmIxMmQ1NDhmZDE2OTM2N2Q0OTQifX19", EntityType.WOLF, MountManager::rideWolf),
    CAT("§eChat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19", EntityType.OCELOT, MountManager::rideCat),
    RABBIT("§eLapin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2QyNzE5MjcxYzcxNDk4MDIyNmZmMWJlZjRjYmIxZDU0NDExY2UzYTkxNGQ3ZGY3MzFiY2M3ZjYxZTk0ZTVjIn19fQ==", EntityType.RABBIT, MountManager::rideRabbit),
    VILLAGER("§6Villageois", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19", EntityType.VILLAGER, MountManager::rideVillager),
    Bat("§cChauve-Souris", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjc2NjE5NjUyZmFmZWM5MGNlOThkZjUwMTNjNjNkYzZhNzc3NzZhYjI3ODczYjczZGFmYjJiNmJkZWIxODUifX19", EntityType.BAT, MountManager::rideBat)
    ;

    private String displayName;
    private String skull;
    private EntityType entityType;
    private PetAction action;

    Pets(String displayName, String skull, EntityType entityType, PetAction action) {
        this.displayName = displayName;
        this.skull = skull;
        this.entityType = entityType;
        this.action = action;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSkull() {
        return skull;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public static Pets getByEntityType(EntityType type) {
        for (Pets value : values()) {
            if (value.getEntityType() == type) return value;
        }
        return Pets.PIG;
    }

    public PetAction getAction() {
        return action;
    }

    public interface PetAction {
        void click(Player var1);
    }
}
