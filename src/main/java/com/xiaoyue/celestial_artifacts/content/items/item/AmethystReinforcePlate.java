package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import net.minecraft.world.item.Rarity;

public class AmethystReinforcePlate extends CCTooltipItem {
	public AmethystReinforcePlate() {
		super(new Properties().rarity(Rarity.RARE), true, () -> CALang.Tooltip.AMETHYST_REINFORCE_PLATE.get(
				TextFacet.num(CAModConfig.COMMON.misc.amethystReinforceEffect.get())));
	}
}
