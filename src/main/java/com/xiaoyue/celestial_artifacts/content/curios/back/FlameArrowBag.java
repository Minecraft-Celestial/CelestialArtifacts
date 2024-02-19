package com.xiaoyue.celestial_artifacts.content.curios.back;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class FlameArrowBag extends XICurioBase {
    public FlameArrowBag() {
        super(new Item.Properties());
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.flame_arrow_bag.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.flame_arrow_bag.shift2");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(CCAttributes.ARROW_DAMAGE.get(), new AttributeModifier(uuid, "extra arrow damage", 0.12, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(CCAttributes.ARROW_KNOCK.get(), new AttributeModifier(uuid, "extra arrow knock", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}
