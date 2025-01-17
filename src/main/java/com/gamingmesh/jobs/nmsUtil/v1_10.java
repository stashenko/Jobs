package com.gamingmesh.jobs.nmsUtil;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.inventory.ItemStack;

import com.gamingmesh.jobs.NMS;

public class v1_10 implements NMS {
    @Override
    public List<Block> getPistonRetractBlocks(BlockPistonRetractEvent event) {
	List<Block> blocks = new ArrayList<>();
	blocks.addAll(event.getBlocks());
	return blocks;
    }

    @Override
    public String getRealType(Entity entity) {
	String name = entity.getType().name();
	switch (entity.getType()) {
	case GUARDIAN:
	    Guardian g = (Guardian) entity;
	    if (g.isElder())
		name = "GuardianElder";
	    break;
	case HORSE:
	    Horse horse = (Horse) entity;
	    if (horse.getVariant() == Variant.UNDEAD_HORSE)
		name = "HorseZombie";
	    if (horse.getVariant() == Variant.SKELETON_HORSE)
		name = "HorseSkeleton";
	    break;
	case SKELETON:
	    Skeleton skeleton = (Skeleton) entity;
	    if (skeleton.getSkeletonType() == SkeletonType.WITHER)
		name = "SkeletonWither";
	    if (skeleton.getSkeletonType() == SkeletonType.STRAY)
		name = "SkeletonStray";
	    break;
	case ZOMBIE:
	    Zombie zombie = (Zombie) entity;
	    if (zombie.isVillager() && zombie.getVillagerProfession().toString().equals("HUSK"))
		return "ZombieVillager";
	    if (zombie.getVillagerProfession().toString().equals("HUSK"))
		return "ZombieHusk";
	    break;
	default:
	    break;
	}
	return name;
    }

    @Override
    public ItemStack getItemInMainHand(Player player) {
	return player.getInventory().getItemInMainHand();
    }

    @Override
    public void setItemInMainHand(Player player, ItemStack item) {
	player.getInventory().setItemInHand(item);
    }

    @Override
    public double getMaxHealth(LivingEntity entity) {
	return entity.getMaxHealth();
    }

    @Override
    public short getDurability(ItemStack item) {
	return item.getDurability();
    }

    @Override
    public Enchantment getEnchantment(String type) {
	return Enchantment.getByName(type.toUpperCase());
    }

    @Override
    public String getEnchantName(Enchantment type) {
	return type.getName();
    }
}
