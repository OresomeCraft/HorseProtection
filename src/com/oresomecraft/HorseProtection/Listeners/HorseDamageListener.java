package com.oresomecraft.HorseProtection.Listeners;

import com.oresomecraft.HorseProtection.HorseProtection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HorseDamageListener implements Listener {

    private HorseProtection plugin;

    public HorseDamageListener(HorseProtection plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.HORSE) {
            if (plugin.getConfig().getString(event.getEntity().getUniqueId().toString() + ".Owner") != null) {
                event.setCancelled(true);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("HorseProtection.moderator") && (event.getDamager() instanceof Player)) {
                        p.sendMessage(ChatColor.RED + "User" + ChatColor.DARK_RED + ((Player) event.getDamager()).getName()
                                + ChatColor.RED + " tried to damage a horse belonging to "
                                + plugin.getConfig().getString(event.getEntity().getUniqueId().toString() + ".Owner"));

                    }
                }
            }
        }
    }
}
