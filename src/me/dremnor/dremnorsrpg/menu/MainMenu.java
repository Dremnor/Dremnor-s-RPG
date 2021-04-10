package me.dremnor.dremnorsrpg.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.commands.DrpgCommand;
import net.md_5.bungee.api.ChatColor;

public class MainMenu {
	
	//view settings
	public static final String MENU_ITEM_NAME = ChatColor.BLUE+"Main Menu";
	public static final String MENU_VIEW_NAME = ChatColor.DARK_BLUE+"Main Menu";
	public static final int MENU_SIZE = 18;
	public static ItemStack FILLER_ITEM_STACK = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	
	//buttons
	public static final String MENU_STATS_BUTTON = ChatColor.DARK_RED+"Show Stats";
	public static final String MENU_LEARN_BUTTON = ChatColor.DARK_RED+"Learn New Ability";
	public static final String MENU_UPGRADE_BUTTON = ChatColor.DARK_RED+"Upgrade Ability";
	public static final String MENU_ENCHANT_BUTTON = ChatColor.DARK_RED+"Enchant Item";
	public static final String MENU_REFORGE_BUTTON = ChatColor.DARK_RED+"Reforge Item";
	
	public static Inventory getMenu(Player p) {
		
		Inventory inventory = Bukkit.createInventory(p, MENU_SIZE,MENU_VIEW_NAME);
		ItemMeta meta = FILLER_ITEM_STACK.getItemMeta();
		meta.setDisplayName("");
		FILLER_ITEM_STACK.setItemMeta(meta);
		
		for(int i=0;i<MENU_SIZE;i++) {
			inventory.setItem(i, FILLER_ITEM_STACK);			
		}
		
		ItemStack Stats = new ItemStack(Material.BOOK);
		ItemMeta sMeta = Stats.getItemMeta();
		sMeta.setDisplayName(MENU_STATS_BUTTON);
		Stats.setItemMeta(sMeta);
		
		ItemStack Learn = new ItemStack(Material.WRITTEN_BOOK);
		ItemMeta lMeta = Learn.getItemMeta();
		lMeta.setDisplayName(MENU_LEARN_BUTTON);
		Learn.setItemMeta(lMeta);
		
		ItemStack Upgrade = new ItemStack(Material.BREWING_STAND);
		ItemMeta uMeta = Upgrade.getItemMeta();
		uMeta.setDisplayName(MENU_UPGRADE_BUTTON);
		Upgrade.setItemMeta(uMeta);

		ItemStack Enchant = new ItemStack(Material.ENCHANTING_TABLE);
		ItemMeta eMeta = Enchant.getItemMeta();
		eMeta.setDisplayName(MENU_ENCHANT_BUTTON);
		Enchant.setItemMeta(eMeta);

		ItemStack Reforge = new ItemStack(Material.ANVIL);
		ItemMeta rMeta = Reforge.getItemMeta();
		rMeta.setDisplayName(MENU_REFORGE_BUTTON);
		Reforge.setItemMeta(rMeta);
		
		
		
		inventory.setItem(2, Stats);
		inventory.setItem(4, Learn);
		inventory.setItem(6, Upgrade);
		inventory.setItem(12, Enchant);
		inventory.setItem(14, Reforge);
		
		p.openInventory(inventory);
		return null;		
	}
	
	public static void itemClicked(ItemStack item, Player p) {
		if(item.getItemMeta().getDisplayName().equals(MENU_STATS_BUTTON)) {
			DrpgCommand.displayPlayerStats(Main.getPlugin(Main.class), p);
			p.closeInventory();
		}
		
		if(item.getItemMeta().getDisplayName().equals(MENU_LEARN_BUTTON)) {
			LearnMenu.getMenu(p);
		}
		
		if(item.getItemMeta().getDisplayName().equals(MENU_UPGRADE_BUTTON)) {
			UpgradeMenu.getMenu(p);
		}
		if(item.getItemMeta().getDisplayName().equals(MENU_ENCHANT_BUTTON)) {
			EnchantMenu.getMenu(p);
		}

	}
	
	
}



