package com.xiaoyue.celestial_artifacts.utils;

import com.xiaoyue.celestial_artifacts.content.curios.core.CurioCacheCap;
import com.xiaoyue.celestial_artifacts.content.old.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.old.generic.AttackICurio;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CurioUtils {


	public static boolean isRangeUseAnim(UseAnim useAnim) {
		return useAnim == UseAnim.BOW || useAnim == UseAnim.CROSSBOW;
	}

	public static boolean isCsOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.CATASTROPHE_SCROLL.get());
	}

	public static int getCurseAmount() {
		int curse = 0;
		if (CatastropheScroll.i_chaotic == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_desire == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_end == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_life == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_truth == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_origin == 0) {
			curse += 1;
		}
		if (CatastropheScroll.i_nihility == 0) {
			curse += 1;
		}
		return curse;
	}

	public static boolean isSeaGodOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.SEA_GOD_CROWN.get(),
				CAItems.SEA_GOD_SCROLL.get());
	}

	public static boolean isEmeraldOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.EMERALD_NECKLACE.get(),
				CAItems.EMERALD_RING.get(), CAItems.EMERALD_BRACELET.get());
	}

	public static boolean isSpiritOn(Player player) {
		return CurioUtils.hasCurio(player, CAItems.SPIRIT_BRACELET.get(),
				CAItems.SPIRIT_NECKLACE.get(), CAItems.SPIRIT_ARROW_BAG.get(),
				CAItems.SPIRIT_ARROW_BAG.get());
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
