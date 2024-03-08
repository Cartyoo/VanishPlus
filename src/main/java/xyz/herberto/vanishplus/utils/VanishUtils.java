package xyz.herberto.vanishplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VanishUtils {

    public static void hidePlayer(Player player) {
        VanishMap.setVanished(player.getUniqueId(), true);
        for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayers.hasPermission("vanishplus.see-vanished")) {
                onlinePlayers.hidePlayer(player);
            }
        }
    }

    public static void revealPlayer(Player player) {
        VanishMap.setVanished(player.getUniqueId(), false);
        for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            onlinePlayers.showPlayer(player);
        }
    }

}
