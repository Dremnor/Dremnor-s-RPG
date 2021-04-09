package me.dremnor.dremnorsrpg.crafting;

import java.util.*;


import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;



import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.misc.Enums.Rarity;

public class ItemGenerator {

	
	public static ItemStack updateItemLore(ItemStack item, Main plugin) {
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer storage = meta.getPersistentDataContainer();
		int level = storage.get(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER);
		int exp = storage.get(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER);
		String crafter = storage.get(new NamespacedKey(plugin, "Crafter"), PersistentDataType.STRING);
		String rarity = storage.get(new NamespacedKey(plugin, "Rarity"), PersistentDataType.STRING);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		switch (Rarity.valueOf(rarity)) {
		case COMMON:
			rarity = ChatColor.WHITE+rarity;
			break;
		case UNCOMMON:
			rarity = ChatColor.DARK_GREEN+rarity;
			break;
		case RARE:
			rarity = ChatColor.BLUE+rarity;
			break;
		case LEGENDARY:
			rarity = ChatColor.GOLD+rarity;
			break;
		}
		
		Map<Enchantment, Integer> enchMap = meta.getEnchants();
		
		List<String> loreList = new ArrayList<String>();
	    for (Map.Entry<Enchantment, Integer> entry : enchMap.entrySet()) {
	    	loreList.add(ChatColor.GOLD+me.dremnor.dremnorsrpg.misc.Enums.enchantNamesMap.get(entry.getKey().getKey()) + " Lv."+entry.getValue());
	    }
		
		
			
		loreList.add(ChatColor.DARK_PURPLE+"Rarity: "+rarity);
		loreList.add(ChatColor.DARK_PURPLE+"Level: "+level);
		loreList.add(ChatColor.DARK_PURPLE+"Exp: "+exp);
		loreList.add(ChatColor.DARK_PURPLE+"Crafted By: "+crafter);

		
		meta.setLore(loreList);		
		item.setItemMeta(meta);

		return item;
	}

	public static void addEnchantLore(){

	}

	
	public static ItemStack setItemStackName(ItemStack item,String name) {		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setItemStackLore(ItemStack item,List<String> lore) {		
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);;
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setItemlocked(ItemStack item, Main plugin) {		
		ItemMeta meta = item.getItemMeta();
		PersistentDataContainer storage = meta.getPersistentDataContainer();
		storage.set(new NamespacedKey(plugin, "Locked"), PersistentDataType.BYTE, (byte)1);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack addToLoreFront(ItemStack item,List<String> lore) {		
		ItemMeta meta = item.getItemMeta();
		List<String> ilore = meta.getLore();
		for(String string : ilore) {
			lore.add(string);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack addToLoreBack(ItemStack item,List<String> lore) {		
		ItemMeta meta = item.getItemMeta();
		List<String> ilore = meta.getLore();
		for(String string : lore) {
			ilore.add(string);
		}
		meta.setLore(ilore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createCraftableItem(ItemStack itemStack, Main plugin, Player p) {
		ItemMeta meta = itemStack.getItemMeta();
		HashMap<String,Integer> abilitis = new HashMap<String,Integer>();
		PersistentDataContainer storage = meta.getPersistentDataContainer();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		storage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, 1);
		storage.set(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER, 0);
		storage.set(new NamespacedKey(plugin, "Rarity"), PersistentDataType.STRING, Rarity.COMMON.name());
		storage.set(new NamespacedKey(plugin, "Crafter"), PersistentDataType.STRING, p.getName());

		String type = itemStack.getType().toString().toLowerCase().split("_")[itemStack.getType().toString().toLowerCase().split("_").length-1];


		switch (type){
			case "axe":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.AXE.toString());
				break;
			case "pickaxe":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.PICKAXE.toString());
				break;
			case "hoe":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.HOE.toString());
				break;
			case "shovel":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.SHOVEL.toString());
				break;
			case "boots":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.BOOTS.toString());
				break;
			case "helmet":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.HELMET.toString());
				break;
			case "leggings":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.LEGGINS.toString());
				break;
			case "chestplate":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.CHESTPLATE.toString());
				break;
			case "sword":
				storage.set(new NamespacedKey(plugin, "Type"), PersistentDataType.STRING, Enums.ItemType.SWORD.toString());
				break;
			default:
				break;
		}




		itemStack.setItemMeta(meta);

		return ItemGenerator.updateItemLore(itemStack, plugin);
	}



}
