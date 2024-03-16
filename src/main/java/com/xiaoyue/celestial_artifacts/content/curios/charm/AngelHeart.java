package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AngelHeart implements MultiLineText, TickFacet, CAAttackToken {

	private static int RemoveInterval() {
		return CAModConfig.COMMON.charm.angelHeartRemoveInterval.get();
	}

	private static int bloodInterval() {
		return CAModConfig.COMMON.charm.angelHeartBloodInterval.get();
	}

	private static int healAmount() {
		return CAModConfig.COMMON.charm.angelHeartHealAmount.get();
	}


	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.ANGEL_HEART_1.get(TextFacet.num(bloodInterval() / 20), TextFacet.num(healAmount()))));
		list.add(TextFacet.wrap(CALang.Charm.ANGEL_HEART_2.get(TextFacet.num(RemoveInterval() / 20))));
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.tickCount % bloodInterval() == 0) {
			entity.heal(healAmount());
		}
		if (entity.tickCount % RemoveInterval() == 0) {
			entity.getActiveEffects().removeIf(ins -> ins.getEffect().getCategory() == MobEffectCategory.HARMFUL);
		}
	}

}
