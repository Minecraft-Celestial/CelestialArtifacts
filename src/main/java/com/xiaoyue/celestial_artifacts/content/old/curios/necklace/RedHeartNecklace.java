package com.xiaoyue.celestial_artifacts.content.old.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class RedHeartNecklace extends XICurioBase {
    public RedHeartNecklace() {
        super(new Item.Properties().rarity(IRarityUtils.RED));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.red_heart_necklace.shift1");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "exhn", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}
