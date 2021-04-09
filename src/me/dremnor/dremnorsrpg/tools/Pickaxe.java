package me.dremnor.dremnorsrpg.tools;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class Pickaxe extends Tools {

    public static final Enums.ItemType TYPE = Enums.ItemType.PICKAXE;
    public static final HashMap<Material,Integer> BLOCK_EXP = new  HashMap<Material,Integer>(){{
        put(Material.STONE, 1);
        put(Material.COBBLESTONE, 1);
        put(Material.COAL_ORE, 4);
        put(Material.IRON_ORE, 8);
        put(Material.GOLD_ORE, 16);
        put(Material.DIAMOND_ORE, 32);
        put(Material.DIORITE, 1);
        put(Material.GRANITE, 1);
        put(Material.ANDESITE, 1);
        put(Material.EMERALD_ORE, 32);
        put(Material.NETHER_QUARTZ_ORE, 16);
        put(Material.NETHER_GOLD_ORE, 16);
    }};


    public HashMap<Material, Integer> getBlockExp() {
        return BLOCK_EXP;
    }

    public static ItemStack addExpToTool(ItemStack item, Material block){
        if(BLOCK_EXP.containsKey(block)){
            ItemMeta meta = item.getItemMeta();

            if(meta != null){
                PersistentDataContainer storage = meta.getPersistentDataContainer();
                if(storage.has(new NamespacedKey(Main.getPlugin(Main.class),"Exp"), PersistentDataType.INTEGER)){
                    int exp = storage.get(new NamespacedKey(Main.getPlugin(Main.class),"Exp"),PersistentDataType.INTEGER);
                    exp += BLOCK_EXP.get(block);
                    storage.set(new NamespacedKey(Main.getPlugin(Main.class),"Exp"),PersistentDataType.INTEGER,exp);
                    item.setItemMeta(meta);
                    return item;
                }
            }
        }
        return item;
    }
}
