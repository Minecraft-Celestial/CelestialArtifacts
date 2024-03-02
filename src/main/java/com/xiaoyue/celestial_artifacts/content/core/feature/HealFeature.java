package com.xiaoyue.celestial_artifacts.content.core.feature;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public interface HealFeature extends IFeature {

	void onPlayerHeal(Player player, LivingHealEvent event);

}
