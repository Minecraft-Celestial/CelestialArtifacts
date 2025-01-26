package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.xiaoyue.celestial_artifacts.content.core.token.ClientTokenHelper;
import com.xiaoyue.celestial_artifacts.content.core.token.SetTokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
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

	static MutableComponent item(Item item) {
		return Component.translatable(item.getDescriptionId()).withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent perc(double val) {
		return Component.literal(Math.round(val * 100) + "%").withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent percSmall(double val) {
		return Component.literal(val * 100 + "%").withStyle(ChatFormatting.AQUA);
	}

	static MutableComponent set(@Nullable Level level, SetTokenFacet<?> set) {
		boolean first = true;
		MutableComponent comp = Component.empty();
		boolean all = true;
		for (var e : set.list()) {
			if (first) first = false;
			else comp = comp.append(CALang.Modular.comma().withStyle(ChatFormatting.GRAY));
			boolean has = ClientTokenHelper.pred(level, p -> CurioUtils.hasCurio(p, e.asItem()));
			all &= has;
			comp = comp.append(ClientTokenHelper.disable(has,
					CALang.Modular.item(e.asItem().getDefaultInstance())));
		}
		return CALang.Modular.SET.get(comp).withStyle(all ? ChatFormatting.YELLOW : ChatFormatting.GRAY);
	}

	void addText(@Nullable Level level, List<Component> list);

}
