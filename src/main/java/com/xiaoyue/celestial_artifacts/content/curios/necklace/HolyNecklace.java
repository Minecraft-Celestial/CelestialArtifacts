package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.feature.HealFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class HolyNecklace implements SingleLineText, HealFeature {

	private static int cooldownFactor() {
		return CAModConfig.COMMON.necklace.holyNecklaceCooldown.get();
	}

	private static MobEffectInstance eff() {
		return new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, CAModConfig.COMMON.necklace.holyNecklaceDuration.get() * 20, 0);
	}

	@Override
	public void onPlayerHeal(Player player, LivingHealEvent event) {
		Item item = CAItems.HOLY_NECKLACE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			player.addEffect(eff());
			player.getCooldowns().addCooldown(item, cooldownFactor() * 20);
		}
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Necklace.HOLY.get(EffectFacet.getDesc(eff()), TextFacet.num(cooldownFactor()));
	}

}
