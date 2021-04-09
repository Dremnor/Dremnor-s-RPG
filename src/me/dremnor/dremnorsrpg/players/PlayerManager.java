package me.dremnor.dremnorsrpg.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;
import org.omg.CORBA.PUBLIC_MEMBER;

import me.dremnor.dremnorsrpg.Main;

public class PlayerManager implements Listener{

	@SuppressWarnings("unused")
	private Main plugin;
	
	
	public PlayerManager(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public boolean onEntityDamage(EntityDamageEvent e) {
		if((e.getEntity() instanceof Player)) {
			if(e.getCause() == DamageCause.BLOCK_EXPLOSION) {
				plugin.getLogger().info("Damage cause: "+e.getCause()+" "+e.getEntity().getCustomName());
				e.setCancelled(true);
			}
		}	
		return false;	
	}
	
	@EventHandler
	public boolean onEntityDamage(BlockDamageEvent e) {
		
		
		return false;	
	}
		
		
		

	
	public static void updatePlayerLvl(Player player, Main plugin){
		
	}
	
	
}
