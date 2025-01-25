package com.xiaoyue.celestial_artifacts.content.curios.back;

import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.core.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2library.capability.conditionals.NetworkSensitiveToken;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class TwistedScabbard extends BaseTickingToken
		implements NetworkSensitiveToken<TwistedScabbard>, CAAttackToken {

	public static final TokenFacet<TwistedScabbard> TOKEN = new TokenFacet<>("twisted_scabbard", TwistedScabbard::new);
	@SerialClass.SerialField
	public int twisted_scabbard_add, timer;

	private static int interval() {
		return CAModConfig.COMMON.back.twistedScabbardInterval.get();
	}

	private static double atk() {
		return CAModConfig.COMMON.back.twistedScabbardAttack.get();
	}

	private static double endAtk() {
		return CAModConfig.COMMON.back.twistedScabbardAttackEnd.get();
	}

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		twisted_scabbard_add++;
		timer = 0;
		if (player instanceof ServerPlayer sp)
			sync(TOKEN.getKey(), this, sp);

	}

	private AttrAdder attr(Player player) {
		return AttrAdder.of("twisted_scabbard", () -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, () -> getVal(player));
	}

	private double getVal(Player player) {
		double factor = atk();
		if (CatastropheScroll.Curses.END.cursing(player)) {
			factor = endAtk();
		}
		return 1 + twisted_scabbard_add * factor;
	}

	@Override
	protected void removeImpl(Player player) {
		attr(player).removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		if (!(player instanceof ServerPlayer sp)) return;
		attr(player).tickImpl(player);
		timer++;
		if (timer >= interval() * 20) {
			timer = 0;
			if (twisted_scabbard_add > 0) {
				twisted_scabbard_add--;
				sync(TOKEN.getKey(), this, sp);
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Back.TWIST_0.get()));
		list.add(TextFacet.wrap(CALang.Back.TWIST_1.get(TextFacet.num(interval()))));
		list.add(TextFacet.wrap(CALang.Back.TWIST_2.get(TextFacet.perc(atk()))));
		list.add(TextFacet.inner(CALang.Back.TWIST_3.get(
				CALang.Curse.END_TITLE.get().withStyle(ChatFormatting.RED),
				TextFacet.perc(endAtk())
		)));

		list.add(TextFacet.wrap(CALang.Back.TWIST_4.get(
				TextFacet.num(twisted_scabbard_add)
		).withStyle(ChatFormatting.DARK_PURPLE)));
	}

	@Override
	public void onSync(@Nullable TwistedScabbard old, Player player) {

	}

}
