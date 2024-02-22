package com.xiaoyue.celestial_artifacts.content.old.curios.pendant;

import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ChaoticPendant extends XICurioBase {
    public ChaoticPendant() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.chaotic_pendant.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.chaotic_pendant.shift2");
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return super.getLootingLevel(slotContext, source, target, baseLooting, stack) + 1;
    }
}
