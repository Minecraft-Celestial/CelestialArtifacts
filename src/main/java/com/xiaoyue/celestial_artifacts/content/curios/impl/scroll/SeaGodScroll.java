package com.xiaoyue.celestial_artifacts.content.curios.impl.scroll;

import com.xiaoyue.celestial_artifacts.content.curios.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.curios.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.TickFacet;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeaGodScroll implements TextFacet, TickFacet, BreakSpeedFeature {

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (player.isInWaterOrRain() && player.tickCount % 20 == 0) {
			EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 200, 1);
			EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 400, 0);
		}
	}

	@Override
	public double getBreakFactor(Player player) {
		if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
			return 5;
		}
		return 1;
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_scroll.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_scroll.shift2");
	}

}
