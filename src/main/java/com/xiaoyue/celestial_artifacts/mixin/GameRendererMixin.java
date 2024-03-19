package com.xiaoyue.celestial_artifacts.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

	@WrapOperation(method = "getNightVisionScale", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;endsWithin(I)Z"))
	private static boolean celestial_artifacts$endsWithin$ambient(MobEffectInstance instance, int pDuration, Operation<Boolean> original) {
		return !instance.isAmbient() && original.call(instance, pDuration);
	}

}
