package me.dremnor.dremnorsrpg.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.skills.Skill;

public class TabCompleterLearn implements TabCompleter{
	 private List<String> subCommands = new ArrayList<>();
	 private List<String> abilitis = new ArrayList<>();
	 
	    @Override
	    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {	    	
	    	if(abilitis.size() == 0) {
	    		Set<String> abilitiStrings =  Main.abilities.keySet();
	    		for(String s : abilitiStrings) {
	    			abilitis.add(s);
	    		}
	    	}
	    	subCommands.clear();
	    	
	    	switch (args.length) {
			case 1:
    			if("learn".toLowerCase().startsWith(args[0].toLowerCase())) {
	    			subCommands.add("learn");
	    		}
    			if("upgrade".toLowerCase().startsWith(args[0].toLowerCase())) {
	    			subCommands.add("upgrade");
	    		}
    			if("stats".toLowerCase().startsWith(args[0].toLowerCase())) {
	    			subCommands.add("stats");
	    		}
    			if("addskillpoints".toLowerCase().startsWith(args[0].toLowerCase())) {
	    			subCommands.add("addskillpoints");
	    		}
    			if(subCommands.size()>0) {
    				return subCommands;
    			}
				break;
			case 2:
				if(args[0].equals("learn")) {
		    		for(String s : abilitis) {
		    			if(s.toLowerCase().startsWith(args[1].toLowerCase())) {
			    			subCommands.add(s);
			    		}
		    		}
	    			if(subCommands.size()>0) {
	    				return subCommands;
	    			}
				}
				if(args[0].equals("upgrade")) {
		    		for(String s : Skill.getPlayerAbilities((Player)sender, Main.getPlugin(Main.class)).keySet()) {
		    			if(s.toLowerCase().startsWith(args[1].toLowerCase())) {
			    			subCommands.add(s);
			    		}
		    		}
	    			if(subCommands.size()>0) {
	    				return subCommands;
	    			}
				}
    			if("stats".toLowerCase().startsWith(args[0].toLowerCase())) {
    				return subCommands;
	    		}
				break;
			case 3:
				if(args[0].equals("learn")) {
					return subCommands;
				}
				if(args[0].equals("upgrade")) {
					return subCommands;
				}
				break;
			default:
				break;
			}
	
	        return null;
	    }
}
