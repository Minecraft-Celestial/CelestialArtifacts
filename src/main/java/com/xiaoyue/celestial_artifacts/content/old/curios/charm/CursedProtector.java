package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class CursedProtector extends AttackICurio {
    public CursedProtector() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_protector.shift4",
                ChatFormatting.LIGHT_PURPLE, (CAModConfig.COMMON.charm.cursedProtectorJudgingLife.get() * 100) + "%",
                (CAModConfig.COMMON.charm.cursedProtectorReduceInjury.get() * 100) + "%");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        Item item = player.getOffhandItem().getItem();
        if (item instanceof ShieldItem) {
            if (player.getCooldowns().isOnCooldown(item)) {
                player.getCooldowns().removeCooldown(item);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier("cpak", 1, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (event.getAmount() > player.getHealth() * CAModConfig.COMMON.charm.cursedProtectorJudgingLife.get()) {
                event.setAmount((float) (event.getAmount() * (1 - CAModConfig.COMMON.charm.cursedProtectorReduceInjury.get())));
            }
            if (event.getSource().getEntity() instanceof LivingEntity entity) {
                if (!EntityUtils.isLookingBehindTarget(player, entity.getEyePosition())) {
                    event.setAmount(event.getAmount() * 0.7f);
                }
            }
        }
    }
}
