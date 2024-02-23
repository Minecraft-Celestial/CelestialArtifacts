package com.xiaoyue.celestial_artifacts.content.curios.feature;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public interface HealFeature {

	void onPlayerHeal(Player player, LivingHealEvent event);

}
