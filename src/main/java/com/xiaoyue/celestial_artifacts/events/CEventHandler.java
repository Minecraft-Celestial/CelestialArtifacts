package com.xiaoyue.celestial_artifacts.events;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.TridentItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CEventHandler {

	public static Multimap<Attribute, AttributeModifier> createEmeraldAttributeMap() {
		Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
		attributesDefault.put(net.minecraft.world.entity.ai.attributes.Attributes.LUCK, new AttributeModifier(ToolTipUtils.getFUuid(), "enel", 2, AttributeModifier.Operation.ADDITION));
		attributesDefault.put(L2DamageTracker.CRIT_RATE.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "encr", 0.15, AttributeModifier.Operation.ADDITION));
		return attributesDefault;
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (CurioUtils.isEmeraldOn(player)) {//TODO
			player.getAttributes().addTransientAttributeModifiers(CEventHandler.createEmeraldAttributeMap());
		}
		if (CurioUtils.isSpiritOn(player)) {
			if (player.isUsingItem()) {
				if (CurioUtils.isRangeUseAnim(player.getUseItem().getUseAnimation())) {
					if (player.getTicksUsingItem() > 60) {
						EntityUtils.addEct(player, CCEffects.ARROW_DAMAGE.get(), 70, 0);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		Entity directEntity = source.getDirectEntity();
		Entity attacker = source.getEntity();
		if (attacker instanceof Player player) {
			if (CurioUtils.isSeaGodOn(player)) {
				if (directEntity instanceof ThrownTrident trident) {
					if (trident.getOwner() != null && trident.getOwner().is(player)) {
						event.setAmount(event.getAmount() * 1.75f);
					}
				}
				if (directEntity != null && directEntity.is(player)) {
					if (player.getMainHandItem().getItem() instanceof TridentItem) {
						event.setAmount(event.getAmount() * 1.5f);
					}
				}
			}
			if (CurioUtils.isSpiritOn(player)) {
				if (directEntity instanceof AbstractArrow) {
					if (EntityUtils.isLookingBehindTarget(entity, player.getEyePosition())) {
						event.setAmount(event.getAmount() * 1.5f);
					}
					if (player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
						if (Math.random() > 0.5) {
							EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 50, 1);
						}
					}
				}
			}
		}
		if (entity instanceof Player player) {
			if (CurioUtils.isSeaGodOn(player)) {
				if (attacker instanceof LivingEntity livingEntity) {
					if (livingEntity.getMobType() == MobType.WATER) {
						event.setAmount(event.getAmount() * 0.65f);
					}
				}
			}
			if (CurioUtils.isSpiritOn(player)) {
				if (LevelUtils.sourceIsPhysics(source)) {
					if (Math.random() < 0.2) {
						event.setCanceled(true);
					} else {
						event.setAmount(event.getAmount() * 0.8f);
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
			if (CurioUtils.isCsOn(player)) {
				if (entity instanceof Monster monster) {
					if (0.02 > Math.random()) {
						monster.spawnAtLocation(CCItems.THE_END_DUST.get());
					}
				}
			}
			CAAttackListener.fireEvent(player, t -> t.onPlayerKill(player, event));
		}
	}

}