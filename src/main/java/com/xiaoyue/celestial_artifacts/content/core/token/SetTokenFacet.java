package com.xiaoyue.celestial_artifacts.content.core.token;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.SetFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TickFacet;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2library.capability.conditionals.ConditionalData;
import dev.xkmc.l2library.capability.conditionals.Context;
import dev.xkmc.l2library.capability.conditionals.TokenKey;
import dev.xkmc.l2library.capability.conditionals.TokenProvider;
import dev.xkmc.l2serial.util.Wrappers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public record SetTokenFacet<T extends BaseTickingToken>(String id, List<ItemLike> list, Supplier<T> sup)
		implements TickFacet, SetFacet, TokenProvider<T, SetTokenFacet<T>>, Context {

	@Override
	public void addText(@Nullable Level level, List<Component> list) {
		var token = ClientTokenHelper.get(this, level);
		if (token != null) token.addText(level, list);
		else sup.get().addTextNoData(list);
	}

	@Override
	public void tick(LivingEntity entity, ItemStack stack) {
		if (entity instanceof Player player) {
			for (var e : list()) {
				if (!CurioUtils.hasCurio(player, e.asItem())) return;
			}
			Wrappers.run(() -> ConditionalData.HOLDER.get(player).getOrCreateData(this, this).update());
		}
	}

	@Override
	public T getData(SetTokenFacet<T> self) {
		return sup.get();
	}

	@Override
	public TokenKey<T> getKey() {
		return TokenKey.of(CelestialArtifacts.loc(id()));
	}

}
