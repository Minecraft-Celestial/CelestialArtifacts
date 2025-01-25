package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class SkywalkerScroll extends BaseTickingToken
		implements NetworkSensitiveToken<SkywalkerScroll>, SkillFeature {

	public static final TokenFacet<SkywalkerScroll> TOKEN = new TokenFacet<>("skywalker_scroll", SkywalkerScroll::new);
	@SerialClass.SerialField
	public ResourceLocation id;
	@SerialClass.SerialField
	public double x, y, z;

	private static int cooldownFactor() {
		return CAModConfig.COMMON.scroll.skyWalkerCooldown.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		if (level != null && id != null) {
			boolean same = level.dimension().location().equals(id);
			list.add(TextFacet.wrap(CALang.Scroll.SKY_WALKER_4.get(
					Component.literal(id.getPath()).withStyle(same ? ChatFormatting.YELLOW : ChatFormatting.RED),
					TextFacet.num((int) x),
					TextFacet.num((int) y),
					TextFacet.num((int) z)
			).withStyle(ChatFormatting.GRAY)));
		}
		list.add(CALang.Modular.SKILL.get().withStyle(ChatFormatting.YELLOW));
		list.add(TextFacet.wrap(CALang.Modular.SKILL_CD.get(TextFacet.num(cooldownFactor()))));
		list.add(TextFacet.wrap(CALang.Scroll.SKY_WALKER_2.get()));
		list.add(TextFacet.wrap(CALang.Scroll.SKY_WALKER_3.get()));
	}

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.SKYWALKER_SCROLL.get();
		if (player.isCrouching()) {
			x = player.getX();
			y = player.getY();
			z = player.getZ();
			id = player.level().dimension().location();
			sync(TOKEN.getKey(), this, player);
		} else if (player.level().dimension().location().equals(id) &&
				!player.getCooldowns().isOnCooldown(item)) {
			player.teleportTo(x, y, z);
			player.getCooldowns().addCooldown(item, cooldownFactor() * 20);
		}
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {

	}

	@Override
	public void onSync(@Nullable SkywalkerScroll skywalkerScroll, Player player) {

	}

}
