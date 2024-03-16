package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.SkillFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
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
public class AbyssWillBadge extends BaseTickingToken implements NetworkSensitiveToken<AbyssWillBadge>, CAAttackToken, SkillFeature {

	public static final TokenFacet<AbyssWillBadge> TOKEN = new TokenFacet<>("abyss_will_badge", AbyssWillBadge::new);

	@SerialClass.SerialField
	public int abyss_will_badge_add;
	@SerialClass.SerialField
	public float abyss_will_badge_damage;

	private static int triggerFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetrigger.get();
	}

	private static int durationFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgeduration.get();
	}

	private static double afterHealthFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgeremainingHP.get();
	}

	private static double triggerPAFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerPA.get();
	}

	private static double triggerPBFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerPB.get();
	}

	private static double triggerDA1Factor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerDA1.get();
	}

	private static double triggerDA2Factor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerDA2.get();
	}

	private static double triggerDB1Factor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerDB1.get();
	}

	private static double triggerDB2Factor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgetriggerDB2.get();
	}

	private static double developtimeFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgedeveloptime.get();
	}

	private static int developlimitFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgedeveloplimit.get();
	}

	private static double increasedrateFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgeincreasedrate.get();
	}

	private static double injuryrateFactor() {
		return CAModConfig.COMMON.charm.AbyssWillBadgeinjuryrate.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_2.get(TextFacet.num(triggerFactor()), TextFacet.num(durationFactor() * 20))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_3.get(TextFacet.perc(afterHealthFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_4.get(TextFacet.perc(triggerPBFactor()), TextFacet.perc(triggerDA1Factor()),
				TextFacet.perc(triggerPBFactor() + triggerPAFactor()), TextFacet.perc(triggerDA2Factor()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_5.get(TextFacet.perc(triggerPBFactor()), TextFacet.perc(triggerDB2Factor()),
				TextFacet.perc(triggerPBFactor() + triggerPAFactor()), TextFacet.perc(triggerDB1Factor()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_6.get(TextFacet.num((int) (developtimeFactor() * 20)), TextFacet.num(developlimitFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_7.get(TextFacet.perc(increasedrateFactor()), TextFacet.perc(injuryrateFactor()))));
		list.add(TextFacet.wrap(CALang.Charm.ABYSS_WILL_BADGE_8.get(TextFacet.num(abyss_will_badge_add))));
	}

	@Override
	public void trigger(ServerPlayer player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (!player.getCooldowns().isOnCooldown(item)) {
			abyss_will_badge_add = triggerFactor();
			sync(TOKEN.getKey(), this, player);
			player.getCooldowns().addCooldown(item, durationFactor() * 20);
		}
	}

	@Override
	protected void tickImpl(Player player) {
		var item = CAItems.ABYSS_WILL_BADGE.get();
		if (player.tickCount % developtimeFactor() * 20 == 0) {
			if (abyss_will_badge_add < developlimitFactor()) {
				abyss_will_badge_add++;
			}
		}
		if (!player.getCooldowns().isOnCooldown(item)) {
			if (abyss_will_badge_add > developtimeFactor() + 1) {
				abyss_will_badge_add = 0;
			}
			if (abyss_will_badge_damage != 0) {
				if (!player.level().isClientSide())
					player.setHealth((float) (player.getHealth() * afterHealthFactor()));
				abyss_will_badge_damage = 0;
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		double random = player.getRandom().nextDouble();
		float bonus;
		if (triggerPBFactor() > random) {
			bonus = (float) triggerDA1Factor();
		} else if (triggerPBFactor() < random && triggerPBFactor() + triggerPAFactor() > random) {
			bonus = (float) triggerDA2Factor();
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= (float) (1 + (abyss_will_badge_add * increasedrateFactor()));
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
		if (triggerPAFactor() > random) {
			bonus = (float) triggerDB2Factor();
		} else if (triggerPBFactor() < random && triggerPAFactor() + triggerPBFactor() > random) {
			bonus = (float) triggerDB1Factor();
		} else {
			bonus = 1;
		}
		if (abyss_will_badge_add > 0) {
			bonus *= (float) (1 + abyss_will_badge_add * injuryrateFactor());
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
