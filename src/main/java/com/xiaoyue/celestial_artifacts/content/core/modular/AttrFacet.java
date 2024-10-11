package com.xiaoyue.celestial_artifacts.content.core.modular;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

public record AttrFacet(Supplier<Attribute> attr, DoubleSupplier val,
						AttributeModifier.Operation op) implements IFacet {

	public static AttrFacet add(Supplier<Attribute> attr, DoubleSupplier val) {
		return new AttrFacet(attr, val, AttributeModifier.Operation.ADDITION);
	}

	public static AttrFacet multBase(Supplier<Attribute> attr, DoubleSupplier val) {
		return new AttrFacet(attr, val, AttributeModifier.Operation.MULTIPLY_BASE);
	}

	public static AttrFacet multTotal(Supplier<Attribute> attr, DoubleSupplier val) {
		return new AttrFacet(attr, val, AttributeModifier.Operation.MULTIPLY_TOTAL);
	}

	public void modify(UUID uuid, String id, Multimap<Attribute, AttributeModifier> ans) {
		ans.put(attr.get(), new AttributeModifier(uuid, id, val.getAsDouble(), op));
	}

	public static MutableComponent simpleAdd(Component text, int val) {
		MutableComponent base = Component.literal(val < 0 ? "-" : "+");
		base.append(ATTRIBUTE_MODIFIER_FORMAT.format(Math.abs(val)));
		base.append(" ");
		base.append(text);
		return base.withStyle(val < 0 ? ChatFormatting.RED : ChatFormatting.BLUE);
	}

	public static MutableComponent simpleMult(Component text, double val) {
		MutableComponent base = Component.literal(val < 0 ? "-" : "+");
		base.append(ATTRIBUTE_MODIFIER_FORMAT.format(Math.abs(val * 100)));
		base.append("% ");
		base.append(text);
		return base.withStyle(val < 0 ? ChatFormatting.RED : ChatFormatting.BLUE);
	}

	public static MutableComponent textMult(Component text, double val) {
		MutableComponent base = Component.literal(val < 0 ? "-" : "+");
		base.append(ATTRIBUTE_MODIFIER_FORMAT.format(Math.abs(val * 100)));
		base.append("%");
		base = Component.empty().append(base.withStyle(ChatFormatting.AQUA)).append(" ");
		base.append(text);
		return base.withStyle(ChatFormatting.GRAY);
	}

}
