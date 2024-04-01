package com.xiaoyue.celestial_artifacts.content.curios.set;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class EmeraldSet extends BaseTickingToken {

	private static final String NAME = "celestial_artifact:emerald_set";
	private static final AttrAdder LUCK = AttrAdder.of(NAME, () -> Attributes.LUCK, AttributeModifier.Operation.ADDITION,
			CAModConfig.COMMON.set.emeraldLuck::get);
	private static final AttrAdder CRIT = AttrAdder.of(NAME, L2DamageTracker.CRIT_RATE::get, AttributeModifier.Operation.ADDITION,
			CAModConfig.COMMON.set.emeraldCrit::get);

	@Override
	protected void removeImpl(Player player) {
		LUCK.removeImpl(player);
		CRIT.removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		LUCK.tickImpl(player);
		CRIT.tickImpl(player);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.set(level, CAItems.emeraldSet()));
		list.add(TextFacet.wrap(LUCK.getTooltip()));
		list.add(TextFacet.wrap(CRIT.getTooltip()));
	}

}
