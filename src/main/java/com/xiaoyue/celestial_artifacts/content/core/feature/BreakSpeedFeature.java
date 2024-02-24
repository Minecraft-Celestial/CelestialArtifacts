package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.modular.IFacet;
import net.minecraft.world.entity.player.Player;

public interface BreakSpeedFeature extends IFacet {

	static BreakSpeedFeature simple(double val) {
		return p -> val;
	}

	double getBreakFactor(Player player);

}
