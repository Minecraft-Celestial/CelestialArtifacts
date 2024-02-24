package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2library.base.effects.EffectUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * duration and period are in seconds
 */
public record EffectFacet(Supplier<MobEffect> eff, int duration, int amplifier, int period)
		implements TickFacet, SingleLineText {

	public static EffectFacet of(Supplier<MobEffect> eff, int duration, int amplifier, int period) {
		return new EffectFacet(eff, duration, amplifier, period);
	}

	@Override
	public Component getLine() {
		MobEffectInstance ins = get();
		MutableComponent desc = Component.translatable(ins.getDescriptionId());
		if (ins.getAmplifier() > 0) {
			desc = Component.translatable("potion.withAmplifier", desc,
					Component.translatable("potion.potency." + ins.getAmplifier()));
		}
		desc = desc.withStyle(ins.getEffect().getCategory().getTooltipFormatting());
		MutableComponent ans;
		if (period <= duration) {
			ans = CALang.Modular.EFFECT_REFRESH.get().withStyle(ChatFormatting.GRAY);
		} else {
			ans = CALang.Modular.EFFECT_FLASH.get(period).withStyle(ChatFormatting.GRAY);
		}
		return ans.append(desc);
	}

	public MobEffectInstance get() {
		return new MobEffectInstance(eff.get(), duration * 20, amplifier, true, true);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (period <= duration || entity.tickCount % (period * 20) == 0) {
			EffectUtil.refreshEffect(entity, get(), EffectUtil.AddReason.SELF, entity);
		}
	}

}
