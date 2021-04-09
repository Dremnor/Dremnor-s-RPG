package me.dremnor.dremnorsrpg.enchants;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

import me.dremnor.dremnorsrpg.Main;



public class CustomeEnchants {
	public static final Enchantment DURABL_ENCHANTMENT = new EnchantWraper("durable", "Durable", 10);
	
	public static void register() {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(DURABL_ENCHANTMENT);
		if(!registered) {
			registerEnchantments(DURABL_ENCHANTMENT);
		}
	}
	
	public static void registerEnchantments(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field field = Enchantment.class.getDeclaredField("acceptingNew");
			field.setAccessible(true);
			field.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			registered = false;
		}
		if(registered) {
			 Main.getPlugin(Main.class).getLogger().info("Enchantment registered: "+enchantment.getKey());
		}
	}
}
