package com.xiaoyue.celestial_artifacts.content.curios.token;

import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface CAAttackToken {
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
