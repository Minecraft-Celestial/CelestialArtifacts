package com.xiaoyue.celestial_artifacts.content.core.attack;

import com.xiaoyue.celestial_artifacts.content.core.modular.IFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SimpleListener {

	public static IFacet hurtBonus(
			Supplier<MutableComponent> text,
			HurtBonusPredicate condition,
			DoubleSupplier bonus) {
		return new HurtBonus(text, condition, bonus);
	}

	public static IFacet protect(
			Supplier<MutableComponent> text,
			ProtectPredicate condition,
			DoubleSupplier bonus) {
		return new Protection(text, condition, bonus);
	}

	public static IFacet protectType(
			Supplier<MutableComponent> text,
			Predicate<DamageSource> condition,
			DoubleSupplier bonus) {
		return new ProtectionType(text, condition, bonus);
	}

	public interface HurtBonusPredicate {

		boolean test(Player player, LivingEntity target, AttackCache cache);

	}

	public interface ProtectPredicate {

		boolean test(Player player, LivingEntity attacker, AttackCache cache);

	}

	record HurtBonus(
			Supplier<MutableComponent> text,
			HurtBonusPredicate condition,
			DoubleSupplier bonus)
			implements TextFacet, CAAttackToken {

		@Override
		public void addText(@Nullable Level level, List<Component> list) {
			list.add(TextFacet.wrap(text.get()));
			list.add(TextFacet.inner(CALang.Modular.HURT_BONUS
					.get(TextFacet.perc(bonus.getAsDouble())).withStyle(ChatFormatting.GRAY)));
		}

		@Override
		public void onPlayerHurtTarget(Player player, AttackCache cache) {
			if (condition.test(player, cache.getAttackTarget(), cache)) {
				cache.addHurtModifier(DamageModifier.multTotal((float) (1 + bonus.getAsDouble())));
			}
		}
	}

	record Protection(
			Supplier<MutableComponent> text,
			ProtectPredicate condition,
			DoubleSupplier bonus)
			implements TextFacet, CAAttackToken {

		@Override
		public void addText(@Nullable Level level, List<Component> list) {
			list.add(TextFacet.wrap(text.get()));
			list.add(TextFacet.inner(CALang.Modular.PROTECT
					.get(TextFacet.perc(bonus.getAsDouble())).withStyle(ChatFormatting.GRAY)));
		}

		@Override
		public void onPlayerHurtTarget(Player player, AttackCache cache) {
			if (condition.test(player, cache.getAttackTarget(), cache)) {
				cache.addHurtModifier(DamageModifier.multTotal((float) (1 - bonus.getAsDouble())));
			}
		}
	}

	record ProtectionType(
			Supplier<MutableComponent> text,
			Predicate<DamageSource> condition,
			DoubleSupplier bonus)
			implements TextFacet, CAAttackToken {

		@Override
		public void addText(@Nullable Level level, List<Component> list) {
			list.add(TextFacet.wrap(CALang.Modular.PROTECT_TYPE
					.get(text.get(), TextFacet.perc(bonus.getAsDouble()))
					.withStyle(ChatFormatting.GRAY)));
		}

		@Override
		public void onPlayerHurtTarget(Player player, AttackCache cache) {
			if (condition.test(CAAttackToken.getSource(cache))) {
				cache.addHurtModifier(DamageModifier.multTotal((float) (1 - bonus.getAsDouble())));
			}
		}

	}

}
