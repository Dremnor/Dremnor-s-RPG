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
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ItemGenerator.setItemStackName(item, ChatColor.GOLD+"Demon Lord Bone");
        storage.set(new NamespacedKey(Main.getPlugin(Main.class), "Type"), PersistentDataType.STRING, Enums.ItemType.BOSSDROP.toString());
        ItemGenerator.setItemStackLore(item, Arrays.asList(ChatColor.DARK_RED+"Rare item used in item Reforging"));
        item.setItemMeta(meta);
        return item;

    }


}
