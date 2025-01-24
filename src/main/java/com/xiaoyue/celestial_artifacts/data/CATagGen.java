package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.Curios;

public class CATagGen {

	public static final TagKey<Item> ANGEL_DESIRE_COMPAT = ItemTags.create(CelestialArtifacts.loc("angel_desire_compat"));
	public static final TagKey<Item> REQUIRE_CURSE = ItemTags.create(CelestialArtifacts.loc("require_curse"));
	public static final TagKey<EntityType<?>> DESIRE_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE, CelestialArtifacts.loc("desire_blacklist"));

	public static final TagKey<Item> ETCHINGS = ItemTags.create(new ResourceLocation(Curios.MODID, "etching"));

	public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
		var curse = pvd.addTag(REQUIRE_CURSE);
		for (var e : CAItems.ALL_CURIOS) {
			var item = ForgeRegistries.ITEMS.getValue(CelestialArtifacts.loc(e));
			if (!(item instanceof ModularCurio curio)) continue;
			if (curio.prop.requireCS()) {
				curse.add(curio);
			}
		}
		pvd.addTag(ItemTags.create(new ResourceLocation("l2hostility", "no_seal")))
				.add(CAItems.CATASTROPHE_SCROLL.get())
				.addTag(ItemTags.create(new ResourceLocation(Curios.MODID, "etching")));
	}

	public static void onEntityTagGen(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> pvd) {
		pvd.addTag(DESIRE_BLACKLIST).add(EntityType.PLAYER);
	}

	private static TagKey<EntityType<?>> entity(String id) {
		return TagKey.create(Registries.ENTITY_TYPE, CelestialArtifacts.loc(id));
	}

}
