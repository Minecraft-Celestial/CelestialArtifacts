package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioHeal;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class DemonCurse extends XICurioBase implements GetCurioHeal {
    public DemonCurse() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift6",
                ChatFormatting.LIGHT_PURPLE, ToolTipUtils.getNoZeroValue(demon_curse_add * 200) + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift7",
                ChatFormatting.LIGHT_PURPLE, ToolTipUtils.getNoZeroValue(demon_curse_add * 100) + "%");
    }

    public double demon_curse_add;

    private Multimap<Attribute, AttributeModifier> createAttributeMap() {
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        attributesDefault.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ToolTipUtils.getFUuid(), "cbat", ToolTipUtils.getNoZeroValue(demon_curse_add * 2), AttributeModifier.Operation.MULTIPLY_BASE));
        attributesDefault.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(ToolTipUtils.getFUuid(), "cbas", ToolTipUtils.getNoZeroValue(demon_curse_add), AttributeModifier.Operation.MULTIPLY_BASE));
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap());
        double attributeValue = player.getAttributeValue(CCAttributes.REPLY_POWER.get());
        demon_curse_add = attributeValue - 1;
    }
    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap());
        demon_curse_add = 0;
    }

    @Override
    public void onPlayerHeal(SlotContext context, Player player, LivingHealEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            event.setAmount(event.getAmount() * 0.1f);
        }
    }
}
