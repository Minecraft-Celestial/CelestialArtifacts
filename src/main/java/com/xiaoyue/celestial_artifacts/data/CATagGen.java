package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class CATagGen {


	public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {
	}

	private static TagKey<EntityType<?>> entity(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, CelestialArtifacts.loc(id));
	}

}
