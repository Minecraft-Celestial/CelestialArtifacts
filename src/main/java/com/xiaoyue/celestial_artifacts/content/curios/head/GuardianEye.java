package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public class GuardianEye extends AttackICurio {
    public GuardianEye() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.guardian_eye.shift4");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (LevelUtils.isServerLevel(player.level())) {
            List<LivingEntity> entities = EntityUtils.getDelimitedMonster(player, 16);
            for (LivingEntity target : entities) {
                EntityUtils.addEct(target, MobEffects.DIG_SLOWDOWN, 100, 1);
            }
            if (player.isUnderWater()) {
                player.setAirSupply(1);
            }
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> setModifiers(String identifier, UUID uuid, ItemStack stack, Multimap<Attribute, AttributeModifier> Modifiers) {
        Modifiers.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "extra swim speed", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        return Modifiers;
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.isUnderWater()) {
                event.setAmount(event.getAmount() * 0.8f);
            }
        }
    }
}
