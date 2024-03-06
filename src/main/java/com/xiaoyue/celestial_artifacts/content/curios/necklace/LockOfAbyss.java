package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LockOfAbyss implements MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.lock_of_abyss.shift4");
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		var entity = cache.getAttackTarget();
		var ins = entity.getEffect(MobEffects.MOVEMENT_SLOWDOWN);
		if (ins != null) {
			int amplifier = ins.getAmplifier();
			if (amplifier >= 7) {
				entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
				var dmg = cache.getDamageDealt() * 2.5f;
				GeneralEventHandler.schedule(() -> entity.hurt(CCDamageTypes.abyss(player), dmg));
			} else {
				EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 200, amplifier + 1);
			}
		} else {
			EntityUtils.addEct(entity, MobEffects.MOVEMENT_SLOWDOWN, 200, 0);
		}
	}
}
