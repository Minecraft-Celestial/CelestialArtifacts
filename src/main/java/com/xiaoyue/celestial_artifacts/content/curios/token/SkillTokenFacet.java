package com.xiaoyue.celestial_artifacts.content.curios.token;

import com.xiaoyue.celestial_artifacts.content.curios.feature.SkillFeature;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import net.minecraft.server.level.ServerPlayer;

public record SkillTokenFacet<T extends BaseTickingToken & SkillFeature>(TokenFacet<T> facet) implements SkillFeature {
	@Override
	public void trigger(ServerPlayer player) {
		var data = ConditionalData.HOLDER.get(player).getData(facet.getKey());
		if (data != null) {
			data.trigger(player);
		}
	}
}
