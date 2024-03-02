package com.xiaoyue.celestial_artifacts.content.items.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BacktrackMirror extends Item {
	public BacktrackMirror() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
		if (Screen.hasShiftDown()) {
			components.add(Component.translatable("tooltip.celestial_artifacts.backtrack_mirror.shift1"));
		} else {
			components.add(Component.translatable("tooltip.celestial_artifacts.has_shift_down"));
		}
		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemInHand = player.getItemInHand(hand);
		if (!player.level().isClientSide()) {
			if (player instanceof ServerPlayer serverPlayer) {
				BlockPos spawnPos = serverPlayer.getRespawnPosition();
				if (spawnPos != null) {
					if (!player.getCooldowns().isOnCooldown(itemInHand.getItem())) {
						player.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
						player.getCooldowns().addCooldown(itemInHand.getItem(), 200);
					}
				} else {
					BlockPos levelPos = level.getSharedSpawnPos();
					if (!player.getCooldowns().isOnCooldown(itemInHand.getItem())) {
						player.teleportTo(levelPos.getX(), levelPos.getY(), levelPos.getZ());
						player.getCooldowns().addCooldown(itemInHand.getItem(), 200);
					}
				}
			}
		}
		return InteractionResultHolder.success(itemInHand);
	}
}
