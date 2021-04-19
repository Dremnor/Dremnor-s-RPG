package me.dremnor.dremnorsrpg;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


import me.dremnor.dremnorsrpg.enchants.sub.Efficiency;
import me.dremnor.dremnorsrpg.enchants.Enchant;
import me.dremnor.dremnorsrpg.enchants.sub.Explosion;
import me.dremnor.dremnorsrpg.enchants.sub.Fortuna;
import me.dremnor.dremnorsrpg.enchants.sub.VeinMiner;
import me.dremnor.dremnorsrpg.menu.UpdateeEnchantInventroy;
import me.dremnor.dremnorsrpg.misc.Enums;
import me.dremnor.dremnorsrpg.summoning.ActionEvent;
import org.bukkit.boss.BossBar;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import me.dremnor.dremnorsrpg.commands.DrpgCommand;
import me.dremnor.dremnorsrpg.crafting.CraftingItems;
import me.dremnor.dremnorsrpg.enchants.EnchantEvent;
import me.dremnor.dremnorsrpg.expgain.ItemExprience;
import me.dremnor.dremnorsrpg.expgain.PlayerExprience;
import me.dremnor.dremnorsrpg.menu.events.MenuEvents;
import me.dremnor.dremnorsrpg.monstersevents.BreedEvent;
import me.dremnor.dremnorsrpg.monstersevents.MonsterDie;
import me.dremnor.dremnorsrpg.monstersevents.MonsterSpawn;
import me.dremnor.dremnorsrpg.players.PlayerJoin;
import me.dremnor.dremnorsrpg.players.PlayerManager;
import me.dremnor.dremnorsrpg.skills.Agility;
import me.dremnor.dremnorsrpg.skills.Dexterity;
import me.dremnor.dremnorsrpg.skills.Skill;
import me.dremnor.dremnorsrpg.skills.Strength;
import me.dremnor.dremnorsrpg.skills.Thoughtness;
import me.dremnor.dremnorsrpg.skills.Vitality;


public class Main extends JavaPlugin{
	
	public static HashMap<String,Skill> abilities;
	public static HashMap<Enums.CustomeEnchants, Enchant> enchants;
	public static HashMap<LivingEntity,BossBar> activebosses;
	public static HashMap<Inventory, UpdateeEnchantInventroy> openedInventory;

	@Override
	public void onEnable() {
		
		//init maps
		openedInventory = new HashMap<Inventory, UpdateeEnchantInventroy>();

		//register commands
		new DrpgCommand(this);
		
		//register events
		new ItemExprience(this);
		new PlayerExprience(this);
		new MonsterDie(this);
		new MonsterSpawn(this);
		new PlayerJoin(this);
		new PlayerManager(this);
		new CraftingItems(this);
		new EnchantEvent(this);
		new BreedEvent(this);
		new MenuEvents(this);
		new ActionEvent(this);
		
		//init skills
		abilities = new HashMap<String,Skill>();
		abilities.put("Vitality",new Vitality(this,"Vitality","Increase Player Max Health",new int[]{100,150,200,250,300,350,400,450,500,550,
																									600,650,700,750,800,850,900,950,1000,1050},200));
		abilities.put("Agility",new Agility(this,"Agility","Increase Player Movement Speed",new int[]{100,150,200,250,300,350,400,450,500,550,
																									600,650,700,750,800,850,900,950,1000,1050},200));
		abilities.put("Dexterity",new Dexterity(this,"Dexterity","Increase Player Attack Speed",new int[]{100,150,200,250,300,350,400,450,500,550,
																									600,650,700,750,800,850,900,950,1000,1050},200));
		abilities.put("Strength",new Strength(this,"Strength","Increase Player ALL Attack Damage",new int[]{100,150,200,250,300,350,400,450,500,550,
																									600,650,700,750,800,850,900,950,1000,1050},200));
		abilities.put("Thoughtness",new Thoughtness(this,"Thoughtness","Increase Player Deffence",new int[]{100,150,200,250,300,350,400,450,500,550,
																									600,650,700,750,800,850,900,950,1000,1050},200));
		//enchants init
		enchants = new HashMap<Enums.CustomeEnchants, Enchant>();
		enchants.put(Enums.CustomeEnchants.Efficiency,new Efficiency(Enums.CustomeEnchants.Efficiency,70,20,"Efficiency",
				Arrays.asList(Enums.ItemType.AXE,Enums.ItemType.PICKAXE,Enums.ItemType.SHOVEL,Enums.ItemType.HOE),
				null));
		enchants.put(Enums.CustomeEnchants.Fortuna,new Fortuna(Enums.CustomeEnchants.Fortuna,300,20,"Fortuna",
				Arrays.asList(Enums.ItemType.AXE,Enums.ItemType.PICKAXE,Enums.ItemType.SHOVEL,Enums.ItemType.HOE),
				null));
		enchants.put(Enums.CustomeEnchants.Explosion,new Explosion(Enums.CustomeEnchants.Explosion,150,20,"Explosion",
				Collections.singletonList(Enums.ItemType.PICKAXE),
				Arrays.asList(Enums.CustomeEnchants.VeinMiner)));
		enchants.put(Enums.CustomeEnchants.VeinMiner,new VeinMiner(Enums.CustomeEnchants.VeinMiner,200,20,"VeinMiner",
				Collections.singletonList(Enums.ItemType.PICKAXE),
				Arrays.asList(Enums.CustomeEnchants.Explosion)));

		//init list
		activebosses = new HashMap<LivingEntity,BossBar>();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);	        
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	
	
	
}
