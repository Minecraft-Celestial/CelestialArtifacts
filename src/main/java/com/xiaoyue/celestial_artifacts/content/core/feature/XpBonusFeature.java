package com.xiaoyue.celestial_artifacts.content.core.feature;

import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.AttrTextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.DoubleSupplier;

public interface XpBonusFeature extends IFeature {

	static XpBonusFeature simple(DoubleSupplier val) {
		return new Simple(val);
	}

	double getXpBonus(Player player);

	record Simple(DoubleSupplier val) implements XpBonusFeature, AttrTextFacet {

		@Override
		public void addAttrText(List<Component> list) {
			list.add(AttrFacet.simpleMult(CALang.Modular.XP.get(), val.getAsDouble()));
		}

		@Override
		public double getXpBonus(Player player) {
			return val.getAsDouble();
		}
	}
}
