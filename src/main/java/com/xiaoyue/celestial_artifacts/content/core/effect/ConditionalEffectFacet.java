package com.xiaoyue.celestial_artifacts.content.core.effect;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.ClientTokenHelper;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record ConditionalEffectFacet(
		boolean toggleText,
		Predicate<Player> pred,
		Supplier<MutableComponent> text,
		List<EffectFacet> effs)
		implements TickFacet, TextFacet {

	public static ConditionalEffectFacet of(
			boolean toggleText,
			Predicate<Player> pred,
			Supplier<MutableComponent> text,
			EffectFacet... effs) {
		return new ConditionalEffectFacet(toggleText, pred, text, List.of(effs));
	}

	public static MutableComponent append(MutableComponent effLine, boolean met, List<EffectFacet> effs) {
		var base = met ? ChatFormatting.GRAY : ChatFormatting.DARK_GRAY;
		boolean first = true;
		for (var e : effs) {
			if (first) first = false;
			else effLine = effLine.append(CALang.Modular.comma().withStyle(base));
			effLine = effLine.append(ClientTokenHelper.disable(met,
					EffectFacet.getDesc(e.get(), true)));
		}
		return effLine;
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		boolean met = !toggleText || ClientTokenHelper.pred(level, pred);
		var base = met ? ChatFormatting.GRAY : ChatFormatting.DARK_GRAY;
		list.add(TextFacet.wrap(text.get().withStyle(base)));
		var effLine = CALang.Modular.EFFECT_REFRESH.get().withStyle(base);
		list.add(TextFacet.inner(append(effLine, met, effs)));
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.level().isClientSide()) return;
		if (entity instanceof Player player && pred.test(player)) {
			for (var e : effs) {
				e.tick(player, stack);
			}
		}
	}

}
