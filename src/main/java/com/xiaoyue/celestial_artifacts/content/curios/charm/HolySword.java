package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HolySword implements MultiLineText, CAAttackToken {
	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_sword.shift4",
				ChatFormatting.GOLD, CAModConfig.COMMON.charm.holySwordLostLifeAddDamage.get() + "%");
	}

	public static float lossLifeAdd(Player player) {
		return (float) (((player.getMaxHealth() - player.getHealth()) / CAModConfig.COMMON.charm.holySwordLostLifeAddDamage.get()) * 0.02f);
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		float min = (float) Math.min(HolySword.lossLifeAdd(player),
				CAModConfig.COMMON.charm.holySwordMaxAddDamage.get());
		cache.addHurtModifier(DamageModifier.multTotal(1 + min));
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		if (cache.getAttackTarget().getMobType() == MobType.UNDEAD) {
			player.heal(cache.getDamageDealt());
		}
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (cache.getAttacker() != null)
			GeneralEventHandler.schedule(() ->
					cache.getAttacker().hurt(player.damageSources().playerAttack(player),
							cache.getDamageDealt() * 0.12f));
	}

}
