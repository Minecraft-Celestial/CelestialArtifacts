package com.xiaoyue.celestial_artifacts.content.curios.heart;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class TwistedHeart extends BaseTickingToken implements CAAttackToken {

	private boolean bonus(Player player) {
		return CatastropheScroll.Curses.NIHILITY.cursing(player);
	}

	private AttrAdder armor(Player player) {
		return AttrAdder.of("twisted_heart", () -> Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.MULTIPLY_TOTAL, bonus(player) ? 0.2 : -0.2);
	}

	private AttrAdder attack(Player player) {
		return AttrAdder.of("twisted_heart", () -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.MULTIPLY_TOTAL, bonus(player) ? 0.33 : -0.33);
	}

	@Override
	protected void removeImpl(Player player) {
		armor(player).removeImpl(player);
		attack(player).removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		armor(player).tickImpl(player);
		attack(player).tickImpl(player);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_heart.shift3");
	}

}
