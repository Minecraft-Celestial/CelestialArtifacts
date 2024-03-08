package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MaterialHandler {

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity instanceof EnderDragon enderDragon) {
			if (event.getAmount() >= 500 && event.getAmount() >= enderDragon.getHealth()) {
				if (0.5 > entity.getRandom().nextDouble()) {
					enderDragon.spawnAtLocation(CAItems.ORIGIN_ETCHING.get());
				} else {
					enderDragon.spawnAtLocation(CAItems.NEBULA_CUBE.get());
				}
			}
		}
	}

}
