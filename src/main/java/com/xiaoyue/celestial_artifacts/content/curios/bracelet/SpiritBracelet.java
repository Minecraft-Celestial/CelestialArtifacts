package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritBracelet implements MultiLineText, CAAttackToken {

	private static int duration() {
		return CAModConfig.COMMON.bracelet.spiritBraceletEffectDuration.get();
	}

	private static MobEffectInstance eff() {
		return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration() * 20, 0);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Bracelet.SPIRIT_0.get(TextFacet.perc(1))));
		list.add(TextFacet.wrap(CALang.Bracelet.SPIRIT_1.get(EffectFacet.getDesc(eff()))));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (!CAAttackToken.isArrow(cache)) return;
		player.addEffect(eff());
	}

}
