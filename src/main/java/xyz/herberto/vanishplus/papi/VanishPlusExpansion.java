package xyz.herberto.vanishplus.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import xyz.herberto.vanishplus.VanishPlus;

public class VanishPlusExpansion extends PlaceholderExpansion {


    private final VanishPlus plugin;

    public VanishPlusExpansion(VanishPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "herberto";
    }

    @Override
    public String getIdentifier() {
        return "vanishplus";
    }

    @Override
    public String getVersion() {
        return VanishPlus.getInstance().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("vanished")) {
            return "";
        }

        return null;
    }

}
