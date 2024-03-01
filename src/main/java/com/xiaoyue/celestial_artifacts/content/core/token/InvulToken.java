package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.content.core.modular.IFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.SingleLineText;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.function.IntSupplier;

public record InvulToken(IntSupplier sup) implements SingleLineText, CAAttackToken {

	public static InvulToken of(IntSupplier sup) {
		return new InvulToken(sup);
	}

	@Override
	public Component getLine() {
		return CALang.Modular.INVUL_TIME.get(
				Component.literal(sup.getAsInt() * 10 + "%")
						.withStyle(ChatFormatting.AQUA)
						.withStyle(ChatFormatting.GRAY));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		player.invulnerableTime += sup.getAsInt();
	}

}
