package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class TwistedBrain implements SingleLineText, CAAttackToken {

	@Override
	public MutableComponent getLine() {
		return Component.translatable("tooltip.celestial_artifacts.twisted_brain.shift1");
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = CAAttackToken.getSource(cache);
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ||
				source.is(DamageTypeTags.BYPASSES_EFFECTS))
			return false;
		if (CAAttackToken.chance(player, 0.17)) {
			EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 100, 1);
			return true;
		}
		return false;
	}
}
