package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.config.CommonConfig;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CAttributes;
import com.xiaoyue.celestial_core.utils.EnchUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class CursedTalisman extends XICurioBase {
    public CursedTalisman() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift3",
                ChatFormatting.LIGHT_PURPLE, (CommonConfig.CURSED_TALISMAN_CRIT_RATE_ADD.get() * 100) + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift4",
                ChatFormatting.LIGHT_PURPLE, (CommonConfig.CURSED_TALISMAN_CRIT_DAMAGE_ADD.get() * 100) + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift6",
                ChatFormatting.LIGHT_PURPLE, (cursed_talisman_add * CommonConfig.CURSED_TALISMAN_CRIT_RATE_ADD.get() * 100) + "%");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift7",
                ChatFormatting.LIGHT_PURPLE, (cursed_talisman_add * CommonConfig.CURSED_TALISMAN_CRIT_DAMAGE_ADD.get() * 100) + "%");
    }

    public int cursed_talisman_add;

    private Multimap<Attribute, AttributeModifier> createAttributeMap() {
        Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
        double cta = cursed_talisman_add * CommonConfig.CURSED_TALISMAN_CRIT_DAMAGE_ADD.get();
        double ctr = cursed_talisman_add * CommonConfig.CURSED_TALISMAN_CRIT_RATE_ADD.get();
        attributesDefault.put(CAttributes.CRIT_DAMAGE.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "cbat", cta, AttributeModifier.Operation.ADDITION));
        attributesDefault.put(CAttributes.CRIT_RATE.get(), new AttributeModifier(ToolTipUtils.getFUuid(), "cbas", ctr, AttributeModifier.Operation.ADDITION));
        return attributesDefault;
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        player.getAttributes().addTransientAttributeModifiers(createAttributeMap());
        cursed_talisman_add = EnchUtils.getTotalCurseEnch(player);
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        player.getAttributes().removeAttributeModifiers(createAttributeMap());
        cursed_talisman_add = 0;
    }
}
