package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TotemFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.curios.TotemHelper;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UndeadCharm implements SingleLineText, TotemFacet {

	private static int cooldownFactor() {
		return CAModConfig.COMMON.charm.undeadCharmCooldown.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Charm.UNDEAD_CHARM.get(TextFacet.num(cooldownFactor()));
	}

	@Override
	public void trigger(Player player, ItemStack stack, TotemHelper.TotemSlot slot, DamageSource source) {
		TotemFacet.super.trigger(player, stack, slot, source);
		player.getCooldowns().addCooldown(stack.getItem(), cooldownFactor() * 20);
	}

}
