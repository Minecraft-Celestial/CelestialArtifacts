package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class CursedTotem extends BaseTickingToken
		implements NetworkSensitiveToken<CursedTotem>, CAAttackToken {//TODO check

	public static final TokenFacet<CursedTotem> TOKEN = new TokenFacet<>("cursed_totem", CursedTotem::new);

	@SerialClass.SerialField
	public int cursed_soul_totem;

	private static int addLevel() {
		return CAModConfig.COMMON.charm.curtemlevels.get();
	}

	private static int maxLevel() {
		return CAModConfig.COMMON.charm.curtemlvlimit.get();
	}

	private static int consume() {
		return CAModConfig.COMMON.charm.curtomconsume.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_1.get()));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_2.get(TextFacet.num(addLevel()) , TextFacet.num(maxLevel()))));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_3.get(TextFacet.num(consume()))));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_1.get(TextFacet.num(cursed_soul_totem))));
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {

	}

	@Override
	public void onPlayerDamaged(Player player, AttackCache cache) {
		cache.addDealtModifier(DamageModifier.nonlinearFinal(1234, v -> parse(player, v)));
	}

	private float parse(Player player, float v) {
		if (v < player.getHealth()) {
			if (cursed_soul_totem < maxLevel()) {
				cursed_soul_totem += addLevel();
				sync(TOKEN.getKey(), this, (ServerPlayer) player);
			}
			return v;
		}
		if (cursed_soul_totem <= 0) return v;
		cursed_soul_totem -= consume();
		sync(TOKEN.getKey(), this, (ServerPlayer) player);
		return 0;
	}

	@Override
	public void onSync(@Nullable CursedTotem cursedTotem, Player player) {

	}

}
