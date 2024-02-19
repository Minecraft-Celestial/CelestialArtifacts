package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioShield;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class KnightShelter extends AttackICurio implements GetCurioShield {
    public KnightShelter() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.knight_shelter.shift4");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.getOffhandItem().getItem() instanceof ShieldItem) {
            if (player.tickCount % 80 == 0) {
                player.heal(1f);
            }
        } else if (player.getMainHandItem().getItem() instanceof ShieldItem) {
            if (player.tickCount % 40 == 0) {
                player.heal(1f);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "aabc", 8, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            event.setAmount(event.getAmount() - 4f);
        }
    }

    @Override
    public void onPlayerBlocked(SlotContext context, Player player, ShieldBlockEvent event) {
        Entity attacker = event.getDamageSource().getEntity();
        if (CurioUtiks.hasCurio(player, this)) {
            if (attacker != null) {
                attacker.hurt(player.damageSources().playerAttack(player), event.getBlockedDamage() * 0.3f);
            }
        }
    }
}
