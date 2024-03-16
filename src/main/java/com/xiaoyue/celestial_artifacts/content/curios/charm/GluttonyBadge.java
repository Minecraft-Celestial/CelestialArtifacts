package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GluttonyBadge implements MultiLineText, CAAttackToken {//TODO check

	private static double protection() {
		return CAModConfig.COMMON.charm.glubadgeimmunity.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.GLUTTONY_BADGE_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.GLUTTONY_BADGE_2.get(TextFacet.perc(protection()))));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		int foodLevel = player.getFoodData().getFoodLevel();
		float val = (float) (1 - foodLevel * protection());
		cache.addDealtModifier(DamageModifier.multTotal(val));
	}

}
