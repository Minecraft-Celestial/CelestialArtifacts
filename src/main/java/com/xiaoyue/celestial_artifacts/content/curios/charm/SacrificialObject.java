package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacrificialObject implements MultiLineText {

	private static double goldChance() {
		return CAModConfig.COMMON.charm.sacrificialObjectHeritage.get();
	}

	private static double chance() {
		return CAModConfig.COMMON.charm.sacrificialObjectSacrifice.get();
	}

	public static void onPlayerDeath(Player player) {
		if (CAAttackToken.chance(player, goldChance())) {
			player.spawnAtLocation(Items.GOLD_INGOT);
		}
		List<LivingEntity> entities = EntityUtils.getExceptForCentralEntity(player, 8, 4, e -> e instanceof Enemy);
		entities.remove(player);
		for (LivingEntity e : entities) {
			if (e.getMaxHealth() < player.getMaxHealth()) {
				if (CAAttackToken.chance(e, chance())) {
					e.kill();
				}
			}
		}
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.SACRIFICIAL_OBJECT_1.get(TextFacet.perc(goldChance()))));
		list.add(TextFacet.wrap(CALang.Charm.SACRIFICIAL_OBJECT_2.get(TextFacet.perc(chance()))));
	}

}
