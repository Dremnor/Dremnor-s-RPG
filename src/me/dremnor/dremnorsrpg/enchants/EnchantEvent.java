package me.dremnor.dremnorsrpg.enchants;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import me.dremnor.dremnorsrpg.Main;

public class EnchantEvent implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	
	
	public EnchantEvent(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public boolean onEnchantment(PrepareItemEnchantEvent e) {
		e.setCancelled(true);
		return false;		
	}
	
	@EventHandler
	public boolean onAnvilCrafting(PrepareAnvilEvent e) {
		e.setResult(new ItemStack(Material.AIR));
		
		return false;
	}
	@EventHandler
	public boolean onAnvilInventory(InventoryOpenEvent e) {
		if(e.getInventory().getType()==InventoryType.ANVIL) {
			e.setCancelled(true);
		}
		if(e.getInventory().getType()==InventoryType.ENCHANTING) {
			e.setCancelled(true);
		}
		return false;
	}
}
