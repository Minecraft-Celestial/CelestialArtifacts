package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmeraldNecklace implements TextFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_necklace.shift2");
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		float luck = player.getLuck();
		if (player.getRandom().nextDouble() < 0.02 + (luck * 0.02)) {
			event.getEntity().spawnAtLocation(net.minecraft.world.item.Items.EMERALD);
		}
	}

}
