package com.xiaoyue.celestial_artifacts.content.old.curios.ring;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.events.CEventHandler;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class EmeraldRing extends XICurioBase {
    public EmeraldRing() {
        super(new Properties().rarity(IRarityUtils.GREEN));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_ring.shift1");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt2");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "erlk", 1, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        if (CurioUtils.hasCurio(player, CAItems.EMERALD_NECKLACE.get(), CAItems.EMERALD_BRACELET.get())) {
            player.getAttributes().removeAttributeModifiers(CEventHandler.createEmeraldAttributeMap());
        }
    }
}
