package me.dremnor.dremnorsrpg.enchants.sub;

import me.dremnor.dremnorsrpg.enchants.Enchant;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Efficiency extends Enchant {


    public Efficiency(Enums.CustomeEnchants name, int cost, int maxLvl, String nameString, List<Enums.ItemType> itemTypeList, List<Enums.CustomeEnchants> forbidenPair) {
        super(name, cost, maxLvl, nameString, itemTypeList, forbidenPair);
    }

    @Override
    public void effect(ItemStack item , int lvl) {
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED,lvl);
    }

    @Override
    public List<Block> blockEffect(ItemStack item, Block block, Player p, int lvl) {
        return null;
    }
}
