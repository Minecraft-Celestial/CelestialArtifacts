package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class NetherFire implements SingleLineText, CAAttackToken {

	@Override
	public MutableComponent getLine() {//TODO text
		return Component.translatable("tooltip.celestial_artifacts.nether_fire.shift2");
	}

	@Override
	public void onPlayerAttackTarget(Player player, AttackCache cache) {
		cache.getAttackTarget().setSecondsOnFire(100);
	}

}
