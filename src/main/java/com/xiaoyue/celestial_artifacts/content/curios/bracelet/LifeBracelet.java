package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CAttributes;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class LifeBracelet extends XICurioBase {
    public LifeBracelet() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.life_bracelet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.life_bracelet.shift2");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 100 == 0) {
            player.heal(2);
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(CAttributes.REPLY_POWER.get(), new AttributeModifier(uuid, "extra reply", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}
