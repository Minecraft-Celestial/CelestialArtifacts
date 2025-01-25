package com.xiaoyue.celestial_artifacts.content.items.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TimePocketWatch extends Item {
	public TimePocketWatch(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		ItemStack stack = pPlayer.getItemInHand(pUsedHand);
		if (!pLevel.isClientSide() && pLevel instanceof ServerLevel serverLevel) {
			long time = serverLevel.getDayTime();
			serverLevel.setDayTime(time + 2000);
			stack.hurtAndBreak(1, pPlayer, e -> e.broadcastBreakEvent(pUsedHand));
		}
		return InteractionResultHolder.success(stack);
	}
}
