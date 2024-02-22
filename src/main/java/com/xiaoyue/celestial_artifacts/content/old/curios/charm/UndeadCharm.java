package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

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

public class UndeadCharm extends AttackICurio {
    public UndeadCharm() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.undead_charm.shift1");
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                if (event.getAmount() > player.getHealth()) {
                    event.setCanceled(true);
                    player.heal(2);
                    player.getCooldowns().addCooldown(this, 3600);
                }
            }
        }
    }
}
