package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RingOfLife extends XICurioBase {
    public RingOfLife() {
        super(new Properties().rarity(IRarityUtils.GREEN));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.ring_of_life.shift1");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (LevelUtils.isServerLevel(player.level())) {
            if (player.tickCount % 100 == 0) {
                BlockPos entityPos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
                int range = 5;
                int limit = 0;

                ArrayList<BlockPos> list = new ArrayList<>();
                for(BlockPos pos : BlockPos.betweenClosed(entityPos.getX() - range, entityPos.getY() - range, entityPos.getZ() - range, entityPos.getX() + range, entityPos.getY() + range, entityPos.getZ() + range)) {
                    Block block = player.level().getBlockState(pos).getBlock();
                    if(block instanceof CropBlock || block instanceof StemBlock || block instanceof KelpBlock || block instanceof SeagrassBlock) {
                        list.add(new BlockPos(pos));
                    }
                }

                if(list.size() > 0) {
                    Random random = new Random();

                    while (list.size() >= 1 && limit < 3) {
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
