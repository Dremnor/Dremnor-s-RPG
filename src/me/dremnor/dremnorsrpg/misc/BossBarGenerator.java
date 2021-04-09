package me.dremnor.dremnorsrpg.misc;

import java.util.List;


import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.dremnor.dremnorsrpg.Main;



public class BossBarGenerator {
	
	public static BossBar createBossBar(Main plugin, LivingEntity livingEntity, String title, BarColor color, BarStyle style, int health) {
		  
		BossBar bossBar = plugin.getServer().createBossBar(title, color, style);
		new BukkitRunnable() {
			int max_health = health;
			@Override
			public void run() {
				 if (!livingEntity.isDead()) {
			           bossBar.setProgress(livingEntity.getHealth() / max_health);
			       } else {
			           List<Player> players = bossBar.getPlayers();
			           for (Player player : players) {
			               bossBar.removePlayer(player);
			           }
			           bossBar.setVisible(false);
			           cancel();
			       }				
			}
		}.runTaskTimer(plugin, 0, 1);

		
		  return bossBar;
	}
}
