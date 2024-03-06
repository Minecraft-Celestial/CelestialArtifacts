package com.xiaoyue.celestial_artifacts.content.curios.pendant;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.CCUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShadowPendant implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift5");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		int brightness = player.level().getMaxLocalRawBrightness(player.getOnPos());
		if (brightness < 7) {
			int add = 7 - brightness;
			cache.addHurtModifier(DamageModifier.multTotal(1 + (add * 0.05f)));
		}
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		player.heal(cache.getDamageDealt() * 0.25f);
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (player.level() instanceof ServerLevel sl) {
			int brightness = CCUtils.getLight(sl, player.blockPosition());
			if (brightness < 7) {
				int add = 7 - brightness;
				cache.addDealtModifier(DamageModifier.multTotal(1 - (add * 0.05f)));
			}
		}

	}

}

