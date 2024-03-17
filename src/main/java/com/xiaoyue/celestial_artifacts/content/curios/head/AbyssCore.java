package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class AbyssCore implements SingleLineText, CAAttackToken {

	private static int cooldownFactor() {
		return CAModConfig.COMMON.head.abyssCoreCooldown.get();
	}

	private static int damageReduceFactor() {
		return CAModConfig.COMMON.head.abyssCoreReduce.get();
	}

	private static int damageJudgementFactor() {
		return CAModConfig.COMMON.head.abyssCoreDamageJudgement.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Head.ABYSS_CORE.get(TextFacet.num(damageJudgementFactor()), TextFacet.num(damageReduceFactor()), TextFacet.num(cooldownFactor()));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (!player.getCooldowns().isOnCooldown(CAItems.ABYSS_CORE.get())) {
			cache.addDealtModifier(DamageModifier.nonlinearPre(245, d -> parse(d, player)));
			player.getCooldowns().addCooldown(CAItems.ABYSS_CORE.get(), cooldownFactor() * 20);
		}
	}

	private float parse(float dmg, Player player) {
		if (dmg >= damageJudgementFactor()) {
			player.getCooldowns().addCooldown(CAItems.ABYSS_CORE.get(), cooldownFactor() * 20);
			return damageReduceFactor();
		}
		return dmg;
	}

}
