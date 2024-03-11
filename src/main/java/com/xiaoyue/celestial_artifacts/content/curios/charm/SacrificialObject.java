package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacrificialObject implements MultiLineText {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sacrificial_object.shift4");
	}

	public static void onPlayerDeath(Player player) {
		if (CAAttackToken.chance(player, 0.5)) {
			player.spawnAtLocation(Items.GOLD_INGOT);
		}
		List<LivingEntity> entities = EntityUtils.getDelimitedMonster(player, 8);
		entities.remove(player);
		for (LivingEntity e : entities) {
			if (e.getMaxHealth() < player.getMaxHealth()) {
				if (CAAttackToken.chance(e, 0.5)) {
					e.kill();
				}
			}
		}
	}

}
