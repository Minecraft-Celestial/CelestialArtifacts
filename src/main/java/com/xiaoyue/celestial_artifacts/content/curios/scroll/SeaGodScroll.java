package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

public class SeaGodScroll implements SingleLineText, BreakSpeedFeature {

	@Override
	public double getBreakFactor(Player player) {
		if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
			return 5;
		}
		return 1;
	}

	@Override
	public MutableComponent getLine() {
		return Component.translatable("tooltip.celestial_artifacts.sea_god_scroll.shift2");
	}

}
