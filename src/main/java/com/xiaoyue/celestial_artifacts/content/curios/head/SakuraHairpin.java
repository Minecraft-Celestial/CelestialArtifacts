package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
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
public class SakuraHairpin extends BaseTickingToken {

	@SerialClass.SerialField
	public double sakura_hairpin_bonus;

	private static double critBonus() {
		return CAModConfig.COMMON.head.sakuraHairpinCritBonusFromLuck.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Head.SAKURA_HAIRPIN.get()));
		list.add(TextFacet.inner(crit().getText(critBonus())));
		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.LIGHT_PURPLE));
		list.add(TextFacet.wrap(crit().getTooltip()));
	}

	private AttrAdder crit() {
		return AttrAdder.of("sakura_hairpin", L2DamageTracker.CRIT_RATE::get,
				AttributeModifier.Operation.ADDITION, sakura_hairpin_bonus);
	}

	@Override
	protected void removeImpl(Player player) {
		crit().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		sakura_hairpin_bonus = player.getLuck() * critBonus();
		crit().tickImpl(player);
	}
}
