package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class AmethystRing extends XICurioBase {
    public AmethystRing() {
        super(new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.amethyst_ring.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.amethyst_ring.shift2");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 20 == 0) {
            EntityUtils.addEct(player, MobEffects.NIGHT_VISION, 400, 0);
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "extra damage", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }
}
