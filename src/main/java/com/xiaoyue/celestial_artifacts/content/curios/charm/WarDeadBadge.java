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

	private static double damageFactor() {
		return CAModConfig.COMMON.charm.warDeadBadgeAtk.get();
	}

	private static double toughnessFactor() {
		return CAModConfig.COMMON.charm.warDeadBadgeArmor.get();
	}

	private static double speedFactor() {
		return CAModConfig.COMMON.charm.warDeadBadgeSpeed.get();
	}

	private static double getThreshold() {
		return CAModConfig.COMMON.charm.warDeadBadgeThreshold.get();
	}

	private static double healFactor() {
		return CAModConfig.COMMON.charm.warDeadBadgeHeal.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_1.get()));
		list.add(TextFacet.inner(atk().getText(damageFactor())));
		list.add(TextFacet.inner(tough().getText(toughnessFactor())));
		list.add(TextFacet.inner(speed().getText(speedFactor())));
		list.add(TextFacet.wrap(CALang.Charm.WAR_DEAD_BADGE_9.get(TextFacet.perc(getThreshold()))));
		list.add(TextFacet.inner(CALang.Charm.WAR_DEAD_BADGE_11.get(TextFacet.perc(healFactor()))));

		list.add(CALang.Modular.CURRENT_BONUS.get().withStyle(ChatFormatting.DARK_PURPLE));
		list.add(TextFacet.wrap(atk().getTooltip()));
		list.add(TextFacet.wrap(tough().getTooltip()));
		list.add(TextFacet.wrap(speed().getTooltip()));
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
			if (player.getHealth() < player.getMaxHealth() * getThreshold()) {
				List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 2);
				player.heal((float) ((player.getMaxHealth() - player.getHealth()) * healFactor() * entities.size()));
			}
		}
	}

}
