package com.xiaoyue.celestial_artifacts.content.curios.feature;

import com.xiaoyue.celestial_artifacts.content.curios.modular.IFacet;
import net.minecraft.server.level.ServerPlayer;

public interface SkillFeature extends IFacet {

	void trigger(ServerPlayer player);

}
