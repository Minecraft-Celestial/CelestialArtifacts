package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_core.register.CCItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;

import java.util.Locale;

public enum LootBoxEnum {
	JUNGLE_PYRAMID_LOOT_BOX(CCItems.JUNGLE_PYRAMID_LOOT_BOX.get(), BiomeTags.HAS_JUNGLE_TEMPLE),
	DESERT_PYRAMID_LOOT_BOX(CCItems.DESERT_PYRAMID_LOOT_BOX.get(), BiomeTags.HAS_DESERT_PYRAMID),
	IGLOO_LOOT_BOX(CCItems.IGLOO_LOOT_BOX.get(), BiomeTags.HAS_IGLOO),
	MANSON_LOOT_BOX(CCItems.MANSON_LOOT_BOX.get(), BiomeTags.HAS_WOODLAND_MANSION),
	PILLAGER_OUTPOST_LOOT_BOX((CCItems.PILLAGER_OUTPOST_LOOT_BOX.get()), BiomeTags.HAS_PILLAGER_OUTPOST);

	public final Item box;
	public final TagKey<Biome> biomes;

	LootBoxEnum(Item box, TagKey<Biome> biomes) {
		this.box = box;
		this.biomes = biomes;
	}

	public ResourceLocation id() {
		return CelestialArtifacts.loc("fishing_box/" + name().toLowerCase(Locale.ROOT));
	}
}
