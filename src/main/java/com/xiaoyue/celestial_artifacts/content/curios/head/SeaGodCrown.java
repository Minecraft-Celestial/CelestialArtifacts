package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.generic.Interface.GetCurioSkill;
import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SeaGodCrown extends XICurioBase implements GetCurioSkill {
    public SeaGodCrown() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god_crown.shift3");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.sea_god.alt3");
    }

    @Override
    public void getCurioSkill(ServerLevel level, ServerPlayer player, SlotContext context) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (!level.isRaining()) {
                if (!player.getCooldowns().isOnCooldown(this)) {
                    level.setWeatherParameters(0, 20000, true, true);
                    player.getCooldowns().addCooldown(this, 600);
                }
            }
        }
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (player.isInWaterOrRain() && player.tickCount % 20 == 0) {
            EntityUtils.addEct(player, MobEffects.WATER_BREATHING, 200, 3);
            EntityUtils.addEct(player, MobEffects.NIGHT_VISION, 400, 0);
        }
    }
}
