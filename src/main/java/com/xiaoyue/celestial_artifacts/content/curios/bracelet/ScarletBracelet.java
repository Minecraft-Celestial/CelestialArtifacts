package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

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

public class ScarletBracelet implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.scarlet_bracelet.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.scarlet_bracelet.shift2");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (player.getHealth() > player.getMaxHealth() * 0.5f) {
			var e = cache.getAttackTarget();
			float i = player.getHealth() - player.getMaxHealth() * 0.5f;
			float addDamage = i * (e.getMaxHealth() * 0.0001f);
			player.setHealth(player.getMaxHealth() * 0.5f);
			cache.addHurtModifier(DamageModifier.nonlinearFinal(900, val ->
					Math.min(e.getMaxHealth() * 0.5f, val * (1 + addDamage))));
		}

	}
}
