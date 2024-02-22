package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.SlotResult;

public class AttackMain implements AttackListener {

    @SubscribeEvent
    public void onUnderAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onUnderAttack(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onUnderHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onUnderHurt(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onUnderDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onUnderDamage(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onPlayerDeath(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCriticalEntity(CriticalHitEvent event) {
        for (SlotResult result : CurioUtils.getACResult(event.getEntity())) {
            AttackICurio curio = (AttackICurio) result.stack().getItem();
            curio.onPlayerCriticalEntity(result.slotContext(), event.getEntity(), event);
        }
    }

    @SubscribeEvent
    public void onPlayerHurtEntity(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onPlayerHurtEntity(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerMeleeEntity(LivingHurtEvent event) {
        if (event.getSource().getDirectEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onPlayerMeleeEntity(result.slotContext(), player, event);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerKillEntity(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            for (SlotResult result : CurioUtils.getACResult(player)) {
                AttackICurio curio = (AttackICurio) result.stack().getItem();
                curio.onPlayerKillEntity(result.slotContext(), player, event);
            }
        }
    }
}
