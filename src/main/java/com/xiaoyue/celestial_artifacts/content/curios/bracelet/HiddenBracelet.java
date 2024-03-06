package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HiddenBracelet implements MultiLineText, TickFacet, CAAttackToken {

	public static int getDur() {
		return CAModConfig.COMMON.bracelet.hiddenBraceletEffectDuration.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift1",
				ChatFormatting.LIGHT_PURPLE, getDur());
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.hidden_bracelet.shift4");
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity.tickCount % (getDur() * 20) == 0) {
			EntityUtils.addEct(entity, CCEffects.HIDDEN.get(), 40, 0);
		}
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		if (player.hasEffect(CCEffects.HIDDEN.get())) {
			cache.addHurtModifier(DamageModifier.multTotal(1.25f));
		}
		if (player.hasEffect(CCEffects.HIDDEN.get())) {
			player.removeEffect(CCEffects.HIDDEN.get());
		}
	}

	@Override
	public boolean onPlayerAttacked(Player player, AttackCache cache) {
		var source = CAAttackToken.getSource(cache);
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ||
				source.is(DamageTypeTags.BYPASSES_EFFECTS))
			return false;
		if (cache.getAttacker() != null && player.hasEffect(CCEffects.HIDDEN.get())) {
			player.removeEffect(CCEffects.HIDDEN.get());
			return true;
		}
		return false;
	}

}
