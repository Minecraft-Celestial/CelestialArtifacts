package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EmeraldNecklace implements SingleLineText, CAAttackToken {

	private static double dropFactor() {
		return CAModConfig.COMMON.necklace.emeraldNecklaceDrop.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Necklace.EMERALD.get(TextFacet.perc(dropFactor()));
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		float luck = player.getLuck();
		if (CAAttackToken.chance(player, dropFactor() * (1 + luck))) {
			event.getEntity().spawnAtLocation(Items.EMERALD);
		}
	}

}
