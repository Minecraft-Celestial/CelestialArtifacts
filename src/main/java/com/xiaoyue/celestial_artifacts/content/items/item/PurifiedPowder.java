package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_core.utils.EnchUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurifiedPowder extends Item {
	public PurifiedPowder() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
		if (Screen.hasShiftDown()) {
			components.add(Component.translatable("tooltip.celestial_artifacts.purified_powder.shift1"));
		} else {
			components.add(Component.translatable("tooltip.celestial_artifacts.has_shift_down"));
		}
		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack mainHandItem = player.getMainHandItem();
		if (LevelUtils.isServerLevel(level)) {
			if (mainHandItem.is(this) && player.getOffhandItem().isEnchanted()) {
				EnchUtils.removeCurseEnch(player);
				player.setItemInHand(hand, Items.AIR.getDefaultInstance());
			}
		}
		return InteractionResultHolder.success(player.getItemInHand(hand));
	}
}
