package com.xiaoyue.celestial_artifacts.content.old.curios.head;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class PrayerCrown extends AttackICurio {
    public PrayerCrown() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.prayer_crown.shift3");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.isHurt() && player.getLastAttacker() != null) {
            if (player.invulnerableTime < 10) {
                player.invulnerableTime = player.invulnerableTime + 3;
            }
        }
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (player.isCrouching()) {
                if (Math.random() > 0.5) {
                    event.setAmount(event.getAmount() * 0.75f);
                    player.heal(event.getAmount() * 0.25f);
                }
            } else {
                event.setAmount(event.getAmount() * 0.75f);
            }
        }
    }
}
