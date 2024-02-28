package com.xiaoyue.celestial_artifacts.content.curios.ring;

import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class FlightRing extends BaseTickingToken {

	@Override
	protected void removeImpl(Player player) {
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
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(Component.translatable("tooltip.celestial_artifacts.flight_ring.shift1"));
	}

}
