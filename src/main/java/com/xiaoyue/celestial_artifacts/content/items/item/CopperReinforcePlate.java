package com.xiaoyue.celestial_artifacts.content.items.item;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import net.minecraft.world.item.Rarity;

public class CopperReinforcePlate extends CCTooltipItem {
	public CopperReinforcePlate() {
		super(new Properties().rarity(Rarity.RARE), true, () ->
				CALang.Tooltip.COPPER_REINFORCE_PLATE.get(TextFacet.perc(CAModConfig.COMMON.misc
						.copperReinforceChance.get())));
	}
}
