package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.curios.charm.SacrificialObject;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSpawnPhantomsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MiscCuriosHandler {

	@SubscribeEvent
	public static void onArrowHit(ProjectileImpactEvent event) {
		Projectile projectile = event.getProjectile();
		Entity entity = event.getEntity();
		if (projectile instanceof AbstractArrow arrow) {
			if (arrow.getOwner() instanceof Player player) {
				// 火焰箭袋
				if (CurioUtils.hasCurio(player, CAItems.FLAME_ARROW_BAG.get())) {
					entity.setSecondsOnFire(60);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onAddedEffect(MobEffectEvent.Added event) {
		MobEffectInstance instance = event.getEffectInstance();
		if (event.getEntity() instanceof Player player) {
			// 灾厄之册
			if (CurioUtils.hasCurio(player, CAItems.CATASTROPHE_SCROLL.get())) {
				if (CurioUtils.isCsOn(player) && !CurioUtils.hasCurio(player, CAItems.NIHILITY_ETCHING.get())) {
					if (!player.hasEffect(instance.getEffect())) {
						if (instance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
							instance.update(new MobEffectInstance(instance.getEffect(),
									(int) (instance.getDuration() * 1.5f), instance.getAmplifier() + 1));
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onFinishItemUse(LivingEntityUseItemEvent.Finish event) {
		ItemStack itemStack = event.getItem();
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			// 暴食徽章
			if (CurioUtils.hasCurio(player, CAItems.GLUTTONY_BADGE.get())) {
				if (itemStack.getUseAnimation() == UseAnim.EAT) {
					EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 40, 0);
					EntityUtils.addEct(player, MobEffects.REGENERATION, 40, 0);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onStarItemUse(LivingEntityUseItemEvent.Start event) {
		ItemStack itemStack = event.getItem();
		LivingEntity entity = event.getEntity();
		if (entity instanceof Player player) {
			// 精灵手环 TODO works?
			if (CurioUtils.hasCurio(player, CAItems.SPIRIT_BRACELET.get())) {
				if (CurioUtils.isRangeUseAnim(itemStack.getUseAnimation())) {
					event.setDuration((int) (event.getDuration() * 0.75f));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onUseTotem(LivingUseTotemEvent event) {
		LivingEntity entity = event.getEntity();
		Entity attacker = event.getSource().getEntity();
		if (entity instanceof Player player) {
			// 被咒者的图腾
			if (CurioUtils.hasCurio(player, CAItems.CURSED_TOTEM.get())) {
				if (attacker instanceof LivingEntity livingEntity) {
					EntityUtils.addEct(livingEntity, MobEffects.WITHER, 600, 2);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onSpawnPhantom(PlayerSpawnPhantomsEvent event) {
		Player player = event.getEntity();
		// 怨影吊坠
		if (CurioUtils.hasCurio(player, CAItems.SHADOW_PENDANT.get())) {
			event.setResult(Event.Result.DENY);
		}
	}

	@SubscribeEvent
	public static void onSpawnEntity(MobSpawnEvent.FinalizeSpawn event) {
		Mob entity = event.getEntity();
		// 扭曲卷轴 TODO
		if (LevelUtils.isServerLevel(entity.level()) && entity instanceof Monster) {
			List<Player> entities = entity.level().getEntitiesOfClass(Player.class, EntityUtils.getAABB(entity, 6, 2));
			for (Player list : entities) {
				if (CurioUtils.hasCurio(list, CAItems.TWISTED_SCROLL.get())) {
					if (list.getRandom().nextDouble() < 0.6) {
						entity.level().addFreshEntity(entity);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onChangeLevel(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player entity = event.getEntity();
		// 旅行者卷轴
		if (CurioUtils.hasCurio(entity, CAItems.TRAVELER_SCROLL.get())) {
			EntityUtils.addEct(entity, MobEffects.MOVEMENT_SPEED, 300, 1);
			EntityUtils.addEct(entity, MobEffects.REGENERATION, 300, 0);
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (CurioUtils.hasCurio(player, CAItems.SACRIFICIAL_OBJECT.get())) {
				SacrificialObject.onPlayerDeath(player);
			}
		}
	}

}
