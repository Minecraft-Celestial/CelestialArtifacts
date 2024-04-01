package com.xiaoyue.celestial_artifacts.content.core.effect;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.ClientTokenHelper;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2library.base.effects.EffectUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * duration and period are in seconds
 */
public record EffectFacet(Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier, IntSupplier period)
		implements TickFacet, SingleLineText {

	public static EffectFacet of(Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier, IntSupplier period) {
		return new EffectFacet(eff, duration, amplifier, period);
	}

	public static EffectFacet of(Supplier<MobEffect> eff) {
		return of(eff, () -> 2, () -> 0, () -> 0);
	}

	public static EffectFacet of(Supplier<MobEffect> eff, int duration, int amplifier) {
		return of(eff, () -> duration, () -> amplifier, () -> 0);
	}

	public static EffectFacet of(Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier) {
		return of(eff, duration, amplifier, () -> 0);
	}

	public static MutableComponent getDesc(MobEffectInstance ins) {
		return getDesc(ins, true);
	}

	public static MutableComponent getDesc(MobEffectInstance ins, boolean showDuration) {
		MutableComponent desc = Component.translatable(ins.getDescriptionId());
		if (ins.getAmplifier() > 0) {
			desc = Component.translatable("potion.withAmplifier", desc,
					Component.translatable("potion.potency." + ins.getAmplifier()));
		}
		if (showDuration && !ins.endsWithin(20)) {
			desc = Component.translatable("potion.withDuration", desc, MobEffectUtil.formatDuration(ins, 1));
		}

		return desc.withStyle(ins.getEffect().getCategory().getTooltipFormatting());
	}

	@Override
	public MutableComponent getLine() {
		return getLine(true);
	}

	MutableComponent getLine(boolean enable) {
		MobEffectInstance ins = get();
		var bad = ChatFormatting.DARK_GRAY;
		ChatFormatting base = enable ? ChatFormatting.GRAY : bad;
		if (period.getAsInt() <= duration.getAsInt()) {
			return CALang.Modular.EFFECT_REFRESH.get().withStyle(base)
					.append(ClientTokenHelper.disable(enable, getDesc(ins, false)));
		} else {
			var p = ClientTokenHelper.disable(enable, TextFacet.num(period.getAsInt()));
			return CALang.Modular.EFFECT_FLASH.get(p).withStyle(base).append(
					ClientTokenHelper.disable(enable, getDesc(ins)));
		}
	}

	public MobEffectInstance get() {
		return new MobEffectInstance(eff.get(), duration.getAsInt() * 20, amplifier.getAsInt(), true, true);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.level().isClientSide()) return;
		if (period.getAsInt() <= duration.getAsInt() || entity.tickCount % (period.getAsInt() * 20) == 0) {
			EffectUtil.refreshEffect(entity, get(), EffectUtil.AddReason.SELF, entity);
		}
	}

}
