package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;
import java.util.function.IntSupplier;

public record SlotFacet(String slot, IntSupplier count) implements IFacet {

	public static SlotFacet of(String slot, int amount) {
		return new SlotFacet(slot, () -> amount);
	}

	public static SlotFacet of(String slot, IntSupplier amount) {
		return new SlotFacet(slot, amount);
	}

	public void modify(UUID uuid, Multimap<Attribute, AttributeModifier> ans) {
		CuriosApi.addSlotModifier(ans, slot, uuid, count.getAsInt(), AttributeModifier.Operation.ADDITION);
	}

}
