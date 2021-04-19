package me.dremnor.dremnorsrpg.enchants.sub;

import me.dremnor.dremnorsrpg.enchants.Enchant;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Explosion extends Enchant {


    public Explosion(Enums.CustomeEnchants name, int cost, int maxLvl, String nameString, List<Enums.ItemType> itemTypeList, List<Enums.CustomeEnchants> forbidenPair) {
        super(name, cost, maxLvl, nameString, itemTypeList, forbidenPair);
    }

    @Override
    public void effect(ItemStack item, int lvl) {
        item.addUnsafeEnchantment(Enchantment.WATER_WORKER,1);
    }

    @Override
    public List<Block> blockEffect(ItemStack item, Block block, Player p, int lvl) {
        Random r = new Random();
        List<Block> blocks = new ArrayList<Block>();
        double d = r.nextDouble();

            if(d<lvl*0.05){
                int radius = 0;
                radius = 2+lvl%5;

                for(int x = 0; x <= 2*radius; x++){
                    for(int y = 0; y <= 2*radius; y++){
                        for(int z = 0; z <= 2*radius; z++){
                            Location newloc = new Location(block.getWorld(), block.getX() +  (x - radius), block.getY() + (y - radius), block.getZ() + (z - radius));
                            Block b = newloc.getBlock();
                            Material mat = b.getType();
                            if(mat != Material.AIR){
                                if(b.getLocation().distance(block.getLocation()) < radius){
                                    blocks.add(b);
                                }
                            }
                        }
                    }
                }
                for(Block b : blocks) {
                    b.breakNaturally(p.getInventory().getItemInMainHand());
                }
            }

        return blocks;
    }



}
