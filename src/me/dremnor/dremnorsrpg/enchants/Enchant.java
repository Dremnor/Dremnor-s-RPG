package me.dremnor.dremnorsrpg.enchants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.crafting.ItemGenerator;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enchant {

    public abstract void effect(ItemStack item, int lvl);
    public abstract List<Block> blockEffect(ItemStack item, Block block, Player p, int lvl);

    public Enums.CustomeEnchants name;
    public String nameString;
    public int cost;
    public int maxLvl;
    public List<Enums.ItemType> itemTypeList;
    public List<Enums.CustomeEnchants> forbidenPair = null;

    public Enchant(Enums.CustomeEnchants name, int cost, int maxLvl, String nameString, List<Enums.ItemType> itemTypeList, List<Enums.CustomeEnchants> forbidenPair){
        this.name = name;
        this.cost = cost;
        this.maxLvl = maxLvl;
        this.itemTypeList = itemTypeList;
        this.nameString = nameString;
        this.forbidenPair = forbidenPair;
    }

    public void setEnchant(ItemStack item, Main plugin, Player p){

        if(checkIfAllowed(item)){
            ItemMeta meta = item.getItemMeta();
            int maxEnchants=0;
            Gson gson = new Gson();
            PersistentDataContainer storage = meta.getPersistentDataContainer();
            HashMap<Enums.CustomeEnchants,Integer> enchants;
            String rarity = storage.get(new NamespacedKey(plugin, "Rarity"), PersistentDataType.STRING);

            if(storage.has(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING)){
                String json = storage.get(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING);
                enchants = new Gson().fromJson(json, new TypeToken<HashMap<Enums.CustomeEnchants,Integer>>(){}.getType());
            }else{
                enchants = new HashMap<Enums.CustomeEnchants,Integer>();
            }
            plugin.getLogger().info("Rarity : "+ rarity);
            switch (Enums.Rarity.valueOf(rarity)) {
                case COMMON:
                    maxEnchants = 1;
                    break;
                case UNCOMMON:
                    maxEnchants = 2;
                    break;
                case RARE:
                    maxEnchants = 3;
                    break;
                case LEGENDARY:
                    maxEnchants = 5;
                    break;
            }

            if(enchants.size()<maxEnchants){
                if(payExp(item,plugin,cost)){
                    enchants.put(name,1);
                    String json = gson.toJsonTree(enchants).toString();
                    storage.set(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING,json);
                    item.setItemMeta(meta);
                    effect(item,1);
                    ItemGenerator.updateItemLore(item,plugin);
                    p.sendMessage(ChatColor.GREEN+"Inbu Added");
                    p.getInventory().addItem(item);
                    p.closeInventory();
                }else{
                    p.sendMessage(ChatColor.RED+"This Item need more exp (Cost: )"+cost);
                }
            }else{
                p.sendMessage(ChatColor.RED+"Need better rarity to add next enchant!");
            }
        }else{
            p.sendMessage("Forbiden");
        }

    }

    public HashMap<Enums.CustomeEnchants,Integer> getEnchantMap(ItemStack item){
        HashMap<Enums.CustomeEnchants,Integer> enchants;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();

        if (storage.has(new NamespacedKey(Main.getPlugin(Main.class), "Enchants"), PersistentDataType.STRING)){
            String json = storage.get(new NamespacedKey(Main.getPlugin(Main.class),"Enchants"), PersistentDataType.STRING);
            enchants = new Gson().fromJson(json, new TypeToken<HashMap<Enums.CustomeEnchants,Integer>>(){}.getType());
        }else{
            enchants = new HashMap<Enums.CustomeEnchants,Integer>();
        }
        return enchants;
    }



    public boolean upgradeEnchant(ItemStack item, Main plugin){
        ItemMeta meta = item.getItemMeta();
        int maxLvl = 0;
        Gson gson = new Gson();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        HashMap<Enums.CustomeEnchants,Integer> enchants;
        String rarity = storage.get(new NamespacedKey(plugin, "Rarity"), PersistentDataType.STRING);

        switch (Enums.Rarity.valueOf(rarity)) {
            case COMMON:
                maxLvl = 5;
                break;
            case UNCOMMON:
                maxLvl = 10;
                break;
            case RARE:
                maxLvl = 15;
                break;
            case LEGENDARY:
                maxLvl = 20;
                break;
        }


        if(storage.has(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING)){
            String json = storage.get(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING);
            enchants = new Gson().fromJson(json, new TypeToken<HashMap<Enums.CustomeEnchants,Integer>>(){}.getType());
            int lvl = enchants.get(name);
            if((lvl+1)<=maxLvl){
                if(payExp(item,plugin,cost*(lvl+1))){
                    lvl +=1;
                    enchants.put(name,lvl);
                    json = gson.toJsonTree(enchants).toString();
                    storage.set(new NamespacedKey(plugin,"Enchants"), PersistentDataType.STRING,json);
                    item.setItemMeta(meta);
                    effect(item,lvl);
                    ItemGenerator.updateItemLore(item,plugin);
                    return true;
                }
            }
        }else{
            return false;
        }

        return false;
    }
    public boolean checkIfAllowed(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        HashMap<Enums.CustomeEnchants,Integer> enchants = getEnchantMap(item);
        if(enchants.size()!=0 && forbidenPair != null){
            for(Map.Entry<Enums.CustomeEnchants,Integer> en : enchants.entrySet()){
                if(forbidenPair.contains(en.getKey())){
                    return false;
                }
            }
        }else{
            return true;
        }
        return true;
    }

    public boolean payExp(ItemStack item, Main plugin, int cost){
        ItemMeta meta = item.getItemMeta();
        Gson gson = new Gson();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        int exp = storage.get(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER);
        if(exp >= cost ){
            exp -= cost;
            storage.set(new NamespacedKey(plugin, "Exp"), PersistentDataType.INTEGER,exp);
            item.setItemMeta(meta);
            return true;
        }else{
            return false;
        }
    }
}