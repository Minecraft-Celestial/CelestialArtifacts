package com.xiaoyue.celestial_artifacts.content.loot;

import com.xiaoyue.celestial_artifacts.register.CALootModifier;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import javax.annotation.Nullable;

@SerialClass
public class FishingCondition implements LootItemCondition, LootItemCondition.Builder {

	@SerialClass.SerialField
	public boolean open;

	@SerialClass.SerialField
	@Nullable
	public Item item;

	@SerialClass.SerialField
	@Nullable
	public TagKey<Biome> biomes;

	@Deprecated
	public FishingCondition() {

	}

	public FishingCondition(boolean open) {
		this.open = open;
	}

	public FishingCondition(boolean open, Item item, TagKey<Biome> biomes) {
		this.open = open;
		this.item = item;
		this.biomes = biomes;
	}

	@Override
	public LootItemConditionType getType() {
		return CALootModifier.FISHING.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		if (!ctx.hasParam(LootContextParams.THIS_ENTITY)) return false;
		if (ctx.getParam(LootContextParams.THIS_ENTITY) instanceof FishingHook hook) {
			boolean isOpen = hook.isOpenWaterFishing() == open;
			Player player = hook.getPlayerOwner();
			if (item == null || player == null || biomes == null || player.getCooldowns().isOnCooldown(item)) {
				return isOpen;
			} else {
				return CurioUtils.hasCurio(player, item) && player.level().getBiome(player.getOnPos()).is(biomes) && isOpen;
			}
		}
		return false;
	}

	@Override
	public LootItemCondition build() {
		return this;
	}
}
