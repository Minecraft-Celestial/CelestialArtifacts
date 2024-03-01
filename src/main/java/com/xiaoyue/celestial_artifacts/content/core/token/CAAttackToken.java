package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_core.utils.LevelUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DamageTypeRoot;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface CAAttackToken {

	default DamageSource getSource(AttackCache cache) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		return event.getSource();
	}

	default boolean isArrow(AttackCache cache) {
		return getSource(cache).getDirectEntity() instanceof AbstractArrow;
	}

	default boolean isMelee(AttackCache cache) {
		if (cache.getAttacker() == null) return false;
		if (cache.getAttacker() == cache.getAttackTarget()) return false;
		var event = cache.getLivingAttackEvent();
		if (event == null) return false;
		if (event.getSource().getDirectEntity() != cache.getAttacker()) return false;
		var ans = event.getSource().typeHolder().unwrapKey().map(DamageTypeRoot::of);
		return ans.isPresent() && (ans.get() == L2DamageTypes.PLAYER_ATTACK || ans.get() == L2DamageTypes.MOB_ATTACK);
	}

	default boolean isPhysics(AttackCache cache) {
		return LevelUtils.sourceIsPhysics(getSource(cache));
	}

	default void onPlayerDamagedFinal(Player player, AttackCache cache) {

	}

	default void onPlayerAttackTarget(Player player, AttackCache cache) {

	}

	default void onPlayerHurtTarget(Player player, AttackCache cache) {

	}

	default void onPlayerDamageTarget(Player player, AttackCache cache) {

	}

	default boolean onPlayerAttacked(Player player, AttackCache cache) {
		return false;
	}

	default void onPlayerHurt(Player player, AttackCache cache) {

	}

	default void onPlayerDamaged(Player player, AttackCache cache) {

	}

	default void onPlayerDamageTargetFinal(Player player, AttackCache cache) {

	}

	default void onCreateSource(Player player, CreateSourceEvent event) {

	}

	default void onPlayerKill(Player player, LivingDeathEvent event) {

	}

}