package me.dremnor.dremnorsrpg.menu;

import me.dremnor.dremnorsrpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class UpdateeEnchantInventroy {
    public Inventory i;
    ItemStack lastItem = null;
    public BukkitTask id;

    public UpdateeEnchantInventroy(Inventory i) {
        this.i = i;
        id = new BukkitRunnable() {
            @Override
            public void run() {
                if(i.getViewers().size()==0){
                    cancel();
                }
                if(i.getItem(13)!= null){
                    if(lastItem == null || !lastItem.equals(i.getItem(13))){
                        EnchantMenu.itemFound(i.getItem(13),(Player)i.getViewers().get(0), i);
                        lastItem = i.getItem(13);
                    }
                }else{
                    if(lastItem != null){
                        if(i.getViewers().size()>0){
                            EnchantMenu.itemFound(null,(Player)i.getViewers().get(0), i);
                        }

                    }
                }

            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0, 1);



    }







    private void Update() {
        Main.getPlugin(Main.class).getLogger().info("chodze w tle");
        if(i.getViewers().size()==0){
            Main.getPlugin(Main.class).getLogger().info("off bo nikt nie pacza");
            Stop();
        }

        if(i.getItem(13)!= null &&!(lastItem == i.getItem(13))){
            EnchantMenu.itemFound(i.getItem(13),(Player)i.getViewers().get(0), i);
        }
    }

    public void Stop() {
        id.cancel();
    }

}
