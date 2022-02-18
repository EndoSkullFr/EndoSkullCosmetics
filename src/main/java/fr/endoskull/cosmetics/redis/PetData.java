package fr.endoskull.cosmetics.redis;

import fr.bebedlastreat.cache.CacheAPI;

import java.util.UUID;

public class PetData {
    private UUID uuid;

    public PetData(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPet(String pet) {
        if (pet == null) {
            CacheAPI.set("pets/" + uuid + "/type", "null");
        } else {
            CacheAPI.set("pets/" + uuid + "/type", pet);
        }
    }

    public String getPet() {
        if (CacheAPI.keyExist("pets/" + uuid + "/type") && !CacheAPI.get("pets/" + uuid + "/type").equalsIgnoreCase("null")) {
            return CacheAPI.get("pets/" + uuid + "/type");
        } else {
            return null;
        }
    }

}
