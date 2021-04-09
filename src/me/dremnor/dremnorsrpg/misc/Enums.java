package me.dremnor.dremnorsrpg.misc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class Enums {

	public static enum Rarity {
	    COMMON,
	    UNCOMMON,
	    RARE,
	    LEGENDARY
	}

	public static enum CustomeEnchants {
		Fortuna,
		Power,
		Gardening,
		Explosion,
		Telepathy,
		Durable,
		Yeld

	}
	
	public static enum ItemType {
		AXE,
		PICKAXE,
		SHOVEL,
		HOE,
		SWORD,
		HELMET,
		CHESTPLATE,
		LEGGINS,
		BOOTS,
		BOW
		
	}
	
	
	public static HashMap<NamespacedKey, String> enchantNamesMap = 
		new  HashMap<NamespacedKey, String>(){{
		    put(Enchantment.LOOT_BONUS_BLOCKS.getKey(), "Fortune");
		    put(Enchantment.LOOT_BONUS_MOBS.getKey(), "Looting");
		    put(Enchantment.DAMAGE_ALL.getKey(), "Sharpness");
		    put(Enchantment.DIG_SPEED.getKey(), "Efficiency");
		    put(Enchantment.LUCK.getKey(), "Luck of the Sea");
		    
		}};
	
}
