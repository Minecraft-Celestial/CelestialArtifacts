package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuardianEye implements TickFacet, MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift4");
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!entity.level().isClientSide()) {
			if (entity.tickCount % 20 == 0) {
				List<LivingEntity> entities = EntityUtils.getDelimitedMonster(entity, 16);
				for (LivingEntity target : entities) {
					EntityUtils.addEct(target, MobEffects.DIG_SLOWDOWN, 100, 1);
				}
			}
			if (entity.isUnderWater()) {
				entity.setAirSupply(1);
			}
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (player.isUnderWater()) {
			cache.addDealtModifier(DamageModifier.multTotal(0.8f));//TODO use module
		}
	}

}
