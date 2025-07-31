package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureType;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.register.CAEffects;
import com.xiaoyue.celestial_core.events.LivingJumpEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CAGeneralEventHandler {

	@SubscribeEvent
	public static void onLivingJump(LivingJumpEvent event) {
		if (event.getEntity() instanceof Player player) {
			for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.JUMP)) {
				e.onJump(player, event);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker instanceof Player player) {
			CAAttackListener.fireEvent(player, t -> t.onPlayerKill(player, event));
		}
	}

	@SubscribeEvent
	public static void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		double factor = 1;
		for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.EXP)) {
			factor += e.getXpBonus(player);
		}
		event.getOrb().value = (int) (event.getOrb().value * factor);
	}

	@SubscribeEvent
	public static void onPlayerBreak(PlayerEvent.BreakSpeed event) {
		Player player = event.getEntity();
		double factor = 1;
		for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.MINING)) {
			factor *= e.getBreakFactor(player);
		}
		event.setNewSpeed((float) (event.getOriginalSpeed() * factor));
	}


	@SubscribeEvent
	public static void onPlayerHeal(LivingHealEvent event) {
		if (event.getEntity().hasEffect(CAEffects.ENFEEBLED_LACERATION.get())) {
			event.setAmount(event.getAmount() * 0.2f);
		}
		if (event.getEntity() instanceof Player player) {
			for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.HEAL))
				e.onPlayerHeal(player, event);
		}
	}

	@SubscribeEvent
	public static void onPlayerBlocked(ShieldBlockEvent event) {
		if (event.getEntity() instanceof Player player) {
			for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.SHIELD))
				e.onPlayerBlocked(player, event);
		}
	}

}
