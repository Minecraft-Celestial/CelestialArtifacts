package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import com.xiaoyue.celestial_core.content.generic.PlayerFlagData;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import dev.xkmc.l2library.capability.conditionals.TokenProvider;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ClientTokenHelper {

	@Nullable
	public static <T extends BaseTickingToken> T get(TokenProvider<T, ?> facet, @Nullable Level level) {
		if (level == null) return null;
		var player = Minecraft.getInstance().player;
		if (player == null) return null;
		return Wrappers.get(() -> ConditionalData.HOLDER.get(player).getData(facet.getKey()));
	}

	public static boolean hasCurio(@Nullable Level level, Item item) {
		level = Minecraft.getInstance().level;
		if (level == null) return false;
		var player = Minecraft.getInstance().player;
		if (player == null) return false;
		return CurioUtils.hasCurio(player, item);
	}

	public static boolean pred(@Nullable Level level, Predicate<Player> pred) {
		level = Minecraft.getInstance().level;
		if (level == null) return false;
		var player = Minecraft.getInstance().player;
		if (player == null) return false;
		return pred.test(player);
	}

	public static boolean flag(@Nullable Level level, String flag) {
		level = Minecraft.getInstance().level;
		if (level == null) return false;
		var player = Minecraft.getInstance().player;
		if (player == null) return false;
		return PlayerFlagData.HOLDER.get(player).hasFlag(flag);
	}

	public static MutableComponent disable(boolean pass, MutableComponent comp) {
		if (pass) return comp;
		return comp.withStyle(ChatFormatting.DARK_GRAY);
	}

}
