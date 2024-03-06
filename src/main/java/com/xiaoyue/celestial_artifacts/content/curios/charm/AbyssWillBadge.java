package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class AbyssWillBadge extends BaseTickingToken
		implements NetworkSensitiveToken<AbyssWillBadge>, CAAttackToken, SkillFeature {

	public static final TokenFacet<AbyssWillBadge> TOKEN = new TokenFacet<>("abyss_will_badge", AbyssWillBadge::new);


	@SerialClass.SerialField
	public int abyss_will_badge_add;
	@SerialClass.SerialField
	public float abyss_will_badge_damage;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift6");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.abyss_will_badge.shift7",
				ChatFormatting.DARK_AQUA, abyss_will_badge_add);
	}

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			abyss_will_badge_add = 15;
			sync(TOKEN.getKey(), this, player);
			player.getCooldowns().addCooldown(item, 300);
		}
	}

	@Override
	protected void tickImpl(Player player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (player.tickCount % 400 == 0) {
			if (abyss_will_badge_add < 10) {
				abyss_will_badge_add++;
			}
		}
		if (!player.getCooldowns().isOnCooldown(item)) {
			if (abyss_will_badge_add > 11) {
				abyss_will_badge_add = 0;
			}
			if (abyss_will_badge_damage != 0) {
				if (!player.level().isClientSide())
					player.setHealth(player.getHealth() * 0.2f);
				abyss_will_badge_damage = 0;
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		double random = player.getRandom().nextDouble();
		float bonus;
		if (0.1 > random) {
			bonus = 2;
		} else if (0.1 < random && 0.5 > random) {
			bonus = 1.5f;
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= 1 + (abyss_will_badge_add * 0.2f);
		}
		cache.addHurtModifier(DamageModifier.multTotal(bonus));
	}

	@Override
	public void onPlayerDamageTargetFinal(Player player, AttackCache cache) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (player.getCooldowns().isOnCooldown(item)) {
			abyss_will_badge_damage += cache.getDamageDealt();
			sync(TOKEN.getKey(), this, (ServerPlayer) player);
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		double random = player.getRandom().nextDouble();
		float bonus;
		if (0.1 > random) {
			bonus = 2.5f;
		} else if (0.1 < random && 0.5 > random) {
			bonus = 1.5f;
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= 1 + abyss_will_badge_add * 0.25f;
		}
		cache.addDealtModifier(DamageModifier.multTotal(bonus));
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	public void onSync(@Nullable AbyssWillBadge abyssWillBadge, Player player) {

	}

}
