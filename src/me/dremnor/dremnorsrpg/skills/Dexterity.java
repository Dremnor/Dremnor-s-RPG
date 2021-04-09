package me.dremnor.dremnorsrpg.skills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import me.dremnor.dremnorsrpg.Main;

public class Dexterity extends Skill {

	public Dexterity(Main plugin, String name, String description, int[] cost, int learncost) {
		super(plugin, name, description, cost, learncost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect(Player p, int lv) {		
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(4+1.5*lv);
	}

}
