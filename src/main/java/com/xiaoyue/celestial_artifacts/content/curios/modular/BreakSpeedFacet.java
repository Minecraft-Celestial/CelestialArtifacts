package com.xiaoyue.celestial_artifacts.content.curios.modular;

import net.minecraft.world.entity.player.Player;

public interface BreakSpeedFacet extends IFacet {

	static BreakSpeedFacet simple(double val) {
		return p -> val;
	}

	double getBreakFactor(Player player);

}
