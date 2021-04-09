package me.dremnor.dremnorsrpg.monstersevents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import me.dremnor.dremnorsrpg.Main;

public class MonsterDie implements Listener{
	private Main plugin;
	
	public MonsterDie(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public boolean onMonsterDeath(EntityDeathEvent e) {
		
		if(e.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity)e.getEntity();
			if(le.getKiller() instanceof Player) {
				PersistentDataContainer PlayerStorage = le.getKiller().getPersistentDataContainer();
				PersistentDataContainer MonsterStorage = e.getEntity().getPersistentDataContainer();
				if(MonsterStorage.has(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER)) {
					plugin.getLogger().info("This entity has Level data");
				}else {
					plugin.getLogger().info("This entity don't have Level Data");
				}
				int xp = MonsterStorage.get(new NamespacedKey(plugin, "Level"), PersistentDataType.INTEGER);
				xp = (int) Math.floor(xp*1.6);
				xp = e.getDroppedExp()+xp;
				int exp = PlayerStorage.get(new NamespacedKey(this.plugin,"Exp"), PersistentDataType.INTEGER);
				PlayerStorage.set(new NamespacedKey(this.plugin,"Exp"), PersistentDataType.INTEGER, exp+xp);				
				String message = ChatColor.DARK_GREEN+""+xp+" Skill Points Aquired(Total: "+(exp+xp)+")";
				le.getKiller().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));		        
			}	
		}	
		return false;		
	}
	


}
