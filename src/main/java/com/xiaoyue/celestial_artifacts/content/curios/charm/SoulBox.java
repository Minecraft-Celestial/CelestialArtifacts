package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulBox implements MultiLineText, CAAttackToken {//TODO check

	private static int cooldownFactor() {
		return CAModConfig.COMMON.charm.soulboximmortalCD.get();
	}

	private static double damageFactor() {
		return CAModConfig.COMMON.charm.soulboxwitherblast.get();
	}

	private static double effectChanceFactor() {
		return CAModConfig.COMMON.charm.soulboxbreak.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_1.get(TextFacet.perc(effectChanceFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_2.get()));
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_3.get(TextFacet.perc(damageFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.SOUL_BOX_4.get(TextFacet.num(cooldownFactor()))));
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
				EntityUtils.addEct(le, CCEffects.SOUL_SHATTER.get(), 1200, 2);
				le.hurt(player.damageSources().wither(), (float) (player.getMaxHealth() * damageFactor()));
				player.getCooldowns().addCooldown(item, cooldownFactor() * 20);
				return 0;
			}
		} else if (CAAttackToken.chance(player, effectChanceFactor())) {
			EntityUtils.addEct(le, CCEffects.SOUL_SHATTER.get(), 100, 0);
		}
		return val;
	}

}
