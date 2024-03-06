package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritBracelet implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_bracelet.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_bracelet.shift2");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (!CAAttackToken.isArrow(cache)) return;
		player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, false, true));
	}

}
