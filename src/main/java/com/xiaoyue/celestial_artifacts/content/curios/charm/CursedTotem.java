package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class CursedTotem extends BaseTickingToken
		implements NetworkSensitiveToken<CursedTotem>, CAAttackToken {

	public static final TokenFacet<CursedTotem> TOKEN = new TokenFacet<>("cursed_totem", CursedTotem::new);

	@SerialClass.SerialField
	public int cursed_soul_totem;

	@Override
	public void addText(@Nullable Level level, List<Component> list) {//TODO text
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.cursed_totem.shift4",
				ChatFormatting.LIGHT_PURPLE, cursed_soul_totem);
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
			if (cursed_soul_totem < 5) {
				cursed_soul_totem++;
				sync(TOKEN.getKey(), this, (ServerPlayer) player);
			}
			return v;
		}
		if (cursed_soul_totem <= 0) return v;
		cursed_soul_totem--;
		sync(TOKEN.getKey(), this, (ServerPlayer) player);
		return 0;
	}

	@Override
	public void onSync(@Nullable CursedTotem cursedTotem, Player player) {

	}

}
