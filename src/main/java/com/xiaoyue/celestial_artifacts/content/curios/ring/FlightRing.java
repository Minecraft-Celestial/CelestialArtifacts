package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

@SerialClass
public class FlightRing extends BaseTickingToken implements SingleLineText {

	@Override
	protected void removeImpl(Player player) {
		if (player.getAbilities().instabuild) return;
		player.getAbilities().flying = false;
		player.getAbilities().mayfly = false;
		player.onUpdateAbilities();
	}

	@Override
	protected void tickImpl(Player player) {
		if (!player.getAbilities().instabuild) {
			player.getAbilities().mayfly = true;
		}
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Ring.FLIGHT.get();
	}
}
