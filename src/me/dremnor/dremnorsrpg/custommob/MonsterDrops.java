package me.dremnor.dremnorsrpg.custommob;

import me.dremnor.dremnorsrpg.Main;
import me.dremnor.dremnorsrpg.custommob.boss.DarkLord;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class MonsterDrops {

    public static void mobDrops(Entity e){
        if(e instanceof LivingEntity){
            LivingEntity entity = (LivingEntity) e;
            PersistentDataContainer storage = e.getPersistentDataContainer();
            if(storage.has(new NamespacedKey(Main.getPlugin(Main.class), "Boss"), PersistentDataType.STRING)){
                bossDrops(e);
            }else{
                Random r = new Random();
                int lvl = 0;
                lvl = storage.get(new NamespacedKey(Main.getPlugin(Main.class), "Level"), PersistentDataType.INTEGER);
                if(entity instanceof Pig){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PORKCHOP,lvl));
                    }
                } else
                if(entity instanceof Cow){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER,lvl));
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.BEEF,lvl));
                    }
                } else
                if(entity instanceof Sheep){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.WHITE_WOOL,lvl));
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.MUTTON,lvl));
                    }
                } else
                if(entity instanceof Creeper){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GUNPOWDER,lvl));
                    }
                } else
                if(entity instanceof Zombie){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH,lvl));
                    }
                } else
                if(entity instanceof Enderman){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL,lvl));
                    }
                } else
                if(entity instanceof Skeleton){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ARROW,lvl));
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE,lvl));
                    }
                } else
                if(entity instanceof Chicken){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.FEATHER,lvl));
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.CHICKEN,lvl));
                    }
                } else
                if(entity instanceof Spider){
                    lvl = r.nextInt(2+lvl);
                    if(lvl!=0){
                        entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.STRING,lvl));
                    }
                }

            }
        }
    }





     public static void bossDrops(Entity e){
         if(e instanceof LivingEntity){
             LivingEntity entity = (LivingEntity) e;
             if(entity.getCustomName() != null){
                 switch (ChatColor.stripColor(entity.getCustomName())){
                     case "Dark Lord":
                         for(ItemStack i : DarkLord.DROPS){
                             e.getLocation().getWorld().dropItemNaturally(e.getLocation(),i);
                         }
                         break;
                 }

             }
         }
     }


}
