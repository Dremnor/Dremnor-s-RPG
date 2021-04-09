package me.dremnor.dremnorsrpg.enchants;

import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Efficiency extends Enchant{
    public Efficiency(Enums.CustomeEnchants name, int cost, int maxLvl, List<Enums.ItemType> itemTypeList) {
        super(name, cost, maxLvl, itemTypeList);
    }

    @Override
    public void effect(ItemStack item , int lvl) {
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED,lvl);
    }
}
