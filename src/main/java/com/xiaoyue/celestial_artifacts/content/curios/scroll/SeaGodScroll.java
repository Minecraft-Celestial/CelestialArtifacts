package com.xiaoyue.celestial_artifacts.content.curios.scroll;

import com.xiaoyue.celestial_artifacts.content.generic.intf.GetCurioBreak;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SeaGodScroll extends XICurioBase implements GetCurioBreak {
    public SeaGodScroll() {
        super(new Properties().rarity(Rarity.RARE));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_scroll.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_scroll.shift2");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt3");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.isInWaterOrRain() && player.tickCount % 20 == 0) {
            EntityUtils.addEct(player, MobEffects.DAMAGE_BOOST, 200, 1);
            EntityUtils.addEct(player, MobEffects.DAMAGE_RESISTANCE, 400, 0);
        }
    }

    @Override
    public void onPlayerBreak(SlotContext context, Player player, PlayerEvent.BreakSpeed event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (player.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                event.setNewSpeed(event.getNewSpeed() * 5f);
            }
        }
    }
}
