package com.xiaoyue.celestial_artifacts.content.curios.heart;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeartOfRevenge implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heart_of_revenge.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heart_of_revenge.shift3");
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 60, 1);
		EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 50, 0);
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (player.getLastHurtByMobTimestamp() >= player.tickCount - 60) {
			cache.addHurtModifier(DamageModifier.multTotal(1.25f));
		}
	}

}
