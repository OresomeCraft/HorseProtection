package com.gmail.AndrewJWickens.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.gmail.AndrewJWickens.HorseProtection;
import com.gmail.AndrewJWickens.DataStorage.EditType;

public class HorseInteractListener implements Listener {
	
	private HorseProtection plugin;
	
	public HorseInteractListener(HorseProtection plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.HORSE) {
			switch (plugin.containsPlayer(event.getPlayer().getName())) {
			case PROTECT: {
				event.setCancelled(true);
				if (plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner") == null) {
					plugin.getConfig().set(event.getRightClicked().getUniqueId().toString() + ".Owner", event.getPlayer().getName());
					plugin.saveConfig();
					event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "You have now protected this Horse/Donkey!");
					plugin.setEdit(event.getPlayer().getName(), EditType.NONE, null);
					event.setCancelled(true);
				} else {
					String name = plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner");
					if (name.equalsIgnoreCase(event.getPlayer().getName())) {
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "You have already protected this Horse/Donkey!");
						event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "To change owners, use: " + ChatColor.AQUA + "/horse changeowner <new owner>");
						event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "To unprotect the Horse/Donkey, use: " + ChatColor.AQUA + "/horse unprotect");
					} else {
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "This Horse/Donkey has already been protected by: " + ChatColor.RED + name);
					}
				}
			} break;
			case UNPROTECT: {
				event.setCancelled(true);
				String name = plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner");
				if (name != null) {
					if (name.equalsIgnoreCase(event.getPlayer().getName()) || event.getPlayer().hasPermission("horseprotection.admin.bypassunprotect")) {
						plugin.getConfig().set(event.getRightClicked().getUniqueId().toString(), null);
						plugin.saveConfig();
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "This Horse/Donkey is no longer protected.");
						plugin.setEdit(event.getPlayer().getName(), EditType.NONE, null);
					} else {
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot do this to a Horse/Donkey owned by: " + ChatColor.RED + name);
					}
				} else {
					event.getPlayer().sendMessage(ChatColor.DARK_RED + "This Horse/Donkey isn't currently protected!");
				}
			} break;
			case CHANGEOWNER: {
				event.setCancelled(true);
				String name = plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner");
				if (name != null) {
					if (name.equalsIgnoreCase(event.getPlayer().getName()) || event.getPlayer().hasPermission("horseprotection.admin.bypasschangeowner")) {
						String newName = plugin.getAdditionalInfo(event.getPlayer().getName());
						boolean isOtherPlayerOn = false;
						Player otherPlayer = null;
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.getName().equalsIgnoreCase(newName)) {
								isOtherPlayerOn = true;
								otherPlayer = p;
							}
						}
						if (isOtherPlayerOn) {
							plugin.getConfig().set(event.getRightClicked().getUniqueId().toString() + ".Owner", newName);
							plugin.saveConfig();
							event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "This Horse/Donkey is now owned by: " + ChatColor.AQUA + newName);
							otherPlayer.sendMessage(ChatColor.GREEN + event.getPlayer().getName() + ChatColor.DARK_GREEN + " has given you ownership of their Horse/Donkey.");
							plugin.setEdit(event.getPlayer().getName(), EditType.NONE, null);
						} else {
							event.getPlayer().sendMessage(ChatColor.DARK_RED + "The new Horse/Donkey owner must be online to use this.");
						}
					} else {
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot do this to a Horse/Donkey owned by: " + ChatColor.RED + name);
					}
				} else {
					event.getPlayer().sendMessage(ChatColor.DARK_RED + "This Horse/Donkey isn't currently protected!");
				}
			} break;
			case NONE: {
				String name = plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner");
				if (name != null) {
					if (!name.equalsIgnoreCase(event.getPlayer().getName()) && !event.getPlayer().hasPermission("horseprotection.admin.bypassprotection")) {
						event.setCancelled(true);
						event.getPlayer().sendMessage(ChatColor.DARK_RED + "You cannot do this to a Horse/Donkey owned by: " + ChatColor.RED + name);
					}
				}
			} break;
			case SEEOWNER: {
				event.setCancelled(true);
				String name = plugin.getConfig().getString(event.getRightClicked().getUniqueId().toString() + ".Owner");
				if (name == null) {
					event.getPlayer().sendMessage(ChatColor.DARK_AQUA + "This Horse/Donkey does not belong to anyone.");
				} else if (name.equalsIgnoreCase(event.getPlayer().getName())) {
					event.getPlayer().sendMessage(ChatColor.DARK_GREEN + "You own this Horse/Donkey.");
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + name + ChatColor.DARK_RED + " owns this Horse/Donkey.");
				}
				plugin.setEdit(event.getPlayer().getName(), EditType.NONE, null);
			} break;
			}
		}
	}
}
