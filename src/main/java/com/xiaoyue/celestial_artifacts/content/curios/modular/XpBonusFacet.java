package com.xiaoyue.celestial_artifacts.content.curios.modular;

import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public interface XpBonusFacet extends IFacet {

	static XpBonusFacet simple(double val) {
		return new Simple(val);
	}

	double getXpBonus(Player player);

	record Simple(double val) implements XpBonusFacet, SingleLineText {

		@Override
		public Component getLine() {
			return AttrFacet.simpleMult(CALang.Modular.XP.get(), val);
		}

		@Override
		public double getXpBonus(Player player) {
			return val;
		}
	}

}
