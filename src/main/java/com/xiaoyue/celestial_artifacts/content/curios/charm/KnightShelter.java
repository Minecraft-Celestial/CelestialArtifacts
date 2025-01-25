package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.feature.ShieldingFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import dev.xkmc.l2library.init.events.GeneralEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightShelter implements MultiLineText, TickFacet, ShieldingFeature {

	private static int healTime() {
		return CAModConfig.COMMON.charm.knightShelterHealInterval.get();
	}

	private static double damage() {
		return CAModConfig.COMMON.charm.knightShelterReflection.get();
	}

	@Override
	public void onPlayerBlocked(Player player, ShieldBlockEvent event) {
		Entity attacker = event.getDamageSource().getEntity();
		if (attacker != null) {
			GeneralEventHandler.schedule(() -> attacker.hurt(player.damageSources().playerAttack(player),
					(float) (event.getBlockedDamage() * damage())));
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.KNIGHT_SHELTER_1.get(TextFacet.num(healTime()))));
		list.add(TextFacet.wrap(CALang.Charm.KNIGHT_SHELTER_2.get(TextFacet.perc(damage()))));
	}

	@Override
	public void tick(LivingEntity player, ItemStack stack) {
		if (player.getMainHandItem().getItem() instanceof ShieldItem) {
			if (player.tickCount % (healTime() * 10) == 0) {
				player.heal(1f);
			}
		} else if (player.getOffhandItem().getItem() instanceof ShieldItem) {
			if (player.tickCount % (healTime() * 20) == 0) {
				player.heal(1f);
			}
		}
	}

}
