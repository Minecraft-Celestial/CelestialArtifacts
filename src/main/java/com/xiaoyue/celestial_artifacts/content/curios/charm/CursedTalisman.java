package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EnchUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class CursedTalisman extends BaseTickingToken {

	@SerialClass.SerialField
	public int cursed_talisman_add;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift3",
				ChatFormatting.LIGHT_PURPLE, (CAModConfig.COMMON.charm.cursedTalismanCritRateAdd.get() * 100) + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift4",
				ChatFormatting.LIGHT_PURPLE, (CAModConfig.COMMON.charm.cursedTalismanCritDamageAdd.get() * 100) + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift6",
				ChatFormatting.LIGHT_PURPLE, (cursed_talisman_add * CAModConfig.COMMON.charm.cursedTalismanCritRateAdd.get() * 100) + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_talisman.shift7",
				ChatFormatting.LIGHT_PURPLE, (cursed_talisman_add * CAModConfig.COMMON.charm.cursedTalismanCritDamageAdd.get() * 100) + "%");

	}

	private AttrAdder dmg() {
		double cta = cursed_talisman_add * CAModConfig.COMMON.charm.cursedTalismanCritDamageAdd.get();
		return AttrAdder.of("cursed_talisman", L2DamageTracker.CRIT_DMG::get,
				AttributeModifier.Operation.ADDITION, cta);
	}

	private AttrAdder ctr() {
		double ctr = cursed_talisman_add * CAModConfig.COMMON.charm.cursedTalismanCritRateAdd.get();
		return AttrAdder.of("cursed_talisman", L2DamageTracker.CRIT_RATE::get,
				AttributeModifier.Operation.ADDITION, ctr);
	}

	@Override
	protected void removeImpl(Player player) {
		dmg().removeImpl(player);
		ctr().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		cursed_talisman_add = EnchUtils.getTotalCurseEnch(player);
		dmg().tickImpl(player);
		ctr().tickImpl(player);
	}

}
