package com.oresomecraft.HorseProtection.Listeners;

import com.oresomecraft.HorseProtection.HorseProtection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class HorseDamageListener implements Listener {

    private HorseProtection plugin;

    public HorseDamageListener(HorseProtection plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.HORSE) {
            if (plugin.getConfig().getString(event.getEntity().getUniqueId().toString() + ".Owner") != null) {
                if (event.getDamager() instanceof Player) {
                    event.setCancelled(true);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("HorseProtection.moderator")) {
                            p.sendMessage(ChatColor.RED + "User" + ChatColor.DARK_RED + ((Player) event.getDamager()).getName()
                                    + ChatColor.RED + " tried to damage a horse belonging to "
                                    + plugin.getConfig().getString(event.getEntity().getUniqueId().toString() + ".Owner"));

                        }
                    }
                } else if (invalidDamageCause(event.getCause())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean invalidDamageCause(DamageCause cause) {
        switch (cause) {
            case ENTITY_ATTACK:
            case PROJECTILE:
            case MAGIC:
            case POISON:
                return true;
        }
        return false;
    }
}
