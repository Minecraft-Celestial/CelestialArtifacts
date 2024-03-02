package com.xiaoyue.celestial_artifacts.content.curios.curse;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.ClientTokenHelper;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CatastropheScroll implements TickFacet, MultiLineText, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.catastrophe_scroll.shift3");
		if (ClientTokenHelper.hasCurio(level, CAItems.CHAOTIC_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_chaotic.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_chaotic.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_chaotic.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_chaotic.shift1",
					ChatFormatting.DARK_RED, (CAModConfig.COMMON.curse.catastropheScrollExplosionDamage.get() * 100) + "%");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_chaotic.shift2",
					ChatFormatting.DARK_RED, (CAModConfig.COMMON.curse.catastropheScrollOtherDamage.get() * 100) + "%");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.ORIGIN_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_origin.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_origin.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_origin.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_origin.shift1",
					ChatFormatting.DARK_RED, (CAModConfig.COMMON.curse.catastropheScrollOriginCurseDamage.get()) * 100 + "%");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.ETCHING_OF_LIFE.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_life.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_life.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_life.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_life.shift1");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.TRUTH_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_truth.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_truth.shift2");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_truth.shift2");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_truth.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_truth.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_truth.shift1");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.DESIRE_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_desire.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_desire.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_desire.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_desire.shift1");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.NIHILITY_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_nihility.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_nihility.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_nihility.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_nihility.shift1");
		}
		if (ClientTokenHelper.hasCurio(level, CAItems.END_ETCHING.get())) {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_end.shift1", ChatFormatting.YELLOW);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_has_end.shift1");
		} else {
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_is_end.shift1", ChatFormatting.DARK_PURPLE);
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_liberate.shift1");
			ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cs_no_end.shift1",
					ChatFormatting.DARK_RED, (CAModConfig.COMMON.curse.catastropheScrollEndCurseDamage.get() * 100) + "%");
		}
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!(entity instanceof Player player)) return;
		if (!CurioUtils.hasCurio(player, CAItems.DESIRE_ETCHING.get())) {
			List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, EntityUtils.getAABB(player, 8, 2));
			for (LivingEntity e : entities) {
				if (e.getLastHurtMobTimestamp() < e.tickCount - 20)
					e.setLastHurtMob(player);
				if (e.getLastHurtByMobTimestamp() < e.tickCount - 20)
					e.setLastHurtByMob(player);
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		float damage;
		if (CurioUtils.hasCurio(player, CAItems.ORIGIN_ETCHING.get())) {
			damage = 1.25f;
		} else {
			damage = (float) (1 - CAModConfig.COMMON.curse.catastropheScrollOriginCurseDamage.get());
		}
		cache.addHurtModifier(DamageModifier.multTotal(damage));
		if (CurioUtils.hasCurio(player, CAItems.END_ETCHING.get())) {
			player.heal(player.getMaxHealth() - player.getHealth() * 0.12f);
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		float factor;
		if (CurioUtils.hasCurio(player, CAItems.CHAOTIC_ETCHING.get())) {
			factor = 0.8f + 0.2f * player.getHealth() / player.getMaxHealth();
		} else {
			if (CAAttackToken.getSource(cache).is(DamageTypes.EXPLOSION)) {
				factor = (float) (1 + CAModConfig.COMMON.curse.catastropheScrollExplosionDamage.get());
			} else {
				factor = (float) (1 + CAModConfig.COMMON.curse.catastropheScrollOtherDamage.get());
			}
		}
		if (CurioUtils.hasCurio(player, CAItems.NIHILITY_ETCHING.get())) {
			if (CAAttackToken.getSource(cache).is(DamageTypes.FELL_OUT_OF_WORLD)) {
				factor *= 0.25f;
			}
			if (cache.getAttacker() != null) {
				EntityUtils.addEct(cache.getAttacker(), MobEffects.POISON, 200, 2);
			}
		}
		cache.addDealtModifier(DamageModifier.multTotal(factor));
		float hp = player.getMaxHealth() * 0.4f;
		if (cache.getAttacker() != null && CurioUtils.hasCurio(player, CAItems.END_ETCHING.get())) {
			cache.addDealtModifier(DamageModifier.nonlinearPre(345, e -> Math.min(e, hp)));
		} else {
			cache.addDealtModifier(DamageModifier.nonlinearPre(345, e -> Math.max(e, hp)));
		}
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (!CurioUtils.hasCurio(player, CAItems.END_ETCHING.get())) {
			if (cache.getDamageDealt() > player.getHealth() * CAModConfig.COMMON.curse.catastropheScrollEndCurseDamage.get()) {
				EntityUtils.addEct(player, MobEffects.MOVEMENT_SLOWDOWN, 600, 1);
				EntityUtils.addEct(player, MobEffects.WEAKNESS, 600, 1);
			}
		}
	}
}
