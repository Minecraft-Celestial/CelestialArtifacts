package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
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

	private static int deBuffTime() {
		return CAModConfig.COMMON.charm.corruptBadgeDebuffDuration.get();
	}

	private static double digSpeed() {
		return CAModConfig.COMMON.charm.corruptBadgeDigSpeedBonus.get();
	}

	private static double atkSpeed() {
		return CAModConfig.COMMON.charm.corruptBadgeAttackSpeedBonus.get();
	}

	private static double damage() {
		return CAModConfig.COMMON.charm.corruptBadgeAttackBonus.get();
	}

	private static int cooldown() {
		return CAModConfig.COMMON.charm.corruptBadgeCooldown.get();
	}

	private static MobEffectInstance ins0() {
		return new MobEffectInstance(MobEffects.POISON, deBuffTime() * 20, 0);
	}

	private static MobEffectInstance ins1() {
		return new MobEffectInstance(MobEffects.UNLUCK, deBuffTime() * 20, 0);
	}

	private static MobEffectInstance ins2() {
		return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, deBuffTime() * 20, 0);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_3.get()));
		list.add(TextFacet.inner(AttrFacet.textMult(CALang.Modular.DIG_SPEED.get(), digSpeed())));
		list.add(TextFacet.inner(speed().getText(atkSpeed())));
		list.add(TextFacet.inner(atk().getText(damage())));

		list.add(CALang.Modular.SKILL.get().withStyle(ChatFormatting.YELLOW));
		list.add(TextFacet.wrap(CALang.Modular.SKILL_CD.get(TextFacet.num(cooldown()))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_2.get(
				EffectFacet.getDesc(ins0()),
				EffectFacet.getDesc(ins1()),
				EffectFacet.getDesc(ins2())
		)));

		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.DARK_PURPLE));
		list.add(TextFacet.wrap(AttrFacet.simpleMult(CALang.Modular.DIG_SPEED.get(), digSpeed() * corrupt_badge_add)));
		list.add(TextFacet.wrap(speed().getTooltip()));
		list.add(TextFacet.wrap(atk().getTooltip()));
	}

	private AttrAdder atk() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * damage());
	}

	private AttrAdder speed() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * atkSpeed());
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
			player.addEffect(ins0());
			player.addEffect(ins1());
			player.addEffect(ins2());
			player.getCooldowns().addCooldown(item, cooldown() * 20);
		}
	}

	@Override
	public double getBreakFactor(Player player) {
		return 1 + corrupt_badge_add * digSpeed();
	}

}
