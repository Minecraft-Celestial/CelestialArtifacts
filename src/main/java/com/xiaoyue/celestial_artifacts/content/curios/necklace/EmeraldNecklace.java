package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EmeraldNecklace implements SingleLineText, CAAttackToken {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.emerald_necklace.shift2");
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		float luck = player.getLuck();
		if (CAAttackToken.chance(player, 0.02 + (luck * 0.02))) {
			event.getEntity().spawnAtLocation(net.minecraft.world.item.Items.EMERALD);
		}
	}

}
