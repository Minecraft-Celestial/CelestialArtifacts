package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EvilEye implements SingleLineText, TickFacet {
	@Override
	public MutableComponent getLine() {//TODO text
		return Component.translatable("tooltip.celestial_artifacts.evil_eye.shift1");
	}

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (player.hasEffect(MobEffects.DARKNESS)) {
			player.removeEffect(MobEffects.DARKNESS);
		} else if (player.hasEffect(MobEffects.BLINDNESS)) {
			player.removeEffect(MobEffects.BLINDNESS);
		}
	}

}
