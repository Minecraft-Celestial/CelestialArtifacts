package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.content.old.generic.intf.GetCurioSkill;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class AbyssWillBadge extends AttackICurio implements GetCurioSkill {//FIXME token
    public AbyssWillBadge() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_AQUA));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift6");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift7",
                ChatFormatting.DARK_AQUA, abyss_will_badge_add);
    }

    @Override
    public void getCurioSkill(ServerLevel level, ServerPlayer player, SlotContext context) {
        if (CurioUtils.hasCurio(player, this)) {
            abyss_will_badge_add = 15;
            player.getCooldowns().addCooldown(this, 300);
        }
    }

    public static int abyss_will_badge_add;
    public static float abyss_will_badge_damage;

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 400 == 0) {
            if (abyss_will_badge_add < 10) {
                abyss_will_badge_add++;
            }
        }
        if (!player.getCooldowns().isOnCooldown(this)) {
            if (abyss_will_badge_add > 11) {
                abyss_will_badge_add = 0;
            }
            if (abyss_will_badge_damage != 0) {
                player.setHealth(player.getHealth() * 0.2f);
                abyss_will_badge_damage = 0;
            }
        }
    }

    @Override
    public void takeTick(SlotContext context, Player player) {
        abyss_will_badge_add = 0;
        abyss_will_badge_damage = 0;
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            double random = Math.random();
            if (0.1 > random) {
                event.setAmount(event.getAmount() * 2f);
            } else if (0.1 < random && 0.5 > random) {
                event.setAmount(event.getAmount() * 1.5f);
            } else {
                event.setAmount(event.getAmount());
            }
            if (AbyssWillBadge.abyss_will_badge_add > 0) {
                event.setAmount(event.getAmount() * (1 + (AbyssWillBadge.abyss_will_badge_add * 0.2f)));
            }
            if (player.getCooldowns().isOnCooldown(this)) {
                AbyssWillBadge.abyss_will_badge_damage += event.getAmount();
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            double random = Math.random();
            if (0.1 > random) {
                event.setAmount(event.getAmount() * 2.5f);
            } else if (0.1 < random && 0.5 > random) {
                event.setAmount(event.getAmount() * 1.5f);
            } else {
                event.setAmount(event.getAmount());
            }
            if (AbyssWillBadge.abyss_will_badge_add > 0) {
                event.setAmount(event.getAmount() * (1 + (AbyssWillBadge.abyss_will_badge_add * 0.25f)));
            }
        }
    }
}
