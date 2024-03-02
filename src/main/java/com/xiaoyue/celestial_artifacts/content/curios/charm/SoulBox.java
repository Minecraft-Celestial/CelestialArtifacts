package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
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

public class SoulBox implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift4");
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
				le.hurt(player.damageSources().wither(), player.getMaxHealth() * 2.5f);
				player.getCooldowns().addCooldown(item, 4800);
				return 0;
			}
		} else if (CAAttackToken.chance(player, 0.3)) {
			EntityUtils.addEct(le, CCEffects.SOUL_SHATTER.get(), 100, 0);
		}
		return val;
	}

}
