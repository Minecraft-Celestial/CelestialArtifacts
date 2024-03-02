package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AngelHeart implements MultiLineText, TickFacet, CAAttackToken {


	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_heart.shift1",
				ChatFormatting.GOLD, CAModConfig.COMMON.charm.angelHeartBloodInterval.get() / 20);
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_heart.shift2",
				ChatFormatting.GOLD, CAModConfig.COMMON.charm.angelHeartRemoveInterval.get() / 20);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.tickCount % CAModConfig.COMMON.charm.angelHeartBloodInterval.get() == 0) {
			entity.heal(CAModConfig.COMMON.charm.angelHeartHealAmount.get());
		}
		if (entity.tickCount % CAModConfig.COMMON.charm.angelHeartRemoveInterval.get() == 0) {
			entity.getActiveEffects().removeIf(ins -> ins.getEffect().getCategory() == MobEffectCategory.HARMFUL);
		}
	}

}
