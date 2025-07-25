package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.loot.EnabledCondition;
import com.xiaoyue.celestial_artifacts.content.loot.FishingCondition;
import com.xiaoyue.celestial_artifacts.content.loot.HasCurioCondition;
import com.xiaoyue.celestial_artifacts.content.loot.PlayerStatCondition;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.content.loot.*;
import com.xiaoyue.celestial_core.data.CCLangData;
import com.xiaoyue.celestial_core.register.CelestialFlags;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

import java.util.function.BiConsumer;

public class CAGLMProvider extends GlobalLootModifierProvider {

	public CAGLMProvider(PackOutput output) {
		super(output, CelestialArtifacts.MODID);
	}

	public static void addInfo(BiConsumer<ItemStack, Component> cons) {
		for (var e : CALootTableGen.values()) {
			for (var i : e.item) {
				cons.accept(i.get().getDefaultInstance(), e == CALootTableGen.FISHING_TREASURE ? e.get() :
						CALang.JEIInfo.FIND.get(e.get()));
			}
		}
		cons.accept(CAItems.DESIRE_ETCHING.asStack(), CALang.JEIInfo.CURSE.get(
				TextFacet.perc(CAModConfig.COMMON.materials.desireEtchingDropChance.get()),
				CatastropheScroll.Curses.DESIRE.title.get(),
				CALang.JEIInfo.DESIRE.get(CCLangData.entity(EntityType.WITHER),
						TextFacet.num(CAModConfig.COMMON.materials.desireEtchingLootingRequirement.get())
				)));

		cons.accept(CAItems.END_ETCHING.asStack(), CALang.JEIInfo.CURSE.get(
				TextFacet.perc(CAModConfig.COMMON.materials.endEtchingDropChance.get()),
				CatastropheScroll.Curses.END.title.get(),
				CALang.JEIInfo.END.get(CCLangData.entity(EntityType.WARDEN),
						TextFacet.num(CAModConfig.COMMON.materials.endEtchingEffectRequirement.get())
				)));

		cons.accept(CAItems.ORIGIN_ETCHING.asStack(), CALang.JEIInfo.CURSE.get(
				TextFacet.perc(CAModConfig.COMMON.materials.originEtchingDropChance.get()),
				CatastropheScroll.Curses.ORIGIN.title.get(),
				CALang.JEIInfo.ORIGIN.get(CCLangData.entity(EntityType.ENDER_DRAGON))));

		cons.accept(CAItems.LIFE_ETCHING.asStack(), CALang.JEIInfo.CURSE.get(
				TextFacet.perc(CAModConfig.COMMON.materials.lifeEtchingDropChance.get()),
				CatastropheScroll.Curses.LIFE.title.get(),
				CALang.JEIInfo.LIFE.get(
						TextFacet.num(CAModConfig.COMMON.materials.lifeEtchingHealthRequirement.get())
				)));

		cons.accept(CAItems.NIHILITY_ETCHING.asStack(), CALang.JEIInfo.CURSE.get(
				TextFacet.perc(CAModConfig.COMMON.materials.nihilityEtchingDropChance.get()),
				CatastropheScroll.Curses.NIHILITY.title.get(),
				CALang.JEIInfo.NIHILITY.get(CCLangData.entity(EntityType.WARDEN))));

		cons.accept(CAItems.CHAOTIC_ETCHING.asStack(), CALang.JEIInfo.CURSE_BY.get(
				TextFacet.perc(CAModConfig.COMMON.materials.chaoticEtchingDropChance.get()),
				CCLangData.entity(EntityType.WITHER),
				CALang.JEIInfo.CHAOTIC.get()));

		cons.accept(CAItems.TRUTH_ETCHING.asStack(), CALang.JEIInfo.CURSE_BY.get(
				TextFacet.perc(CAModConfig.COMMON.materials.truthEtchingDropChance.get()),
				CCLangData.entity(EntityType.WITHER),
				CALang.JEIInfo.TRUTH.get()));

		cons.accept(CAItems.CHARMING_BRACELET.asStack(), CALang.JEIInfo.CHARMING.get(
				TextFacet.num(CAModConfig.COMMON.materials.charmingBraceletReputationRequirement.get()),
				TextFacet.perc(CAModConfig.COMMON.materials.charmingBraceletDropChance.get()),
				CALang.Modular.item(CAItems.CHARMING_BRACELET.asStack()),
				CALang.Modular.item(Items.GOLD_INGOT.getDefaultInstance())
		));

		cons.accept(CAItems.GUARDIAN_EYE.asStack(), CALang.JEIInfo.CURSE_DROP.get(
				CALang.Modular.curseItem(),
				TextFacet.perc(CAModConfig.COMMON.materials.guardianEyeDropChance.get()),
				CCLangData.entity(EntityType.ELDER_GUARDIAN)));

		cons.accept(CAItems.DEMON_CURSE.asStack(), CCLangData.AFTER_WITHER_DROP.get(
				CCLangData.entity(EntityType.WITHER).setStyle(Style.EMPTY),
				TextFacet.perc(CAModConfig.COMMON.materials.demonCurseDropChance.get()).setStyle(Style.EMPTY),
				CCLangData.entity(EntityType.VEX).setStyle(Style.EMPTY)));

		cons.accept(CAItems.TWISTED_HEART.asStack(), CALang.JEIInfo.WITHER_KILL.get(
				TextFacet.perc(CAModConfig.COMMON.materials.twistedHeartDropChance.get()),
				CCLangData.entity(EntityType.WITHER),
				CALang.JEIInfo.LIFE.get(TextFacet.num(CAModConfig.COMMON.materials.twistedHeartHealthRequirement.get()))));

		cons.accept(CAItems.TWISTED_BRAIN.asStack(), CALang.JEIInfo.WITHER_KILL.get(
				TextFacet.perc(CAModConfig.COMMON.materials.twistedBrainDropChance.get()),
				CCLangData.entity(EntityType.WITHER),
				CALang.JEIInfo.TRUTH.get()));

	}

