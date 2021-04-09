package me.dremnor.dremnorsrpg.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantEffects {

	public static void breakSphere(Block center, Player p,ItemStack tool , int radius) {
		List<Block> blocks = new ArrayList<Block>();
		
        for(int x = 0; x <= 2*radius; x++){
            for(int y = 0; y <= 2*radius; y++){
                for(int z = 0; z <= 2*radius; z++){
                    Location newloc = new Location(center.getWorld(), center.getX() +  (x - radius), center.getY() + (y - radius), center.getZ() + (z - radius));
                    Block b = newloc.getBlock();
                    Material mat = b.getType();
                    if(mat != Material.AIR){
                    	if(b.getLocation().distance(center.getLocation()) < radius){
                    		blocks.add(b);
                    	}                        
                    }                   
                }}}
        
        for(Block b : blocks) {
        	b.breakNaturally(tool);
        }
	}
	
}
