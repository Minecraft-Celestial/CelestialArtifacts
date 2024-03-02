package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrayerCrown implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift3");
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (player.isCrouching())
			cache.addDealtModifier(DamageModifier.multTotal(0.75f));//TODO module
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (player.isCrouching()) {
			if (CAAttackToken.chance(player, 0.5)) {
				GeneralEventHandler.schedule(() -> player.heal(cache.getDamageDealt() * 0.25f));
			}
		}
	}
}
