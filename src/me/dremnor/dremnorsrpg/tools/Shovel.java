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

public class Shovel extends Tools {

    public static final Enums.ItemType TYPE = Enums.ItemType.SHOVEL;
    public static final HashMap<Material,Integer> BLOCK_EXP = new  HashMap<Material,Integer>(){{
        put(Material.GRASS_BLOCK, 1);
        put(Material.DIRT, 1);
        put(Material.SAND, 1);
        put(Material.SOUL_SAND, 16);
        put(Material.GRAVEL, 4);
        put(Material.CLAY, 8);
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
