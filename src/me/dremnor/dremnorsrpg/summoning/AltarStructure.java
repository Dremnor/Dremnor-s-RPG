package me.dremnor.dremnorsrpg.summoning;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.crafting.CustomeItems;
import me.dremnor.dremnorsrpg.custommob.MonsterPreparator;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class AltarStructure {

    static Material[][] structure = {
            {Material.AIR,Material.BLACKSTONE_SLAB,Material.AIR},
            {Material.BLACKSTONE_SLAB,Material.CRYING_OBSIDIAN,Material.BLACKSTONE_SLAB},
            {Material.AIR,Material.BLACKSTONE_SLAB,Material.AIR},
    };



    public static boolean checkStructure(Block b){
        Location l  = b.getLocation();
        World w = l.getWorld();

        double x = l.getX()-1;
        double y = l.getY()-1;
        double z = l.getZ()-1;

        l.setY(l.getY()-1);
        for (int ix = 0;ix<3;ix++){
            for (int iz = 0;iz<3;iz++){
                if(w.getBlockAt(new Location(w,x+ix,y,z+iz)).getType() != structure[ix][iz]){
                    w.getBlockAt(new Location(w,x+ix,y,z+iz)).setType(Material.SPONGE);
                    return false;
                }
            }
        }

        return true;
    }

    public static void summonBoss(Enums.Tickets ticket, Block b, Player p){
        Location l = b.getLocation();
        World w = b.getWorld();

        switch (ticket) {
            case DARK_LORD:
                MonsterPreparator.prepareDarkLord(l);
                w.strikeLightning(l);
                p.getInventory().remove(CustomeItems.DARK_LORD_TICKET());
                break;
            case JELLYSTORM:
                p.getInventory().remove(CustomeItems.JELLY_STORM_TICKET());
                break;
        }

    }

    public static boolean checkTicket(ItemStack i){
        Main.getPlugin(Main.class).getLogger().info(i.toString());
        ItemMeta meta = i.getItemMeta();
        PersistentDataContainer storage = meta.getPersistentDataContainer();
        if(storage.has(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING)){
            String s = storage.get(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING);
            for(Enums.Tickets ticket : Arrays.asList(Enums.Tickets.values())){
                if(ticket.toString().equals(s)){
                    return true;
                }
            }
        }

        return false;
    }

}
