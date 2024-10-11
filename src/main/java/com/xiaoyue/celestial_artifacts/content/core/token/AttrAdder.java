package com.xiaoyue.celestial_artifacts.content.core.token;

import dev.xkmc.l2damagetracker.contents.curios.AttrTooltip;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public record AttrAdder(String name, Supplier<Attribute> attr, UUID uuid,
						AttributeModifier.Operation op, DoubleSupplier value)
		implements ISubToken, IAttrAdder {

	public static AttrAdder of(String name, Supplier<Attribute> id, AttributeModifier.Operation op, double value) {
		return new AttrAdder(name, id, MathHelper.getUUIDFromString(name), op, () -> value);
	}

	public static AttrAdder of(String name, Supplier<Attribute> id, AttributeModifier.Operation op, DoubleSupplier value) {
		return new AttrAdder(name, id, MathHelper.getUUIDFromString(name), op, value);
	}

	public void tickImpl(Player player) {
		addAttr(player);
	}

	public void addAttr(LivingEntity player) {
		if (player.level().isClientSide()) return;
		double val = value.getAsDouble();
		var ins = player.getAttribute(attr.get());
		if (ins == null) return;
		var mod = ins.getModifier(uuid);
		if (mod == null || mod.getOperation() != op || mod.getAmount() != val) {
			ins.removeModifier(uuid);
			ins.addTransientModifier(new AttributeModifier(uuid, name, val, op));
		}
	}

	public void removeImpl(Player player) {
		if (player.level().isClientSide()) return;
		var ins = player.getAttribute(attr.get());
		if (ins == null) return;
		ins.removeModifier(uuid);
	}

	public MutableComponent getTooltip() {
		return AttrTooltip.getDesc(attr.get(), value.getAsDouble(), op());
	}

	public MutableComponent getText(double val) {
		return AttrTooltip.getText(attr.get(), val, op());
	}

}
