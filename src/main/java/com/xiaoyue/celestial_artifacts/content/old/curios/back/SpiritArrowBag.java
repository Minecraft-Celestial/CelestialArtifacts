package com.xiaoyue.celestial_artifacts.content.old.curios.back;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class SpiritArrowBag extends XICurioBase {
    public SpiritArrowBag() {
        super(new Item.Properties().rarity(IRarityUtils.GREEN));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt6");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt7");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(CCAttributes.ARROW_SPEED.get(), new AttributeModifier(uuid, "sabees", 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(L2DamageTracker.BOW_STRENGTH.get(), new AttributeModifier(uuid, "sabead", 0.18, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(CCAttributes.ARROW_KNOCK.get(), new AttributeModifier(uuid, "sabeak", 0.18, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}