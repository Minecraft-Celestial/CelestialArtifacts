package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class MagicHorseshoe extends XICurioBase {
    public MagicHorseshoe() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.magic_horseshoe.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.magic_horseshoe.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.magic_horseshoe.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "muaa", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "luaa", 1, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }
}
