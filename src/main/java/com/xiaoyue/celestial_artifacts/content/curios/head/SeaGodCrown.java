package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeaGodCrown implements TextFacet, TickFacet, SkillFeature {

	@Override
	public void trigger(ServerPlayer player) {
		if (!player.serverLevel().isRaining()) {
			if (!player.getCooldowns().isOnCooldown(CAItems.SEA_GOD_CROWN.get())) {
				player.serverLevel().setWeatherParameters(0, 20000, true, true);
				player.getCooldowns().addCooldown(CAItems.SEA_GOD_CROWN.get(), 600);
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift3");
	}

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (player.isInWaterOrRain() && player.tickCount % 20 == 0) {
			EntityUtils.addEct(player, MobEffects.WATER_BREATHING, 200, 3);
			EntityUtils.addEct(player, MobEffects.NIGHT_VISION, 400, 0);
		}
	}

}
