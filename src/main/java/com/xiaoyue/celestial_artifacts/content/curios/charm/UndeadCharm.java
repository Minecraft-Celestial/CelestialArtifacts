package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class UndeadCharm implements SingleLineText, CAAttackToken {
	@Override
	public MutableComponent getLine() {
		return Component.translatable("tooltip.celestial_artifacts.undead_charm.shift1");
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
				player.getCooldowns().addCooldown(item, 3600);
				return 0;
			}
		}
		return val;
	}

}
