package com.xiaoyue.celestial_artifacts.content.old.curios.heart;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
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
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class DemonHeart extends AttackICurio {
    public DemonHeart() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift6");
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        int curse = CurioUtils.getCurseAmount();
        if (curse >= 3) {
            if (entity.isOnFire()) {
                entity.clearFire();
            }
        }
        if (curse >= 5) {
            if (entity.hasEffect(MobEffects.WEAKNESS)) {
                entity.removeEffect(MobEffects.WEAKNESS);
            }
            if (entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ToolTipUtils.getFUuid(), "dheat", ToolTipUtils.getNoZeroValue(CurioUtils.getCurseAmount()), AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            event.setAmount(event.getAmount() * (1 + (CurioUtils.getCurseAmount() * 0.1f)));
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            event.setAmount(event.getAmount() * (1 - (CurioUtils.getCurseAmount() * 0.05f)));
        }
    }
}
