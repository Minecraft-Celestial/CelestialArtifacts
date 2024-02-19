package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.events.CEventHandler;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class EmeraldBracelet extends AttackICurio {
    public EmeraldBracelet() {
        super(new Item.Properties().rarity(IRarityUtils.GREEN));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_bracelet.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_bracelet.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald_bracelet.shift3");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.emerald.alt2");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(CCAttributes.CRIT_RATE.get(), new AttributeModifier(uuid, "lbcr", 0.1, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        if (CurioUtiks.hasTwoCurio(player, CAItems.EMERALD_RING.get(), CAItems.EMERALD_NECKLACE.get())) {
            player.getAttributes().removeAttributeModifiers(CEventHandler.createEmeraldAttributeMap());
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.getLuck() > 2) {
                if (Math.random() > 0.5) {
                    EntityUtils.addEct(player, MobEffects.ABSORPTION, 100, 1);
                }
            }
        }
    }
}
