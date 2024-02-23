package com.xiaoyue.celestial_artifacts.content.curios.modular;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface TickFacet extends IFacet {

	void tick(LivingEntity entity, ItemStack stack);

}
