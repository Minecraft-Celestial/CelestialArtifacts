package com.xiaoyue.celestial_artifacts.utils;

import com.xiaoyue.celestial_artifacts.content.curios.core.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.ArrayList;
import java.util.List;

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

	public static boolean isSeaGodOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.SEA_GOD_CROWN.get(),
				CAItems.SEA_GOD_SCROLL.get());
	}

	public static boolean isEmeraldOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.EMERALD_NECKLACE.get(),
				CAItems.EMERALD_RING.get(), CAItems.EMERALD_BRACELET.get());
	}

	public static List<SlotResult> getACResult(Player player) {
		List<SlotResult> list = new ArrayList<SlotResult>();
		CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
			List<SlotResult> curios = handler.findCurios(itemStack -> itemStack.getItem() instanceof AttackICurio);
			list.addAll(curios);
		});
		return list;
	}

	public static boolean hasCurio(Player player, Item... item) {
		return CurioCacheCap.HOLDER.get(player).has(item);
	}

}
