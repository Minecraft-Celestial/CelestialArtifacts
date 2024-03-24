package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import dev.xkmc.l2library.capability.conditionals.Context;
import dev.xkmc.l2library.capability.conditionals.TokenKey;
import dev.xkmc.l2library.capability.conditionals.TokenProvider;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public record TokenFacet<T extends BaseTickingToken>(String id, Supplier<T> sup)
		implements TickFacet, TextFacet, TokenProvider<T, TokenFacet<T>>, Context {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		var token = ClientTokenHelper.get(this, level);
		if (token != null) token.addText(level, list);
		else sup.get().addTextNoData(list);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity instanceof Player player) {
			Wrappers.run(() -> ConditionalData.HOLDER.get(player).getOrCreateData(this, this).update());
		}
	}

	@Nullable
	public T get(LivingEntity entity) {
		if (entity instanceof Player player) {
			return Wrappers.get(() -> ConditionalData.HOLDER.get(player).getData(getKey()));
		}
		return null;
	}

	@Override
	public T getData(TokenFacet<T> self) {
		return sup.get();
	}

	@Override
	public TokenKey<T> getKey() {
		return TokenKey.of(CelestialArtifacts.loc(id));
	}

}
