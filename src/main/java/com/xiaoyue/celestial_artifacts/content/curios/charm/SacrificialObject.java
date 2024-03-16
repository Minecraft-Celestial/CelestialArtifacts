package com.xiaoyue.celestial_artifacts.content.curios.charm;

import com.xiaoyue.celestial_artifacts.content.core.modular.MultiLineText;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacrificialObject implements MultiLineText {//TODO check

	private static int goldAmount() {
		return CAModConfig.COMMON.charm.sacriheritage.get();
	}

	private static double chance() {
		return CAModConfig.COMMON.charm.sacriselfexplode.get();
	}

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		list.add(TextFacet.wrap(CALang.Charm.SACRIFICIAL_OBJECT_1.get(TextFacet.num(goldAmount()))));
		list.add(TextFacet.wrap(CALang.Charm.SACRIFICIAL_OBJECT_2.get(TextFacet.perc(chance()))));
	}

	public static void onPlayerDeath(Player player) {
		if (CAAttackToken.chance(player, 0.5)) {
			player.spawnAtLocation(Items.GOLD_INGOT, goldAmount());
		}
		List<LivingEntity> entities = EntityUtils.getDelimitedMonster(player, 8);
		entities.remove(player);
		for (LivingEntity e : entities) {
			if (e.getMaxHealth() < player.getMaxHealth()) {
				if (CAAttackToken.chance(e, chance())) {
					e.kill();
				}
			}
		}
	}

}
