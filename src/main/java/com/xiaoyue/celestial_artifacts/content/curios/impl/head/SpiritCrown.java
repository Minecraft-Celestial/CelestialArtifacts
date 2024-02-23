package com.xiaoyue.celestial_artifacts.content.curios.impl.head;

import com.xiaoyue.celestial_artifacts.content.curios.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritCrown implements TextFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_crown.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_crown.shift2");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (!isArrow(cache)) return;
		List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 6, 2);
		if (entities.size() < 3) {
			cache.addHurtModifier(DamageModifier.multTotal(1.45f));
		}
		float distance = player.distanceTo(cache.getAttackTarget());
		cache.addHurtModifier(DamageModifier.multTotal(1 + distance * 0.01f));
	}

}
