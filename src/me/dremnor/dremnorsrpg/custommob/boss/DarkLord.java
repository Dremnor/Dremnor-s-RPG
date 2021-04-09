package me.dremnor.dremnorsrpg.custommob.boss;


import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import net.minecraft.server.v1_16_R3.EntityAnimal;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntityMonster;
import net.minecraft.server.v1_16_R3.EntitySkeletonWither;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;



public class DarkLord extends EntitySkeletonWither {
	
	public DarkLord(Location loc) {		
		super(EntityTypes.WITHER_SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(),loc.getY(),loc.getZ());	
		this.goalSelector.a(0,new PathfinderGoalMeleeAttack(this, 1.2D, false));
		this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3.0F, 1.0F));
		this.goalSelector.a(2,new PathfinderGoalRandomStrollLand(this, 0.6D));
		this.goalSelector.a(3,new PathfinderGoalRandomLookaround(this));		
		this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));		
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<EntityCreature>(this, EntityCreature.class, true));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<EntityAnimal>(this, EntityAnimal.class, true));
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<EntityMonster>(this, EntityMonster.class, true));
	}
		
}
