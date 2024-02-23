package com.xiaoyue.celestial_artifacts.content.curios.feature;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public interface ShieldingFeature {

	void onPlayerBlocked(Player player, ShieldBlockEvent event);

}
