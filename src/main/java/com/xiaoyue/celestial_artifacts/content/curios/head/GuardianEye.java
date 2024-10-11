package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuardianEye implements TickFacet, MultiLineText, CAAttackToken {

	private static MobEffectInstance eff() {
		return new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Head.GUARDIAN_EYE_1.get(
				EffectFacet.getDesc(eff(), false)
		).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Head.GUARDIAN_EYE_2.get().withStyle(ChatFormatting.GRAY)));
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!entity.level().isClientSide()) {
			if (entity.tickCount % 20 == 0) {
				List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(entity, 16, 4, e -> e instanceof Enemy);
				for (LivingEntity target : entities) {
					target.addEffect(eff());
				}
			}
			if (entity.isUnderWater()) {
				entity.setAirSupply(1);
			}
		}
	}
}
