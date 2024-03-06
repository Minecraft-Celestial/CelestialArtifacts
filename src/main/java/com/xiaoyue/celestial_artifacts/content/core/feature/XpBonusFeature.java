package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.function.DoubleSupplier;

public interface XpBonusFeature extends IFeature {

	static XpBonusFeature simple(DoubleSupplier val) {
		return new Simple(val);
	}

	double getXpBonus(Player player);

	record Simple(DoubleSupplier val) implements XpBonusFeature, SingleLineText {

		@Override
		public MutableComponent getLine() {
			return AttrFacet.simpleMult(CALang.Modular.XP.get(), val.getAsDouble());
		}

		@Override
		public double getXpBonus(Player player) {
			return val.getAsDouble();
		}
	}

}
