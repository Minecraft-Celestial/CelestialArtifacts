package com.xiaoyue.celestial_artifacts.content.old.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class GallopNecklace extends AttackICurio {
    public GallopNecklace() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.gallop_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.gallop_necklace.shift2");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "extra speed", 0.08, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.isAlive()) {
                event.setAmount(event.getAmount() + (player.getSpeed() * 1.5f));
            }
        }
    }
}
