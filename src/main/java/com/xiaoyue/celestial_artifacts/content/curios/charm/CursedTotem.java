package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TotemFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2damagetracker.contents.attack.AttackCache;
import dev.xkmc.l2damagetracker.contents.curios.TotemHelper;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class CursedTotem extends BaseTickingToken
		implements NetworkSensitiveToken<CursedTotem>, CAAttackToken, TotemFacet {

	public static final TokenFacet<CursedTotem> TOKEN = new TokenFacet<>("cursed_totem", CursedTotem::new);

	@SerialClass.SerialField
	public int cursed_soul_totem;

	private static int maxLevel() {
		return CAModConfig.COMMON.charm.cursedTotemMaxLevel.get();
	}

	private static int consume() {
		return CAModConfig.COMMON.charm.cursedTotemConsumption.get();
	}

	private static int duration() {
		return CAModConfig.COMMON.charm.cursedTotemEffectDuration.get();
	}

	private static int amplifier() {
		return CAModConfig.COMMON.charm.cursedTotemEffectLevel.get();
	}

	public static MobEffectInstance eff() {
		return new MobEffectInstance(MobEffects.WITHER, duration() * 20, amplifier());
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_1.get(EffectFacet.getDesc(eff()))));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_2.get(TextFacet.num(maxLevel()))));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_3.get(TextFacet.num(consume()))));
		list.add(TextFacet.wrap(CALang.Charm.CURSED_TOTEM_4.get(TextFacet.num(cursed_soul_totem)).withStyle(ChatFormatting.DARK_PURPLE)));
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {
	}

	@Override
	public void onPlayerDamagedFinal(Player player, AttackCache cache) {
		if (cache.getDamageDealt() < player.getHealth())
			if (player instanceof ServerPlayer sp && cursed_soul_totem < maxLevel()) {
				cursed_soul_totem++;
				sync(TOKEN.getKey(), this, sp);
			}
	}

	@Override
	public void trigger(Player self, ItemStack holded, TotemHelper.TotemSlot second, DamageSource source) {
		TotemFacet.super.trigger(self, holded, second, source);
		if (self instanceof ServerPlayer sp) {
			cursed_soul_totem -= consume();
			sync(TOKEN.getKey(), this, sp);
		}
	}

	@Override
	public boolean allow(Player self, ItemStack stack, DamageSource source) {
		return cursed_soul_totem >= consume() && TotemFacet.super.allow(self, stack, source);
	}

	@Override
	public void onSync(@Nullable CursedTotem cursedTotem, Player player) {

	}

}
