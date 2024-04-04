package com.xiaoyue.celestial_artifacts.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.xiaoyue.celestial_artifacts.events.CAMiscCuriosHandler;
import dev.shadowsoffire.apotheosis.ench.table.ApothEnchantmentMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ApothEnchantmentMenu.class)
public abstract class ApothEnchantmentMenuMixin extends EnchantmentMenu {

	public ApothEnchantmentMenuMixin(int pContainerId, Inventory pPlayerInventory) {
		super(pContainerId, pPlayerInventory);
	}

	@ModifyReturnValue(at = @At("RETURN"), method = "getEnchantmentList")
	public List<EnchantmentInstance> celestial_artifacts$apoth_getEnchantmentList$chaoticPendant(List<EnchantmentInstance> original) {
		var slot = slots.get(20);
		CAMiscCuriosHandler.onEnchTable(slot, original);
		return original;
	}

}
