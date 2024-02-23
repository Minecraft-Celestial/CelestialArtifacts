package com.xiaoyue.celestial_artifacts.content.curios.impl.scroll;

import com.xiaoyue.celestial_artifacts.content.curios.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.curios.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkywalkerScroll extends BaseTickingToken implements SkillFeature {

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.SKYWALKER_SCROLL.get();
		if (player.isCrouching()) {
			x = player.getX();
			y = player.getY();
			z = player.getZ();
		} else if (!player.getCooldowns().isOnCooldown(item)) {
			player.teleportTo(x, y, z);
			player.getCooldowns().addCooldown(item, 1200);
		}
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {

	}

	@SerialClass.SerialField
	public double x, y, z;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift4");
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift5", ChatFormatting.AQUA, (float) x);
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift6", ChatFormatting.AQUA, (float) y);
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift7", ChatFormatting.AQUA, (float) z);
	}


}
