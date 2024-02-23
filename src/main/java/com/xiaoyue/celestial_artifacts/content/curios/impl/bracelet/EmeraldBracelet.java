package com.xiaoyue.celestial_artifacts.content.curios.impl.bracelet;

import com.xiaoyue.celestial_artifacts.content.curios.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmeraldBracelet implements TextFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_bracelet.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_bracelet.shift3");
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (player.getLuck() > 2) {
			if (player.getRandom().nextDouble() > 0.5) {
				EntityUtils.addEct(player, MobEffects.ABSORPTION, 100, 1);
			}
		}
	}

}
