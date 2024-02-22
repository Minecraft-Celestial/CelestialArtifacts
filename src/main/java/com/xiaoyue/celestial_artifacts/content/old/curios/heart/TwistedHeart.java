package com.xiaoyue.celestial_artifacts.content.old.curios.heart;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.content.old.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class TwistedHeart extends XICurioBase {
    public TwistedHeart() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift3");
    }

    @Override
    public boolean canBeHurtBy(@NotNull DamageSource source) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        if (CatastropheScroll.i_nihility == 2) {
            Modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "thnat", -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
            Modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "thnad", -0.33, AttributeModifier.Operation.MULTIPLY_TOTAL));
        } else if (CatastropheScroll.i_nihility == 0) {
            Modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "theat", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL));
            Modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "thead", 0.33, AttributeModifier.Operation.MULTIPLY_TOTAL));
            return Modifiers;
        }
        return Modifiers;
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            return !CurioUtils.hasCurio(player, CAItems.TWISTED_BRAIN.get());
        }
        return false;
    }
}
