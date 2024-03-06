package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SolarMagnet implements MultiLineText, TickFacet {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.solar_magnet.shift4");
	}

	public void getAttractingItems(Player player) {
		Level level = player.level();
		if (!level.isClientSide()) {
			List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox()
					.inflate(5));
			for (ItemEntity list : entities) {
				list.teleportTo(player.getX(), player.getY(), player.getZ());
			}
		}
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (!(entity instanceof ServerPlayer player)) return;
		this.getAttractingItems(player);
		if (player.level().isDay()) {
			if (player.isOnFire()) {
				player.clearFire();
			}

		}
	}

}
