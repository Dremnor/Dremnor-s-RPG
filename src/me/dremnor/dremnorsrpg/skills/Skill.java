package me.dremnor.dremnorsrpg.skills;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.expgain.PlayerExprience;

public abstract class Skill {

	public Main plugin;	
	
	public abstract void effect(Player p, int lv);
	
	public String name;
	public String description;
	public int[] cost;
	public int learncost;
		
	public Skill(Main plugin, String name, String description, int[] cost,int learncost) {
		this.plugin = plugin;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.learncost = learncost;
	}
	
	public static HashMap<String,Integer> getPlayerAbilities(Player p, Main plugin){
		PersistentDataContainer storage = p.getPersistentDataContainer();
		if(storage.has(new NamespacedKey(plugin,"Abilitis"), PersistentDataType.STRING)) {
			String json = storage.get(new NamespacedKey(plugin,"Abilitis"), PersistentDataType.STRING);			
			HashMap<String,Integer> abilities = new Gson().fromJson(json, new TypeToken<HashMap<String,Integer>>(){}.getType());
			
			return abilities;
		}else{
			return new HashMap<String,Integer>();
		}
	}
	
	public static void setPlayerAbilitis(Player p, Main plugin, HashMap<String,Integer> abilitis) {
		PersistentDataContainer storage = p.getPersistentDataContainer();
		Gson gson = new Gson();		
		String json = gson.toJsonTree(abilitis).toString();
		storage.set(new NamespacedKey(plugin,"Abilitis"), PersistentDataType.STRING,json);		
	}
	
	public static void addPlayerAbiliti(Player p, Main plugin, Skill skill) {
		HashMap<String,Integer> abilities = getPlayerAbilities(p,plugin);
		abilities.put(skill.name, 1);
		setPlayerAbilitis(p,plugin,abilities);
	}
	
	public static boolean paySkillPoints(Player p, Main plugin, int cost) {
		PersistentDataContainer storage = p.getPersistentDataContainer();
		int sp = storage.get(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER);
		if(sp>= cost) {
			sp -= cost;
			storage.set(new NamespacedKey(plugin,"Exp"), PersistentDataType.INTEGER,sp);
			return true;
		}else {
			return false;
		}
	}
	
	public static void upgradePlayerAbiliti(Player p, Main plugin, Skill ability) {
		HashMap<String,Integer> abilities = getPlayerAbilities(p,plugin);
		if(abilities.containsKey(ability.name)) {
			int lvl = abilities.get(ability.name);
			if(lvl<20) {
				if(paySkillPoints(p,plugin,ability.cost[lvl-1])) {				
					abilities.put(ability.name, lvl+1);
					setPlayerAbilitis(p,plugin,abilities);
					ability.effect(p,lvl+1);
					PlayerExprience.updatePlayerLvl(plugin,p);
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
					p.sendMessage(ChatColor.DARK_GREEN+"Congratulation! You just upgraded "+ability.name+" to level "+(lvl+1)+".");
				}else {
					p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
					p.sendMessage(ChatColor.RED+"You cant aford upgrade!(Cost: "+ability.cost[lvl-1]+")");
				}
			}else {
				p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
				p.sendMessage(ChatColor.RED+"Ability level can't be higher than 20!");
			}
		}else {
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
			p.sendMessage(ChatColor.RED+"You need to learn this ability first!");
		}
	}
	
	static public boolean hasAbiliti(Player p, Main plugin, String name) {
		HashMap<String,Integer> abilities = getPlayerAbilities(p,plugin);
		if(abilities.containsKey(name)) {
			return true;
		}else{
			return false;
		}			
	}
	
	public void learn(Player p) {
		if(!hasAbiliti(p,plugin, name)) {
			if(paySkillPoints(p,plugin,learncost)) {
				addPlayerAbiliti(p,plugin,this);
				effect(p,1);
				PlayerExprience.updatePlayerLvl(plugin,p);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 1);
				p.sendMessage(ChatColor.DARK_GREEN+""+name+" ability aquired! Description: "+description);
			}else{
				p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
				p.sendMessage(ChatColor.RED+"You can't afford this ability(Cost: "+learncost+")");
			}					
		}else {
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 3, 1);
			p.sendMessage(ChatColor.RED+"You already know this ability(Name: "+name+")");
		}
		
	}
	
	
	
	
}
