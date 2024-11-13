package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.AttrTextFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.IntSupplier;

public record InvulToken(IntSupplier sup) implements AttrTextFacet, CAAttackToken {

	public static InvulToken of(IntSupplier sup) {
		return new InvulToken(sup);
	}

	@Override
	public void addAttrText(List<Component> list) {
		list.add(AttrFacet.simpleMult(CALang.Modular.INVUL_TIME.get(), sup.getAsInt() * 0.1));
	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		player.invulnerableTime += sup.getAsInt();
	}

}
