package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class SkywalkerScroll extends BaseTickingToken
		implements NetworkSensitiveToken<SkywalkerScroll>, SkillFeature {

	public static final TokenFacet<SkywalkerScroll> TOKEN = new TokenFacet<>("skywalker_scroll", SkywalkerScroll::new);

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.SKYWALKER_SCROLL.get();
		if (player.isCrouching()) {
			x = player.getX();
			y = player.getY();
			z = player.getZ();
			sync(TOKEN.getKey(), this, player);
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
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift4");
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift5", ChatFormatting.AQUA, (float) x);
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift6", ChatFormatting.AQUA, (float) y);
		ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift7", ChatFormatting.AQUA, (float) z);
	}

	@Override
	public void onSync(@Nullable SkywalkerScroll skywalkerScroll, Player player) {

	}

}
