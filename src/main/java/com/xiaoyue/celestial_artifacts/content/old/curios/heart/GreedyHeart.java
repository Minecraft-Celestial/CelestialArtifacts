package com.xiaoyue.celestial_artifacts.content.old.curios.heart;

import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioXp;
import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
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

public class GreedyHeart extends XICurioBase implements GetCurioXp {
    public GreedyHeart() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.greedy_heart.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.greedy_heart.shift2");
    }

    @Override
    public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack stack) {
        return super.getLootingLevel(slotContext, source, target, baseLooting, stack) + 1;
    }

    @Override
    public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack stack) {
        return super.getFortuneLevel(slotContext, lootContext, stack) + 1;
    }

    @Override
    public void onPlayerPickupXp(SlotContext context, Player player, PlayerXpEvent.PickupXp event) {
        if (CurioUtils.hasCurio(event.getEntity(), this)) {
            event.getEntity().giveExperiencePoints(event.getOrb().value * 2);
        }
    }
}
