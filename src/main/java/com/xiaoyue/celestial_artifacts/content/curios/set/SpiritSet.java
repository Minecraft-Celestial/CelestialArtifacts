package com.xiaoyue.celestial_artifacts.content.curios.set;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.base.effects.EffectUtil;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class SpiritSet extends BaseTickingToken implements CAAttackToken {

	private static int getEffectThreshold() {
		return CAModConfig.COMMON.set.spiritPullDuration.get();
	}

	private static double getBackShootDamage() {
		return CAModConfig.COMMON.set.spiritBackShootBonus.get();
	}

	private static double getInflictChance() {
		return CAModConfig.COMMON.set.spiritInflictChance.get();
	}

	private static double getProtect() {
		return CAModConfig.COMMON.set.spiritProtect.get();
	}

	private static CALang.DamageTypes type() {
		return CALang.DamageTypes.PROJECTILE;
	}

	private static MobEffect getTrigger() {
		return MobEffects.MOVEMENT_SPEED;
	}

	private static MobEffectInstance getPullEff() {
		return new MobEffectInstance(CCEffects.ARROW_DAMAGE.get(),
				40, 0, true, true);
	}

	private static MobEffectInstance getInflictEff() {
		return new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,
				CAModConfig.COMMON.set.spiritEffectDuration.get() * 20,
				CAModConfig.COMMON.set.spiritEffectAmplifier.get());
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {
		if (player.isUsingItem()) {
			if (CurioUtils.isRangeUseAnim(player.getUseItem().getUseAnimation())) {
				if (player.getTicksUsingItem() >= getEffectThreshold() * 20) {
					EffectUtil.refreshEffect(player, getPullEff(), EffectUtil.AddReason.SELF, player);
				}
			}
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (!CAAttackToken.isArrow(cache)) return;
		if (EntityUtils.isLookingBehindTarget(cache.getAttackTarget(), player.getEyePosition())) {
			float factor = 1 + (float) getBackShootDamage();
			cache.addHurtModifier(DamageModifier.multTotal(factor));
		}
		if (player.hasEffect(getTrigger())) {
			if (CAAttackToken.chance(player, getInflictChance())) {
				cache.getAttackTarget().addEffect(getInflictEff());
			}
		}
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		if (player.hasEffect(getTrigger()) && CAAttackToken.isArrow(cache)) {
			float factor = 1 - (float) getProtect();
			cache.addDealtModifier(DamageModifier.multTotal(factor));
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.set(level, CAItems.spiritSet()));
		list.add(TextFacet.wrap(CALang.Sets.SPIRIT_0.get(
				TextFacet.num(getEffectThreshold()),
				EffectFacet.getDesc(getPullEff(), false)
		)).withStyle(ChatFormatting.GRAY));
		list.add(TextFacet.wrap(CALang.Sets.SPIRIT_1.get(
				type().get(), TextFacet.perc(getBackShootDamage())
		).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Sets.SPIRIT_2.get(
				TextFacet.eff(getTrigger())
		).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Sets.SPIRIT_3.get(
				TextFacet.perc(getInflictChance()),
				EffectFacet.getDesc(getInflictEff(), true)
		).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Modular.PROTECT_TYPE.get(
				type().get(), TextFacet.perc(getProtect())
		).withStyle(ChatFormatting.GRAY)));
	}

}
