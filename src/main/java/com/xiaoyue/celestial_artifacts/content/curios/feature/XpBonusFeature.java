package com.xiaoyue.celestial_artifacts.content.curios.feature;

import com.xiaoyue.celestial_artifacts.content.curios.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.IFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public interface XpBonusFeature extends IFacet {

	static XpBonusFeature simple(double val) {
		return new Simple(val);
	}

	double getXpBonus(Player player);

	record Simple(double val) implements XpBonusFeature, SingleLineText {

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
