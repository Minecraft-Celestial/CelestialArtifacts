package com.xiaoyue.celestial_artifacts.content.old.curios.pendant;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class ShadowPendant extends AttackICurio {
    public ShadowPendant() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_PURPLE));
    }

    @Override
    public boolean isHasCsCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.shadow_pendant.shift5");
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            player.heal(event.getAmount() * 0.25f);
            int brightness = player.level().getMaxLocalRawBrightness(player.getOnPos());
            if (brightness < 7) {
                int add = 7 - brightness;
                event.setAmount(event.getAmount() * (1 + (add * 0.05f)));
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtils.hasCurio(player, this)) {
            int brightness = player.level().getBrightness(LightLayer.BLOCK, player.blockPosition());
            if (brightness < 7) {
                int add = 7 - brightness;
                event.setAmount(event.getAmount() * (1 - (add * 0.05f)));
            }
        }
    }
}

