package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.data.CALang;
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
		return CALang.Scroll.SEA_GOD.get();
	}

}
