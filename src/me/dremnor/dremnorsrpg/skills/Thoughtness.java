package me.dremnor.dremnorsrpg.skills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import me.dremnor.dremnorsrpg.Main;

public class Thoughtness extends Skill{

	public Thoughtness(Main plugin, String name, String description, int[] cost, int learncost) {
		super(plugin, name, description, cost, learncost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect(Player p, int lv) {
		p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(lv);			
	}
	

}
