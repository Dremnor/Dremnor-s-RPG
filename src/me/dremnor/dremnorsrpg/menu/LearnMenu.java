package me.dremnor.dremnorsrpg.menu;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.dremnor.dremnorsrpg.Main;
import net.md_5.bungee.api.ChatColor;




public class LearnMenu {
	
	public static final String MENU_VIEW_NAME = ChatColor.DARK_BLUE+"Learn Ability";
	public static final int MENU_SIZE = 36;
	public static ItemStack FILLER_ITEM_STACK = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	
	public static Inventory getMenu(Player p) {
		Inventory inventory = Bukkit.createInventory(p, MENU_SIZE,MENU_VIEW_NAME);
		ItemMeta meta = FILLER_ITEM_STACK.getItemMeta();
		meta.setDisplayName("");
		FILLER_ITEM_STACK.setItemMeta(meta);
		
		for(int i=0;i<MENU_SIZE;i++) {
			inventory.setItem(i, FILLER_ITEM_STACK);			
		}
		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta iMeta = back.getItemMeta();
		iMeta.setDisplayName(ChatColor.DARK_RED+"BACK");
		back.setItemMeta(iMeta);
		inventory.setItem(35, back);


		Set<String> keySet = Main.abilities.keySet();
		int i = 10;
		for(String key : keySet) {
			boolean flag = true;
			while(flag && i<(MENU_SIZE-9)) {
				if((i+1)%9 != 0 && (i+1)%9 != 1) {					
					ItemStack stack = new ItemStack(Material.WRITTEN_BOOK);
					ItemMeta meta1 = stack.getItemMeta();
					meta1.setLore(new ArrayList<String>());
					meta1.setDisplayName(ChatColor.DARK_GREEN+key);
					stack.setItemMeta(meta1);
					inventory.setItem(i, stack);
					i++;
					flag = false;
				}else {
					i++;
				}
			}		
		}
		
		
		p.openInventory(inventory);
		return inventory;
	}
	
	public static void itemClicked(ItemStack item, Player p) {
		if(Main.abilities.containsKey(ChatColor.stripColor(item.getItemMeta().getDisplayName()))) {
			Main.abilities.get(ChatColor.stripColor(item.getItemMeta().getDisplayName())).learn(p);
			MainMenu.getMenu(p);
		}
		if(item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED+"BACK")) {
			MainMenu.getMenu(p);
		}
	}
}
