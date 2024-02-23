package com.xiaoyue.celestial_artifacts.content.curios.impl.back;

import com.xiaoyue.celestial_artifacts.content.curios.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class TitanScabbard implements CAAttackToken, SingleLineText {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.titan_scabbard.shift2");
	}

	@Override
	public void onPlayerAttackTarget(Player player, AttackCache cache) {
		if (!isMelee(cache)) return;
		if (player.hasEffect(CCEffects.BLADE_MODIFIER.get())) {
			if (cache.getAttackTarget().getMaxHealth() > player.getMaxHealth()) {
				cache.addHurtModifier(DamageModifier.multTotal(1.75f));
			}
		}
	}

}