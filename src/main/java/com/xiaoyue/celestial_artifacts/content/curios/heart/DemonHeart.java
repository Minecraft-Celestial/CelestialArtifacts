package com.xiaoyue.celestial_artifacts.content.curios.heart;

import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class DemonHeart extends BaseTickingToken implements CAAttackToken {

	protected AttrAdder attr(Player player) {
		return AttrAdder.of("demon_heart", () -> Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADDITION,
				() -> CurioUtils.getCurseAmount(player));
	}

	@Override
	protected void removeImpl(Player player) {
		attr(player).removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		attr(player).tickImpl(player);
		int curse = CurioUtils.getCurseAmount(player);
		if (curse >= 3) {
			if (player.isOnFire()) {
				player.clearFire();
			}
		}
		if (curse >= 5) {
			if (player.hasEffect(MobEffects.WEAKNESS)) {
				player.removeEffect(MobEffects.WEAKNESS);
			}
			if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
				player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.demon_heart.shift6");
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		cache.addHurtModifier(DamageModifier.multTotal(1 + CurioUtils.getCurseAmount(player) * 0.1f));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		cache.addDealtModifier(DamageModifier.multTotal(1 - CurioUtils.getCurseAmount(player) * 0.05f));
	}

}
