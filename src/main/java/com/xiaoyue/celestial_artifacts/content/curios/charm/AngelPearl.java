package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class AngelPearl extends BaseTickingToken {

	@SerialClass.SerialField
	private int angel_pearl_add;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift6", ChatFormatting.GOLD, angel_pearl_add * 8f + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.angel_pearl.shift7", ChatFormatting.GOLD, angel_pearl_add);

	}

	private AttrAdder reply() {
		double val = angel_pearl_add * 0.08;
		return AttrAdder.of("angel_pearl", CCAttributes.REPLY_POWER, AttributeModifier.Operation.ADDITION, val);
	}

	private AttrAdder armor() {
		double val = angel_pearl_add;
		return AttrAdder.of("angel_pearl", () -> Attributes.ARMOR, AttributeModifier.Operation.ADDITION, val);

	}

	@Override
	protected void removeImpl(Player player) {
		reply().removeImpl(player);
		armor().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		angel_pearl_add = EntityUtils.getBeneficialEffect(player);
		reply().tickImpl(player);
		armor().tickImpl(player);
		if (player.tickCount % 40 == 0) {
			List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2, living -> living instanceof Player);
			for (LivingEntity list : entities) {
				EntityUtils.addEct(list, MobEffects.REGENERATION, 30, 0);
			}
		}
	}

}
