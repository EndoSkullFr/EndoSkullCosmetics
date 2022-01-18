package fr.endoskull.cosmetics.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.endoskull.cosmetics.pets.*;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.BiomeBase.BiomeMeta;
import org.bukkit.entity.EntityType;

public class CustomEntityType {

    private static HashMap<String, Integer> ensI = new HashMap<>();
    private static HashMap<Integer, String> ensN = new HashMap<>();


    public static void registerAllEntities() {
        registerEntity("Pig", 90, EntityPig.class, RideablePig.class);
        registerEntity("Cow", 92, EntityCow.class, RideableCow.class);
        registerEntity("MushroomCow", 96, EntityMushroomCow.class, RideableMushroom.class);
        registerEntity("Sheep", 91, EntitySheep.class, RideableSheep.class);
        registerEntity("Chicken", 93, EntityChicken.class, RideableChicken.class);
        registerEntity("Wolf", 95, EntityWolf.class, RideableWolf.class);
        registerEntity("Ocelot", 98, EntityOcelot.class, RideableCat.class);
        registerEntity("Rabbit", 101, EntityRabbit.class, RideableRabbit.class);
        registerEntity("Villager", 120, EntityVillager.class, RideableVillager.class);
    }

    public static void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass,
                                      Class<? extends EntityInsentient> customClass) {
        try {

            List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMaps.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMaps.get(2).containsKey(id)) {
                dataMaps.get(0).remove(name);
                dataMaps.get(2).remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);

            for (Field f : BiomeBase.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(BiomeBase.class.getSimpleName())) {
                    if (f.get(null) != null) {

                        for (Field list : BiomeBase.class.getDeclaredFields()) {
                            if (list.getType().getSimpleName().equals(List.class.getSimpleName())) {
                                list.setAccessible(true);
                                @SuppressWarnings("unchecked")
                                List<BiomeMeta> metaList = (List<BiomeMeta>) list.get(f.get(null));

                                for (BiomeMeta meta : metaList) {
                                    Field clazz = BiomeMeta.class.getDeclaredFields()[0];
                                    if (clazz.get(meta).equals(nmsClass)) {
                                        clazz.set(meta, customClass);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            ensI.put(name, id);
            ensN.put(id, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getID(String name) {
        return ensI.get(name);
    }

    public static String getName(int id) {
        return ensN.get(id);
    }
}