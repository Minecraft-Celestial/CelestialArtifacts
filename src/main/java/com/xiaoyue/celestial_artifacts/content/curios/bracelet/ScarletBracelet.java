package com.xiaoyue.celestial_artifacts.content.curios.bracelet;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScarletBracelet implements MultiLineText, CAAttackToken {

	private static double threshold() {
		return 0.5;
	}

	private static double maxDamage() {
		return 0.5;
	}

	private static double damageBoost() {
		return 0.0001;
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Bracelet.SCARLET_0.get(TextFacet.perc(threshold()))));
		list.add(TextFacet.wrap(CALang.Bracelet.SCARLET_1.get(
				TextFacet.percSmall(damageBoost()), TextFacet.perc(maxDamage())
		)));
	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		float health = player.getMaxHealth() * (float) threshold();
		if (player.getHealth() > health) {
			float cost = player.getHealth() - health;
			player.setHealth(health);
			var e = cache.getAttackTarget();
			float cap = (float) Math.min(maxDamage(), cost * damageBoost());
			cache.addHurtModifier(DamageModifier.addExtra(e.getMaxHealth() * cap));
		}

	}
}