	public static LootItemCondition killer(EntityType<?> type) {
		return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
				EntityPredicate.Builder.entity().of(type)).build();
	}

	public static LootItemCondition killer(TagKey<EntityType<?>> tag) {
		return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
				EntityPredicate.Builder.entity().of(tag)).build();
	}

	public static LootItemCondition entity(EntityPredicate.Builder builder) {
		return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, builder).build();
	}

	public static LootItemCondition entityType(EntityType<?> type) {
		return entity(EntityPredicate.Builder.entity().of(type));
	}

	public static LootItemCondition entityType(TagKey<EntityType<?>> tag) {
		return entity(EntityPredicate.Builder.entity().of(tag));
	}

	public static LootItemCondition damage(TagKey<DamageType> tag) {
		return DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType()
				.tag(TagPredicate.is(tag))).build();
	}

	@Override
	protected void start() {
		for (var e : CALootTableGen.values()) {
			if (e != CALootTableGen.FISHING_TREASURE) {
				add(e.id().getPath(), new AddLootTableModifier(e.id(),
						LootTableIdCondition.builder(e.target).build()));
			} else {
				add(e.id().getPath(), new AddLootTableModifier(e.id(),
						new FishingCondition(true),
						LootTableIdCondition.builder(e.target).build()));
			}
		}

		for (LootBoxEnum e : LootBoxEnum.values()) {
			add(e.id().getPath(), new FishingCooldownModifier(CAItems.TREASURE_HUNTER_NECKLACE.get(),
					e.box, IntConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.necklace.treasureHunterNecklaceCooldown),
					DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.necklace.treasureHunterNecklaceChance),
					new FishingCondition(true, CAItems.TREASURE_HUNTER_NECKLACE.get(), e.biomes))
			);
		}

		add("drops/desire_etching", new AddItemModifier(CAItems.DESIRE_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.desireEtchingDropChance),
				CatastropheScroll.Curses.DESIRE.asCondition(),
				entityType(EntityType.WITHER), new PlayerStatCondition(PlayerStatCondition.Type.LOOT,
				IntConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.desireEtchingLootingRequirement))));
		add("drops/end_etching", new AddItemModifier(CAItems.END_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endEtchingDropChance),
				CatastropheScroll.Curses.END.asCondition(),
				entityType(EntityType.WARDEN), new PlayerEffectCondition(MobEffectCategory.HARMFUL,
				IntConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endEtchingEffectRequirement))));
		add("drops/origin_etching", new AddItemModifier(CAItems.ORIGIN_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.originEtchingDropChance),
				CatastropheScroll.Curses.ORIGIN.asCondition(),
				entityType(EntityType.ENDER_DRAGON), entity(EntityPredicate.Builder.entity()
				.located(LocationPredicate.atYLocation(MinMaxBounds.Doubles.atLeast(200.0))))));
		add("drops/life_etching", new AddItemModifier(CAItems.LIFE_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.lifeEtchingDropChance),
				CatastropheScroll.Curses.LIFE.asCondition(),
				new EntityHealthCondition(IntConfigValue.of(CAModConfig.COMMON_PATH,
						CAModConfig.COMMON.materials.lifeEtchingHealthRequirement))));
		add("drops/nihility_etching", new AddItemModifier(CAItems.NIHILITY_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.nihilityEtchingDropChance),
				CatastropheScroll.Curses.NIHILITY.asCondition(),
				entityType(EntityType.WARDEN), damage(DamageTypeTags.BYPASSES_ENCHANTMENTS)));
		add("drops/chaotic_etching", new AddItemModifier(CAItems.CHAOTIC_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.chaoticEtchingDropChance),
				new EnabledCondition(CAItems.CHAOTIC_ETCHING.get()),
				entityType(EntityType.WITHER), damage(DamageTypeTags.IS_EXPLOSION)));
		add("drops/truth_etching", new AddItemModifier(CAItems.TRUTH_ETCHING.get(), CAItems.NEBULA_CUBE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.truthEtchingDropChance),
				new EnabledCondition(CAItems.TRUTH_ETCHING.get()),
				entityType(EntityType.WITHER), killer(EntityTypeTags.RAIDERS)));

		add("drops/the_end_dust", new AddItemModifier(CAItems.THE_END_DUST.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endDustDropChance),
				new HasCurioCondition(CAItems.CATASTROPHE_SCROLL.get()), entity(EntityPredicate.Builder.entity().targetedEntity(
				EntityPredicate.Builder.entity().of(EntityType.PLAYER).build()))));
		add("drops/charming_bracelet", new AddItemModifier(CAItems.CHARMING_BRACELET.get(), Items.GOLD_INGOT,
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.charmingBraceletDropChance),
				new EnabledCondition(CAItems.CHARMING_BRACELET.get()), entityType(EntityType.VILLAGER),
				new PlayerStatCondition(PlayerStatCondition.Type.REPUTATION, IntConfigValue.of(CAModConfig.COMMON_PATH,
						CAModConfig.COMMON.materials.charmingBraceletReputationRequirement))));
		add("drops/guardian_eye", new AddItemModifier(CAItems.GUARDIAN_EYE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.guardianEyeDropChance),
				new EnabledCondition(CAItems.GUARDIAN_EYE.get()),
				entityType(EntityType.ELDER_GUARDIAN)));
		add("drops/demon_curse", new AddItemModifier(CAItems.DEMON_CURSE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.demonCurseDropChance),
				new EnabledCondition(CAItems.DEMON_CURSE.get()),
				new PlayerFlagCondition(CelestialFlags.NETHER_STAGE), entityType(EntityType.VEX)));
		add("drops/twisted_heart", new AddItemModifier(CAItems.TWISTED_HEART.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.twistedHeartDropChance),
				new EnabledCondition(CAItems.TWISTED_HEART.get()),
				killer(EntityType.WITHER), new EntityHealthCondition(IntConfigValue.of(CAModConfig.COMMON_PATH,
				CAModConfig.COMMON.materials.twistedHeartHealthRequirement))));
		add("drops/twisted_brain", new AddItemModifier(CAItems.TWISTED_BRAIN.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.twistedBrainDropChance),
				new EnabledCondition(CAItems.TWISTED_BRAIN.get()),
				killer(EntityType.WITHER), entityType(EntityTypeTags.RAIDERS)));
	}

}
