package me.dremnor.dremnorsrpg.commands;


import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.menu.EnchantMenu;
import me.dremnor.dremnorsrpg.misc.TabCompleterLearn;
import me.dremnor.dremnorsrpg.skills.Skill;


public class DrpgCommand implements CommandExecutor{

	private Main plugin;
	BossBar bar;
	
	public DrpgCommand(Main plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("drpg").setExecutor(this);
		this.plugin.getCommand("drpg").setTabCompleter(new TabCompleterLearn());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		if(!cmd.getName().equals("drpg")) {		
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("cmd only for players");
			return true;
		}
		
		switch (args.length) {
		case 1:
			switch (args[0]) {
			case "stats":
				displayPlayerStats(plugin,(Player)sender);				
				break;
			case "spawn":
				if(((Player)sender).hasPermission("dremnorsrpg.Admin")) {
					//MonsterPreparator.prepareDarkLord(((Player)sender).getLocation());
					//((Player)sender).getWorld().createExplosion(((Player)sender).getLocation(), 2,false,false,(Player)sender);
					
					EnchantMenu.getMenu((Player)sender);
				}
				break;
			default:
				break;
			}
			break;
		case 2:
			switch (args[0]) {
			case "upgrade":
				if(Main.abilities.containsKey(args[1])) {				
					Skill.upgradePlayerAbiliti((Player)sender,plugin,Main.abilities.get(args[1]));
					return true;
				}else {
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
					((Player)sender).sendMessage(ChatColor.RED+"Wrong name of ability!");
				}
				break;
			case "learn":
				if(Main.abilities.containsKey(args[1])) {				
					Main.abilities.get(args[1]).learn((Player)sender);
					return true;
				}else {
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
					((Player)sender).sendMessage(ChatColor.RED+"Wrong name of ability!");
				}
				break;
			default:
				break;
			}
			break;
		case 3:
			switch (args[0]) {
				case "addskillpoints":
					if(((Player)sender).hasPermission("dremnorsrpg.Admin")) {
						if(args.length == 3){
							if(Bukkit.getPlayer(args[1]) != null) {
								Player player = Bukkit.getPlayer(args[1]);
								PersistentDataContainer storage = player.getPersistentDataContainer();
								int sp = storage.get(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER);
								if(Main.isNumeric(args[2])) {
									sp += Integer.parseInt(args[2]);
									storage.set(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER,sp);
									player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 3, 1);
									player.sendMessage(ChatColor.DARK_AQUA+"You just got "+Integer.parseInt(args[2])+"SP from god. Enjoy!");
								}					
							}else {
								if(args[1].equals("*")) {
									for(Player player : Bukkit.getOnlinePlayers()) {
										PersistentDataContainer storage = player.getPersistentDataContainer();
										int sp = storage.get(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER);
										if(Main.isNumeric(args[2])) {
											sp += Integer.parseInt(args[2]);
											storage.set(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER,sp);
											player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 2, 1);
											player.sendMessage(ChatColor.DARK_AQUA+"You just got "+Integer.parseInt(args[2])+"SP from god. Enjoy!");
										}
									}
								}
							}
						}else {
							((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
							((Player)sender).sendMessage(ChatColor.RED+"Usage: /addskillpoints <player> <amount>");
						}
						if(args.length < 3) {
							((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
							((Player)sender).sendMessage(ChatColor.RED+"Usage: /addskillpoints <player> <amount>");
						}
					}else {
						((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
						((Player)sender).sendMessage(ChatColor.RED+"You don't have required permission to use this command.");
					}
					break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		
		
		
		
		return false;
	}
	
	public static void displayPlayerStats(Main plugin, Player p) {
		PersistentDataContainer storage = p.getPersistentDataContainer();
		int level = storage.get(new NamespacedKey(plugin,"Level"), PersistentDataType.INTEGER);
		int sp = storage.get(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER);
		p.sendMessage(ChatColor.GREEN+"Total Level: "+level);
		p.sendMessage(ChatColor.GREEN+"Skill Points: "+sp);
		if(storage.has(new NamespacedKey(plugin,"Abilitis"), PersistentDataType.STRING)) {
			String s = storage.get(new NamespacedKey(plugin,"Abilitis"), PersistentDataType.STRING);						
			HashMap<String,Integer> abilitis = new Gson().fromJson(s, new TypeToken<HashMap<String,Integer>>(){}.getType());			
			p.sendMessage(ChatColor.DARK_GREEN+"Skill List:");
			
			for(Map.Entry<String, Integer> entry : abilitis.entrySet()) {
				int lvl = entry.getValue();
				p.sendMessage(ChatColor.GREEN+entry.getKey()+" : lv. "+lvl);				
			}			
		}		
	}
			
}
