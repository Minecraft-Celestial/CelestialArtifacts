package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.TridentItem;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CEventHandler {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		Entity directEntity = source.getDirectEntity();
		Entity attacker = source.getEntity();
		if (attacker instanceof Player player) {
			if (CurioUtils.isSeaGodOn(player)) {
				if (directEntity instanceof ThrownTrident trident) {
					if (trident.getOwner() != null && trident.getOwner().is(player)) {
						event.setAmount(event.getAmount() * 1.75f);
					}
				}
				if (directEntity != null && directEntity.is(player)) {
					if (player.getMainHandItem().getItem() instanceof TridentItem) {
						event.setAmount(event.getAmount() * 1.5f);
					}
				}
			}
		}
		if (entity instanceof Player player) {
			if (CurioUtils.isSeaGodOn(player)) {
				if (attacker instanceof LivingEntity livingEntity) {
					if (livingEntity.getMobType() == MobType.WATER) {
						event.setAmount(event.getAmount() * 0.65f);
					}
				}
			}
		}
	}

}