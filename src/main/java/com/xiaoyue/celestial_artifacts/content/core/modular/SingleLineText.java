package com.xiaoyue.celestial_artifacts.content.core.modular;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface SingleLineText extends TextFacet {

	MutableComponent getLine();

	@Override
	default void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(getLine().withStyle(ChatFormatting.GRAY)));
	}
}
