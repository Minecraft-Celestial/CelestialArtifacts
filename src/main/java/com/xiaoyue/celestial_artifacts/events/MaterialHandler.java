package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MaterialHandler {

	@SubscribeEvent
	public static void onItemFished(ItemFishedEvent event) {
		Player player = event.getEntity();
		if (player.getY() > 308) {
			if (0.03 > player.getRandom().nextDouble()) {
				player.addItem(new ItemStack(CAItems.MAGIC_HORSESHOE.get()));
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof EnderDragon enderDragon) {
			if (event.getAmount() > 500 && event.getAmount() > enderDragon.getHealth()) {
				if (0.5 > entity.getRandom().nextDouble()) {
					enderDragon.spawnAtLocation(CAItems.ORIGIN_ETCHING.get());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDrop(LivingDropsEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attack = event.getSource().getEntity();
		if (attack instanceof Player player) {
			if (CurioUtils.isCsOn(player)) {
				if (entity instanceof WitherBoss witherBoss) {
					if (event.getLootingLevel() > 7) {
						if (0.25 > entity.getRandom().nextDouble()) {
							event.getDrops().add(new ItemEntity(witherBoss.level(), witherBoss.getX(), witherBoss.getY(), witherBoss.getZ(), new ItemStack(CAItems.DESIRE_ETCHING.get())));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();

		if (attacker instanceof Player player) {
			CompoundTag data = player.getPersistentData();
			if (data.getBoolean("the_next")) {

				if (entity instanceof Vex vex) {
					if (0.03 > entity.getRandom().nextDouble()) {
						vex.spawnAtLocation(CAItems.DEMON_CURSE.get());
					}
				}
			}

			if (entity instanceof WitherBoss witherBoss) {
				if (!data.getBoolean("the_next")) {
					data.putBoolean("the_next", true);
				}

			}

			if (entity instanceof Villager villager) {
				if (villager.getPlayerReputation(player) > 100) {
					if (0.5 > entity.getRandom().nextDouble()) {
						villager.spawnAtLocation(CAItems.CHARMING_BRACELET.get());
					}
				}
			}

			if (entity instanceof ElderGuardian elderGuardian) {
				if (0.5 > entity.getRandom().nextDouble()) {
					elderGuardian.spawnAtLocation(CAItems.GUARDIAN_EYE.get());
				}
			}

			if (CurioUtils.isCsOn(player)) {
				if (EntityUtils.getHarmfulEffect(player) > 9) {
					if (entity instanceof Warden warden) {
						if (0.75 > entity.getRandom().nextDouble()) {
							warden.spawnAtLocation(CAItems.END_ETCHING.get());
						}
					}
				}
			}

			if (CurioUtils.isCsOn(player)) {
				if (entity.getMaxHealth() > CAModConfig.ETCHING_OF_LIFE_DROP_CONDITION.get()) {
					if (0.15 > entity.getRandom().nextDouble()) {
						entity.spawnAtLocation(CAItems.ETCHING_OF_LIFE.get());
					}
				}
			}
		}

		if (attacker instanceof WitherBoss) {
			if (entity.getMaxHealth() > 20) {
				if (0.1 > entity.getRandom().nextDouble()) {
					entity.spawnAtLocation(CAItems.TWISTED_HEART.get());
				}
			}
		}

		if (attacker instanceof WitherBoss) {
			if (entity instanceof Villager || entity instanceof EnderMan || entity instanceof Pillager
					|| entity instanceof Evoker || entity instanceof Piglin) {
				if (0.1 > entity.getRandom().nextDouble()) {
					entity.spawnAtLocation(CAItems.TWISTED_BRAIN.get());
				}
			}
		}

		if (entity instanceof WitherBoss witherBoss) {
			if (source.is(DamageTypeTags.IS_EXPLOSION)) {
				if (0.3 > entity.getRandom().nextDouble()) {
					witherBoss.spawnAtLocation(CAItems.CHAOTIC_ETCHING.get());
				}
			}
		}

		if (entity instanceof Warden warden) {
			if (source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
				if (0.5 > entity.getRandom().nextDouble()) {
					warden.spawnAtLocation(CAItems.NIHILITY_ETCHING.get());
				}
			}
		}
	}
}
