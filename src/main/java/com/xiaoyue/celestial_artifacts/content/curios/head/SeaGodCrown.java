package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeaGodCrown implements MultiLineText, SkillFeature {

	private static int cd() {
		return CAModConfig.COMMON.head.seaGodCrownCoolDown.get();
	}

	@Override
	public void trigger(ServerPlayer player) {
		if (!player.serverLevel().isRaining()) {
			if (!player.getCooldowns().isOnCooldown(CAItems.SEA_GOD_CROWN.get())) {
				player.serverLevel().setWeatherParameters(0, 20000, true, true);
				player.getCooldowns().addCooldown(CAItems.SEA_GOD_CROWN.get(), cd() * 20);
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(CALang.Modular.SKILL.get().withStyle(ChatFormatting.YELLOW));
		list.add(TextFacet.wrap(CALang.Modular.SKILL_CD.get(TextFacet.num(cd()))));
		list.add(TextFacet.wrap(CALang.Head.SEA_GOD_CROWN.get()));
	}

}
