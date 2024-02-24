package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
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

public class StarNecklace implements TextFacet, TickFacet, CAAttackToken {

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (EntityUtils.isLookingBehindTarget(cache.getAttackTarget(), player.getEyePosition()) && player.level().isNight()) {
			cache.addHurtModifier(DamageModifier.multTotal(1.4f));
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.star_necklace.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.star_necklace.shift2");
	}

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (LevelUtils.isServerLevel(player.level())) {
			if (player.level().isNight() && player.tickCount % 20 == 0) {
				EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 100, 0);
			}
		}
	}

}
