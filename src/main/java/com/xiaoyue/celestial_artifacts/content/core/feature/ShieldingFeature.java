package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.modular.IFacet;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public interface ShieldingFeature extends IFacet {

	void onPlayerBlocked(Player player, ShieldBlockEvent event);

}
