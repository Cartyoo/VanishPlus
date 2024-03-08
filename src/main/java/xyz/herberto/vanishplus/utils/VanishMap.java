package xyz.herberto.vanishplus.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class VanishMap {

    @Getter private static HashMap<UUID, Boolean> vanishMap = new HashMap<>();


    public static void setVanished(UUID uuid, boolean vanished) {
        vanishMap.put(uuid, vanished);
    }

    public static boolean getVanished(UUID uuid) {
        return vanishMap.getOrDefault(uuid, false);
    }

    public void removeVanished(UUID uuid) {
        vanishMap.remove(uuid);
    }

}
