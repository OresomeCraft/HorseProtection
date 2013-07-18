package com.oresomecraft.HorseProtection.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.oresomecraft.HorseProtection.HorseProtection;
import com.oresomecraft.HorseProtection.DataStorage.EditType;

public class HorseProtectionCommands implements CommandExecutor {

    private HorseProtection plugin;

    public HorseProtectionCommands(HorseProtection plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("horse")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("protect")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 1) {
                            if (player.hasPermission("horseprotection.other.protect")) {
                                plugin.setEdit(player.getName(), EditType.PROTECT, null);
                                player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to protect.");
                            } else {
                                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse protect");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
                    }
                } else if (args[0].equalsIgnoreCase("unprotect")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 1) {
                            if (player.hasPermission("horseprotection.other.unprotect")) {
                                plugin.setEdit(player.getName(), EditType.UNPROTECT, null);
                                player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to remove");
                                player.sendMessage(ChatColor.DARK_GREEN + "protection from.");
                            } else {
                                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse unprotect");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
                    }
                } else if (args[0].equalsIgnoreCase("owner")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 2) {
                            if (player.hasPermission("horseprotection.other.changeowner")) {
                                boolean isOtherPlayerOn = false;
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getName().equalsIgnoreCase(args[1])) {
                                        isOtherPlayerOn = true;
                                    }
                                }
                                if (isOtherPlayerOn) {
                                    plugin.setEdit(player.getName(), EditType.CHANGEOWNER, args[1]);
                                    player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to change");
                                    player.sendMessage(ChatColor.DARK_GREEN + "the owner for.");
                                    player.sendMessage(ChatColor.RED + "NOTE:" + ChatColor.DARK_RED + " Once you do so they will no longer be yours!");
                                    player.sendMessage(ChatColor.DARK_RED + "To cancel this, use: " + ChatColor.RED + "/horse cancel");
                                } else {
                                    player.sendMessage(ChatColor.DARK_RED + "The new Horse/Donkey owner must be online to use this.");
                                }
                            } else {
                                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse owner <new owner>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
                    }
                /*
				} else if (args[0].equalsIgnoreCase("addmember")) {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (args.length == 2) {
							if (player.hasPermission("horseprotection.other.addmember")) {
								plugin.setEdit(player.getName(), EditType.ADDMEMBER);
								player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to add a new");
								player.sendMessage(ChatColor.DARK_GREEN + "member to.");
							} else {
								player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
							}
						} else {
							player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse addmember <new member>");
						}
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
					}
				} else if (args[0].equalsIgnoreCase("removemember")) {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (args.length == 2) {
							if (player.hasPermission("horseprotection.other.removemember")) {
								plugin.setEdit(player.getName(), EditType.REMOVEMEMBER);
								player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to remove a");
								player.sendMessage(ChatColor.DARK_GREEN + "member from.");
							} else {
								player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
							}
						} else {
							player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse removemember <member name>");
						}
					} else {
						sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
					} */
                } else if (args[0].equalsIgnoreCase("cancel")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 1) {
                            if (player.hasPermission("horseprotection.other.cancel")) {
                                plugin.setEdit(player.getName(), EditType.NONE, null);
                                player.sendMessage(ChatColor.DARK_AQUA + "You have cancelled your Horse/Donkey protection action.");
                            } else {
                                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse cancel");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
                    }
                } else if (args[0].equalsIgnoreCase("ownedby")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (args.length == 1) {
                            if (player.hasPermission("horseprotection.other.seeowner")) {
                                plugin.setEdit(player.getName(), EditType.SEEOWNER, null);
                                player.sendMessage(ChatColor.DARK_GREEN + "Please " + ChatColor.GREEN + "RIGHT-CLICK" + ChatColor.DARK_GREEN + " the Horse/Donkey you wish to see the");
                                player.sendMessage(ChatColor.DARK_GREEN + "owner of.");
                            } else {
                                player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
                            }
                        } else {
                            player.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse ownedby");
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "You must be in-game to use this command.");
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Invalid command. Use: " + ChatColor.RED + "/horse" + ChatColor.DARK_RED + " to list all commands.");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "========= Horse Protection Commands =========");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.DARK_PURPLE + "/horse protect" + ChatColor.LIGHT_PURPLE + ": Protects a Horse/Donkey");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.DARK_PURPLE + "/horse unprotect" + ChatColor.LIGHT_PURPLE + ": Unprotect a Horse/Donkey");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.DARK_PURPLE + "/horse owner <name>" + ChatColor.LIGHT_PURPLE + ": Change Horse/Donkey ownership");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.DARK_PURPLE + "/horse ownedby" + ChatColor.LIGHT_PURPLE + ": View the Horse/Donkey's owner.");
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + ChatColor.DARK_PURPLE + "/horse cancel" + ChatColor.LIGHT_PURPLE + ": Cancel your last /horse command.");
            }
            return true;
        }
        return false;
    }

}
