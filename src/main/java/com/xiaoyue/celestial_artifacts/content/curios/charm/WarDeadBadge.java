package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class WarDeadBadge extends BaseTickingToken implements CAAttackToken {

	@SerialClass.SerialField
	public float war_dead_badge_add;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift6",
				ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 200 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift7",
				ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 200 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift8",
				ChatFormatting.LIGHT_PURPLE, war_dead_badge_add * 100 + "%");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift9");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift10");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift11");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.war_dead_badge.shift12");
	}

	private AttrAdder atk() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add * 2);
	}

	private AttrAdder tough() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.ARMOR_TOUGHNESS,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add * 2);
	}

	private AttrAdder speed() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.MOVEMENT_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add);
	}

	@Override
	protected void removeImpl(Player player) {
		atk().removeImpl(player);
		tough().removeImpl(player);
		speed().removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		war_dead_badge_add = 1 - player.getHealth() / player.getMaxHealth();
		atk().tickImpl(player);
		tough().tickImpl(player);
		speed().tickImpl(player);
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (CurioUtils.isCsOn(player) && !CurioUtils.hasCurio(player, CAItems.CHAOTIC_ETCHING.get())) {
			if (player.getHealth() < player.getMaxHealth() * 0.1f) {
				List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2);
				player.heal((player.getMaxHealth() - player.getHealth()) * 0.01f * entities.size());
			}
		}
	}

}
