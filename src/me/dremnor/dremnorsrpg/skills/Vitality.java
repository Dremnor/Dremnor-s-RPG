package me.dremnor.dremnorsrpg.skills;


import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;


import me.dremnor.dremnorsrpg.Main;

public class Vitality extends Skill{

	public Vitality(Main plugin, String name, String description, int[] cost,int learncost) {
		super(plugin, name, description, cost,learncost);
		
	}

	
	public void effect(Player p, int lv) {	
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20+lv);		
	}
			
}
