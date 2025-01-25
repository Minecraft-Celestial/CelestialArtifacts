package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_core.events.LivingJumpEvent;
import net.minecraft.world.entity.player.Player;

public interface JumpFeature extends IFeature {

	void onJump(Player player, LivingJumpEvent event);

}
