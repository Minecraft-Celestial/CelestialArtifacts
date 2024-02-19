package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.generic.AttackICurio;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_artifacts.utils.CurioUtiks;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class HolyTalisman extends AttackICurio {
    public HolyTalisman() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public void addCurioInformation(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift1");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift2");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift3");
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.holy_talisman.shift4");
    }

    @Override
    public void equipmentTick(SlotContext context, Player player) {
        List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2, LivingEntity::isAlive);
        int size = entities.size();
        if (player.tickCount % 200 == 0) {
            for (LivingEntity list : entities) {
                if (list instanceof Monster monster) {
                    EntityUtils.addEct(monster, MobEffects.WEAKNESS, size * 50, 0);
                }
            }
        }
    }

    @Override
    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity.getMobType() == MobType.UNDEAD) {
                    event.setAmount(event.getAmount() * 0.65f);
                } else {
                    event.setAmount(event.getAmount() * 0.75f);
                }
            }
        }
    }

    @Override
    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
        if (CurioUtiks.hasCurio(player, this)) {
            if (event.getSource().getEntity() instanceof LivingEntity) {
                if (player.getHealth() < event.getAmount()) {
                    if (!player.getCooldowns().isOnCooldown(this)) {
                        event.setCanceled(true);
                        player.setHealth(1f);
                        player.setAbsorptionAmount(player.getMaxHealth());
                        player.getCooldowns().addCooldown(this, 1200);
                    }
                }
            }
        }
    }
}
