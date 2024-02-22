package com.xiaoyue.celestial_artifacts.content.curios.impl.back;

import com.xiaoyue.celestial_artifacts.content.curios.token.BaseTickingToken;
import com.xiaoyue.celestial_artifacts.content.curios.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.curios.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.content.old.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
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
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SerialClass
public class TwistedScabbard extends BaseTickingToken
		implements NetworkSensitiveToken<TwistedScabbard>, CAAttackToken {

	public static final TokenFacet<TwistedScabbard> TOKEN = new TokenFacet<>("twisted_scabbard", TwistedScabbard::new);

	@SerialClass.SerialField
	public int twisted_scabbard_add;

	@Override
	public void onPlayerKill(Player player, LivingDeathEvent event) {
		twisted_scabbard_add++;
		if (player instanceof ServerPlayer sp)
			sync(TOKEN.getKey(), this, sp);

	}

	@Override
	public void onPlayerHurtTarget(Player player, AttackCache cache) {
		var event = cache.getLivingHurtEvent();
		assert event != null;
		var source = event.getSource();
		if (source.getDirectEntity() == player) {
			float factor = 0.05f;
			if (CurioUtils.isCsOn(player)) {
				if (CatastropheScroll.i_end == 0) {//TODO
					factor = 0.1f;
				}
			}
			factor = 1 + twisted_scabbard_add * factor;
			cache.addHurtModifier(DamageModifier.multTotal(factor));
		}
	}

	@Override
	protected void removeImpl(Player player) {

	}

	@Override
	protected void tickImpl(Player player) {
		if (player instanceof ServerPlayer sp) {
			if (player.tickCount % 100 == 0) {
				if (twisted_scabbard_add > 0) {
					twisted_scabbard_add--;
					sync(TOKEN.getKey(), this, sp);
				}
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift1");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift2");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift3");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift4");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift5");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift6");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift7");
		ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_artifacts.twisted_scabbard.shift8",
				ChatFormatting.LIGHT_PURPLE, twisted_scabbard_add);
	}

	@Override
	public void onSync(@Nullable TwistedScabbard twistedScabbard, Player player) {

	}

}
