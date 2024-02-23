package com.xiaoyue.celestial_artifacts.content.curios.impl.set;

import com.xiaoyue.celestial_artifacts.content.curios.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class SpiritSet extends BaseTickingToken implements CAAttackToken {

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {
		if (player.isUsingItem()) {
			if (CurioUtils.isRangeUseAnim(player.getUseItem().getUseAnimation())) {
				if (player.getTicksUsingItem() > 60) {
					EntityUtils.addEct(player, CCEffects.ARROW_DAMAGE.get(), 70, 0);
				}
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		var event = cache.getLivingHurtEvent();
		assert event != null;
		var direct = event.getSource().getDirectEntity();
		if (direct instanceof AbstractArrow) {
			if (EntityUtils.isLookingBehindTarget(cache.getAttackTarget(), player.getEyePosition())) {
				event.setAmount(event.getAmount() * 1.5f);
			}
			if (player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
				if (player.getRandom().nextDouble() > 0.5) {
					EntityUtils.addEct(cache.getAttackTarget(), MobEffects.MOVEMENT_SLOWDOWN, 50, 1);
				}
			}
		}
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var event = cache.getLivingAttackEvent();
		assert event != null;
		var source = event.getSource();
		if (LevelUtils.sourceIsPhysics(source)) {
			if (player.getRandom().nextDouble() < 0.2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		var event = cache.getLivingDamageEvent();
		assert event != null;
		var source = event.getSource();
		if (LevelUtils.sourceIsPhysics(source)) {
			cache.addDealtModifier(DamageModifier.multTotal(0.8f));
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt6");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.spirit.alt7");
	}
}
