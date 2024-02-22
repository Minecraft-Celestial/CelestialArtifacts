package com.xiaoyue.celestial_artifacts.content.old.curios.charm;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class CursedTotem extends AttackICurio {
    public CursedTotem() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift4",
                ChatFormatting.LIGHT_PURPLE, cursed_soul_totem);
    }

    public int cursed_soul_totem;

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            if (event.getAmount() < player.getHealth()) {
                if (cursed_soul_totem < 5) {
                    cursed_soul_totem++;
                }
            } else if (cursed_soul_totem > 0) {
                if (player.getLastAttacker() != null) {
                    if (!player.getLastAttacker().isDeadOrDying()) {
                        event.setCanceled(true);
                        cursed_soul_totem--;
                    }
                }
            }
        }
    }
}
