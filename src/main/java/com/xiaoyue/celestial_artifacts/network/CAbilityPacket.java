package com.xiaoyue.celestial_artifacts.network;

import com.xiaoyue.celestial_artifacts.content.core.feature.FeatureType;
import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CAbilityPacket {

	public CAbilityPacket() {

	}

	public CAbilityPacket(FriendlyByteBuf buf) {

	}

	public void toBuf(FriendlyByteBuf buf) {

	}

	public boolean handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			assert player != null;
			for (var e : CurioCacheCap.HOLDER.get(player).getFeature(FeatureType.SKILL)) {
				e.trigger(player);
			}
		});
		return true;
	}
}