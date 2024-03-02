package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CursedProtector implements MultiLineText, TickFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift4",
				ChatFormatting.LIGHT_PURPLE, (CAModConfig.COMMON.charm.cursedProtectorJudgingLife.get() * 100) + "%",
				(CAModConfig.COMMON.charm.cursedProtectorReduceInjury.get() * 100) + "%");
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!(entity instanceof Player player)) return;
		Item item = player.getOffhandItem().getItem();
		if (item instanceof ShieldItem) {
			if (player.getCooldowns().isOnCooldown(item)) {
				player.getCooldowns().removeCooldown(item);
			}
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (cache.getAttacker() != null && !EntityUtils.isLookingBehindTarget(player, cache.getAttacker().getEyePosition())) {
			cache.addDealtModifier(DamageModifier.multTotal(0.7f));
		}
		cache.addDealtModifier(DamageModifier.nonlinearFinal(200, val -> parse(player, val)));
	}

	private float parse(Player player, float val) {
		if (val > player.getHealth() * CAModConfig.COMMON.charm.cursedProtectorJudgingLife.get()) {
			return val * (float) (1 - CAModConfig.COMMON.charm.cursedProtectorReduceInjury.get());
		}
		return val;
	}
}
