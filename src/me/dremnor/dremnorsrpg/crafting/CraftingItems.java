package me.dremnor.dremnorsrpg.crafting;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginAwareness.Flags;

import com.google.gson.Gson;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.misc.Enums.Rarity;



public class CraftingItems implements Listener{

	private Main plugin;
	
	public CraftingItems(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static List<Material> craftable = Arrays.asList(
			Material.WOODEN_AXE,Material.STONE_AXE,Material.IRON_AXE,Material.GOLDEN_AXE,Material.DIAMOND_AXE,Material.NETHERITE_AXE,
			Material.WOODEN_PICKAXE,Material.STONE_PICKAXE,Material.IRON_PICKAXE,Material.GOLDEN_PICKAXE,Material.DIAMOND_PICKAXE,Material.NETHERITE_PICKAXE,
			Material.WOODEN_SWORD,Material.STONE_SWORD,Material.IRON_SWORD,Material.GOLDEN_SWORD,Material.DIAMOND_SWORD,Material.NETHERITE_SWORD,
			Material.WOODEN_HOE,Material.STONE_HOE,Material.IRON_HOE,Material.GOLDEN_HOE,Material.DIAMOND_HOE,Material.NETHERITE_HOE,
			Material.WOODEN_SHOVEL,Material.STONE_SHOVEL,Material.IRON_SHOVEL,Material.GOLDEN_SHOVEL,Material.DIAMOND_SHOVEL,Material.NETHERITE_SHOVEL,
			Material.LEATHER_CHESTPLATE,Material.LEATHER_BOOTS,Material.LEATHER_HELMET,Material.LEATHER_LEGGINGS,
			Material.IRON_CHESTPLATE,Material.IRON_BOOTS,Material.IRON_HELMET,Material.IRON_LEGGINGS,
			Material.GOLDEN_CHESTPLATE,Material.GOLDEN_BOOTS,Material.GOLDEN_HELMET,Material.GOLDEN_LEGGINGS,
			Material.DIAMOND_CHESTPLATE,Material.DIAMOND_BOOTS,Material.DIAMOND_HELMET,Material.DIAMOND_LEGGINGS,
			Material.NETHERITE_CHESTPLATE,Material.NETHERITE_BOOTS,Material.NETHERITE_HELMET,Material.NETHERITE_LEGGINGS);
	
	
	@EventHandler
	public boolean onRecipeFormed(PrepareItemCraftEvent e) {
		for(ItemStack i : e.getInventory().getMatrix()){
			if(i != null){
				if(craftable.contains(i.getType())){
					e.getInventory().setResult(null);
				}
			}
		}

		if(e.getInventory().getResult() != null){
			if(craftable.contains(e.getInventory().getResult().getType()))  {
				e.getInventory().setResult(ItemGenerator.createCraftableItem(new ItemStack(e.getInventory().getResult().getType()),plugin,(Player)e.getViewers().get(0)));
				plugin.getLogger().info("Recipe Swap Done");
			}
		}

		return false;
	}
	

	
	
}
