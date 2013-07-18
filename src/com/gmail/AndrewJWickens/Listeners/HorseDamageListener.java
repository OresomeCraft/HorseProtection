package com.gmail.AndrewJWickens.Listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.gmail.AndrewJWickens.HorseProtection;

public class HorseDamageListener implements Listener {

	private HorseProtection plugin;
	
	public HorseDamageListener(HorseProtection plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntityType() == EntityType.HORSE) {
			if (plugin.getConfig().getString(event.getEntity().getUniqueId().toString() + ".Owner") != null) {
				event.setCancelled(true);
			}
		}
	}
}
