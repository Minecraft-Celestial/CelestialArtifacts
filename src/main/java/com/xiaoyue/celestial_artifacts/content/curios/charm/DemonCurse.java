package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class DemonCurse extends BaseTickingToken {

	@SerialClass.SerialField
	public double demon_curse_add;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift6",
				ChatFormatting.LIGHT_PURPLE, demon_curse_add * 200 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_curse.shift7",
				ChatFormatting.LIGHT_PURPLE, demon_curse_add * 100 + "%");
	}

	private AttrAdder atk() {
		return AttrAdder.of("demon_curse", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, demon_curse_add * 2);
	}

	private AttrAdder speed() {
		return AttrAdder.of("demon_curse", () -> Attributes.MOVEMENT_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, demon_curse_add);
	}

	@Override
	protected void removeImpl(Player player) {
		atk().removeImpl(player);
		speed().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		demon_curse_add = (player.getAttributeValue(CCAttributes.REPLY_POWER.get()) - 1) * 10;
		atk().tickImpl(player);
		speed().tickImpl(player);
	}

}
