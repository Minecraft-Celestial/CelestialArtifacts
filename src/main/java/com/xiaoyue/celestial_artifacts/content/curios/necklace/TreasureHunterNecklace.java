package com.xiaoyue.celestial_artifacts.content.curios.necklace;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import net.minecraft.network.chat.MutableComponent;

public class TreasureHunterNecklace implements SingleLineText {

	@Override
	public MutableComponent getLine() {
		return CALang.Necklace.TREASURE_HUNTER_NECKLACE.get(TextFacet.num(
				CAModConfig.COMMON.necklace.treasureHunterNecklaceCooldown.get()));
	}
}
