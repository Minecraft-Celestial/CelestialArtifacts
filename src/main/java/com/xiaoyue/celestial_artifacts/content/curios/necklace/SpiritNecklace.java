package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SpiritNecklace extends AttackICurio {
    public SpiritNecklace() {
        super(new Item.Properties().rarity(IRarityUtils.DARK_GREEN));
    }

    @Override
    public boolean isHasLinkageCurio() {
        return true;
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_necklace.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit_necklace.shift2");
    }

    @Override
    public void addCurioLinkageInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt4");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt5");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt6");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt7");
    }

    public boolean spirit_necklace_add;

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        if (!spirit_necklace_add) {
            if (player.tickCount % 100 == 0) {
                spirit_necklace_add = true;
            }
        }
    }

    @Override
    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (event.getSource().getDirectEntity() instanceof AbstractArrow) {
                if (spirit_necklace_add) {
                    event.setAmount(event.getAmount() * 1.25f);
                    spirit_necklace_add = false;
                }
            }
        }
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            EntityUtils.addEct(player, MobEffects.MOVEMENT_SPEED, 100, 0);
        }
    }
}
