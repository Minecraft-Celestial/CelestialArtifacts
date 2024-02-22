package com.xiaoyue.celestial_artifacts.content.modular;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

public record SlotFacet(String slot, int count) implements IFacet {

	public void modify(UUID uuid, Multimap<Attribute, AttributeModifier> ans) {
		CuriosApi.addSlotModifier(ans, slot, uuid, count, AttributeModifier.Operation.ADDITION);
	}

}
