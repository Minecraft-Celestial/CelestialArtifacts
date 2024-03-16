package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.register.CCEffects;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulBox implements MultiLineText, CAAttackToken {

	private static int cooldownFactor() {
		return CAModConfig.COMMON.charm.soulBoxCooldown.get();
	}

	private static double damageFactor() {
		return CAModConfig.COMMON.charm.soulBoxReflect.get();
	}

	private static double effectChanceFactor() {
		return CAModConfig.COMMON.charm.soulBoxShatterChance.get();
	}

	private static int durHigh() {
		return CAModConfig.COMMON.charm.soulBoxShatterHighDuration.get();
	}

	private static int ampHigh() {
		return CAModConfig.COMMON.charm.soulBoxShatterHighLevel.get();
	}

	private static int durLow() {
		return CAModConfig.COMMON.charm.soulBoxShatterLowDuration.get();
	}

	private static int ampLow() {
		return CAModConfig.COMMON.charm.soulBoxShatterLowLevel.get();
	}

	private static MobEffectInstance effHigh() {
		return new MobEffectInstance(CCEffects.SOUL_SHATTER.get(), durHigh() * 20, ampHigh());
	}

	private static MobEffectInstance effLow() {
		return new MobEffectInstance(CCEffects.SOUL_SHATTER.get(), durLow() * 20, ampLow());
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_0.get(TextFacet.perc(effectChanceFactor()), EffectFacet.getDesc(effLow()))));
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_1.get(TextFacet.num(cooldownFactor()))));
		list.add(TextFacet.inner(CALang.Charm.SOUL_BOX_2.get(EffectFacet.getDesc(effHigh()))));
		list.add(TextFacet.inner(CALang.Charm.SOUL_BOX_3.get(TextFacet.perc(damageFactor()))));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (cache.getAttacker() != null) {
			cache.addDealtModifier(DamageModifier.nonlinearFinal(1220, v -> parse(player, cache.getAttacker(), v)));
		}
	}

	private float parse(Player player, LivingEntity le, float val) {
		Item item = CAItems.SOUL_BOX.get();
		if (val > player.getHealth()) {
			if (!player.getCooldowns().isOnCooldown(item)) {
				le.addEffect(effHigh());
				le.hurt(CCDamageTypes.abyss(player), (float) (player.getMaxHealth() * damageFactor()));
				player.getCooldowns().addCooldown(item, cooldownFactor() * 20);
				return 0;
			}
		} else if (CAAttackToken.chance(player, effectChanceFactor())) {
			le.addEffect(effLow());
		}
		return val;
	}

}
