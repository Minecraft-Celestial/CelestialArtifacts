package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.ShieldingFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightShelter implements MultiLineText, TickFacet, ShieldingFeature {

	@Override
	public void onPlayerBlocked(Player player, ShieldBlockEvent event) {
		Entity attacker = event.getDamageSource().getEntity();
		if (attacker != null) {
			GeneralEventHandler.schedule(() -> attacker.hurt(player.damageSources().playerAttack(player),
					event.getBlockedDamage() * 0.3f));
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift4");
	}

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (player.getOffhandItem().getItem() instanceof ShieldItem) {
			if (player.tickCount % 80 == 0) {
				player.heal(1f);
			}
		} else if (player.getMainHandItem().getItem() instanceof ShieldItem) {
			if (player.tickCount % 40 == 0) {
				player.heal(1f);
			}
		}
	}

}
