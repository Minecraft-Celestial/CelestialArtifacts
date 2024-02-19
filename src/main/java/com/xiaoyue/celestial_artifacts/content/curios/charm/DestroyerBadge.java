package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class DestroyerBadge extends AttackICurio {
    public DestroyerBadge() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.destroyer_badge.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.destroyer_badge.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.destroyer_badge.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "dbead", 0.4, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onPlayerCriticalEntity(SlotContext context, Player player, CriticalHitEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.getHealth() < player.getMaxHealth() * 0.5f) {
                event.setDamageModifier(event.getDamageModifier() * 1.2f);
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            event.setAmount(event.getAmount() * 1.5f);
        }
    }
}
