package com.xiaoyue.celestial_artifacts.content.curios.pendant;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.CCUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShadowPendant implements MultiLineText, CAAttackToken {

	private static double damageHealFactor() {
		return CAModConfig.COMMON.pendant.shadowPendantDamageHeal.get();
	}

	private static double damageBonusFactor() {
		return CAModConfig.COMMON.pendant.shadowPendantDamageBonus.get();
	}

	private static double damageReductionFactor() {
		return CAModConfig.COMMON.pendant.shadowPendantDamageReduction.get();
	}

	private static int light() {
		return CAModConfig.COMMON.pendant.shadowPendantLightLevel.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Pendant.SHADOW_1.get(TextFacet.perc(damageHealFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Pendant.SHADOW_2.get().withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Pendant.SHADOW_3.get(TextFacet.num(light())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Pendant.SHADOW_4.get(TextFacet.perc(damageBonusFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Pendant.SHADOW_5.get(TextFacet.perc(damageReductionFactor())).withStyle(ChatFormatting.GRAY)));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		int brightness = CCUtils.getLight(player.level(), player.blockPosition().above());
		if (brightness < light()) {
			int add = light() - brightness;
			cache.addHurtModifier(DamageModifier.multTotal((float) (1 + (add * damageBonusFactor()))));
		}
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		player.heal((float) (cache.getDamageDealt() * damageHealFactor()));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (player.level() instanceof ServerLevel sl) {
			int brightness = CCUtils.getLight(sl, player.blockPosition().above());
			if (brightness < light()) {
				int add = light() - brightness;
				cache.addDealtModifier(DamageModifier.multTotal((float) (1 - (add * damageReductionFactor()))));
			}
		}

	}

}

