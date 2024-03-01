package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.feature.ShieldingFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public class EnderProtector implements SingleLineText, ShieldingFeature {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.ender_protector.shift3");
	}

	@Override
	public void onPlayerBlocked(Player player, ShieldBlockEvent event) {
		Entity attacker = event.getDamageSource().getEntity();
		if (attacker instanceof LivingEntity livingEntity) {
			if (CAAttackToken.chance(player, 0.5)) {
				int x = livingEntity.getRandom().nextInt(16);
				int z = livingEntity.getRandom().nextInt(16);
				livingEntity.teleportTo(livingEntity.getX() + x, livingEntity.getY(), livingEntity.getZ() - z);
			}
		}
	}

}
