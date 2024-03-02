package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class CorruptBadge extends BaseTickingToken implements SkillFeature, BreakSpeedFeature {

	@SerialClass.SerialField
	public int corrupt_badge_add;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift6");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift7");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift8",
				ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 14 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift9",
				ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 3 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.corrupt_badge.shift10",
				ChatFormatting.LIGHT_PURPLE, corrupt_badge_add * 9 + "%");
	}

	private AttrAdder atk() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * 0.14);
	}

	private AttrAdder speed() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * 0.03);
	}

	@Override
	protected void removeImpl(Player player) {
		atk().removeImpl(player);
		speed().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		corrupt_badge_add = EntityUtils.getHarmfulEffect(player);
		atk().tickImpl(player);
		speed().tickImpl(player);
	}

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.CORRUPT_BADGE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			EntityUtils.addEct(player, MobEffects.POISON, 300, 0);
			EntityUtils.addEct(player, MobEffects.UNLUCK, 300, 0);
			EntityUtils.addEct(player, MobEffects.MOVEMENT_SLOWDOWN, 300, 0);
			player.getCooldowns().addCooldown(item, 1200);
		}
	}

	@Override
	public double getBreakFactor(Player player) {
		return 1 + corrupt_badge_add * 0.09f;
	}

}
