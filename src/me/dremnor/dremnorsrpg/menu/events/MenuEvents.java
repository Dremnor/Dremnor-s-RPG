package me.dremnor.dremnorsrpg.menu.events;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.menu.EnchantMenu;
import me.dremnor.dremnorsrpg.menu.LearnMenu;
import me.dremnor.dremnorsrpg.menu.MainMenu;
import me.dremnor.dremnorsrpg.menu.UpgradeMenu;

public class MenuEvents implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
		
	public MenuEvents(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public boolean onMenuClick(InventoryClickEvent e) {
		plugin.getLogger().info("zlapalem click");
		if(e.getView().getTitle().equals(MainMenu.MENU_VIEW_NAME)) {
			if(e.getCurrentItem() != null)
				MainMenu.itemClicked(e.getCurrentItem(), (Player)e.getWhoClicked());
			e.setCancelled(true);			
		}
		
		if(e.getView().getTitle().equals(EnchantMenu.MENU_VIEW_NAME)) {
			if(e.getCurrentItem() != null) {
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				if(meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "Locked"),PersistentDataType.BYTE)) {
					e.setCancelled(true);	
				}
				if(e.getCurrentItem().getType() == Material.WRITTEN_BOOK){
					EnchantMenu.itemClicked(e.getCurrentItem(),(Player)e.getWhoClicked(),plugin, e.getInventory());
				}
			}else {
				
			}			
		}
		
		if(e.getView().getTitle().equals(LearnMenu.MENU_VIEW_NAME)) {
			if(e.getCurrentItem() != null)
				LearnMenu.itemClicked(e.getCurrentItem(), (Player)e.getWhoClicked());
			e.setCancelled(true);			
		}
		
		if(e.getView().getTitle().equals(UpgradeMenu.MENU_VIEW_NAME)) {
			if(e.getCurrentItem() != null)
				UpgradeMenu.itemClicked(e.getCurrentItem(), (Player)e.getWhoClicked());
			e.setCancelled(true);			
		}
		
		if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(MainMenu.MENU_ITEM_NAME)) {
				e.setCancelled(true);
				MainMenu.getMenu((Player)e.getWhoClicked());			
			}
		}
		
		
		return false;	
	}
	
	@EventHandler
	private boolean onItemDrop(PlayerDropItemEvent e) {
		
		
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(MainMenu.MENU_ITEM_NAME)) {
			e.setCancelled(true);
		}
		
		return false;		
	}
	
	@EventHandler
	private boolean onItemMove(InventoryMoveItemEvent e) {
		
		if(e.getItem().getItemMeta().getDisplayName().equals(MainMenu.MENU_ITEM_NAME)) {
			e.setCancelled(true);
		}
		plugin.getLogger().info("zlapalem move item");
		return false;		
	}

	@EventHandler
	private boolean onInventoryInteract(InventoryInteractEvent e){
		plugin.getLogger().info("zlapalem interazct");
		return false;
	}
	
	@EventHandler
	private boolean onItemRightClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
					if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(MainMenu.MENU_ITEM_NAME)) {
						MainMenu.getMenu((Player)e.getPlayer());
						e.setCancelled(true);
						return false;
					}
				}				
			}				
		return false;		
	}
	
	
}
