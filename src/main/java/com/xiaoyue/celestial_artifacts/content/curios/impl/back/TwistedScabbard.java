package com.xiaoyue.celestial_artifacts.content.curios.impl.back;

import com.xiaoyue.celestial_artifacts.content.curios.token.AttrAdder;
import com.xiaoyue.celestial_artifacts.content.curios.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
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
		double factor = 0.05f;
		if (CurioUtils.isCsOn(player) && !CurioUtils.hasCurio(player, CAItems.END_ETCHING.get())) {
			factor = 0.1f;
		}
		return 1 + twisted_scabbard_add * factor;
	}

	@Override
	protected void removeImpl(Player player) {
		attr(player).removeImpl(player);
	}

	@Override
	protected void tickImpl(Player player) {
		attr(player).tickImpl(player);
		timer++;
		if (timer >= 100) {
			timer = 0;
			if (twisted_scabbard_add > 0) {
				twisted_scabbard_add--;
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(Component.translatable("tooltip.celestial_artifacts.twisted_scabbard.shift4"));
		list.add(Component.translatable("tooltip.celestial_artifacts.twisted_scabbard.shift5"));
		list.add(Component.translatable("tooltip.celestial_artifacts.twisted_scabbard.shift6"));
		list.add(Component.translatable("tooltip.celestial_artifacts.twisted_scabbard.shift7"));
		list.add(Component.translatable("tooltip.celestial_artifacts.twisted_scabbard.shift8",
				twisted_scabbard_add).withStyle(ChatFormatting.LIGHT_PURPLE));
	}

	@Override
	public void onSync(@Nullable TwistedScabbard twistedScabbard, Player player) {

	}

}
