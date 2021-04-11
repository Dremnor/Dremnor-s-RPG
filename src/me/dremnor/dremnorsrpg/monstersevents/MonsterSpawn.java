package me.dremnor.dremnorsrpg.monstersevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.custommob.MonsterPreparator;
import me.dremnor.dremnorsrpg.misc.GlobalMessage;

public class MonsterSpawn implements Listener{
	private Main plugin;
	private List<String> bosseStrings = Arrays.asList(ChatColor.GOLD+""+ChatColor.BOLD+"Dark Lord");
	private final int MAX_LEVEL = 50;

	public MonsterSpawn(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onChunkLoad(final ChunkLoadEvent e) {		
		Chunk chunk = e.getChunk();
		for(Entity entity : chunk.getEntities()) {
			if(entity instanceof LivingEntity) {
				PersistentDataContainer storage = entity.getPersistentDataContainer();			
				if(!storage.has(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER)) {						
					int level = calculateLevel(entity);
					Random r = new Random();
					String name = entity.getType().name().toString().toUpperCase(); 
					level = r.nextInt(level)+3;
					if(level >MAX_LEVEL){
						level = MAX_LEVEL;
					}

					if(entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CREEPER) {
						((LivingEntity) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(((LivingEntity) entity).getHealth()+level*2);
						((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth()+level*2);
						((LivingEntity) entity).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2+level*1.5);
					}					
					storage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, level);
					entity.setCustomName(ChatColor.GOLD+name+" "+ChatColor.GREEN+"Lv."+level);
					entity.setCustomNameVisible(true);
					if(entity.getType() != EntityType.VILLAGER){
						((LivingEntity)entity).setRemoveWhenFarAway(true);
					}else {
						((LivingEntity)entity).setRemoveWhenFarAway(false);
					}						
				}	
			}
		}							
	}

	
	
	@EventHandler
	public void onCreatureSpawn(final CreatureSpawnEvent e){
		if(e.getEntity() instanceof LivingEntity) {
			if(!bosseStrings.contains(e.getEntity().getCustomName())) {
				PersistentDataContainer storage = e.getEntity().getPersistentDataContainer();			
				if(!storage.has(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER)) {
					int level = calculateLevel(e.getEntity());
					Random r = new Random();
					String name = e.getEntity().getType().name().toString().toUpperCase(); 
					level = r.nextInt(level)+3;
					if(e.getEntity().getType() == EntityType.ZOMBIE || e.getEntity().getType() == EntityType.SKELETON || e.getEntity().getType() == EntityType.SPIDER || e.getEntity().getType() == EntityType.CREEPER) {
						((LivingEntity) e.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(((LivingEntity) e.getEntity()).getHealth()+level*2);
						((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getHealth()+level*2);
						((LivingEntity) e.getEntity()).getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2+level*1.5);
					}
					storage.set(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER, level);
					e.getEntity().setCustomName(ChatColor.GOLD+name+" "+ChatColor.GREEN+"Lv."+level);
					e.getEntity().setCustomNameVisible(true);
					if(e.getEntity().getType() != EntityType.VILLAGER && !(e.getEntity() instanceof Animals) ){
						((LivingEntity)e.getEntity()).setRemoveWhenFarAway(true);
					}else {
						((LivingEntity)e.getEntity()).setRemoveWhenFarAway(false);
					}
					
				}
			}else {				
				MonsterPreparator.finishDarkLord(e.getEntity(),plugin);
				GlobalMessage.sendToAllPlayers(plugin, ChatColor.RED+"Boss Monster Apeared!", e.getEntity().getCustomName()+
						ChatColor.RESET+" location: X."+Math.round(e.getLocation().getX())+" Y."+Math.round(e.getLocation().getY())+" Z."+Math.round(e.getLocation().getZ()));
			}
	
		}
	}
	

	private int calculateLevel(final Entity e) {
		
			//long start = System.nanoTime();

			List<Player> nearPlayers = new ArrayList<Player>();		
			List<Entity> near = e.getNearbyEntities(60, 60, 60);
			for(Entity entity : near) {
			    if(entity instanceof Player) {
			        nearPlayers.add((Player) entity);  
			    }
			}			
			int bestPlayerLvl = 0;			
			if(nearPlayers.size()>0) {
				for(Player p : nearPlayers){
					PersistentDataContainer storage = p.getPersistentDataContainer();
					int lvp = storage.get(new NamespacedKey(this.plugin,"Level"), PersistentDataType.INTEGER);
					if(lvp>bestPlayerLvl){
						bestPlayerLvl = lvp;
					}
				}
			}else {
				bestPlayerLvl = 5;
			}
			if(bestPlayerLvl<5) {
				bestPlayerLvl = 5;
			}
			
			//long finish = System.nanoTime();
			//long timeElapsed = finish - start;
			//plugin.getLogger().info("Entity Spawn("+e.getName()+") And Scan: "+timeElapsed );
			
			return bestPlayerLvl;	
	}
	
	

	
	
	
	
}
