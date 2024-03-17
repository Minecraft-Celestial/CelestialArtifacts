package com.xiaoyue.celestial_artifacts.content.curios.head;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritCrown implements MultiLineText, CAAttackToken {

	private static double proj() {
		return CAModConfig.COMMON.head.spiritCrownArrowDamageMultiplier.get();
	}

	private static double dist() {
		return CAModConfig.COMMON.head.spiritCrownDistanceDamage.get();
	}

	private static int count() {
		return CAModConfig.COMMON.head.spiritCrownMaxEntityCount.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Head.SPIRIT_CROWN_1.get(
				TextFacet.num(count()),
				TextFacet.perc(proj())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Head.SPIRIT_CROWN_2.get(TextFacet.perc(dist())).withStyle(ChatFormatting.GRAY)));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (!CAAttackToken.isArrow(cache)) return;
		List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 6, 2);
		if (entities.size() <= count()) {
			cache.addHurtModifier(DamageModifier.multTotal((float) (1 + proj())));
		}
		float distance = player.distanceTo(cache.getAttackTarget());
		cache.addHurtModifier(DamageModifier.multTotal((float) (1 + distance * dist())));
	}

}
