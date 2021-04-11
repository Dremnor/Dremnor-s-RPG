package me.dremnor.dremnorsrpg.custommob;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.custommob.boss.DarkLord;
import me.dremnor.dremnorsrpg.misc.BossBarGenerator;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.WorldServer;

public class MonsterPreparator {

	public static DarkLord prepareDarkLord(Location loc) {
		DarkLord darklord = new DarkLord(loc);
		darklord.setCustomName(new ChatComponentText(ChatColor.GOLD+""+ChatColor.BOLD+"Dark Lord"));
		darklord.setCustomNameVisible(true);
		darklord.setHealth(3000);		
		WorldServer worldServer = ((CraftWorld)loc.getWorld()).getHandle();		
		worldServer.addEntity(darklord);		
		return darklord;
	}
	
	public static void finishDarkLord(LivingEntity e, Main plugin){
		e.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2000);
		e.setHealth(2000);		
		e.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
		e.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
		e.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
		e.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
		e.setRemoveWhenFarAway(false);
		ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
		itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
		itemStack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
		e.getEquipment().setItemInMainHand(itemStack);
		e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
		e.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(3);
		PersistentDataContainer storage = e.getPersistentDataContainer();
		storage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, 60);
		storage.set(new NamespacedKey(plugin, "Boss"), PersistentDataType.STRING, "Dark Lord");
		BossBar b = BossBarGenerator.createBossBar(plugin, e, e.getCustomName(), BarColor.BLUE, BarStyle.SOLID, 2000);
		Main.activebosses.put(e,b);
		int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> updatebar(e),1L,1L);
		e.getPersistentDataContainer().set(new NamespacedKey(plugin, "Schedule"), PersistentDataType.INTEGER, i); 
	}
	
	private static void updatebar(LivingEntity e) {
		List<Player> players = new ArrayList<Player>();
		if(e.isDead()) {
			Main.activebosses.remove(e);
			Bukkit.getScheduler().cancelTask(e.getPersistentDataContainer().get(new NamespacedKey(Main.getPlugin(Main.class), "Schedule"), PersistentDataType.INTEGER));
		}
		BossBar bar = Main.activebosses.get(e);
		if(bar!=null) {
			for(Entity et : e.getLocation().getWorld().getNearbyEntities(e.getLocation(), 30, 30, 30)) {
				if(et instanceof Player) {
					if(!players.contains(et)) {
						players.add((Player)et);
					}
				}
			}
			for(Player p : bar.getPlayers()) {
				if(!players.contains(p)){
					bar.removePlayer(p);
				}
			}
			for(Player p: players) {
				if(!bar.getPlayers().contains(p)) {
					bar.addPlayer(p);
				}
			}
		}

		
		
	}
	
	
	
	
}
