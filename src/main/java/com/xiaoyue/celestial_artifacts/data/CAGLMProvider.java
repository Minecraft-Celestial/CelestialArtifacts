package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.loot.CurseOnCondition;
import com.xiaoyue.celestial_artifacts.content.loot.PlayerStatCondition;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.content.loot.*;
import com.xiaoyue.celestial_core.events.CCGeneralEventHandler;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class CAGLMProvider extends GlobalLootModifierProvider {

	public CAGLMProvider(PackOutput output) {
		super(output, CelestialArtifacts.MODID);
	}

	@Override
	protected void start() {
		add("drops/desire_etching", new AddItemModifier(CAItems.DESIRE_ETCHING.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.desireEtchingDropChance),
				entityType(EntityType.WITHER), new CurseOnCondition(), new PlayerStatCondition(PlayerStatCondition.Type.LOOT,
				IntConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.desireEtchingLootingRequirement))));
		add("drops/end_etching", new AddItemModifier(CAItems.END_ETCHING.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endEtchingDropChance),
				entityType(EntityType.WARDEN), new CurseOnCondition(), new PlayerEffectCondition(MobEffectCategory.HARMFUL,
				IntConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endEtchingEffectRequirement))));
		add("drops/life_etching", new AddItemModifier(CAItems.LIFE_ETCHING.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.lifeEtchingDropChance),
				new CurseOnCondition(), new EntityHealthCondition(IntConfigValue.of(CAModConfig.COMMON_PATH,
				CAModConfig.COMMON.materials.lifeEtchingHealthRequirement))));
		add("drops/nihility_etching", new AddItemModifier(CAItems.NIHILITY_ETCHING.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.nihilityEtchingDropChance),
				entityType(EntityType.WARDEN), damage(DamageTypeTags.BYPASSES_ENCHANTMENTS)));
		add("drops/chaotic_etching", new AddItemModifier(CAItems.CHAOTIC_ETCHING.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.chaoticEtchingDropChance),
				entityType(EntityType.WITHER), damage(DamageTypeTags.IS_EXPLOSION)));
		add("drops/truth_etching", new AddItemModifier(CAItems.TRUTH_ETCHING.get(), null,
				entityType(EntityType.WITHER), killer(EntityTypeTags.RAIDERS)));

		add("drops/the_end_dust", new AddItemModifier(CAItems.THE_END_DUST.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.endDustDropChance),
				new CurseOnCondition(), entity(EntityPredicate.Builder.entity().targetedEntity(
				EntityPredicate.Builder.entity().of(EntityType.PLAYER).build()))));
		add("drops/charming_bracelet", new AddItemModifier(CAItems.CHARMING_BRACELET.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.charmingBraceletDropChance),
				new PlayerStatCondition(PlayerStatCondition.Type.REPUTATION, IntConfigValue.of(CAModConfig.COMMON_PATH,
						CAModConfig.COMMON.materials.charmingBraceletReputationRequirement))));
		add("drops/guardian_eye", new AddItemModifier(CAItems.GUARDIAN_EYE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.guardianEyeDropChance),
				LootTableTemplate.byPlayer().build(), entityType(EntityType.ELDER_GUARDIAN)));
		add("drops/demon_curse", new AddItemModifier(CAItems.DEMON_CURSE.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.demonCurseDropChance),
				new PlayerFlagCondition(CCGeneralEventHandler.NETHER_STAGE), entityType(EntityType.VEX)));
		add("drops/twisted_heart", new AddItemModifier(CAItems.TWISTED_HEART.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.twistedHeartDropChance),
				killer(EntityType.WITHER), new EntityHealthCondition(IntConfigValue.of(CAModConfig.COMMON_PATH,
				CAModConfig.COMMON.materials.twistedHeartHealthRequirement))));
		add("drops/twisted_brain", new AddItemModifier(CAItems.TWISTED_BRAIN.get(),
				DoubleConfigValue.of(CAModConfig.COMMON_PATH, CAModConfig.COMMON.materials.twistedBrainDropChance),
				killer(EntityType.WITHER), entityType(CATagGen.DROPS_TWISTED_BRAIN)));

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

}
