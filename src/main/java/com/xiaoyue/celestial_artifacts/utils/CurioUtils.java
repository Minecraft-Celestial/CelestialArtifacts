package com.xiaoyue.celestial_artifacts.utils;

import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;

public class CurioUtils {

	public static boolean isRangeUseAnim(UseAnim useAnim) {
		return useAnim == UseAnim.BOW || useAnim == UseAnim.CROSSBOW;
	}

	public static boolean isCsOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.CATASTROPHE_SCROLL.get());
	}

	public static int getCurseAmount(Player player) {
		if (!isCsOn(player)) return 0;
		int total = 7;
		if (hasCurio(player, CAItems.CHAOTIC_ETCHING.get())) total--;
		if (hasCurio(player, CAItems.DESIRE_ETCHING.get())) total--;
		if (hasCurio(player, CAItems.END_ETCHING.get())) total--;
		if (hasCurio(player, CAItems.ETCHING_OF_LIFE.get())) total--;
		if (hasCurio(player, CAItems.TRUTH_ETCHING.get())) total--;
		if (hasCurio(player, CAItems.ORIGIN_ETCHING.get())) total--;
		if (hasCurio(player, CAItems.NIHILITY_ETCHING.get())) total--;
		return total;
	}

	public static boolean hasCurio(Player player, Item... item) {
		return CurioCacheCap.HOLDER.get(player).has(item);
	}

}
