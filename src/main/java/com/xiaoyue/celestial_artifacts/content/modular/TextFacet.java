package com.xiaoyue.celestial_artifacts.content.modular;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TextFacet extends IFacet {

	void addText(@Nullable Level level, List<Component> list, boolean multiLine);

}
