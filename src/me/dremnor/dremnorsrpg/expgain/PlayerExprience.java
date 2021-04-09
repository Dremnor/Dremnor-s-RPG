package me.dremnor.dremnorsrpg.expgain;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.skills.Skill;

public class PlayerExprience implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public PlayerExprience(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void updatePlayerLvl(Main plugin,Player p ){
		HashMap<String,Integer> abilities = Skill.getPlayerAbilities(p,plugin);
		PersistentDataContainer storage = p.getPersistentDataContainer();
		int level = 0;
		Set<String> keySet = abilities.keySet();
		
		for(String key : keySet) {
			level += abilities.get(key);
		}
		storage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, level);
		p.setDisplayName(ChatColor.GOLD+p.getName()+ChatColor.GREEN+" Lv."+level+ChatColor.RESET);
		p.setPlayerListName(ChatColor.GOLD+p.getName()+ChatColor.GREEN+" Lv."+level+ChatColor.RESET);
		
	}
	
	
}
