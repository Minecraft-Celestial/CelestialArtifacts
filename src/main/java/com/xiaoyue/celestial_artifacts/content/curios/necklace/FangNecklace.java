package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class FangNecklace extends AttackICurio {
    public FangNecklace() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_GREEN));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.fang_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.fang_necklace.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.fang_necklace.shift3");
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "extra attack speed", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (Math.random() >= 0.5) {
                EntityUtils.addEct(event.getEntity(), MobEffects.POISON, 100, 2);
            }
            if (EntityUtils.hasArmor(event.getEntity())) {
                event.setAmount(event.getAmount() * 1.25f);
            }
        }
    }
}
