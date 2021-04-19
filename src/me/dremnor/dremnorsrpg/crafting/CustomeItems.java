package me.dremnor.dremnorsrpg.crafting;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class CustomeItems{

    public static ItemStack DARK_LORD_BONE(){

        ItemStack item = new ItemStack(Material.BONE);
        item = ItemGenerator.setItemStackName(item, ChatColor.GOLD+"Demon Lord Bone");
        item = ItemGenerator.setItemStackLore(item, Arrays.asList(ChatColor.DARK_RED+"Rare item used in item Reforging"));
        item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        storage.set(new NamespacedKey(Main.getPlugin(Main.class), "Type"), PersistentDataType.STRING, Enums.ItemType.BOSSDROP.toString());
        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack DARK_LORD_TICKET(){
        ItemStack item = new ItemStack(Material.PAPER);
        item = ItemGenerator.setItemStackName(item, ChatColor.GOLD+"Demon Lord Spawn Ticket");
        item = ItemGenerator.setItemStackLore(item, Arrays.asList(ChatColor.DARK_RED+"Item Used at altar to spawn Dark Lord",ChatColor.GOLD+"Single use item!!"));
        item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        storage.set(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING, Enums.Tickets.DARK_LORD.toString());
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack JELLY_STORM_TICKET(){
        ItemStack item = new ItemStack(Material.PAPER);
        item = ItemGenerator.setItemStackName(item, ChatColor.GOLD+"Jelly Storm Spawn Ticket");
        item = ItemGenerator.setItemStackLore(item, Arrays.asList(ChatColor.DARK_RED+"Item Used at altar to spawn Jelly Storm",ChatColor.GOLD+"Single use item!!"));
        item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        storage.set(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING, Enums.Tickets.JELLYSTORM.toString());
        item.setItemMeta(meta);

        return item;
    }


}
