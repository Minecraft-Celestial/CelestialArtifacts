package com.xiaoyue.celestial_artifacts.content.curios.back;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class LeechScabbard implements CAAttackToken, SingleLineText {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.leech_scabbard.shift2");
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		if (!isMelee(cache)) return;
		if (player.hasEffect(CCEffects.BLADE_MODIFIER.get())) {
			player.heal(cache.getDamageDealt() * 0.25f);
		}
	}

}