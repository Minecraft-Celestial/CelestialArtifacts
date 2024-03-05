package com.xiaoyue.celestial_artifacts.content.core.feature;

import net.minecraft.world.entity.player.Player;

import java.util.function.DoubleSupplier;

public interface BreakSpeedFeature extends IFeature {

	static BreakSpeedFeature simple(DoubleSupplier val) {
		return p -> val.getAsDouble();
	}

	double getBreakFactor(Player player);

}
