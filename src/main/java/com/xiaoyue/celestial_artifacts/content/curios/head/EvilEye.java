package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class EvilEye implements SingleLineText, TickFacet {

	@Override
	public MutableComponent getLine() {
		return CALang.Head.EVIL_EYE.get();
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
