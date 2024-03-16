package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.feature.ShieldingFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public class EnderProtector implements SingleLineText, ShieldingFeature {

	private static double teleportFactor() {
		return CAModConfig.COMMON.necklace.enderProtectorChance.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Necklace.ENDER_PROTECTOR.get(TextFacet.perc(teleportFactor()));
	}

	@Override
	public void onPlayerBlocked(Player player, ShieldBlockEvent event) {
		Entity attacker = event.getDamageSource().getEntity();
		if (attacker instanceof LivingEntity livingEntity) {
			if (CAAttackToken.chance(player, teleportFactor())) {
				int x = livingEntity.getRandom().nextInt(16);
				int z = livingEntity.getRandom().nextInt(16);
				livingEntity.teleportTo(livingEntity.getX() + x, livingEntity.getY(), livingEntity.getZ() - z);
			}
		}
	}

}
