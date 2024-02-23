package com.xiaoyue.celestial_artifacts.content.curios.impl.charm;

import com.xiaoyue.celestial_artifacts.content.curios.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SandsTalisman implements TextFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sands_talisman.shift1");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		float biomesTemp = player.level().getBiome(player.blockPosition()).get().getBaseTemperature() * 100;
		if (biomesTemp >= 1) {
			cache.addHurtModifier(DamageModifier.multTotal(1.3f));
		}
	}

}