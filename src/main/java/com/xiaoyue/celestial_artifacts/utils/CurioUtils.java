package com.xiaoyue.celestial_artifacts.utils;

import com.xiaoyue.celestial_artifacts.content.core.modular.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;

public class CurioUtils {

	public static boolean isRangeUseAnim(UseAnim useAnim) {
		return useAnim == UseAnim.BOW || useAnim == UseAnim.CROSSBOW;
	}

	public static boolean isCsOn(Player player) {
		return ConditionalData.HOLDER.get(player).hasData(CatastropheScroll.TOKEN.getKey()) ||
				hasCurio(player, CAItems.CATASTROPHE_SCROLL.get());
	}

	public static int getCurseAmount(Player player) {
		if (!isCsOn(player)) return 0;
		int ans = 0;
		for (var e : CatastropheScroll.Curses.values()) {
			if (e.cursing(player)) ans++;
		}
		return ans;
	}

	public static boolean hasCurio(Player player, Item... item) {
		return CurioCacheCap.HOLDER.get(player).has(item);
	}

}
