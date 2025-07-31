package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
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
public class AbyssWillBadge extends BaseTickingToken implements NetworkSensitiveToken<AbyssWillBadge>, CAAttackToken, SkillFeature {

	public static final TokenFacet<AbyssWillBadge> TOKEN = new TokenFacet<>("abyss_will_badge", AbyssWillBadge::new);

	@SerialClass.SerialField
	public int abyss_will_badge_add;

	@SerialClass.SerialField
	public float abyss_will_badge_damage;

	private static int initial() {
		return CAModConfig.COMMON.charm.abyssWillBadgeInitialLevel.get();
	}

	private static int duration() {
		return CAModConfig.COMMON.charm.abyssWillBadgeDuration.get();
	}

	private static double remainHP() {
		return CAModConfig.COMMON.charm.abyssWillBadgeRemainingHealth.get();
	}

	private static double pa() {
		return CAModConfig.COMMON.charm.abyssWillBadgeChanceSmall.get();
	}

	private static double pb() {
		return CAModConfig.COMMON.charm.abyssWillBadgeChanceLarge.get();
	}

	private static double atkA() {
		return CAModConfig.COMMON.charm.abyssWillBadgeBonusSmall.get();
	}

	private static double atkB() {
		return CAModConfig.COMMON.charm.abyssWillBadgeBonusLarge.get();
	}

	private static double dmgA() {
		return CAModConfig.COMMON.charm.abyssWillBadgePenaltySmall.get();
	}

	private static double dmgB() {
		return CAModConfig.COMMON.charm.abyssWillBadgePenaltyLarge.get();
	}

	private static int interval() {
		return CAModConfig.COMMON.charm.abyssWillBadgeGrowInterval.get();
	}

	private static int limit() {
		return CAModConfig.COMMON.charm.abyssWillBadgeLimit.get();
	}

	private static double atkBonus() {
		return CAModConfig.COMMON.charm.abyssWillBadgeDamageBonus.get();
	}

	private static double hurtPenalty() {
		return CAModConfig.COMMON.charm.abyssWillBadgeDamagePenalty.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_3.get(TextFacet.perc(pa()), TextFacet.perc(atkA()),
				TextFacet.perc(pb()), TextFacet.perc(atkB()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_4.get(TextFacet.perc(pa()), TextFacet.perc(dmgB()),
				TextFacet.perc(pb()), TextFacet.perc(dmgA()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_5.get(TextFacet.num(interval()), TextFacet.num(limit()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_6.get(TextFacet.perc(atkBonus()), TextFacet.perc(hurtPenalty()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_7.get(TextFacet.num(abyss_will_badge_add)).withStyle(ChatFormatting.DARK_PURPLE)));
		list.add(CALang.Modular.SKILL.get().withStyle(ChatFormatting.YELLOW));
		list.add(TextFacet.wrap(CALang.Modular.SKILL_CD.get(TextFacet.num(duration()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_1.get(TextFacet.num(initial()), TextFacet.num(duration()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_2.get(TextFacet.perc(remainHP()))));
	}

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			abyss_will_badge_add = initial();
			sync(TOKEN.getKey(), this, player);
			player.getCooldowns().addCooldown(item, duration() * 20);
		}
	}

	@Override
	protected void tickImpl(Player player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (player.tickCount % (interval() * 20) == 0) {
			if (abyss_will_badge_add < limit()) {
				abyss_will_badge_add++;
			}
		}
		if (!player.getCooldowns().isOnCooldown(item)) {
			if (abyss_will_badge_add > limit() + 1) {
				abyss_will_badge_add = 0;
			}
			if (abyss_will_badge_damage != 0) {
				if (!player.level().isClientSide())
					player.setHealth((float) (player.getHealth() * remainHP()));
				abyss_will_badge_damage = 0;
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		double random = player.getRandom().nextDouble();
		float bonus;
		if (pb() > random) {
			bonus = (float) atkA();
		} else if (pb() < random && pb() + pa() > random) {
			bonus = (float) atkB();
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= (float) (1 + (abyss_will_badge_add * atkBonus()));
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
		if (pa() > random) {
			bonus = (float) dmgB();
		} else if (pb() < random && pa() + pb() > random) {
			bonus = (float) dmgA();
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= (float) (1 + abyss_will_badge_add * hurtPenalty());
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
