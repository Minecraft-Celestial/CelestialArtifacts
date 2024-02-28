package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NetheriteRing implements SingleLineText, CAAttackToken {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.netherite_ring.shift2");
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (cache.getAttackTarget().level().dimension().equals(Level.NETHER)) {
			cache.addDealtModifier(DamageModifier.multTotal(0.9f));
		}
	}

}
