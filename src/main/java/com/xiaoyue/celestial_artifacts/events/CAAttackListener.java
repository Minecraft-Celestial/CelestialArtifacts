package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.CreateSourceEvent;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CAAttackListener implements AttackListener {

	@Override
	public void onCreateSource(CreateSourceEvent event) {
		if (event.getResult() != null) {
			if (event.getResult().toRoot() == L2DamageTypes.PLAYER_ATTACK) {
				if (event.getAttacker() instanceof Player player) {
					for (var e : ConditionalData.HOLDER.get(player).data.values()) {
						if (e instanceof CAAttackToken token) {
							token.onCreateSource(player, event);
						}
					}
				}
			}
		}
	}


	@Override
	public void onDamageFinalized(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingDamageEvent();
		assert event != null;
		if (cache.getAttackTarget() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerDamagedFinal(player, cache);
				}
			}
		}
		if (cache.getAttacker() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerDamageTargetFinal(player, cache);
				}
			}
		}
	}

	@Override
	public void onAttack(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		if (cache.getAttackTarget() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerAttacked(player, cache);
					if (event.isCanceled())
						return;
				}
			}
		}
		if (cache.getAttacker() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerAttackTarget(player, cache);
				}
			}
		}
	}

	@Override
	public void onHurt(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingHurtEvent();
		assert event != null;
		if (cache.getAttacker() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerHurtTarget(player, cache);
				}
			}
		}
		if (cache.getAttackTarget() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerHurt(player, cache);
				}
			}
		}
	}

	@Override
	public void onDamage(AttackCache cache, ItemStack weapon) {
		var event = cache.getLivingDamageEvent();
		assert event != null;
		if (cache.getAttacker() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerDamageTarget(player, cache);
				}
			}
		}
		if (cache.getAttackTarget() instanceof Player player) {
			for (var e : ConditionalData.HOLDER.get(player).data.values()) {
				if (e instanceof CAAttackToken token) {
					token.onPlayerDamaged(player, cache);
				}
			}
		}
	}

}
