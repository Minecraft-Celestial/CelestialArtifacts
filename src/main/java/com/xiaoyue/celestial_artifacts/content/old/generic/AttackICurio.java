package com.xiaoyue.celestial_artifacts.content.old.generic;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import top.theillusivec4.curios.api.SlotContext;

public class AttackICurio extends XICurioBase {
    public AttackICurio(Properties properties) {
        super(properties);
    }

    public void onUnderAttack(SlotContext context, Player player, LivingAttackEvent event) {
    }

    public void onUnderHurt(SlotContext context, Player player, LivingHurtEvent event) {
    }

    public void onUnderDamage(SlotContext context, Player player, LivingDamageEvent event) {
    }

    public void onPlayerCriticalEntity(SlotContext context, Player player, CriticalHitEvent event) {
    }

    public void onPlayerHurtEntity(SlotContext context, Player player, LivingHurtEvent event) {
    }

    public void onPlayerMeleeEntity(SlotContext context, Player player, LivingHurtEvent event) {
    }

    public void onPlayerKillEntity(SlotContext context, Player player, LivingDeathEvent event) {
    }

    public void onPlayerDeath(SlotContext context, Player player, LivingDeathEvent event) {
    }
}
