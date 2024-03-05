package com.xiaoyue.celestial_artifacts.content.core.modular;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public interface TextFacet extends IFacet {

	static SingleLineText line(Supplier<MutableComponent> comp) {
		return comp::get;
	}

	static MutableComponent wrap(MutableComponent inner) {
		return Component.literal("· ").withStyle(ChatFormatting.GRAY).append(inner);
	}

	static MutableComponent inner(MutableComponent inner) {
		return Component.literal("-> ").withStyle(ChatFormatting.GRAY).append(inner);
	}

	static MutableComponent num(int val) {
		return Component.literal("" + val).withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent eff(MobEffect eff) {
		return eff.getDisplayName().copy().withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent perc(double val) {
		return Component.literal(Math.round(val * 100) + "%").withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent percSmall(double val) {
		return Component.literal(val * 100 + "%").withStyle(ChatFormatting.AQUA);
	}

	void addText(@Nullable Level level, List<Component> list);

}
