package com.xiaoyue.celestial_artifacts.content.curios.back;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;

public class LeechScabbard implements CAAttackToken, SingleLineText {

	private static MobEffect getEff() {
		return CCEffects.BLADE_MODIFIER.get();
	}

	private static double getHeal() {
		return CAModConfig.COMMON.back.leechScabbardHealFactor.get();
	}

	@Override
	public Component getLine() {
		var eff = getEff().getDisplayName().copy().withStyle(ChatFormatting.AQUA);
		var val = Component.literal((int) Math.round(getHeal() * 100) + "%").withStyle(ChatFormatting.GOLD);
		return CALang.Back.LEECH.get(eff, val).withStyle(ChatFormatting.GRAY);
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		if (!isMelee(cache)) return;
		if (player.hasEffect(getEff())) {
			player.heal(cache.getDamageDealt() * (float) getHeal());
		}
	}

}