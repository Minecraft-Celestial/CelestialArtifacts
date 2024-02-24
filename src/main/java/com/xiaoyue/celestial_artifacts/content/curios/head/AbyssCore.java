package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class AbyssCore implements SingleLineText, CAAttackToken {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.abyss_core.shift1");
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (!player.getCooldowns().isOnCooldown(CAItems.ABYSS_CORE.get())) {
			cache.addDealtModifier(DamageModifier.nonlinearPre(245, e -> Math.min(e, 12)));
			player.getCooldowns().addCooldown(CAItems.ABYSS_CORE.get(), 120);
		}
	}
}
