package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.register.CEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class SoulBox extends AttackICurio {
    public SoulBox() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.soul_box.shift4");
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        Entity attacker = event.getSource().getEntity();
        if (CurioUtiks.hasCurio(player, this)) {
            if (event.getAmount() > player.getHealth()) {
                if (attacker instanceof LivingEntity livingEntity) {
                    EntityUtils.addEct(livingEntity, CEffects.SOUL_SHATTER.get(), 1200, 2);
                    livingEntity.hurt(player.damageSources().wither(), player.getMaxHealth() * 2.5f);
                    if (!player.getCooldowns().isOnCooldown(this)) {
                        event.setCanceled(true);
                        player.getCooldowns().addCooldown(this, 4800);
                    }
                }
            } else if (Math.random() > 0.3) {
                if (attacker instanceof LivingEntity livingEntity) {
                    EntityUtils.addEct(livingEntity, CEffects.SOUL_SHATTER.get(), 100, 0);
                }
            }
        }
    }
}
