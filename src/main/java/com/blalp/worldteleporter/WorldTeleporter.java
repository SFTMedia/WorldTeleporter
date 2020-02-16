package com.blalp.worldteleporter;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldTeleporter extends JavaPlugin {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equals("worldtp")&&sender.hasPermission("worldteleporter.teleport")){
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("list")) {
					for(World world:Bukkit.getWorlds()) {
						sender.sendMessage("[WorldTeleporter] "+world.getName()+" is found at "+world.getWorldFolder());
					}
					return true;
				}
				if(sender instanceof Player) {
					if(Bukkit.getWorld(args[0])!=null) {
						((Player)sender).teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
					} else {
						sender.sendMessage("[WorldTeleporter] Please endter a valid world.");
					}
				} else {
					sender.sendMessage("[WorldTeleporter] You are not a player, silly.");
				}
			} else if (args.length==2) {
				if(sender.hasPermission("[WorldTeleporter] worldteleporter.other")) {
					if(Bukkit.getWorld(args[1])!=null) {
						if(Bukkit.getPlayer(args[0])!=null) {
							Bukkit.getPlayer(args[0]).teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
						} else if (Bukkit.getPlayer(UUID.fromString(args[0]))!=null) {
							Bukkit.getPlayer(UUID.fromString(args[0])).teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
						}
					} else {
						sender.sendMessage("[WorldTeleporter] Please endter a valid world.");
					}
				} else {
					sender.sendMessage("[WorldTeleporter] You dont have permission to teleport others.");
				}
			} else {
				sender.sendMessage("[WorldTeleporter] Please use valid args: /worldtp [name] [world] or /worldtp [world] to teleport yourself.");
			}
			return true;
		}
		return false;
	}
}
