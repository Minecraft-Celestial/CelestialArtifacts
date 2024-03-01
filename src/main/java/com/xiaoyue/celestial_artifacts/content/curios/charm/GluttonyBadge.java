package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GluttonyBadge implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.gluttony_badge.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.gluttony_badge.shift3");
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		int foodLevel = player.getFoodData().getFoodLevel();
		float val = 1 - foodLevel * 0.01f;
		cache.addDealtModifier(DamageModifier.multTotal(val));
	}

}
