package com.xiaoyue.celestial_artifacts.content.curios.impl.head;

import com.xiaoyue.celestial_artifacts.content.curios.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrayerCrown implements TickFacet, TextFacet, CAAttackToken {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift3");
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!(entity instanceof Player player)) return;
		if (player.isHurt() && player.getLastAttacker() != null) {
			if (player.invulnerableTime < 10) {
				player.invulnerableTime += 3;//FIXME ?
			}
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		cache.addDealtModifier(DamageModifier.multTotal(0.75f));
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (player.isCrouching()) {
			if (player.getRandom().nextDouble() > 0.5) {
				GeneralEventHandler.schedule(() -> player.heal(cache.getDamageDealt() * 0.25f));
			}
		}
	}
}
