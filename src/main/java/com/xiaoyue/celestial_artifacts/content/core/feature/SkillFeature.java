package com.xiaoyue.celestial_artifacts.content.core.feature;

import net.minecraft.server.level.ServerPlayer;

public interface SkillFeature extends IFeature {

	void trigger(ServerPlayer player);

}
