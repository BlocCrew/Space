package com.norway240.spaceplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdExe implements CommandExecutor{
		 
	/*private Space plugin; // pointer to your main class, unrequired if you don't need methods from the main class
	 
		public CmdExe(Space plugin) {
			this.plugin = plugin;
		}*/
	 
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			return false;
	}
}
