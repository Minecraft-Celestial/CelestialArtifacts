package com.xiaoyue.celestial_artifacts.content.curios.back;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.content.curios.CatastropheScroll;
import com.xiaoyue.celestial_core.register.CAttributes;
import com.xiaoyue.celestial_core.register.CEffects;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class TwistedScabbard extends AttackICurio {
    public TwistedScabbard() {
        super(new Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift6");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift7");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift8",
                ChatFormatting.LIGHT_PURPLE, twisted_scabbard_add);
    }

    public int twisted_scabbard_add;

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 100 == 0) {
            EntityUtils.addEct(player, CEffects.BLADE_MODIFIER.get(), 60, 0);
            if (twisted_scabbard_add > 0) {
                twisted_scabbard_add--;
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK, new AttributeModifier(uuid, "aabe", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "aabd", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        Modifiers.put(CAttributes.REPLY_POWER.get(), new AttributeModifier(uuid, "aare", -0.5, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onPlayerKillEntity(SlotContext context, Player player, LivingDeathEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            twisted_scabbard_add++;
        }
    }

    @Override
    public void onPlayerMeleeEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (CurioUtiks.isCsOn(player)) {
                if (CatastropheScroll.i_end == 0) {
                    event.setAmount(event.getAmount() * (1 + (twisted_scabbard_add * 0.1f)));
                }
            } else {
                event.setAmount(event.getAmount() * (1 + (twisted_scabbard_add * 0.05f)));
            }
        }
    }
}
