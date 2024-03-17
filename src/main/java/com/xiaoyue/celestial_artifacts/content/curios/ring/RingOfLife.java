package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class RingOfLife implements SingleLineText, TickFacet {

	private static int intervalFactor() {
		return CAModConfig.COMMON.ring.ringOfLifeEffectInterval.get();
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Ring.RING_OF_LIFE.get(TextFacet.num(intervalFactor()));
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity instanceof Player player && !player.level().isClientSide()) {
			if (player.tickCount % (intervalFactor() * 20) == 0) {
				BlockPos entityPos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
				int range = 5;
				int limit = 0;

				ArrayList<BlockPos> list = new ArrayList<>();
				for (BlockPos pos : BlockPos.betweenClosed(entityPos.getX() - range, entityPos.getY() - range, entityPos.getZ() - range, entityPos.getX() + range, entityPos.getY() + range, entityPos.getZ() + range)) {
					BlockState block = player.level().getBlockState(pos);
					if (block.getBlock() instanceof BonemealableBlock bmb && !block.isSolid() &&
							bmb.isValidBonemealTarget(player.level(), pos, block, false)) {
						list.add(new BlockPos(pos));
					}
				}

				if (!list.isEmpty()) {
					var random = player.getRandom();

					while (!list.isEmpty() && limit < 3) {
						BlockPos pos = list.remove(random.nextInt(list.size()));
						BlockState state = player.level().getBlockState(pos);

						if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), player.level(), pos, player)) {
							if (state != player.level().getBlockState(pos)) {
								limit++;
								player.level().levelEvent(2005, pos, 0);
							}
						}
					}
				}
			}
		}
	}
}
