package com.xiaoyue.celestial_artifacts.content.core.effect;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record HurtPlayerEffectFacet(
		@Nullable Predicate<Player> pred,
		@Nullable Predicate<DamageSource> type,
		@Nullable Supplier<MutableComponent> text,
		@Nullable DoubleSupplier chance,
		List<EffectFacet> effs
) implements TextFacet, CAAttackToken {

	public static HurtPlayerEffectFacet of(
			Predicate<Player> pred,
			Supplier<MutableComponent> text,
			DoubleSupplier chance,
			EffectFacet eff) {
		return new HurtPlayerEffectFacet(pred, null, text, chance,
				List.of(eff));
	}

	public static HurtPlayerEffectFacet ofType(
			Predicate<DamageSource> pred,
			Supplier<MutableComponent> text,
			EffectFacet eff) {
		return new HurtPlayerEffectFacet(null, pred, text, null,
				List.of(eff));
	}

	public static HurtPlayerEffectFacet of(EffectFacet... effs) {
		return new HurtPlayerEffectFacet(null, null, null, null, List.of(effs));
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (chance == null || CAAttackToken.chance(player, chance.getAsDouble())) {
			for (var e : effs) {
				player.addEffect(e.get());
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		double ch = chance == null ? 1 : chance.getAsDouble();
		MutableComponent base;
		if (ch >= 1) {
			base = CALang.Modular.EFFECT_HURT.get().withStyle(ChatFormatting.GRAY);
		} else {
			var che = TextFacet.perc(ch);
			base = CALang.Modular.EFFECT_HURT_CHANCE.get(che).withStyle(ChatFormatting.GRAY);
		}
		base = ConditionalEffectFacet.append(base, true, effs);
		if (pred != null && text != null) {
			var cond = text.get();
			list.add(TextFacet.wrap(cond.withStyle(ChatFormatting.GRAY)));
			list.add(TextFacet.inner(base));
		} else {
			list.add(TextFacet.wrap(base));
		}
	}

}
