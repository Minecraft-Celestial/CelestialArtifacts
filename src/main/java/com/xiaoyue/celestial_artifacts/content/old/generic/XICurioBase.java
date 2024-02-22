package com.xiaoyue.celestial_artifacts.content.old.generic;

import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class XICurioBase extends BaseICurio {
    public XICurioBase(Properties properties) {
        super(properties.fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            addCurioInformation(itemStack, level, list, tooltipFlag);
        } else if (isHasCsCurio()) {
            ToolTipUtils.addLocalTooltip(list, ToolTipUtils.NullTooltip);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_cs_curio");
            ToolTipUtils.addLocalTooltip(list, ToolTipUtils.NullTooltip);
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_shift_down");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_shift_down");
        }
        if (isHasLinkageCurio()) {
            if (Screen.hasAltDown()) {
                addCurioLinkageInformation(itemStack, level, list, tooltipFlag);
            } else {
                ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.has_alt_down");
            }
        }
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
    }

    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
    }

    public boolean isHasCsCurio() {
        return false;
    }

    public boolean isHasLinkageCurio() {
        return false;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            boolean noThis = !CurioUtils.hasCurio(player, this);
            if (noThis && !isHasCsCurio()) return true;
            if (noThis && isHasCsCurio() && CurioUtils.isCsOn(player)) return true;
        }
        return false;
    }
}
