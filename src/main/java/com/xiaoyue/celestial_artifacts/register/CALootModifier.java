package com.xiaoyue.celestial_artifacts.register;

import com.mojang.serialization.Codec;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.loot.EnabledCondition;
import com.xiaoyue.celestial_artifacts.content.loot.FishingCondition;
import com.xiaoyue.celestial_artifacts.content.loot.HasCurioCondition;
import com.xiaoyue.celestial_artifacts.content.loot.PlayerStatCondition;
import com.xiaoyue.celestial_core.content.loot.CCConditionSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class CALootModifier {

	public static final RegistryEntry<LootItemConditionType> HAS_CURIO, PLAYER_STAT, ENABLED, FISHING;

	static {
		HAS_CURIO = reg("has_curio", HasCurioCondition.class);
		ENABLED = reg("enabled", EnabledCondition.class);
		PLAYER_STAT = reg("player_stat", PlayerStatCondition.class);
		FISHING = reg("fishing", FishingCondition.class);
	}

	private static <T extends IGlobalLootModifier> RegistryEntry<Codec<T>> reg(String str, NonNullSupplier<Codec<T>> codec) {
		return CelestialArtifacts.REGISTRATE.simple(str, ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, codec);
	}

	private static <T extends LootItemCondition> RegistryEntry<LootItemConditionType> reg(String str, Class<T> codec) {
		return CelestialArtifacts.REGISTRATE.simple(str, Registries.LOOT_CONDITION_TYPE, () ->
				new LootItemConditionType(new CCConditionSerializer<>(codec)));
	}

	public static void register() {

	}

}
