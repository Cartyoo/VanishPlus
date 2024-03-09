package xyz.herberto.vanishplus.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.herberto.vanishplus.VanishPlus;
import xyz.herberto.vanishplus.utils.CC;
import xyz.herberto.vanishplus.utils.VanishMap;
import xyz.herberto.vanishplus.utils.VanishUtils;

public class VanishCommand extends BaseCommand {

    @CommandAlias("vanish|v")
    @CommandPermission("vanishplus.command.vanish")
    @Syntax("[enable|disable] [player]")
    @CommandCompletion("enable|disable|@players @players")

    public void vanish(Player player, @Optional String enabled, @Optional OnlinePlayer target) {

        if(enabled != null) {
            if(target == null) {
                if (enabled.equalsIgnoreCase("enable") || enabled.equalsIgnoreCase("on")) {
                    if (VanishMap.getVanished(player.getUniqueId())) {
                        player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-enabled")));
                    } else {
                        player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                        VanishUtils.hidePlayer(player);
                    }
                } else if (enabled.equalsIgnoreCase("disable") || enabled.equalsIgnoreCase("off")) {
                    if (!VanishMap.getVanished(player.getUniqueId())) {
                        player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-disabled")));
                    } else {
                        player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                        VanishUtils.revealPlayer(player);
                    }
                } else {
                    if(Bukkit.getPlayer(enabled) != null) {
                        Player targetPlayer = Bukkit.getPlayer(enabled);
                        if(VanishMap.getVanished(targetPlayer.getUniqueId())) {
                            if(targetPlayer.getPlayer() == player){
                                player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                            } else {
                                player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "vanished").replaceAll("%target%", targetPlayer.getName())));
                                targetPlayer.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                            }
                            VanishUtils.revealPlayer(targetPlayer);
                        } else {
                            if(targetPlayer.getPlayer() == player) {
                                player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                            } else {
                                player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "unvanished").replaceAll("%target%", targetPlayer.getName())));
                                targetPlayer.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                            }
                            VanishUtils.hidePlayer(targetPlayer);
                        }
                    } else {
                        player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.not-a-value").replaceAll("%value%", enabled)));
                    }
                }

            } else {
                if(enabled.equalsIgnoreCase("enable") || enabled.equalsIgnoreCase("on")) {
                    if(VanishMap.getVanished(target.getPlayer().getUniqueId())) {
                        if(target.getPlayer() == player) {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-enabled")));
                        } else {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-enabled-other").replaceAll("%target%", target.getPlayer().getName())));
                        }
                    } else {
                        if(target.getPlayer() == player) {
                           player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                        } else {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "vanished").replaceAll("%target%", target.getPlayer().getName())));
                            target.getPlayer().sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                        }
                        VanishUtils.hidePlayer(target.getPlayer());
                    }
                } else if(enabled.equalsIgnoreCase("disable") || enabled.equalsIgnoreCase("off")) {
                    if(!VanishMap.getVanished(target.getPlayer().getUniqueId())) {
                        if(target.getPlayer() == player) {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-disabled")));
                        } else {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanish-already-disabled-other").replaceAll("%target%", target.getPlayer().getName())));
                        }
                    } else {
                        if(target.getPlayer() == player) {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                        }  else {
                            player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "unvanished").replaceAll("%target%", target.getPlayer().getName())));
                            target.getPlayer().sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                        }
                        VanishUtils.revealPlayer(target.getPlayer());
                    }
                }
            }

        } else {
            if(target != null) {
                if(VanishMap.getVanished(target.getPlayer().getUniqueId())) {
                    player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "unvanished").replaceAll("%target%", target.getPlayer().getName())));
                    target.getPlayer().sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                    VanishUtils.hidePlayer(target.getPlayer());
                } else {
                    player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished-other").replaceAll("%action%", "vanished").replaceAll("%target%", target.getPlayer().getName())));
                    target.getPlayer().sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                    VanishUtils.hidePlayer(target.getPlayer());
                }
            } else {
                if(VanishMap.getVanished(player.getUniqueId())) {
                    player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "unvanished")));
                    VanishUtils.revealPlayer(player);
                } else {
                    player.sendMessage(CC.translate(VanishPlus.getConfigFile().getString("messages.vanished").replaceAll("%action%", "vanished")));
                    VanishUtils.hidePlayer(player);
                }
            }
        }



    }

}
