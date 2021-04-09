package me.dremnor.dremnorsrpg.misc;

import org.bukkit.entity.Player;

import me.dremnor.dremnorsrpg.Main;

public class GlobalMessage {

	
	
	public static void sendToAllPlayers(Main plugin , String message, String submessage) {
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			p.sendTitle(message,submessage,10,70,20);
		}
	}
	
}
