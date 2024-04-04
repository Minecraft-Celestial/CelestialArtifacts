package com.xiaoyue.celestial_artifacts.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xiaoyue.celestial_artifacts.events.CAMiscCuriosHandler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin extends AbstractContainerMenu {

	protected EnchantmentMenuMixin(@Nullable MenuType<?> pMenuType, int pContainerId) {
		super(pMenuType, pContainerId);
	}

	@ModifyReturnValue(at = {@At("RETURN")}, method = "getEnchantmentList")
	public List<EnchantmentInstance> celestial_artifacts$getEnchantmentList$chaoticPendant(List<EnchantmentInstance> original) {
		var slot = slots.get(20);
		CAMiscCuriosHandler.onEnchTable(slot, original);
		return original;
	}
}
