package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class YellowDuck implements SingleLineText, TickFacet {

	private static final double DEFAULT_DOWN_SPEED = -0.32;

	@Override
	public MutableComponent getLine() {
		return CALang.Head.YELLOW_DUCK.get();
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.isInWater() && !entity.isUnderWater()) {
			if (entity.getDeltaMovement().y < 0) {
				double speed = entity.isCrouching() ? DEFAULT_DOWN_SPEED : 0;
				entity.setDeltaMovement(entity.getDeltaMovement().x, speed, entity.getDeltaMovement().z);
			}
		}
	}
}
