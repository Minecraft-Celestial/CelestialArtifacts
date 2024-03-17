package com.xiaoyue.celestial_artifacts;

import com.xiaoyue.celestial_artifacts.register.CAKeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CelestialArtifacts.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CArtifactsClient {

	@SubscribeEvent
	public static void keyRegister(RegisterKeyMappingsEvent event) {
		event.register(CAKeyMapping.ABILITY_KEY);
	}

}
