package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class GallopNecklace implements SingleLineText, CAAttackToken {

	private static double factor() {
		return CAModConfig.COMMON.necklace.gallopNecklaceDamageFactor.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Necklace.GALLOP.get(TextFacet.perc(factor()));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		cache.addHurtModifier(DamageModifier.add(player.getSpeed() * (float) factor()));
	}

}
