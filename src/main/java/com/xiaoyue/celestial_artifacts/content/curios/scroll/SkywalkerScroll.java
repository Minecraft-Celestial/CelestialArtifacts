package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioSkill;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SkywalkerScroll extends XICurioBase implements GetCurioSkill {
    public SkywalkerScroll() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift4");
        ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift5", ChatFormatting.AQUA, (float) x);
        ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift6", ChatFormatting.AQUA, (float) y);
        ToolTipUtils.addValueTooltip(list, "tooltip.celestial_artifacts.skywalker_scroll.shift7", ChatFormatting.AQUA, (float) z);
    }

    public static double x;
    public static double y;
    public static double z;

    @Override
    public void getCurioSkill(ServerLevel level, ServerPlayer player, SlotContext context) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.isCrouching()) {
                x = player.getX();
                y = player.getY();
                z = player.getZ();
            } else if (!player.getCooldowns().isOnCooldown(this)) {
                player.teleportTo(x, y, z);
                player.getCooldowns().addCooldown(this, 1200);
            }
        }
    }
}
