package me.dremnor.dremnorsrpg.summoning;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.crafting.CustomeItems;
import me.dremnor.dremnorsrpg.misc.Enums;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class ActionEvent implements Listener {

    private Main plugin;


    public ActionEvent(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public boolean onPlayerRightClick(PlayerInteractEvent e){
        e.getPlayer().sendMessage("zlapalem");
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            e.getPlayer().sendMessage("zlapalem 2");
            if(e.getClickedBlock().getType() == Material.BELL) {
                e.getPlayer().sendMessage("zlapalem 3");
                if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR){
                    e.getPlayer().sendMessage("zlapalem 3.1");
                    if (AltarStructure.checkTicket(e.getPlayer().getInventory().getItemInMainHand())) {
                        e.getPlayer().sendMessage("zlapalem 3.2");
                        if (AltarStructure.checkStructure(e.getClickedBlock())) {
                            e.getPlayer().sendMessage("zlapalem 4");
                            ItemMeta meta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                            PersistentDataContainer storage = meta.getPersistentDataContainer();
                            if (storage.has(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING)) {
                                e.getPlayer().sendMessage("zlapalem 5");
                                String s = storage.get(new NamespacedKey(Main.getPlugin(Main.class), "Ticket"), PersistentDataType.STRING);
                                AltarStructure.summonBoss(Enums.Tickets.valueOf(s), e.getClickedBlock(),e.getPlayer());
                            }
                        }
                    }
            }
            }
        }
        return false;
    }


}
