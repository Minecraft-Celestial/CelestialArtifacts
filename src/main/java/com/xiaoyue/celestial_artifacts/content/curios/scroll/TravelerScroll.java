package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
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
import net.minecraftforge.common.ForgeMod;

import java.util.List;
import java.util.UUID;

public class TravelerScroll extends XICurioBase {
    public TravelerScroll() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant());
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.traveler_scroll.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.traveler_scroll.shift2");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "exsp", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(Attributes.FLYING_SPEED, new AttributeModifier(uuid, "exfs", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "exss", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}
