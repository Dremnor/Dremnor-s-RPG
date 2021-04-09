package me.dremnor.dremnorsrpg.skills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import me.dremnor.dremnorsrpg.Main;

public class Agility extends Skill{

	public Agility(Main plugin, String name, String description, int[] cost, int learncost) {
		super(plugin, name, description, cost, learncost);
	}

	@Override
	public void effect(Player p, int lv) {
		p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1+0.01*lv);		
	}

}
