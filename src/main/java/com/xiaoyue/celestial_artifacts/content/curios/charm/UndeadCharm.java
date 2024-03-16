package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class UndeadCharm implements SingleLineText, CAAttackToken {

	private static int cooldownFactor() {
		return CAModConfig.COMMON.charm.undeadCD.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Charm.UNDEAD_CHARM.get(TextFacet.num(cooldownFactor()));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		cache.addDealtModifier(DamageModifier.nonlinearFinal(1225, v -> parse(player, v)));
	}

	private float parse(Player player, float val) {
		Item item = CAItems.UNDEAD_CHARM.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			if (val > player.getHealth()) {
				player.heal(2);
				player.getCooldowns().addCooldown(item, cooldownFactor() * 20);
				return 0;
			}
		}
		return val;
	}

}
