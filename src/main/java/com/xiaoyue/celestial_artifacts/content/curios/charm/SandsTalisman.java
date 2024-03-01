package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SandsTalisman implements SingleLineText, CAAttackToken {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.sands_talisman.shift1");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		float biomesTemp = player.level().getBiome(player.blockPosition()).get().getBaseTemperature() * 100;
		if (biomesTemp >= 1) {
			cache.addHurtModifier(DamageModifier.multTotal(1.3f));
		}
	}

}