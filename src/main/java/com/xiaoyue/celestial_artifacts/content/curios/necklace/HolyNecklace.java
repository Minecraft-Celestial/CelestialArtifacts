package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioHeal;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class HolyNecklace extends XICurioBase implements GetCurioHeal {
    public HolyNecklace() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_necklace.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_necklace.shift3");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.isHurt() && player.getLastAttacker() != null) {
            if (player.invulnerableTime <= 10) {
                player.invulnerableTime = player.invulnerableTime + 5;
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "add health", 4, AttributeModifier.Operation.ADDITION));
        return Modifiers;
    }

    @Override
    public void onPlayerHeal(SlotContext context, Player player, LivingHealEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 30, 0);
                player.getCooldowns().addCooldown(this, 60);
            }
        }
    }
}
