package com.norway240.spaceplugin;

import org.bukkit.GameMode;
import org.bukkit.Location;
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
		
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	  public void onPlayerMove(PlayerMoveEvent event) {
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
}
