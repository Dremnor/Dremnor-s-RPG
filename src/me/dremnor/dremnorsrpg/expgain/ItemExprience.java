package me.dremnor.dremnorsrpg.expgain;

import java.util.HashMap;

import me.dremnor.dremnorsrpg.misc.Enums;
import me.dremnor.dremnorsrpg.tools.Axe;
import me.dremnor.dremnorsrpg.tools.Hoe;
import me.dremnor.dremnorsrpg.tools.Pickaxe;
import me.dremnor.dremnorsrpg.tools.Shovel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;



import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.crafting.CraftingItems;
import me.dremnor.dremnorsrpg.crafting.ItemGenerator;


public class ItemExprience implements Listener{

	private Main plugin;
	
	public ItemExprience(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		initExpMaps();
	}
	
	HashMap<Material, Integer> pickaxeExp;
	HashMap<Material, Integer> axeExp;
	HashMap<Material, Integer> hoeExp;
	HashMap<Material, Integer> swordExp;
	
	private void initExpMaps() {
		pickaxeExp = new HashMap<Material, Integer>();
		axeExp = new HashMap<Material, Integer>();
		hoeExp = new HashMap<Material, Integer>();
		swordExp = new HashMap<Material, Integer>();
		
		//pickaxe blocks		
		pickaxeExp.put(Material.STONE, 1);
		pickaxeExp.put(Material.COBBLESTONE, 1);
		pickaxeExp.put(Material.COAL_ORE, 4);
		pickaxeExp.put(Material.IRON_ORE, 8);
		pickaxeExp.put(Material.GOLD_ORE, 16);
		pickaxeExp.put(Material.DIAMOND_ORE, 32);
		pickaxeExp.put(Material.DIORITE, 1);
		pickaxeExp.put(Material.GRANITE, 1);
		pickaxeExp.put(Material.ANDESITE, 1);
		pickaxeExp.put(Material.EMERALD_ORE, 32);
		pickaxeExp.put(Material.NETHER_QUARTZ_ORE, 16);
		pickaxeExp.put(Material.NETHER_GOLD_ORE, 16);
		
	}
	
	
	
	@EventHandler
	public boolean onBlockBreak(BlockBreakEvent e) {
		
		if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {		
			return true;
		}
		
		Material item = e.getPlayer().getInventory().getItemInMainHand().getType();
		Block block = e.getBlock();
		if(CraftingItems.craftable.contains(item)) {
			ItemMeta itemMeta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
			PersistentDataContainer storage =  itemMeta.getPersistentDataContainer();
			String type = storage.get(new NamespacedKey(Main.getPlugin(Main.class), "Type"), PersistentDataType.STRING);

			Enums.ItemType itemType = Enums.ItemType.valueOf(type);

			plugin.getLogger().info("Zlapalem Block Break ! : "+itemType.toString() +"item type: "+e.getBlock().getType());
			switch (itemType){
				case AXE:
					Axe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					ItemGenerator.updateItemLore(e.getPlayer().getInventory().getItemInMainHand(), plugin);
					break;
				case PICKAXE:
					Pickaxe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					ItemGenerator.updateItemLore(e.getPlayer().getInventory().getItemInMainHand(), plugin);
					break;
				case SHOVEL:
					Shovel.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					ItemGenerator.updateItemLore(e.getPlayer().getInventory().getItemInMainHand(), plugin);
					break;
				case HOE:
					Hoe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					ItemGenerator.updateItemLore(e.getPlayer().getInventory().getItemInMainHand(), plugin);
					break;
				default:
					break;
			}
		}
		return false;
	}
	

	public void pickaxeCheck(Player p , Material block) {
		ItemStack itemStack = p.getInventory().getItemInMainHand();
		ItemMeta meta = itemStack.getItemMeta();
		PersistentDataContainer storage = meta.getPersistentDataContainer();






		if(storage.has(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER)) {
			if(pickaxeExp.containsKey(block)) {
				int exp = storage.get(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER);
				exp+=pickaxeExp.get(block);
				storage.set(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER, exp);
			}
			itemStack.setItemMeta(meta);
			ItemGenerator.updateItemLore(itemStack, plugin);
		}else{
			p.getInventory().setItemInMainHand(ItemGenerator.createCraftableItem(p.getInventory().getItemInMainHand(), plugin, p));
			pickaxeCheck(p , block);
		}
	}
	
	
	
	
	
}
