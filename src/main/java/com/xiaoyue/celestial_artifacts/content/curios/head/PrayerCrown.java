package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class PrayerCrown implements SingleLineText, CAAttackToken {

	private static double healAmountFactor() {
		return CAModConfig.COMMON.head.prayerCrownHealAmount.get();
	}

	private static double healChance() {
		return CAModConfig.COMMON.head.prayerCrownHealChance.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Head.PRAYER_CROWN.get(TextFacet.perc(healChance()), TextFacet.perc(healAmountFactor()));
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (player.isCrouching()) {
			if (CAAttackToken.chance(player, healChance())) {
				GeneralEventHandler.schedule(() -> player.heal((float) (cache.getDamageDealt() * healAmountFactor())));
			}
		}
	}
}
