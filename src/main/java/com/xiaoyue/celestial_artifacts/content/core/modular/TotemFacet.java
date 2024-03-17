package com.xiaoyue.celestial_artifacts.content.core.modular;

import dev.xkmc.l2damagetracker.contents.curios.TotemHelper;
import dev.xkmc.l2damagetracker.contents.curios.TotemUseToClient;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public interface TotemFacet extends IFacet {

	@OverridingMethodsMustInvokeSuper
	default void trigger(Player self, ItemStack stack, TotemHelper.TotemSlot slot, DamageSource source) {
		L2DamageTracker.PACKET_HANDLER.toTrackingPlayers(new TotemUseToClient(self, stack), self);
		self.setHealth(1);
		self.removeAllEffects();
		self.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
		self.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
		self.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
	}

	@OverridingMethodsMustInvokeSuper
	default boolean allow(Player player, ItemStack stack, DamageSource source) {
		return !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) &&
				!player.getCooldowns().isOnCooldown(stack.getItem());
	}

}
