package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class CATagGen {

	public static final TagKey<EntityType<?>> DROPS_TWISTED_BRAIN = entity("drops_twisted_brain");

	public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {
		pvd.addTag(DROPS_TWISTED_BRAIN).addTag(EntityTypeTags.RAIDERS)
				.add(EntityType.VILLAGER, EntityType.WANDERING_TRADER,
						EntityType.ENDERMAN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE);
	}

	private static TagKey<EntityType<?>> entity(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, CelestialArtifacts.loc(id));
	}

}
