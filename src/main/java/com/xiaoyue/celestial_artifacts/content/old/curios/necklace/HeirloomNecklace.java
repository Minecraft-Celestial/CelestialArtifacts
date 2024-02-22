package com.xiaoyue.celestial_artifacts.content.old.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioXp;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class HeirloomNecklace extends XICurioBase implements GetCurioXp {
    public HeirloomNecklace() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heirloom_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heirloom_necklace.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heirloom_necklace.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.heirloom_necklace.shift4");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "aaba", 2, AttributeModifier.Operation.ADDITION));
        Modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "aabs", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return super.getFortuneLevel(slotContext, lootContext, stack) + 1;
    }

    @Override
    public void onPlayerPickupXp(SlotContext context, Player player, PlayerXpEvent.PickupXp event) {
        if (CurioUtils.hasCurio(event.getEntity(), this)) {
            player.giveExperiencePoints((int) (event.getOrb().value * 0.1));
        }
    }
}
