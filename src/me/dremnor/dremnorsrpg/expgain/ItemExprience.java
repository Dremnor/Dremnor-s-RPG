package me.dremnor.dremnorsrpg.expgain;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.dremnor.dremnorsrpg.enchants.Enchant;
import me.dremnor.dremnorsrpg.enchants.sub.Explosion;
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
			HashMap<Enums.CustomeEnchants,Integer> enchants = null;
			if(storage.has(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING)){
				String json = storage.get(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING);
				enchants = new Gson().fromJson(json, new TypeToken<HashMap<Enums.CustomeEnchants,Integer>>(){}.getType());
			}
			Enums.ItemType itemType = Enums.ItemType.valueOf(type);

			plugin.getLogger().info("Zlapalem Block Break ! : "+itemType.toString() +"item type: "+e.getBlock().getType());
			switch (itemType){
				case AXE:
					Axe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					ItemGenerator.updateItemLore(e.getPlayer().getInventory().getItemInMainHand(), plugin);
					break;
				case PICKAXE:
					if( enchants != null && enchants.size()>0){
						if(enchants.containsKey(Enums.CustomeEnchants.Explosion)){
							Main.enchants.get(Enums.CustomeEnchants.Explosion).blockEffect(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock(),e.getPlayer(),enchants.get(Enums.CustomeEnchants.Explosion));
						}else{
							Pickaxe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
						}
					}else{
						Pickaxe.addExpToTool(e.getPlayer().getInventory().getItemInMainHand(),e.getBlock().getType());
					}


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
}
