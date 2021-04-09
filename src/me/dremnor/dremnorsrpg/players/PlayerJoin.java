package me.dremnor.dremnorsrpg.players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.expgain.PlayerExprience;

public class PlayerJoin implements Listener{

	private Main plugin;
	private NamespacedKey generated;
	
	
	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		generated = new NamespacedKey(this.plugin,"Generated");
	}
	
	@EventHandler
	public boolean onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = (Player)e.getPlayer();
		PersistentDataContainer storage = p.getPersistentDataContainer();
		if(storage.has(generated, PersistentDataType.BYTE)) {
			byte b = (byte)storage.get(generated, PersistentDataType.BYTE);
			if(b != (byte)1) {
				generatePlayerData(p);
			}
		}else{
			generatePlayerData(p);
		}
		PlayerExprience.updatePlayerLvl(plugin,p);
		
		ItemStack menu = new ItemStack(Material.ENDER_EYE);
		ItemMeta meta = menu.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE+"Main Menu");
		menu.setItemMeta(meta);
		if(!p.getInventory().contains(menu)) {
			p.getInventory().setItem(8, menu);
		}
		
		
		return false;		
	}
	
	
	public void generatePlayerData(Player p) {
		PersistentDataContainer storage = p.getPersistentDataContainer();
		storage.set(generated, PersistentDataType.BYTE, (byte)1);
		storage.set(new NamespacedKey(this.plugin,"Level"), PersistentDataType.INTEGER, 1);
		storage.set(new NamespacedKey(this.plugin,"Exp"), PersistentDataType.INTEGER, 0);
		p.setCustomName(ChatColor.GOLD+p.getName()+ChatColor.GREEN+" Lv.1");
		p.setDisplayName(ChatColor.GOLD+p.getName()+ChatColor.GREEN+" Lv.1");
		p.setCustomNameVisible(true);
		p.sendMessage(ChatColor.DARK_AQUA+"Player Data Generated Enjoy :)");
	}
	
	
	
	
}
