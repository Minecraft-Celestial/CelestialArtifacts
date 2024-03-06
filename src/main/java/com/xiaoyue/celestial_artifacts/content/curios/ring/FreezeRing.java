package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;

public class FreezeRing implements SingleLineText, CAAttackToken {

	@Override
	public MutableComponent getLine() {//TODO text
		return Component.translatable("tooltip.celestial_artifacts.freeze_ring.shift1");
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = CAAttackToken.getSource(cache);
		return source.is(DamageTypeTags.IS_FREEZING) &&
				!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) &&
				!source.is(DamageTypeTags.BYPASSES_EFFECTS);
	}

}
