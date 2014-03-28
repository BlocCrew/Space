package com.norway240.spaceplugin; 

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Space extends JavaPlugin implements Listener {
	public void onEnable() {
		getLogger().info("Space Plugin has been enabled!");
		getServer().getPluginManager().registerEvents(this, this);
		initializeConfig();
	}
  
	public void onDisable() {
		getLogger().info("Space Plugin has been disabled!");
	}
	
	public void initializeConfig(){
		FileConfiguration config = getConfig();
		
		config.addDefault("Space.world.spaceworldenabled", true);
		config.addDefault("Space.world.spaceworldname", "space");
		config.addDefault("Space.height.spaceheightenabled", false);
		config.addDefault("Space.height.spaceheight", 128);
		config.addDefault("Space.ignorecreative", false);
		
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		//FileConfiguration config = getConfig();
	    String spaceworld = "space";
	    Player player = event.getPlayer();
	    Location loc = player.getLocation();
	    
	    if (loc.getWorld().getName().equalsIgnoreCase(spaceworld)) {
	    	player.setAllowFlight(true);
	    	if (player.isOnGround()){
	    		player.setFlying(false);
	    	}else{
	    		player.setFlying(true);
	    	}
	    }else{
	    	if(!(player.getGameMode()==GameMode.CREATIVE)){
	    		player.setAllowFlight(false);
	    		player.setFlying(false);
	    	}
	    }
	    
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = getConfig();
		if (cmd.getName().equalsIgnoreCase("spaceplugin")){
				if(args.length == 1){
					if (args[0].equalsIgnoreCase("reload")){
						reloadConfig();
						sender.sendMessage("Spaceplugin config reloaded");
					}
				}else if(args.length == 2){
					if (args[0].equalsIgnoreCase("world")){
						if (args[1].equalsIgnoreCase("true")){
							config.set("Space.world.spaceworldenabled", true);
							sender.sendMessage("Spaceworld enabled");
						}else if(args[1].equalsIgnoreCase("false")){
							config.set("Space.world.spaceworldenabled", false);
							sender.sendMessage("Spaceworld disabled");
						}else{
							String newWorldName = args[1].toString();
							config.set("Space.world.spaceworldname", newWorldName);
							sender.sendMessage("Set Spaceworld to: " + newWorldName);
						}
					}
					if (args[0].equalsIgnoreCase("height")){
						if (args[1].equalsIgnoreCase("true")){}
						//TODO: else: set world space height
						sender.sendMessage("Setting changed");
					}
				}else{
					sender.sendMessage("SpacePlugin Commands:");
					sender.sendMessage("/spaceplugin - shows this help info");
					sender.sendMessage("/spaceplugin <setting> <value> - sets a config option");
					sender.sendMessage("/spaceplugin reload - reloads the config");
				}
			return true;
		}
		return false;
	}
	
}
