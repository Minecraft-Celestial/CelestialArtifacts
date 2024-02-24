package com.xiaoyue.celestial_artifacts.content.core.token;

import net.minecraft.world.entity.player.Player;

public interface ISubToken {

	void removeImpl(Player player);

	void tickImpl(Player player);

}
