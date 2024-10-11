package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EnchUtils;
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

	private static double crit() {
		return CAModConfig.COMMON.charm.cursedTalismanCritRateAdd.get();
	}

	private static double critDamage() {
		return CAModConfig.COMMON.charm.cursedTalismanCritDamageAdd.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TALISMAN_1.get()));
		list.add(TextFacet.inner(ctr().getText(crit())));
		list.add(TextFacet.inner(dmg().getText(critDamage())));

		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.DARK_PURPLE));
		list.add(TextFacet.wrap(ctr().getTooltip()));
		list.add(TextFacet.wrap(dmg().getTooltip()));
	}

	private AttrAdder dmg() {
		double cta = cursed_talisman_add * critDamage();
		return AttrAdder.of("cursed_talisman", L2DamageTracker.CRIT_DMG::get,
				AttributeModifier.Operation.ADDITION, cta);
	}

	private AttrAdder ctr() {
		double ctr = cursed_talisman_add * crit();
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
