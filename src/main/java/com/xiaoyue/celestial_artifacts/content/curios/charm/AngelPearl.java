package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
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
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class AngelPearl extends XICurioBase {
    public AngelPearl() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift6", ChatFormatting.GOLD, angel_pearl_add * 8f + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift7", ChatFormatting.GOLD, angel_pearl_add);
    }

    private int angel_pearl_add;

    private Multimap<Attribute, AttributeModifier> createAttributeMap() {
        double add_rp = angel_pearl_add * 0.08;
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        attributesDefault.put(CCAttributes.REPLY_POWER.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "aper", add_rp, AttributeModifier.Operation.MULTIPLY_BASE));
        attributesDefault.put(Attributes.ARMOR, new AttributeModifier(ToolTipUtils.getFUuid(), "apea", angel_pearl_add, AttributeModifier.Operation.ADDITION));
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap());
        angel_pearl_add = EntityUtils.getBeneficialEffect(player);
        if (player.tickCount % 40 == 0) {
            List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2, living -> living instanceof Player);
            for (LivingEntity list : entities) {
                EntityUtils.addEct(list, MobEffects.REGENERATION, 30, 0);
            }
        }
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap());
        angel_pearl_add = 0;
    }
}
