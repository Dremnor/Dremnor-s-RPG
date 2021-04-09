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

public class Hoe extends Tools {

    public static final Enums.ItemType TYPE = Enums.ItemType.HOE;
    public static final HashMap<Material,Integer> BLOCK_EXP = new  HashMap<Material,Integer>(){{
        put(Material.WHEAT, 2);
        put(Material.CARROTS, 4);
        put(Material.POTATOES, 4);
        put(Material.BEETROOTS, 4);
        put(Material.PUMPKIN, 16);
        put(Material.MELON, 16);
        put(Material.COCOA, 8);
        put(Material.CACTUS, 4);
        put(Material.BARRIER, 4);
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
