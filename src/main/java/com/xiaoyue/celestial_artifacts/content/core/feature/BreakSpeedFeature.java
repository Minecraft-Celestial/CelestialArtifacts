package com.xiaoyue.celestial_artifacts.content.core.feature;

import net.minecraft.world.entity.player.Player;

public interface BreakSpeedFeature extends IFeature {

	static BreakSpeedFeature simple(double val) {
		return p -> val;
	}

	double getBreakFactor(Player player);

}
