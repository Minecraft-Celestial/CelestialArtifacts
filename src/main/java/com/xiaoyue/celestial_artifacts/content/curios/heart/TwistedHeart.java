package com.xiaoyue.celestial_artifacts.content.curios.heart;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class TwistedHeart extends BaseTickingToken implements CAAttackToken {

	private static double damageFactor() {
		return CAModConfig.COMMON.heart.twistedHeartDamage.get();
	}

	private static double toughnessFactor() {
		return CAModConfig.COMMON.heart.twistedHeartToughness.get();
	}

	private boolean bonus(Player player) {
		return CatastropheScroll.Curses.NIHILITY.cursing(player);
	}

	private AttrAdder armor(Player player) {
		return AttrAdder.of("twisted_heart", () -> Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.MULTIPLY_TOTAL, bonus(player) ? TwistedHeart::toughnessFactor : () -> -toughnessFactor());
	}

	private AttrAdder attack(Player player) {
		return AttrAdder.of("twisted_heart", () -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.MULTIPLY_TOTAL, bonus(player) ? TwistedHeart::damageFactor : () -> -damageFactor());
	}

	@Override
	protected void removeImpl(Player player) {
		armor(player).removeImpl(player);
		attack(player).removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		armor(player).tickImpl(player);
		attack(player).tickImpl(player);
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Heart.TWISTED_HEART_1.get(TextFacet.perc(damageFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Heart.TWISTED_HEART_2.get(TextFacet.perc(toughnessFactor())).withStyle(ChatFormatting.GRAY)));
		list.add(TextFacet.wrap(CALang.Heart.TWISTED_HEART_3.get().withStyle(ChatFormatting.GRAY)));
	}

}
