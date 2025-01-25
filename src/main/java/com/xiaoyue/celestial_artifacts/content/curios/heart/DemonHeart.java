package com.xiaoyue.celestial_artifacts.content.curios.heart;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
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

	private static double damageBonusFactor() {
		return CAModConfig.COMMON.heart.demonHeartDamageBonus.get();
	}

	private static double damageReductionFactor() {
		return CAModConfig.COMMON.heart.demonHeartDamageReduction.get();
	}

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
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Heart.DEMON_HEART_1.get().withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Heart.DEMON_HEART_2.get(TextFacet.num(1)).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Heart.DEMON_HEART_3.get(TextFacet.perc(damageBonusFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.inner(CALang.Heart.DEMON_HEART_4.get(TextFacet.perc(damageReductionFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Heart.DEMON_HEART_5.get().withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Heart.DEMON_HEART_6.get().withStyle(ChatFormatting.GRAY)));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		cache.addHurtModifier(DamageModifier.multTotal((float) (1 + CurioUtils.getCurseAmount(player) * damageBonusFactor())));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		cache.addDealtModifier(DamageModifier.multTotal((float) (1 - CurioUtils.getCurseAmount(player) * damageReductionFactor())));
	}

}
