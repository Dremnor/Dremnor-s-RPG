package me.dremnor.dremnorsrpg.monstersevents;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;

public class BreedEvent implements Listener{
	
	private Main plugin;
	private static final int MAX_LVL = 100;
	
	public BreedEvent (Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public boolean onAnimalBreed(EntityBreedEvent e) {
		Entity m = e.getMother();
		PersistentDataContainer mstorage = m.getPersistentDataContainer();
		Entity f = e.getFather();
		PersistentDataContainer fstorage = f.getPersistentDataContainer();
		if(mstorage.has(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER) && fstorage.has(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER)) {
			int ml = mstorage.get(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER);
			int fl = fstorage.get(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER);
			Random random = new Random();
			int clvl = (ml+fl)/2 +random.nextInt(5)+2 ;
			if(clvl > MAX_LVL) {
				clvl = MAX_LVL;
			}
			Entity child = e.getEntity();
			PersistentDataContainer chilstorage = child.getPersistentDataContainer();
			chilstorage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, clvl);
			e.getEntity().setCustomName(ChatColor.GOLD+child.getName()+" "+ChatColor.GREEN+"Lv."+clvl);
			e.getEntity().setCustomNameVisible(true);
			((LivingEntity)e.getEntity()).setRemoveWhenFarAway(true);		
		}
		return false;
	}
}
