package com.xiaoyue.celestial_artifacts.content.curios.feature;

import com.xiaoyue.celestial_artifacts.content.curios.modular.IFacet;
import net.minecraft.world.entity.player.Player;

public interface BreakSpeedFeature extends IFacet {

	static BreakSpeedFeature simple(double val) {
		return p -> val;
	}

	double getBreakFactor(Player player);

}
