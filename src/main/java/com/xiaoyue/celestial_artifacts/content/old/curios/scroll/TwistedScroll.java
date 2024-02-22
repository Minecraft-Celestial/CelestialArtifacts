package com.xiaoyue.celestial_artifacts.content.old.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class TwistedScroll extends XICurioBase {
    public TwistedScroll() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scroll.shift1");
    }
}
