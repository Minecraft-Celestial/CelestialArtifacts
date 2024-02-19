package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioShield;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
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
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class EnderProtector extends XICurioBase implements GetCurioShield {
    public EnderProtector() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }


    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.ender_protector.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.ender_protector.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.ender_protector.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "adat", 4, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onPlayerBlocked(SlotContext context, Player player, ShieldBlockEvent event) {
        Entity attacker = event.getDamageSource().getEntity();
        if (CurioUtiks.hasCurio(player, this)) {
            if (attacker instanceof LivingEntity livingEntity) {
                if (Math.random() > 0.35) {
                    int x = livingEntity.getRandom().nextInt(16);
                    int z = livingEntity.getRandom().nextInt(16);
                    livingEntity.teleportTo(livingEntity.getX() + x, livingEntity.getY(), livingEntity.getZ() - z);
                }
            }
        }
    }
}
