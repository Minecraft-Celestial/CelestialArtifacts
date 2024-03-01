package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import dev.xkmc.l2library.capability.conditionals.ConditionalToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

@SerialClass
public abstract class BaseTickingToken extends ConditionalToken implements MultiLineText {

	@SerialClass.SerialField
	public int life;

	public boolean tick(Player player) {
		if (life > 0)
			life--;
		if (life <= 0) {
			removeImpl(player);
		} else {
			tickImpl(player);
		}
		return life <= 0;
	}

	public void update() {
		life = 2;
	}

	protected abstract void removeImpl(Player player);

	protected abstract void tickImpl(Player player);

	public void addTextNoData(List<Component> list) {
		addText(null, list);
	}

}