package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureType;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class CAAttackListener implements AttackListener {

	public static void fireEvent(Player player, Consumer<CAAttackToken> cons) {
		for (var token : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.ATK)) {
			cons.accept(token);
		}
	}

	public static boolean fireEventCancellable(Player player, Predicate<CAAttackToken> cons) {
		for (var token : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.ATK)) {
			if (cons.test(token)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onCreateSource(CreateSourceEvent event) {
		if (event.getResult() != null) {
			if (event.getResult().toRoot() == L2DamageTypes.PLAYER_ATTACK) {
				if (event.getAttacker() instanceof Player player) {
					fireEvent(player, t -> t.onCreateSource(player, event));
				}
			}
		}
	}

	@Override
	public void onDamageFinalized(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingDamageEvent();
		assert event != null;
		if (cache.getAttackTarget() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerDamagedFinal(player, cache));
		}
		if (cache.getAttacker() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerDamageTargetFinal(player, cache));
		}
	}

	@Override
	public void onAttack(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		if (cache.getAttackTarget() instanceof Player player) {
			if (fireEventCancellable(player, t -> t.onPlayerAttacked(player, cache))) {
				event.setCanceled(true);
				return;
			}
		}
		if (cache.getAttacker() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerAttackTarget(player, cache));
		}
	}

	@Override
	public void onHurt(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingHurtEvent();
		assert event != null;
		if (cache.getAttacker() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerHurtTarget(player, cache));
			if (cache.getAttackTarget() instanceof Mob mob) {
				if (mob.targetSelector.getAvailableGoals().isEmpty()) {
					CatastropheScroll.Curses.DESIRE.trigger(player);
				}
			}
		}
		if (cache.getAttackTarget() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerHurt(player, cache));
			CatastropheScroll.Curses.LIFE.trigger(player);
		}
	}

	@Override
	public void onDamage(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingDamageEvent();
		assert event != null;
		if (cache.getAttacker() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerDamageTarget(player, cache));
		}
		if (cache.getAttackTarget() instanceof Player player) {
			fireEvent(player, t -> t.onPlayerDamaged(player, cache));
		}
	}

}
