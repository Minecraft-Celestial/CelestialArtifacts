package com.xiaoyue.celestial_artifacts.content.core.token;

import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.UUID;

public record SlotAdder(String name, String slotId, UUID slot, int slotCount) implements ISubToken, IAttrAdder {

	public static SlotAdder of(String name, String id, int count) {
		return new SlotAdder(name + "_bonus", id, MathHelper.getUUIDFromString(name + "_" + id), count);
	}

	public void tickImpl(Player player) {
		if (player.level().isClientSide()) return;
		var opt = CuriosApi.getCuriosInventory(player).resolve()
				.flatMap(x -> x.getStacksHandler(slotId));
		if (opt.isEmpty()) return;
		var old = opt.get().getModifiers().get(slot);
		if (old == null || old.getAmount() != slotCount) {
			opt.get().removeModifier(slot);
			opt.get().addPermanentModifier(new AttributeModifier(slot, name,
					slotCount, AttributeModifier.Operation.ADDITION));
		}
	}

	public void removeImpl(Player player) {
		if (player.level().isClientSide()) return;
		var opt = CuriosApi.getCuriosInventory(player).resolve()
				.flatMap(x -> x.getStacksHandler(slotId));
		if (opt.isEmpty()) return;
		opt.get().removeModifier(slot);
	}

	public MutableComponent getTooltip() {
		return Component.translatable(
				"attribute.modifier.plus." + AttributeModifier.Operation.ADDITION.toValue(),
				ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(slotCount),
				Component.translatable("curios.identifier." + slotId));
	}

}
