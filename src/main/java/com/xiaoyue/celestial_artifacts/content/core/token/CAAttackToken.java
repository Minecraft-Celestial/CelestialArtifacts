package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.content.core.feature.IFeature;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.contents.damage.DamageTypeRoot;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface CAAttackToken extends IFeature {

	static boolean chance(LivingEntity player, double chance) {
		return player.getRandom().nextDouble() < chance;
	}

	static DamageSource getSource(AttackCache cache) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		return event.getSource();
	}

	static boolean isArrow(AttackCache cache) {
		return getSource(cache).is(DamageTypeTags.IS_PROJECTILE);
	}

	static boolean isMelee(AttackCache cache) {
		if (cache.getAttacker() == null) return false;
		if (cache.getAttacker() == cache.getAttackTarget()) return false;
		var event = cache.getLivingAttackEvent();
		if (event == null) return false;
		if (event.getSource().getDirectEntity() != cache.getAttacker()) return false;
		var ans = event.getSource().typeHolder().unwrapKey().map(DamageTypeRoot::of);
		return ans.isPresent() && (ans.get() == L2DamageTypes.PLAYER_ATTACK || ans.get() == L2DamageTypes.MOB_ATTACK);
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
