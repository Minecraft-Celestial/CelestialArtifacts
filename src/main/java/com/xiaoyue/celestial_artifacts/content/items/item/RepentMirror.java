package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_core.utils.LevelUtils;
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

public class RepentMirror extends Item {
	public RepentMirror() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
		if (Screen.hasShiftDown()) {
			components.add(Component.translatable("tooltip.celestial_artifacts.repent_mirror.shift1"));
		} else {
			components.add(Component.translatable("tooltip.celestial_artifacts.has_shift_down"));
		}
		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemInHand = player.getItemInHand(hand);
		if (LevelUtils.isServerLevel(player.level())) {
			if (player instanceof ServerPlayer serverPlayer) {
				if (serverPlayer.getLastDeathLocation().isPresent() && !player.getCooldowns().isOnCooldown(itemInHand.getItem())) {
					BlockPos deathPos = serverPlayer.getLastDeathLocation().get().pos();
					player.teleportTo(deathPos.getX(), deathPos.getY(), deathPos.getZ());
					player.getCooldowns().addCooldown(itemInHand.getItem(), 200);
				}
			}
		}
		return InteractionResultHolder.success(itemInHand);
	}
}
