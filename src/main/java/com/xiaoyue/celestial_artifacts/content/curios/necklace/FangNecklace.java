package com.xiaoyue.celestial_artifacts.content.curios.necklace;

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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FangNecklace implements MultiLineText, CAAttackToken {
	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.fang_necklace.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.fang_necklace.shift3");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (CAAttackToken.chance(player, 0.5)) {
			EntityUtils.addEct(cache.getAttackTarget(), MobEffects.POISON, 100, 2);
		}
		for (var e : cache.getAttackTarget().getArmorSlots()) {
			if (e.getItem() instanceof ArmorItem) {
				cache.addHurtModifier(DamageModifier.multTotal(1.25f));
				return;
			}
		}
	}

}
