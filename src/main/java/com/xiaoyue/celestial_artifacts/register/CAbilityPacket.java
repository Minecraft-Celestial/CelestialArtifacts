package com.xiaoyue.celestial_artifacts.register;

import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureType;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import dev.xkmc.l2serial.network.SerialPacketBase;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

@SerialClass
public class CAbilityPacket extends SerialPacketBase {

	@Override
	public void handle(NetworkEvent.Context context) {
		ServerPlayer player = context.getSender();
		assert player != null;
		for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.SKILL)) {
			e.trigger(player);
		}
	}

}