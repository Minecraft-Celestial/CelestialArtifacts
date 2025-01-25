package com.xiaoyue.celestial_artifacts.content.container;

import com.xiaoyue.celestial_artifacts.content.items.item.PotionsBag;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class PotionSlot extends Slot {
	public PotionSlot(Container pContainer, int pSlot, int pX, int pY) {
		super(pContainer, pSlot, pX, pY);
	}

	@Override
	public boolean mayPlace(ItemStack pStack) {
		return PotionsBag.isPotion(pStack);
	}
}
