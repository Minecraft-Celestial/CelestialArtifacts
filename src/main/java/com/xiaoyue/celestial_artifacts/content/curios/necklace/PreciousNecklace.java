package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class PreciousNecklace extends XICurioBase {
    public PreciousNecklace() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.precious_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.precious_necklace.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.precious_necklace.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        CuriosApi.addSlotModifier(Modifiers, "charm", uuid, 1, AttributeModifier.Operation.ADDITION);
        Modifiers.put(CCAttributes.CRIT_DAMAGE.get(), new AttributeModifier(uuid, "extra crit damage", 0.2, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return super.getFortuneLevel(slotContext, lootContext, stack) + 1;
    }
}
