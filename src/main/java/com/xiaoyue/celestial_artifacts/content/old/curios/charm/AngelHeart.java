package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class AngelHeart extends AttackICurio {
    public AngelHeart() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_heart.shift1",
                ChatFormatting.GOLD, CAModConfig.ANGEL_HEART_BLOOD_INTERVAL.get() / 20);
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_heart.shift2",
                ChatFormatting.GOLD, CAModConfig.ANGEL_HEART_REMOVE_INTERVAL.get() / 20);
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_heart.shift3");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % CAModConfig.ANGEL_HEART_BLOOD_INTERVAL.get() == 0) {
            player.heal(CAModConfig.ANGEL_HEART_HEAL_AMOUNT.get());
        }
        if (player.tickCount % CAModConfig.ANGEL_HEART_REMOVE_INTERVAL.get() == 0) {
            player.getActiveEffects().removeIf(EffectInstance -> EffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL);
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.getHealth() < player.getMaxHealth() * 0.5f) {
                event.setAmount(event.getAmount() * 0.9f);
            }
        }
    }
}
