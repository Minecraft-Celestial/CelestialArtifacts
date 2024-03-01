package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.feature.HealFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

public class HolyNecklace implements SingleLineText, HealFeature {

	@Override
	public void onPlayerHeal(Player player, LivingHealEvent event) {
		Item item = CAItems.HOLY_NECKLACE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 30, 0);
			player.getCooldowns().addCooldown(item, 60);
		}
	}

	@Override
	public Component getLine() {
		return Component.translatable("tooltip.celestial_artifacts.holy_necklace.shift2");
	}

}
