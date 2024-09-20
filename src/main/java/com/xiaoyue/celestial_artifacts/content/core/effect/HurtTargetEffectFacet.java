package com.xiaoyue.celestial_artifacts.content.core.effect;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public record HurtTargetEffectFacet(
		@Nullable DoubleSupplier chance,
		Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier
) implements SingleLineText, CAAttackToken {

	public static HurtTargetEffectFacet of(
			DoubleSupplier chance, Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier) {
		return new HurtTargetEffectFacet(chance, eff, duration, amplifier);
	}

	public static HurtTargetEffectFacet of(Supplier<MobEffect> eff, IntSupplier duration, IntSupplier amplifier) {
		return new HurtTargetEffectFacet(null, eff, duration, amplifier);
	}

	public MobEffectInstance get() {
		return new MobEffectInstance(eff.get(), duration.getAsInt() * 20, amplifier.getAsInt());
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (chance == null || CAAttackToken.chance(player, chance.getAsDouble())) {
			cache.getAttackTarget().addEffect(new MobEffectInstance(eff.get(), duration.getAsInt() * 20, amplifier.getAsInt()));
		}
	}

	@Override
	public MutableComponent getLine() {
		double ch = chance == null ? 1 : chance.getAsDouble();
		if (ch >= 1) {
			return CALang.Modular.EFFECT_INFLICT.get().withStyle(ChatFormatting.GRAY)
					.append(EffectFacet.getDesc(get(), true));
		} else {
			var che = TextFacet.perc(ch);
			return CALang.Modular.EFFECT_INFLICT_CHANCE.get(che).withStyle(ChatFormatting.GRAY)
					.append(EffectFacet.getDesc(get(), true));
		}
	}

}
