package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class WarDeadBadge extends BaseTickingToken implements CAAttackToken {//TODO check

	@SerialClass.SerialField
	public float war_dead_badge_add;

	private static double damageFactor() {
		return CAModConfig.COMMON.charm.wardeadStrength.get();
	}

	private static double toughnessFactor() {
		return CAModConfig.COMMON.charm.wardeadArmor.get();
	}

	private static double speedFactor() {
		return CAModConfig.COMMON.charm.wardeadSpeed.get();
	}

	private static double healFactor() {
		return CAModConfig.COMMON.charm.wardeadBleeds.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_2.get(TextFacet.perc(damageFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_3.get(TextFacet.perc(toughnessFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_4.get(TextFacet.perc(speedFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_5.get()));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_6.get(TextFacet.perc(war_dead_badge_add * damageFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_7.get(TextFacet.perc(war_dead_badge_add * toughnessFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_8.get(TextFacet.perc(war_dead_badge_add * speedFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_9.get()));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_10.get()));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_11.get()));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_12.get(TextFacet.perc(healFactor()))));
	}

	private AttrAdder atk() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.ATTACK_DAMAGE,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add * damageFactor() * 100);
	}

	private AttrAdder tough() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.ARMOR_TOUGHNESS,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add * toughnessFactor() * 100);
	}

	private AttrAdder speed() {
		return AttrAdder.of("war_dead_badge", () -> Attributes.MOVEMENT_SPEED,
				AttributeModifier.Operation.MULTIPLY_BASE, war_dead_badge_add * speedFactor() * 100);
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
		if (CatastropheScroll.Curses.CHAOS.cursing(player)) {
			if (player.getHealth() < player.getMaxHealth() * 0.2f) {
				List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2);
				player.heal((float) ((player.getMaxHealth() - player.getHealth()) * healFactor() * entities.size()));
			}
		}
	}

}
