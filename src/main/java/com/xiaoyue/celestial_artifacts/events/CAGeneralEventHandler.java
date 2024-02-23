package com.xiaoyue.celestial_artifacts.events;

import com.xiaoyue.celestial_artifacts.content.curios.core.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CAGeneralEventHandler {

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();
		if (attacker instanceof Player player) {
			if (CurioUtils.isCsOn(player)) {
				if (entity instanceof Monster monster) {
					if (0.02 > monster.getRandom().nextDouble()) {
						monster.spawnAtLocation(CCItems.THE_END_DUST.get());
					}
				}
			}
			CAAttackListener.fireEvent(player, t -> t.onPlayerKill(player, event));
		}
	}

	@SubscribeEvent
	public void onPlayerPickupXp(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		double factor = 1;
		for (var e : CurioCacheCap.HOLDER.get(player).getXp()) {
			factor += e.getXpBonus(player);
		}
		event.getOrb().value = (int) (event.getOrb().value * factor);
	}

	@SubscribeEvent
	public void onPlayerBreak(PlayerEvent.BreakSpeed event) {
		Player player = event.getEntity();
		double factor = 1;
		for (var e : CurioCacheCap.HOLDER.get(player).getMining()) {
			factor *= e.getBreakFactor(player);
		}
		event.setNewSpeed((float) (event.getOriginalSpeed() * factor));
	}

}
