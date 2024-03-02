package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.function.IntSupplier;

public record InvulToken(IntSupplier sup) implements SingleLineText, CAAttackToken {

	public static InvulToken of(IntSupplier sup) {
		return new InvulToken(sup);
	}

	@Override
	public MutableComponent getLine() {
		return CALang.Modular.INVUL_TIME.get(TextFacet.perc(sup.getAsInt() * 0.1));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		player.invulnerableTime += sup.getAsInt();
	}

}
