package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GluttonyBadge implements MultiLineText, CAAttackToken {

	private static double protection() {
		return CAModConfig.COMMON.charm.gluttonyBadgeProtection.get();
	}

	private static int duration() {
		return CAModConfig.COMMON.charm.gluttonyBadgeEffectDuration.get();
	}

	public static MobEffectInstance effAtk() {
		return new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration() * 20, 0);
	}

	public static MobEffectInstance effReg() {
		return new MobEffectInstance(MobEffects.REGENERATION, duration() * 20, 0);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.GLUTTONY_BADGE_1.get(
				EffectFacet.getDesc(effAtk()),
				EffectFacet.getDesc(effReg())
		)));
		list.add(TextFacet.wrap(CALang.Charm.GLUTTONY_BADGE_2.get(TextFacet.perc(protection()))));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		int foodLevel = player.getFoodData().getFoodLevel();
		float val = (float) (1 - foodLevel * protection());
		cache.addDealtModifier(DamageModifier.multTotal(val));
	}

}
