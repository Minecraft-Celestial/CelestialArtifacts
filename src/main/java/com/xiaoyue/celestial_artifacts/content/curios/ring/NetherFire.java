package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetherFire implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.nether_fire.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.nether_fire.shift2");
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = CAAttackToken.getSource(cache);
		return source.is(DamageTypeTags.IS_FIRE) &&
				!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) &&
				!source.is(DamageTypeTags.BYPASSES_EFFECTS);
	}

	@Override
	public void onPlayerAttackTarget(Player player, AttackCache cache) {
		cache.getAttackTarget().setSecondsOnFire(100);
	}

}
