package com.xiaoyue.celestial_artifacts.content.curios.token;

import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import dev.xkmc.l2library.capability.conditionals.TokenProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ClientTokenHelper {

	@Nullable
	public static <T extends BaseTickingToken> T get(TokenProvider<T, ?> facet, @Nullable Level level) {
		if (level == null) return null;
		var player = Minecraft.getInstance().player;
		if (player == null) return null;
		return ConditionalData.HOLDER.get(player).getData(facet.getKey());
	}

	public static boolean hasCurio(@Nullable Level level, Item item) {
		if (level == null) return false;
		var player = Minecraft.getInstance().player;
		if (player == null) return false;
		return CurioUtils.hasCurio(player, item);
	}

}
