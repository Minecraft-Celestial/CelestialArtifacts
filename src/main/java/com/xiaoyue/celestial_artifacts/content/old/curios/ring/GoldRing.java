package com.xiaoyue.celestial_artifacts.content.old.curios.ring;

import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class GoldRing extends XICurioBase {
    public GoldRing() {
        super(new Properties());
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.gold_ring.shift1");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.tickCount % 20 == 0) {
            EntityUtils.addEct(player, MobEffects.LUCK, 200, 0);
        }
    }
}
