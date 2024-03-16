package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrayerCrown implements MultiLineText, CAAttackToken {

	private static double healAmountFactor() {
		return CAModConfig.COMMON.head.prayerCrownHealAmount.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
	  list.add(TextFacet.wrap(CALang.Head.PRAYER_CROWN_1.get(TextFacet.perc(healAmountFactor())).withStyle(ChatFormatting.GRAY)));
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (player.isCrouching()) {
			if (CAAttackToken.chance(player, 0.5)) {
				GeneralEventHandler.schedule(() -> player.heal((float) (cache.getDamageDealt() * healAmountFactor())));
			}
		}
	}
}
