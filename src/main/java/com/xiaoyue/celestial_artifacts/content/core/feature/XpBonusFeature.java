package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.IFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public interface XpBonusFeature extends IFeature {

	static XpBonusFeature simple(double val) {
		return new Simple(val);
	}

	double getXpBonus(Player player);

	record Simple(double val) implements XpBonusFeature, SingleLineText {

		@Override
		public MutableComponent getLine() {
			return AttrFacet.simpleMult(CALang.Modular.XP.get(), val);
		}

		@Override
		public double getXpBonus(Player player) {
			return val;
		}
	}

}
