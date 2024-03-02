package com.xiaoyue.celestial_artifacts.content.core.feature;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public interface ShieldingFeature extends IFeature {

	void onPlayerBlocked(Player player, ShieldBlockEvent event);

}
