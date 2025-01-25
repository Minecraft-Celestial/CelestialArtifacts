package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2library.util.tools.TeleportTool;
import net.minecraft.ChatFormatting;
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
		super(new Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	private static int cd() {
		return CAModConfig.COMMON.misc.backtrackMirrorCooldown.get();
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
		list.add(CALang.Tooltip.REPENT.get(TextFacet.num(cd())).withStyle(ChatFormatting.GRAY));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemInHand = player.getItemInHand(hand);
		if (!player.level().isClientSide()) {
			if (player instanceof ServerPlayer sp) {
				if (!player.getCooldowns().isOnCooldown(itemInHand.getItem())) {
					var opt = sp.getLastDeathLocation();
					if (opt.isPresent()) {
						var dim = opt.get().dimension();
						var lv = sp.server.getLevel(dim);
						if (lv != null) {
							BlockPos pos = opt.get().pos();
							TeleportTool.performTeleport(sp, lv, pos.getX(), pos.getY(), pos.getZ(),
									player.getYRot(), player.getXRot());
							player.getCooldowns().addCooldown(itemInHand.getItem(), 20 * cd());
						}
					}
				}
			}
		}
		return InteractionResultHolder.success(itemInHand);
	}

}
