package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
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

	private static int deBuffTime() {
		return CAModConfig.COMMON.charm.corbadDEBUFF.get();
	}

	private static int deBuff() {
		return CAModConfig.COMMON.charm.corbadDEBUFFNur.get();
	}

	private static double digSpeed() {
		return CAModConfig.COMMON.charm.corbaddigrate.get();
	}

	private static double atkSpeed() {
		return CAModConfig.COMMON.charm.corbadattack.get();
	}

	private static double damage() {
		return CAModConfig.COMMON.charm.corbaddamage.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_2.get(TextFacet.num(deBuffTime() * 20))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_3.get(TextFacet.num(deBuff()))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_4.get(TextFacet.perc(digSpeed()))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_5.get(TextFacet.perc(atkSpeed()))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_6.get(TextFacet.perc(damage()))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_7.get()));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_8.get(TextFacet.perc(digSpeed() * corrupt_badge_add))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_9.get(TextFacet.perc(atkSpeed() * corrupt_badge_add))));
		list.add(TextFacet.wrap(CALang.Charm.CORRUPT_BADGE_10.get(TextFacet.perc(damage() * corrupt_badge_add))));
	}

	private AttrAdder atk() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * damage() / deBuff());
	}

	private AttrAdder speed() {
		return AttrAdder.of("currupt_badge", () -> Attributes.ATTACK_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, corrupt_badge_add * atkSpeed() / deBuff());
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
			EntityUtils.addEct(player, MobEffects.POISON, deBuffTime() * 20, 0);
			EntityUtils.addEct(player, MobEffects.UNLUCK, deBuffTime() * 20, 0);
			EntityUtils.addEct(player, MobEffects.MOVEMENT_SLOWDOWN, deBuffTime() * 20, 0);
			player.getCooldowns().addCooldown(item, 1200);
		}
	}

	@Override
	public double getBreakFactor(Player player) {
		return 1 + corrupt_badge_add * digSpeed();
	}

}
