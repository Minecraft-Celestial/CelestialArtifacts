package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class TwistedBrain implements SingleLineText, CAAttackToken {

	private static double chance() {
		return CAModConfig.COMMON.charm.twistedbrainAvoidChance.get();
	}

	private static int duration() {
		return CAModConfig.COMMON.charm.twistedBrainEffectDuration.get();
	}

	private static MobEffectInstance eff() {
		return new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration() * 20, 1);
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Charm.TWISTED_BRAIN.get(TextFacet.perc(chance()), EffectFacet.getDesc(eff()));
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = CAAttackToken.getSource(cache);
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ||
				source.is(DamageTypeTags.BYPASSES_EFFECTS))
			return false;
		if (CAAttackToken.chance(player, chance())) {
			player.addEffect(eff());
			return true;
		}
		return false;
	}
}
