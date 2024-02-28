package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.SlotContext;

public class FreezeRing implements SingleLineText, CAAttackToken {

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.freeze_ring.shift1");
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = getSource(cache);
		return source.is(DamageTypeTags.IS_FREEZING) &&
				!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) &&
				!source.is(DamageTypeTags.BYPASSES_EFFECTS);
	}

}
