package com.xiaoyue.celestial_artifacts.content.curios.modular;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public interface TextFacet extends IFacet {

	static SingleLineText line(Supplier<Component> comp) {
		return comp::get;
	}

	void addText(@Nullable Level level, List<Component> list, boolean multiLine);

}
