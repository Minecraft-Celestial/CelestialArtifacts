package com.xiaoyue.celestial_artifacts.content.old.curios.head;

import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EvilEye extends XICurioBase {
    public EvilEye() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.evil_eye.shift1");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 20 == 0) {
            if (player.hasEffect(MobEffects.DARKNESS)) {
                player.removeEffect(MobEffects.DARKNESS);
            } else if (player.hasEffect(MobEffects.BLINDNESS)) {
                player.removeEffect(MobEffects.BLINDNESS);
            }
        }
    }
}
