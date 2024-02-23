package com.xiaoyue.celestial_artifacts.content.curios.impl.head;

import com.xiaoyue.celestial_artifacts.content.curios.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.curios.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class EvilEye implements SingleLineText, TickFacet {
    @Override
    public Component getLine() {
        return Component.translatable("tooltip.celestial_artifacts.evil_eye.shift1");
    }

    @Override
    public void tick(LivingEntity player, ItemStack stack) {
        if (player.hasEffect(MobEffects.DARKNESS)) {
            player.removeEffect(MobEffects.DARKNESS);
        } else if (player.hasEffect(MobEffects.BLINDNESS)) {
            player.removeEffect(MobEffects.BLINDNESS);
        }
    }

}
