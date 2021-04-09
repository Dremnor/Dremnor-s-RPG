package me.dremnor.dremnorsrpg.menu;

import java.beans.PersistenceDelegate;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.dremnor.dremnorsrpg.enchants.Enchant;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.crafting.CraftingItems;
import me.dremnor.dremnorsrpg.crafting.ItemGenerator;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EnchantMenu {
	//view settings

		public static final String MENU_VIEW_NAME = ChatColor.DARK_BLUE+"Item Imbue";
		public static final int MENU_SIZE = 54;
		public static ItemStack FILLER_ITEM_STACK = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		
		
		public static Inventory getMenu(Player p) {
			
			Inventory inventory = Bukkit.createInventory(p, MENU_SIZE,MENU_VIEW_NAME);
			ItemMeta meta = FILLER_ITEM_STACK.getItemMeta();
			meta.setDisplayName(" ");
			FILLER_ITEM_STACK.setItemMeta(meta);
			FILLER_ITEM_STACK = ItemGenerator.setItemlocked(FILLER_ITEM_STACK,  Main.getPlugin(Main.class));
			ItemStack redglass = ItemGenerator.setItemStackName(new ItemStack(Material.RED_STAINED_GLASS_PANE), "");
			redglass = ItemGenerator.setItemlocked(redglass,  Main.getPlugin(Main.class));
			for(int i=0;i<MENU_SIZE;i++) {
				inventory.setItem(i, FILLER_ITEM_STACK);			
			}
			
			ItemStack back = new ItemStack(Material.BARRIER);
			back = ItemGenerator.setItemStackName(back, ChatColor.DARK_RED+"BACK");
			back = ItemGenerator.setItemlocked(back,Main.getPlugin(Main.class));
			inventory.setItem(8, back);
			
			
			
			inventory.setItem(4, ItemGenerator.setItemlocked(ItemGenerator.setItemStackLore(ItemGenerator.setItemStackName(new ItemStack(Material.BOOK), ChatColor.DARK_RED+"Info"), 
						      Arrays.asList(ChatColor.AQUA+"Put your item below",ChatColor.AQUA+"then click an available imbue!")), Main.getPlugin(Main.class)));
			inventory.setItem(3,redglass);
			inventory.setItem(12,redglass);
			inventory.setItem(21,redglass);
			inventory.setItem(5,redglass);
			inventory.setItem(14,redglass);
			inventory.setItem(23,redglass);
			inventory.setItem(22,redglass);
			inventory.setItem(13, null);
			
			
			
			p.openInventory(inventory);
			return null;		
		}
		
		public static void itemClicked(ItemStack item, Player p, Inventory i) {
			if(i.getItem(13) != null){
				ItemStack itemupgrade = i.getItem(13);
				ItemMeta meta = itemupgrade.getItemMeta();
				PersistentDataContainer storage = meta.getPersistentDataContainer();
				HashMap<Enums.CustomeEnchants,Integer> enchants;
				Enums.ItemType type = null;
				Main.getPlugin(Main.class).getLogger().info("test 1");
				if(storage.has(new NamespacedKey(Main.getPlugin(Main.class),"Type"), PersistentDataType.STRING)){
					Main.getPlugin(Main.class).getLogger().info("test 2");
					type = Enums.ItemType.valueOf(storage.get(new NamespacedKey(Main.getPlugin(Main.class),"Type"), PersistentDataType.STRING));
				}
				Main.getPlugin(Main.class).getLogger().info("test 13)");
				if(item!=i.getItem(13)){

				}else
				if(type != null){
					Main.getPlugin(Main.class).getLogger().info("test 4");
					if(storage.has(new NamespacedKey(Main.getPlugin(Main.class),"Enchants"), PersistentDataType.STRING)){
						String json = storage.get(new NamespacedKey(Main.getPlugin(Main.class),"Enchants"), PersistentDataType.STRING);
						enchants = new Gson().fromJson(json, new TypeToken<HashMap<Enums.CustomeEnchants,Integer>>(){}.getType());
						Main.getPlugin(Main.class).getLogger().info("test 5");
						int index = 36;
						for(Map.Entry<Enums.CustomeEnchants, Enchant> entry : Main.enchants.entrySet()){
		 				int lvl = -1;
							if(enchants.containsKey(entry.getKey())){
								lvl = enchants.get(entry.getKey());
								Main.getPlugin(Main.class).getLogger().info("test 6");
							}
							if(entry.getValue().itemTypeList.contains(type)){
								Main.getPlugin(Main.class).getLogger().info("test 7");
								ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
								book = ItemGenerator.setItemStackName(book,entry.getValue().name.toString());
								book = ItemGenerator.setItemlocked(book,Main.getPlugin(Main.class));
								if(lvl != -1){
									book = ItemGenerator.setItemStackLore(book,Arrays.asList("Click to Aply Imbu.","Exp Cost: "+entry.getValue().cost*(lvl+1)));
								}else{
									book = ItemGenerator.setItemStackLore(book,Arrays.asList("Click to Aply Imbu.","Exp Cost: "+entry.getValue().cost));
								}
								i.setItem(index,book);
								index++;
							}
						}
					}else{
						Main.getPlugin(Main.class).getLogger().info("test 8");
						int index = 36;
						for(Map.Entry<Enums.CustomeEnchants, Enchant> entry : Main.enchants.entrySet()){
							Main.getPlugin(Main.class).getLogger().info("test 9");
							if(entry.getValue().itemTypeList.contains(type)){
								Main.getPlugin(Main.class).getLogger().info("test 10");
								ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
								book = ItemGenerator.setItemStackName(book,entry.getValue().name.toString());
								book = ItemGenerator.setItemlocked(book,Main.getPlugin(Main.class));
								book = ItemGenerator.setItemStackLore(book,Arrays.asList("Click to Aply Imbu.","Exp Cost: "+entry.getValue().cost));
								i.setItem(index,book);
								p.openInventory(i);
								index++;
							}
						}
					}
				}
			}
		}
		
		public static void itemMove(Inventory i) {
			if(i.getItem(13) != null) {
				if(CraftingItems.craftable.contains(i.getItem(13).getType())) {
					ItemStack item = i.getItem(13);
					ItemMeta itemMeta = item.getItemMeta();


					PersistentDataContainer storage =  itemMeta.getPersistentDataContainer();


					Enums.ItemType itemType = Enums.ItemType.valueOf(storage.get(new NamespacedKey(Main.getPlugin(Main.class), "Type"), PersistentDataType.STRING));



				}
			}
		}
		
}
