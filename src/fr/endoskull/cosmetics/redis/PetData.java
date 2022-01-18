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
            CacheAPI.remove("pets/" + uuid + "/type");
        } else {
            CacheAPI.set("pets/" + uuid + "/type", pet);
        }
    }

    public String getPet() {
        return CacheAPI.get("pets/" + uuid + "/type");
    }

}
