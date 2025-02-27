package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;

public class CAModConfig {

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	public static String COMMON_PATH;

	static {
		final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();

		final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
	}

	/**
	 * Registers any relevant listeners for config
	 */
	public static void init() {
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
		COMMON_PATH = register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static String register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = "celestial_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
		return path;
	}

	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public final Materials materials;
		public final Back back;
		public final Bracelet bracelet;
		public final Charm charm;
		public final Curse curse;
		public final Head head;
		public final Heart heart;
		public final Necklace necklace;
		public final Pendant pendant;
		public final Ring ring;
		public final Scroll scroll;
		public final Body body;
		public final Set set;
		public final Misc misc;
		public final Toggles toggles;

		Common(ForgeConfigSpec.Builder builder) {
			materials = new Materials(builder);
			back = new Back(builder);
			bracelet = new Bracelet(builder);
			charm = new Charm(builder);
			curse = new Curse(builder);
			head = new Head(builder);
			heart = new Heart(builder);
			necklace = new Necklace(builder);
			pendant = new Pendant(builder);
			ring = new Ring(builder);
			scroll = new Scroll(builder);
			body = new Body(builder);
			set = new Set(builder);
			misc = new Misc(builder);
			toggles = new Toggles(builder);

		}

		public static class Materials {

			public final ForgeConfigSpec.DoubleValue desireEtchingDropChance;
			public final ForgeConfigSpec.IntValue desireEtchingLootingRequirement;
			public final ForgeConfigSpec.DoubleValue endEtchingDropChance;
			public final ForgeConfigSpec.IntValue endEtchingEffectRequirement;
			public final ForgeConfigSpec.DoubleValue originEtchingDropChance;
			public final ForgeConfigSpec.IntValue originEtchingEffectRequirement;
			public final ForgeConfigSpec.DoubleValue lifeEtchingDropChance;
			public final ForgeConfigSpec.IntValue lifeEtchingHealthRequirement;
			public final ForgeConfigSpec.DoubleValue chaoticEtchingDropChance;
			public final ForgeConfigSpec.DoubleValue nihilityEtchingDropChance;
			public final ForgeConfigSpec.DoubleValue truthEtchingDropChance;

			public final ForgeConfigSpec.DoubleValue endDustDropChance;
			public final ForgeConfigSpec.DoubleValue charmingBraceletDropChance;
			public final ForgeConfigSpec.IntValue charmingBraceletReputationRequirement;
			public final ForgeConfigSpec.DoubleValue guardianEyeDropChance;
			public final ForgeConfigSpec.DoubleValue demonCurseDropChance;
			public final ForgeConfigSpec.DoubleValue twistedHeartDropChance;
			public final ForgeConfigSpec.IntValue twistedHeartHealthRequirement;
			public final ForgeConfigSpec.DoubleValue twistedBrainDropChance;

			private Materials(ForgeConfigSpec.Builder builder) {
				builder.push("materials");
				desireEtchingDropChance = builder.defineInRange("desireEtchingDropChance", 0.25, 0, 1);
				desireEtchingLootingRequirement = builder.defineInRange("desireEtchingLootingRequirement", 7, 0, 100);
				endEtchingDropChance = builder.defineInRange("endEtchingDropChance", 0.75, 0, 1);
				endEtchingEffectRequirement = builder.defineInRange("endEtchingEffectRequirement", 9, 0, 100);
				originEtchingDropChance = builder.defineInRange("originEtchingDropChance", 0.5, 0, 1);
				originEtchingEffectRequirement = builder.defineInRange("originEtchingEffectRequirement", 200, 0, 100);
				lifeEtchingDropChance = builder.defineInRange("lifeEtchingDropChance", 0.15, 0, 1);
				lifeEtchingHealthRequirement = builder.defineInRange("lifeEtchingHealthRequirement", 500, 1, 1000000);
				chaoticEtchingDropChance = builder.defineInRange("chaoticEtchingDropChance", 0.3, 0, 1);
				nihilityEtchingDropChance = builder.defineInRange("nihilityEtchingDropChance", 0.5, 0, 1);
				truthEtchingDropChance = builder.defineInRange("truthEtchingDropChance", 0.5, 0, 1);

				endDustDropChance = builder.defineInRange("endDustDropChance", 0.02, 0, 1);
				charmingBraceletDropChance = builder.defineInRange("charmingBraceletDropChance", 0.5, 0, 1);
				charmingBraceletReputationRequirement = builder.defineInRange("charmingBraceletReputationRequirement", 100, 0, 10000);
				guardianEyeDropChance = builder.defineInRange("guardianEyeDropChance", 0.5, 0, 1);
				demonCurseDropChance = builder.defineInRange("demonCurseDropChance", 0.03, 0, 1);
				twistedHeartDropChance = builder.defineInRange("twistedHeartDropChance", 0.1, 0, 1);
				twistedHeartHealthRequirement = builder.defineInRange("twistedHeartHealthRequirement", 20, 0, 10000);
				twistedBrainDropChance = builder.defineInRange("twistedBrainDropChance", 0.1, 0, 1);
				builder.pop();
			}

		}

		public static class Back {

			public final ForgeConfigSpec.DoubleValue magicArrowBagBowStrength;
			public final ForgeConfigSpec.IntValue magicArrowBagArrowKnock;
			public final ForgeConfigSpec.DoubleValue flameArrowBagBowStrength;
			public final ForgeConfigSpec.IntValue flameArrowBagArrowKnock;
			public final ForgeConfigSpec.IntValue flameArrowBagTime;
			public final ForgeConfigSpec.DoubleValue spiritArrowBagBowStrength;
			public final ForgeConfigSpec.DoubleValue spiritArrowBagArrowSpeed;
			public final ForgeConfigSpec.IntValue spiritArrowBagArrowKnock;
			public final ForgeConfigSpec.DoubleValue leechScabbardHealFactor;
			public final ForgeConfigSpec.DoubleValue titanScabbardDamageFactor;
			public final ForgeConfigSpec.IntValue twistedScabbardInterval;
			public final ForgeConfigSpec.DoubleValue twistedScabbardAttack;
			public final ForgeConfigSpec.DoubleValue twistedScabbardAttackEnd;
			public final ForgeConfigSpec.DoubleValue twistedScabbardAtkKonck;
			public final ForgeConfigSpec.DoubleValue twistedScabbardAtkSpeed;
			public final ForgeConfigSpec.DoubleValue twistedScabbardHealRate;
			public final ForgeConfigSpec.IntValue ironScabbardBladeInterval;
			public final ForgeConfigSpec.IntValue leechScabbardBladeInterval;
			public final ForgeConfigSpec.IntValue titanScabbardBladeInterval;
			public final ForgeConfigSpec.IntValue twistedScabbardBladeInterval;

			private Back(ForgeConfigSpec.Builder builder) {
				builder.push("back");

				magicArrowBagBowStrength = builder.comment("Magic Arrow Bag: bow strength")
						.defineInRange("magicArrowBagBowStrength", 0.1, 0, 10);
				magicArrowBagArrowKnock = builder.comment("Magic Arrow Bag: arrow knock")
						.defineInRange("magicArrowBagArrowKnock", 1, 0, 100);

				flameArrowBagBowStrength = builder.comment("Flame Arrow Bag: bow strength")
						.defineInRange("flameArrowBagBowStrength", 0.1, 0, 10);
				flameArrowBagArrowKnock = builder.comment("Flame Arrow Bag: arrow knock")
						.defineInRange("flameArrowBagArrowKnock", 1, 0, 100);
				flameArrowBagTime = builder.comment("Flame Arrow Bag: flame")
						.defineInRange("flameArrowBagTime", 60, 1, 36000);

				spiritArrowBagBowStrength = builder.comment("Spirit Arrow Bag: bow strength")
						.defineInRange("spiritArrowBagBowStrength", 0.2, 0, 1);
				spiritArrowBagArrowSpeed = builder.comment("Spirit Arrow Bag: arrow speed")
						.defineInRange("spiritArrowBagArrowSpeed", 0.5, 0, 10);
				spiritArrowBagArrowKnock = builder.comment("Spirit Arrow Bag: arrow knock")
						.defineInRange("spiritArrowBagArrowKnock", 2, 0, 100);

				ironScabbardBladeInterval = builder.comment("Iron Scabbard: interval in seconds for blade modifier effect")
						.defineInRange("ironScabbardBladeInterval", 10, 0, 100);

				leechScabbardBladeInterval = builder.comment("Leech Scabbard: interval in seconds for blade modifier effect")
						.defineInRange("leechScabbardBladeInterval", 10, 0, 100);
				leechScabbardHealFactor = builder.comment("Leech Scabbard: healing rate as percentage of damage dealt")
						.defineInRange("leechScabbardHealFactor", 0.25, 0, 100);

				titanScabbardBladeInterval = builder.comment("Titan Scabbard: interval in seconds for blade modifier effect")
						.defineInRange("titanScabbardBladeInterval", 7, 0, 100);
				titanScabbardDamageFactor = builder.comment("Titan Scabbard: damage bonus when effect applies")
						.defineInRange("titanScabbardDamageFactor", 0.75, 0, 100);

				twistedScabbardInterval = builder.comment("Twisted Scabbard: interval, in seconds, to lose 1 layer of Twist")
						.defineInRange("twistedScabbardInterval", 5, 1, 1000);
				twistedScabbardAttack = builder.comment("Twisted Scabbard: attack bonus per layer of Twist")
						.defineInRange("twistedScabbardAttack", 0.05, 0, 1);
				twistedScabbardAttackEnd = builder.comment("Twisted Scabbard: attack bonus in End curse")
						.defineInRange("twistedScabbardAttackEnd", 0.1, 0, 1);
				twistedScabbardBladeInterval = builder.comment("Twisted Scabbard: interval in seconds for blade modifier effect")
						.defineInRange("twistedScabbardBladeInterval", 5, 0, 100);
				twistedScabbardAtkKonck = builder.comment("Twisted Scabbard: attack knock back")
						.defineInRange("twistedScabbardAtkKonck", 1d, 0, 100);
				twistedScabbardAtkSpeed = builder.comment("Twisted Scabbard: attack speed bonus")
						.defineInRange("twistedScabbardAtkSpeed", 0.25, 0, 1);
				twistedScabbardHealRate = builder.comment("Twisted Scabbard: heal rate penalty")
						.defineInRange("twistedScabbardHealRate", 0.5, 0, 1);

				builder.pop();
			}

		}

		public static class Bracelet {

			// hidden_bracelet
			public final ForgeConfigSpec.IntValue hiddenBraceletInterval;
			public final ForgeConfigSpec.DoubleValue hiddenBraceletDamageBoost;

			//charm_brace
			public final ForgeConfigSpec.IntValue charmingBraceletArmor;
			public final ForgeConfigSpec.IntValue charmingBraceletCooldown;
			public final ForgeConfigSpec.IntValue charmingBraceletReputationBonus;

			//scarlet_bloodrage
			public final ForgeConfigSpec.DoubleValue scarletBraceletMaxCost;
			public final ForgeConfigSpec.DoubleValue scarletBraceletDamageBonus;
			public final ForgeConfigSpec.DoubleValue scarletBraceletDamageLimit;

			//spirit_bracelet
			public final ForgeConfigSpec.IntValue spiritBraceletEffectDuration;


			public final ForgeConfigSpec.DoubleValue lifeBraceletRegenBonus;
			public final ForgeConfigSpec.DoubleValue preciousBraceletReach;
			public final ForgeConfigSpec.DoubleValue preciousBraceletMagicReduction;
			public final ForgeConfigSpec.DoubleValue redRubyBraceletFireReduction;

			// emerald_necklace
			public final ForgeConfigSpec.DoubleValue emeraldBraceletCritRate;
			public final ForgeConfigSpec.IntValue emeraldBraceletLuckReq;
			public final ForgeConfigSpec.DoubleValue emeraldBraceletEffChance;

			private Bracelet(ForgeConfigSpec.Builder builder) {
				builder.push("bracelet");

				// hidden_bracelet
				{
					hiddenBraceletInterval = builder
							.comment("Hidden Bracelet: Hidden effect interval")
							.defineInRange("hiddenBraceletInterval", 7, 1, 100000);
					hiddenBraceletDamageBoost = builder
							.comment("Hidden Bracelet: Damage boost in hidden effect")
							.defineInRange("hiddenBraceletDamageBoost", 0.25, 0, 100);
				}

				//charming bracelet
				{
					charmingBraceletArmor = builder
							.comment("Charming Bracelet: Armor bonus")
							.defineInRange("charmingBraceletArmor", 2, 0, Integer.MAX_VALUE);
					charmingBraceletCooldown = builder
							.comment("Charming Bracelet: Cool down for target transfer on hurt, in seconds")
							.defineInRange("charmingBraceletCooldown", 10, 1, Integer.MAX_VALUE);
					charmingBraceletReputationBonus = builder
							.comment("Charming Bracelet: Reputation bonus when you kill zombies")
							.defineInRange("charmingBraceletReputationBonus", 1, 0, Integer.MAX_VALUE);
				}

				//scarlet_brace
				{
					scarletBraceletMaxCost = builder
							.comment("Scarlet Bracelet: remaining HP as percentage")
							.defineInRange("scarletMaxCost", 0.50, 0, 1);
					scarletBraceletDamageBonus = builder
							.comment("Scarlet Bracelet: damage boost as percentage of target max health per health cost")
							.defineInRange("scarletBraceletDamageBonus", 0.0001, 0, Integer.MAX_VALUE);
					scarletBraceletDamageLimit = builder
							.comment("Scarlet Bracelet: max damage boost as percentage of target max health")
							.defineInRange("scarletBraceletDamageLimit", 0.50, 0, Integer.MAX_VALUE);

				}

				//spirit_brace
				spiritBraceletEffectDuration = builder
						.comment("Spirit Bracelet: speed effect duration in seconds")
						.defineInRange("spiritBraceletEffectDuration", 5, 1, Integer.MAX_VALUE);


				// emerald bracelet
				{
					emeraldBraceletCritRate = builder
							.comment("Emerald Bracelet: crit rate bonus")
							.defineInRange("emeraldBraceletCritRate", 0.1, 0, 1);

					emeraldBraceletLuckReq = builder
							.comment("Emerald Bracelet: luck requirement to gain effect")
							.defineInRange("emeraldBraceletLuckReq", 2, 0, 100);

					emeraldBraceletEffChance = builder
							.comment("Emerald Bracelet: absorption effect chance")
							.defineInRange("emeraldBraceletEffChance", 0.1, 0, 1);
				}

				lifeBraceletRegenBonus = builder
						.comment("Life Bracelet: regen rate bonus")
						.defineInRange("lifeBraceletRegenBonus", 0.15, 0, 10);

				preciousBraceletReach = builder
						.comment("Precious Bracelet: block reach bonus")
						.defineInRange("preciousBraceletReach", 1d, 0, 10);

				preciousBraceletMagicReduction = builder
						.comment("Precious Bracelet: magic damage reduction")
						.defineInRange("preciousBraceletMagicReduction", 0.4, 0, 1);

				redRubyBraceletFireReduction = builder
						.comment("Red Ruby Bracelet: fire damage reduction")
						.defineInRange("redRubyBraceletFireReduction", 0.9, 0, 1);

				builder.pop();
			}

		}

		public static class Charm {

			// destroyer badge
			public final ForgeConfigSpec.DoubleValue destroyerBadgeAttack;
			public final ForgeConfigSpec.DoubleValue destroyerBadgeDamagePenalty;
			public final ForgeConfigSpec.DoubleValue destroyerBadgeThreshold;
			public final ForgeConfigSpec.DoubleValue destroyerBadgeHurtBonus;

			// holy sword
			public final ForgeConfigSpec.DoubleValue holySwordCritRate;
			public final ForgeConfigSpec.DoubleValue holySwordReflect;
			public final ForgeConfigSpec.DoubleValue holySwordLostLifeAddDamage;

			//holy talisman
			public final ForgeConfigSpec.IntValue holyTalismanWeakenInterval;
			public final ForgeConfigSpec.DoubleValue holyTalismanProtection;
			public final ForgeConfigSpec.DoubleValue holyTalismanProtectionUndead;
			public final ForgeConfigSpec.IntValue holyTalismanEffectDuration;
			public final ForgeConfigSpec.IntValue holyTalismanCooldown;

			//AbyssWillBadge
			public final ForgeConfigSpec.IntValue abyssWillBadgeInitialLevel;
			public final ForgeConfigSpec.IntValue abyssWillBadgeDuration;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeRemainingHealth;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeChanceSmall;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeChanceLarge;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeBonusSmall;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeBonusLarge;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgePenaltySmall;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgePenaltyLarge;
			public final ForgeConfigSpec.IntValue abyssWillBadgeGrowInterval;
			public final ForgeConfigSpec.IntValue abyssWillBadgeLimit;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeDamageBonus;
			public final ForgeConfigSpec.DoubleValue abyssWillBadgeDamagePenalty;

			// angel heart
			public final ForgeConfigSpec.DoubleValue angelHeartThreshold;
			public final ForgeConfigSpec.DoubleValue angelHeartProtection;
			public final ForgeConfigSpec.IntValue angelHeartRemoveInterval;
			public final ForgeConfigSpec.IntValue angelHeartBloodInterval;
			public final ForgeConfigSpec.IntValue angelHeartHealAmount;

			// AngelPearl
			public final ForgeConfigSpec.DoubleValue angelPearlRegen;
			public final ForgeConfigSpec.IntValue angelPearlArmor;

			//corrupt badge
			public final ForgeConfigSpec.IntValue corruptBadgeDebuffDuration;
			public final ForgeConfigSpec.DoubleValue corruptBadgeDigSpeedBonus;
			public final ForgeConfigSpec.DoubleValue corruptBadgeAttackSpeedBonus;
			public final ForgeConfigSpec.DoubleValue corruptBadgeAttackBonus;
			public final ForgeConfigSpec.IntValue corruptBadgeCooldown;

			// cursed protector
			public final ForgeConfigSpec.DoubleValue cursedProtectorThreshold;
			public final ForgeConfigSpec.DoubleValue cursedProtectorReduction;
			public final ForgeConfigSpec.DoubleValue cursedProtectorFrontProtect;

			// cursed talisman
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritRateAdd;
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritDamageAdd;

			//cursed totem
			public final ForgeConfigSpec.IntValue cursedTotemMaxLevel;
			public final ForgeConfigSpec.IntValue cursedTotemConsumption;
			public final ForgeConfigSpec.IntValue cursedTotemEffectDuration;
			public final ForgeConfigSpec.IntValue cursedTotemEffectLevel;

			//demon curse
			public final ForgeConfigSpec.DoubleValue demonCurseAttackBonus;
			public final ForgeConfigSpec.DoubleValue demonCurseSpeedBonus;

			//gluttony badge
			public final ForgeConfigSpec.IntValue gluttonyBadgeHungerLevel;
			public final ForgeConfigSpec.DoubleValue gluttonyBadgeProtection;
			public final ForgeConfigSpec.IntValue gluttonyBadgeEffectDuration;

			//knight shelter
			public final ForgeConfigSpec.IntValue knightShelterArmor;
			public final ForgeConfigSpec.IntValue knightShelterDamageReduction;
			public final ForgeConfigSpec.IntValue knightShelterHealInterval;
			public final ForgeConfigSpec.DoubleValue knightShelterReflection;

			// magic horseshoe
			public final ForgeConfigSpec.DoubleValue magicHorseshoeSpeedBonus;
			public final ForgeConfigSpec.DoubleValue magicHorseshoeLuck;
			public final ForgeConfigSpec.DoubleValue magicHorseshoeFallReduction;

			//sacrificial object
			public final ForgeConfigSpec.DoubleValue sacrificialObjectReduction;
			public final ForgeConfigSpec.DoubleValue sacrificialObjectHeritage;
			public final ForgeConfigSpec.DoubleValue sacrificialObjectSacrifice;

			//soul box
			public final ForgeConfigSpec.DoubleValue soulBoxShatterChance;
			public final ForgeConfigSpec.DoubleValue soulBoxReflect;
			public final ForgeConfigSpec.IntValue soulBoxCooldown;
			public final ForgeConfigSpec.IntValue soulBoxShatterHighDuration;
			public final ForgeConfigSpec.IntValue soulBoxShatterHighLevel;
			public final ForgeConfigSpec.IntValue soulBoxShatterLowDuration;
			public final ForgeConfigSpec.IntValue soulBoxShatterLowLevel;

			// bearing stamen
			public final ForgeConfigSpec.DoubleValue bearingStamenMaxHealth;
			public final ForgeConfigSpec.DoubleValue bearingStamenRegen;
			public final ForgeConfigSpec.IntValue bearingStamenLevel;

			// sands talisman
			public final ForgeConfigSpec.DoubleValue sandsTalismanDamageBonus;
			public final ForgeConfigSpec.DoubleValue sandsTalismanExpBonus;

			//twisted brain
			public final ForgeConfigSpec.DoubleValue twistedbrainAvoidChance;
			public final ForgeConfigSpec.IntValue twistedBrainEffectDuration;

			//undead charm
			public final ForgeConfigSpec.IntValue undeadCharmCooldown;

			//wardead badge
			public final ForgeConfigSpec.DoubleValue warDeadBadgeHeal;
			public final ForgeConfigSpec.DoubleValue warDeadBadgeAtk;
			public final ForgeConfigSpec.DoubleValue warDeadBadgeArmor;
			public final ForgeConfigSpec.DoubleValue warDeadBadgeSpeed;
			public final ForgeConfigSpec.DoubleValue warDeadBadgeThreshold;

			// solar magnet
			public final ForgeConfigSpec.DoubleValue solarMagnetDamageBonus;

			private Charm(ForgeConfigSpec.Builder builder) {
				builder.push("charm");

				// destroyer badge
				{
					destroyerBadgeAttack = builder
							.comment("Destroyer Badge: attack bonus")
							.defineInRange("destroyerBadgeAttack", 0.4, 0, 100);
					destroyerBadgeDamagePenalty = builder
							.comment("Destroyer Badge: damage penalty")
							.defineInRange("destroyerBadgeDamagePenalty", 0.5, 0, 100);
					destroyerBadgeThreshold = builder
							.comment("Destroyer Badge: health threshold for hurt boost")
							.defineInRange("destroyerBadgeThreshold", 0.5, 0, 100);
					destroyerBadgeHurtBonus = builder
							.comment("Destroyer Badge: hurt boost")
							.defineInRange("destroyerBadgeHurtBonus", 0.2, 0, 100);
				}

				// holy_sword
				{
					holySwordCritRate = builder
							.comment("Holy Sword: crit rate bonus")
							.defineInRange("holySwordCritRate", 0.15, 0, 1);
					holySwordLostLifeAddDamage = builder
							.comment("Holy Sword: atk bonus per life lost")
							.defineInRange("holySwordLostLifeAddDamage", 0.02, 0, 100);
					holySwordReflect = builder
							.comment("Holy Sword: damage reflection rate")
							.defineInRange("holySwordReflect", 0.12, 0, 10);
				}

				//holy talisman
				{
					holyTalismanWeakenInterval = builder
							.comment("Holy Talisman: weakness apply interval in seconds")
							.defineInRange("holyTalismanWeakenInterval", 10, 1, Integer.MAX_VALUE);
					holyTalismanEffectDuration = builder
							.comment("Holy Talisman: effect duration per mob in seconds")
							.defineInRange("holyTalismanEffectDuration", 2, 1, Integer.MAX_VALUE);
					holyTalismanProtection = builder
							.comment("Holy Talisman: non-undead protection")
							.defineInRange("holyTalismanProtection", 0.25, 0, 1.00);
					holyTalismanProtectionUndead = builder
							.comment("Holy Talisman: undead protection")
							.defineInRange("holyTalismanProtectionUndead", 0.35, 0, 1.00);
					holyTalismanCooldown = builder
							.comment("Holy Talisman: totem cool down")
							.defineInRange("holyTalismanCooldown", 60, 1, Integer.MAX_VALUE);
				}


				//AbyssWillBadge
				{
					abyssWillBadgeInitialLevel = builder
							.comment("AbyssWillBadge: The levels after triggering the skill")
							.defineInRange("abyssWillBadgeInitialLevel", 15, 0, Integer.MAX_VALUE);
					abyssWillBadgeDuration = builder
							.comment("AbyssWillBadge: The duration of the skill in seconds")
							.defineInRange("abyssWillBadgeDuration", 15, 0, Integer.MAX_VALUE);
					abyssWillBadgeRemainingHealth = builder
							.comment("AbyssWillBadge: Remaining HP in percentage after skill ends")
							.defineInRange("abyssWillBadgeRemainingHealth", 0.2, 0, 1.00);
					abyssWillBadgeChanceSmall = builder
							.comment("AbyssWillBadge: Chance of small damage bonus")
							.defineInRange("abyssWillBadgeChanceSmall", 0.4, 0.0, 1.0);
					abyssWillBadgeChanceLarge = builder
							.comment("AbyssWillBadge: Chance of large damage bonus")
							.defineInRange("abyssWillBadgeChanceLarge", 0.1, 0.0, 1.0);
					abyssWillBadgeBonusSmall = builder
							.comment("AbyssWillBadge: Small damage bonus")
							.defineInRange("abyssWillBadgeBonusSmall", 1.5, 0.0, 10);
					abyssWillBadgeBonusLarge = builder
							.comment("AbyssWillBadge: Large damage bonus")
							.defineInRange("abyssWillBadgeBonusLarge", 2.0, 0.0, 50);
					abyssWillBadgePenaltySmall = builder
							.comment("AbyssWillBadge: Small hurt penalty")
							.defineInRange("abyssWillBadgePenaltySmall", 2.0, 0.0, 10);
					abyssWillBadgePenaltyLarge = builder
							.comment("AbyssWillBadge: Large hurt penalty")
							.defineInRange("abyssWillBadgePenaltyLarge", 2.5, 0.0, 50);
					abyssWillBadgeGrowInterval = builder
							.comment("AbyssWillBadge: Interval for adding levels in seconds")
							.defineInRange("abyssWillBadgeGrowInterval", 20, 1, Integer.MAX_VALUE);
					abyssWillBadgeLimit = builder
							.comment("AbyssWillBadge: Maximum level for passive growth")
							.defineInRange("abyssWillBadgeLimit", 10, 1, Integer.MAX_VALUE);
					abyssWillBadgeDamageBonus = builder
							.comment("AbyssWillBadge: damage bonus per level")
							.defineInRange("abyssWillBadgeDamageBonus", 0.20, 0, 1.00);
					abyssWillBadgeDamagePenalty = builder
							.comment("AbyssWillBadge: damage penalty per level")
							.defineInRange("abyssWillBadgeDamagePenalty", 0.25, 0, 1.00);

				}

				// angel_heart
				{
					angelHeartThreshold = builder
							.comment("Angel Heart: health threshold for protection")
							.defineInRange("angelHeartThreshold", 0.5, 0, 1);
					angelHeartProtection = builder
							.comment("Angel Heart: damage reduction")
							.defineInRange("angelHeartProtection", 0.2, 0, 1);

					angelHeartRemoveInterval = builder
							.comment("Angel Heart: interval in seconds to remove negative effects")
							.defineInRange("angelHeartRemoveInterval", 30, 1, Integer.MAX_VALUE);

					angelHeartBloodInterval = builder
							.comment("Angel Heart: interval in seconds to restore health")
							.defineInRange("angelHeartBloodInterval", 2, 1, Integer.MAX_VALUE);

					angelHeartHealAmount = builder
							.comment("Angel Heart: how much health is restored at once")
							.defineInRange("angelHeartHealAmount", 1, 1, Integer.MAX_VALUE);
				}

				// angel_pearl
				{
					angelPearlRegen = builder
							.comment("Angel Pearl: Regen rate per beneficial effect")
							.defineInRange("angelPearlRegen", 0.08, 0, 1.00);
					angelPearlArmor = builder
							.comment("Angel Pearl: armor bonus per beneficial effect")
							.defineInRange("angelPearlArmor", 1, 0, Integer.MAX_VALUE);
				}

				//CorruptBadge
				{
					corruptBadgeDebuffDuration = builder
							.comment("Corrupt Badge: Negative effect duration in seconds")
							.defineInRange("corruptBadgeDebuffDuration", 15, 1, Integer.MAX_VALUE);
					corruptBadgeDigSpeedBonus = builder
							.comment("Corrupt Badge: dig speed bonus per negative effect")
							.defineInRange("corruptBadgeDigSpeedBonus", 0.09, 0.00, Integer.MAX_VALUE);
					corruptBadgeAttackSpeedBonus = builder
							.comment("Corrupt Badge: attack speed bonus per negative effect")
							.defineInRange("corruptBadgeAttackSpeedBonus", 0.03, 0.00, Integer.MAX_VALUE);
					corruptBadgeAttackBonus = builder
							.comment("Corrupt Badge: attack bonus per negative effect")
							.defineInRange("corruptBadgeAttackBonus", 0.14, 0.00, Integer.MAX_VALUE);
					corruptBadgeCooldown = builder
							.comment("Corrupt Badge: Skill cooldown in seconds")
							.defineInRange("corruptBadgeCooldown", 60, 1, Integer.MAX_VALUE);
				}

				// cursed_protector
				{
					cursedProtectorFrontProtect = builder
							.comment("Cursed Protector: damage reduction from front")
							.defineInRange("cursedProtectorFrontProtect", 0.3, 0, 1);
					cursedProtectorThreshold = builder
							.comment("Cursed Protector: health threshold to trigger damage reduction")
							.defineInRange("cursedProtectorThreshold", 0.35, 0, 1);
					cursedProtectorReduction = builder
							.comment("Cursed Protector: damage reduction percentage")
							.defineInRange("cursedProtectorReduction", 0.25, 0, 1);
				}

				// cursed_talisman
				{
					cursedTalismanCritRateAdd = builder
							.comment("Cursed Talisman: critical hit rate increased by each enchantment")
							.defineInRange("cursedTalismanCritRateAdd", 0.04, 0, 1);

					cursedTalismanCritDamageAdd = builder
							.comment("Cursed Talisman: critical hit damage increased by each enchantment")
							.defineInRange("cursedTalismanCritDamageAdd", 0.08, 0, 1);
				}

				//cursed_totem
				{
					cursedTotemMaxLevel = builder
							.comment("Cursed Totem: maximum level")
							.defineInRange("cursedTotemMaxLevel", 5, 0, Integer.MAX_VALUE);
					cursedTotemConsumption = builder
							.comment("Cursed Totem: levels consumed when negating fatal damage")
							.defineInRange("cursedTotemConsumption", 5, 0, Integer.MAX_VALUE);
					cursedTotemEffectDuration = builder
							.comment("Cursed Totem: duration of wither effect in seconds")
							.defineInRange("cursedTotemEffectDuration", 30, 0, Integer.MAX_VALUE);
					cursedTotemEffectLevel = builder
							.comment("Cursed Totem: level of wither effect (0 means Lv.I)")
							.defineInRange("cursedTotemEffectLevel", 2, 0, 5);
				}

				//demon curse
				{
					demonCurseAttackBonus = builder
							.comment("Demon Curse: attack bonus per 1% extra regen rate")
							.defineInRange("demonCurseAttackBonus", 0.02, 0.0, 10.0);
					demonCurseSpeedBonus = builder
							.comment("Demon Curse: speed bonus per 1% extra regen rate")
							.defineInRange("demonCurseSpeedBonus", 0.01, 0.0, 10.0);
				}

				//gluttony badge
				{
					gluttonyBadgeHungerLevel = builder
							.comment("Gluttony Badge: Hunger effect level")
							.defineInRange("gluttonyBadgeHungerLevel", 2, 1, 100);
					gluttonyBadgeProtection = builder
							.comment("Gluttony Badge: damage reduction per food level")
							.defineInRange("gluttonyBadgeProtection", 0.01, 0, 1.00);
					gluttonyBadgeEffectDuration = builder
							.comment("Gluttony Badge: effect duration in seconds after eating food")
							.defineInRange("gluttonyBadgeEffectDuration", 2, 1, 100);
				}

				//knight shelter
				{
					knightShelterArmor = builder
							.comment("Knight Shelter: armor")
							.defineInRange("knightShelterArmor", 8, 0, Integer.MAX_VALUE);
					knightShelterDamageReduction = builder
							.comment("Knight Shelter: damage reduction")
							.defineInRange("knightShelterDamageReduction", 4, 0, Integer.MAX_VALUE);
					knightShelterHealInterval = builder
							.comment("Knight Shelter: healing interval for offhand shield in seconds. Main hand shield takes half of the time")
							.defineInRange("knightShelterHealInterval", 4, 0, Integer.MAX_VALUE);
					knightShelterReflection = builder
							.comment("Knight Shelter: reflection percentage")
							.defineInRange("knightShelterReflection", 0.30, 0, 1);
				}

				// magic horseshoe
				{
					magicHorseshoeSpeedBonus = builder
							.comment("Magic Horseshoe: speed bonus")
							.defineInRange("magicHorseshoeSpeedBonus", 0.25, 0, 10);

					magicHorseshoeLuck = builder
							.comment("Magic Horseshoe: luck")
							.defineInRange("magicHorseshoeLuck", 1.0, 0, 10);

					magicHorseshoeFallReduction = builder
							.comment("Magic Horseshoe: fall damage reduction")
							.defineInRange("magicHorseshoeFallReduction", 0.95, 0, 10);

				}

				//sacrificial object
				{
					sacrificialObjectReduction = builder
							.comment("Sacrificial Object: damage reduction percentage")
							.defineInRange("sacriReduction", 0.05, 0, 1);
					sacrificialObjectHeritage = builder
							.comment("Sacrificial Object: gold ingot drop chance on death")
							.defineInRange("sacrificialObjectHeritage", 0.5, 0, 1);
					sacrificialObjectSacrifice = builder
							.comment("Sacrificial Object :death sacrifice chance")
							.defineInRange("sacrificialObjectSacrifice", 0.45, 0, 1);
				}

				//soul box
				{
					soulBoxShatterChance = builder
							.comment("Soul Box: chance to inflict shatter effect")
							.defineInRange("soulBoxShatterChance", 0.30, 0, 1);
					soulBoxReflect = builder
							.comment("Soul Box: abyss damage as percentage of original damage")
							.defineInRange("soulBoxReflect", 2.50, 0, 100);
					soulBoxCooldown = builder
							.comment("Soul Box: cool down in seconds")
							.defineInRange("soulBoxCooldown", 240, 1, Integer.MAX_VALUE);
					soulBoxShatterHighDuration = builder
							.comment("Soul Box: effect duration inflicted on fatal hit, in seconds")
							.defineInRange("soulBoxShatterHighDuration", 60, 1, Integer.MAX_VALUE);
					soulBoxShatterHighLevel = builder
							.comment("Soul Box: effect level inflicted on fatal hit, (0 means Lv.I)")
							.defineInRange("soulBoxShatterHighLevel", 2, 0, 100);
					soulBoxShatterLowDuration = builder
							.comment("Soul Box: effect duration inflicted on non-fatal hit, in seconds")
							.defineInRange("soulBoxShatterLowDuration", 5, 1, Integer.MAX_VALUE);
					soulBoxShatterLowLevel = builder
							.comment("Soul Box: effect level inflicted on non-fatal hit, (0 means Lv.I)")
							.defineInRange("soulBoxShatterLowLevel", 0, 0, 100);
				}

				// bearing stamen
				{
					bearingStamenMaxHealth = builder
							.comment("Bearing Stamen: max health bonus")
							.defineInRange("bearingStamenMaxHealth", 20d, 0, 1000);
					bearingStamenRegen = builder
							.comment("Bearing Stamen: regen bonus")
							.defineInRange("bearingStamenRegen", 0.25, 0, 10);
					bearingStamenLevel = builder
							.comment("Bearing Stamen: effect level (0 means Lv.I)")
							.defineInRange("bearingStamenLevel", 1, 0, 10);
				}

				// sands talisman
				{
					sandsTalismanDamageBonus = builder
							.comment("Sands Talisman: damage bonus in hot regions")
							.defineInRange("sandsTalismanDamageBonus", 0.3, 0, 10);
					sandsTalismanExpBonus = builder
							.comment("Sands Talisman: exp bonus")
							.defineInRange("sandsTalismanExpBonus", 0.5, 0, 10);
				}

				//twisted brain
				{
					twistedbrainAvoidChance = builder
							.comment("Twisted Brain: chance to avoid damage")
							.defineInRange("twistedbrainAvoidChance", 0.17, 0, 1);
					twistedBrainEffectDuration = builder
							.comment("Twisted Brain: damage boost effect duration")
							.defineInRange("twistedBrainEffectDuration", 5, 0, 100);
				}

				//undead charm
				{
					undeadCharmCooldown = builder
							.comment("Undead Charm: Cool Down in seconds")
							.defineInRange("undeadCharmCooldown", 180, 1, Integer.MAX_VALUE);
				}

				//wardead badge
				{
					warDeadBadgeHeal = builder
							.comment("War Dead Badge: healing in percentage of lost health per surrounding entity")
							.defineInRange("warDeadBadgeHeal", 0.01, 0, 1);
					warDeadBadgeAtk = builder
							.comment("War Dead Badge: damage bonus per 1% lost health")
							.defineInRange("warDeadBadgeAtk", 0.02, 0, 1);
					warDeadBadgeArmor = builder
							.comment("War Dead Badge: armor bonus per 1% lost health")
							.defineInRange("warDeadBadgeArmor", 0.02, 0, 1);
					warDeadBadgeSpeed = builder
							.comment("War Dead Badge: speed bonus per 1% lost health")
							.defineInRange("warDeadBadgeSpeed", 0.01, 0, 1);
					warDeadBadgeThreshold = builder
							.comment("War Dead Badge: health threshold to trigger healing")
							.defineInRange("warDeadBadgeThreshold", 0.5, 0, 1);
				}

				// solar magnet
				{
					solarMagnetDamageBonus = builder
							.comment("Solar Magnet: damage bonus at day")
							.defineInRange("solarMagnetDamageBonus", 0.25, 0, 10);
				}

				builder.pop();
			}

		}

		public static class Curse {

			public final ForgeConfigSpec.DoubleValue chaoticExplosionDamage;
			public final ForgeConfigSpec.DoubleValue chaoticOtherDamage;
			public final ForgeConfigSpec.DoubleValue chaoticBlessDamageReduction;

			public final ForgeConfigSpec.IntValue originTriggerDurability;
			public final ForgeConfigSpec.DoubleValue originCurseDamage;
			public final ForgeConfigSpec.DoubleValue originBlessDamage;

			public final ForgeConfigSpec.DoubleValue lifeCurseHealth;
			public final ForgeConfigSpec.DoubleValue lifeCurseHeal;
			public final ForgeConfigSpec.DoubleValue lifeBlessHealth;
			public final ForgeConfigSpec.DoubleValue lifeBlessHeal;

			public final ForgeConfigSpec.DoubleValue truthCurseMinDamage;
			public final ForgeConfigSpec.DoubleValue truthBlessMaxDamage;

			public final ForgeConfigSpec.DoubleValue nihilityCurseDuration;
			public final ForgeConfigSpec.DoubleValue nihilityBlessReduction;
			public final ForgeConfigSpec.IntValue nihilityBlessDuration;

			public final ForgeConfigSpec.DoubleValue endCurseThreshold;
			public final ForgeConfigSpec.IntValue endCurseDuration;
			public final ForgeConfigSpec.DoubleValue endBlessHeal;

			public final ForgeConfigSpec.IntValue etchingSlotSize;
			public final ForgeConfigSpec.IntValue charmSlotSize;

			private Curse(ForgeConfigSpec.Builder builder) {
				builder.push("curse");

				chaoticExplosionDamage = builder
						.comment("Chaotic Curse: Damage increment percentage for explosion")
						.defineInRange("chaoticExplosionDamage", 0.75, 0, 100);

				chaoticOtherDamage = builder
						.comment("Chaotic Curse: Damage increment percentage for non-explosion")
						.defineInRange("chaoticOtherDamage", 0.5, 0, 100);

				chaoticBlessDamageReduction = builder
						.comment("Chaotic Bless: Max damage reduction at zero health")
						.defineInRange("chaoticBlessDamageReduction", 0.5, 0, 1);

				originTriggerDurability = builder
						.comment("Origin Curse: Durability threshold to trigger curse ")
						.defineInRange("originTriggerDurability", 500, 0, 1000000);

				originCurseDamage = builder
						.comment("Origin Curse: Player attack damage reduction")
						.defineInRange("originCurseDamage", 0.5, 0, 1);

				originBlessDamage = builder
						.comment("Origin Bless: Player attack damage bonus")
						.defineInRange("originBlessDamage", 0.25, 0, 100);

				lifeCurseHealth = builder
						.comment("Life Curse: Player max health reduction")
						.defineInRange("lifeCurseHealth", 0.25, 0, 1);

				lifeCurseHeal = builder
						.comment("Life Curse: Player healing reduction")
						.defineInRange("lifeCurseHeal", 0.5, 0, 1);

				lifeBlessHealth = builder
						.comment("Life Bless: Player max health bonus")
						.defineInRange("lifeBlessHealth", 0.2, 0, 100);

				lifeBlessHeal = builder
						.comment("Life Bless: Player healing bonus")
						.defineInRange("lifeBlessHeal", 0.3, 0, 100);

				truthCurseMinDamage = builder
						.comment("Truth Curse: minimum damage from mobs")
						.defineInRange("truthCurseMinDamage", 0.3, 0, 1);

				truthBlessMaxDamage = builder
						.comment("Truth Bless: maximum damage from mobs")
						.defineInRange("truthBlessMaxDamage", 0.6, 0, 1);

				nihilityCurseDuration = builder
						.comment("Nihility Curse: negative effect duration")
						.defineInRange("nihilityCurseDuration", 1d, 0, 100);

				nihilityBlessReduction = builder
						.comment("Nihility Bless: void damage reduction")
						.defineInRange("nihilityBlessReduction", 0.75, 0, 1);

				nihilityBlessDuration = builder
						.comment("Nihility Bless: effect duration inflicted")
						.defineInRange("nihilityBlessDuration", 10, 1, 1000);

				endCurseThreshold = builder
						.comment("End Curse: damage threshold for negative effects")
						.defineInRange("endCurseThreshold", 0.1, 0, 1);

				endCurseDuration = builder
						.comment("End Curse: negative effect duration")
						.defineInRange("endCurseDuration", 10, 1, 1000);

				endBlessHeal = builder
						.comment("End Bless: healing as percentage of health lost")
						.defineInRange("endBlessHeal", 0.2, 0, 1);

				etchingSlotSize = builder
						.comment("Catastrophe Scroll: Number of etching slots size")
						.defineInRange("etchingSlotSize", 7, 0, 7);

				charmSlotSize = builder
						.comment("Catastrophe Scroll: Number of charm slots size")
						.defineInRange("charmSlotSize", 3, 0, 10);

				builder.pop();
			}

		}

		public static class Head {

			public final ForgeConfigSpec.IntValue abyssCoreCooldown;
			public final ForgeConfigSpec.IntValue abyssCoreReduce;
			public final ForgeConfigSpec.IntValue abyssCoreDamageJudgement;
			public final ForgeConfigSpec.DoubleValue guardianEyeSwimSpeedBonus;
			public final ForgeConfigSpec.DoubleValue guardianEyeProtection;
			public final ForgeConfigSpec.DoubleValue prayerCrownHealAmount;
			public final ForgeConfigSpec.DoubleValue prayerCrownHealChance;
			public final ForgeConfigSpec.DoubleValue prayerCrownProtection;
			public final ForgeConfigSpec.DoubleValue spiritCrownArrowDamageMultiplier;
			public final ForgeConfigSpec.DoubleValue spiritCrownDistanceDamage;
			public final ForgeConfigSpec.IntValue spiritCrownMaxEntityCount;
			public final ForgeConfigSpec.IntValue seaGodCrownCoolDown;
			public final ForgeConfigSpec.IntValue sakuraHairpinMaxHealthAdd;
			public final ForgeConfigSpec.DoubleValue sakuraHairpinArmorBonus;
			public final ForgeConfigSpec.DoubleValue sakuraHairpinCritBonusFromLuck;
			public final ForgeConfigSpec.DoubleValue angelDesireDamageBonus;

			private Head(ForgeConfigSpec.Builder builder) {
				builder.push("head");
				abyssCoreCooldown = builder
						.comment("Abyss Core: effect cooldown")
						.defineInRange("abyssCoreCooldown", 60, 0, 100000);
				abyssCoreDamageJudgement = builder
						.comment("Abyss Core: damage judgement")
						.defineInRange("abyssCoreDamageJudgement", 12, 1, 100000);
				abyssCoreReduce = builder
						.comment("Abyss Core: damage reduce")
						.defineInRange("abyssCoreReduce", 12, 0, 100000);
				guardianEyeSwimSpeedBonus = builder
						.comment("Guardian Eye: swim speed bonus")
						.defineInRange("guardianEyeSwimSpeedBonus", 0.15, 0, 100);
				guardianEyeProtection = builder
						.comment("Guardian Eye: protection")
						.defineInRange("guardianEyeProtection", 0.2, 0, 1);
				prayerCrownHealAmount = builder
						.comment("Prayer Crown: heal amount")
						.defineInRange("prayerCrownHealAmount", 0.25, 0, 10);
				prayerCrownHealChance = builder
						.comment("Prayer Crown: heal chance")
						.defineInRange("prayerCrownHealChance", 0.5, 0, 1);
				prayerCrownProtection = builder
						.comment("Prayer Crown: protection")
						.defineInRange("prayerCrownProtection", 0.25, 0, 10);
				spiritCrownMaxEntityCount = builder
						.comment("Spirit Crown: max entity count around player to trigger damage boost")
						.defineInRange("spiritCrownMaxEntityCount", 3, 0, 100);
				spiritCrownArrowDamageMultiplier = builder
						.comment("Spirit Crown: arrow damage multiplier")
						.defineInRange("spiritCrownArrowDamageMultiplier", 0.45, 0, 100);
				spiritCrownDistanceDamage = builder
						.comment("Spirit Crown: distance damage multiplier")
						.defineInRange("spiritCrownDistanceDamage", 0.02, 0, 1);
				seaGodCrownCoolDown = builder
						.comment("Sea God Crown: skill cool down")
						.defineInRange("seaGodCrownCoolDown", 30, 0, 1000);
				sakuraHairpinMaxHealthAdd = builder
						.comment("Sakura Hairpin: max health add amount")
						.defineInRange("sakuraHairpinMaxHealthAdd", 10, 0, 500);
				sakuraHairpinArmorBonus = builder
						.comment("Sakura Hairpin: armor bonus amount")
						.defineInRange("sakuraHairpinArmorBonus", 0.1, 0, 10);
				sakuraHairpinCritBonusFromLuck = builder
						.comment("Sakura Hairpin: crit rate bonus from luck")
						.defineInRange("sakuraHairpinCritBonusFromLuck", 0.03, 0, 1);
				angelDesireDamageBonus = builder
						.comment("Angel Desire: damage bonus when flying")
						.defineInRange("angelDesireDamageBonus", 0.5, 0, 10);
				builder.pop();
			}

		}

		public static class Heart {

			public final ForgeConfigSpec.DoubleValue heartOfRevengeBowStrength;
			public final ForgeConfigSpec.IntValue heartOfRevengeValidTime;
			public final ForgeConfigSpec.DoubleValue heartOfRevengeDamageBonus;
			public final ForgeConfigSpec.DoubleValue demonHeartDamageBonus;
			public final ForgeConfigSpec.DoubleValue demonHeartDamageReduction;
			public final ForgeConfigSpec.DoubleValue twistedHeartDamage;
			public final ForgeConfigSpec.DoubleValue twistedHeartToughness;
			public final ForgeConfigSpec.DoubleValue greedyHeartXpBonus;


			private Heart(ForgeConfigSpec.Builder builder) {
				builder.push("heart");
				heartOfRevengeBowStrength = builder
						.comment("Heart of Revenge: bow strength")
						.defineInRange("heartOfRevengeBowStrength", 0.06, 0, 1);
				heartOfRevengeValidTime = builder
						.comment("Heart of Revenge: valid time for revenge")
						.defineInRange("heartOfRevengeValidTime", 3, 0, 10);
				heartOfRevengeDamageBonus = builder
						.comment("Heart of Revenge: revenge damage")
						.defineInRange("heartOfRevengeDamageBonus", 0.25, 0, 10);
				demonHeartDamageBonus = builder
						.comment("Demon Heart: damage bonus")
						.defineInRange("demonHeartDamageBonus", 0.1, 0, 1);
				demonHeartDamageReduction = builder
						.comment("Demon Heart: damage reduction")
						.defineInRange("demonHeartDamageReduction", 0.05, 0, 1);
				twistedHeartDamage = builder
						.comment("Twisted Heart: damage")
						.defineInRange("twistedHeartDamage", 0.33, 0, 1);
				twistedHeartToughness = builder
						.comment("Twisted Heart: toughness")
						.defineInRange("twistedHeartToughness", 0.2, 0, 1);
				greedyHeartXpBonus = builder
						.comment("Greedy Heart: Xp pickup bonus")
						.defineInRange("greedyHeartXpBonus", 2d, 0, 100);

				builder.pop();
			}

		}

		public static class Necklace {

			public final ForgeConfigSpec.DoubleValue starNecklaceDamageBonus;
			public final ForgeConfigSpec.IntValue crossNecklaceInvulTick;
			public final ForgeConfigSpec.DoubleValue gallopNecklaceSpeedBonus;
			public final ForgeConfigSpec.DoubleValue gallopNecklaceDamageFactor;
			public final ForgeConfigSpec.DoubleValue fangNecklaceAttack;
			public final ForgeConfigSpec.DoubleValue fangNecklaceDamageBonus;
			public final ForgeConfigSpec.DoubleValue fangNecklacePoisonChance;
			public final ForgeConfigSpec.IntValue fangNecklacePoisonDuration;
			public final ForgeConfigSpec.IntValue fangNecklacePoisonLevel;
			public final ForgeConfigSpec.DoubleValue preciousNecklaceCritDmg;
			public final ForgeConfigSpec.DoubleValue holyNecklaceMaxHealth;
			public final ForgeConfigSpec.IntValue holyNecklaceInvulTick;
			public final ForgeConfigSpec.DoubleValue hierloomNecklaceArmor;
			public final ForgeConfigSpec.DoubleValue hierloomNecklaceSpeed;
			public final ForgeConfigSpec.DoubleValue hierloomNecklaceExp;
			public final ForgeConfigSpec.DoubleValue emeraldNecklaceDrop;
			public final ForgeConfigSpec.DoubleValue enderProtectorChance;
			public final ForgeConfigSpec.IntValue holyNecklaceCooldown;
			public final ForgeConfigSpec.IntValue holyNecklaceDuration;
			public final ForgeConfigSpec.DoubleValue lockOfAbyssExtraDamage;
			public final ForgeConfigSpec.IntValue lockOfAbyssDuration;
			public final ForgeConfigSpec.IntValue lockOfAbyssThreshold;
			public final ForgeConfigSpec.DoubleValue enderProtectorToughness;
			public final ForgeConfigSpec.DoubleValue redHeartNecklaceMaxHealth;
			public final ForgeConfigSpec.DoubleValue spiritNecklaceProjectile;
			public final ForgeConfigSpec.IntValue treasureHunterNecklaceCooldown;
			public final ForgeConfigSpec.DoubleValue treasureHunterNecklaceChance;

			private Necklace(ForgeConfigSpec.Builder builder) {
				builder.push("necklace");
				starNecklaceDamageBonus = builder
						.comment("Star Necklace: Damage bonus from behind")
						.defineInRange("starNecklaceDamageBonus", 0.4, 0, 10);
				crossNecklaceInvulTick = builder
						.comment("Cross Necklace: Invulnerability time bonus in ticks")
						.defineInRange("crossNecklaceInvulTick", 10, 0, 100);
				gallopNecklaceSpeedBonus = builder
						.comment("Gallop Necklace: speed bonus")
						.defineInRange("gallopNecklaceSpeedBonus", 0.08, 0, 10);
				gallopNecklaceDamageFactor = builder
						.comment("Gallop Necklace: damage factor of speed")
						.defineInRange("gallopNecklaceDamageFactor", 1.5, 0, 100000);
				emeraldNecklaceDrop = builder
						.comment("Emerald Necklace: factor of dropping an emerald")
						.defineInRange("emeraldNecklaceDropEmerald", 0.02, 0, 1);
				enderProtectorChance = builder
						.comment("Ender Protector: teleport chance")
						.defineInRange("enderProtectorTeleportChance", 0.5, 0, 1);
				holyNecklaceCooldown = builder
						.comment("Holy Necklace: cooldown")
						.defineInRange("holyNecklaceCooldown", 3, 0, 60);
				holyNecklaceDuration = builder
						.comment("Holy Necklace: duration in seconds")
						.defineInRange("holyNecklaceDuration", 2, 0, 600);
				lockOfAbyssExtraDamage = builder
						.comment("Lock Of Abyss: extra damage multiplier")
						.defineInRange("lockOfAbyssExtraDamage", 2.5, 0, 10);
				lockOfAbyssDuration = builder
						.comment("Lock Of Abyss: slowness duration in seconds")
						.defineInRange("lockOfAbyssDuration", 10, 1, 1000);
				lockOfAbyssThreshold = builder
						.comment("Lock Of Abyss: layer of slowness to take effect")
						.defineInRange("lockOfAbyssThreshold", 7, 1, 10);
				// fang necklace
				{
					fangNecklaceAttack = builder
							.comment("Fang Necklace: Attack bonus")
							.defineInRange("fangNecklaceAttack", 0.1, 0, 10);
					fangNecklaceDamageBonus = builder
							.comment("Fang Necklace: Damage bonus from behind")
							.defineInRange("fangNecklaceDamageBonus", 0.25, 0, 10);
					fangNecklacePoisonChance = builder
							.comment("Fang Necklace: poison inflict chance")
							.defineInRange("fangNecklacePoisonChance", 0.5, 0, 1);
					fangNecklacePoisonDuration = builder
							.comment("Fang Necklace: poison inflicted duration in seconds")
							.defineInRange("fangNecklacePoisonDuration", 5, 0, 1000);
					fangNecklacePoisonLevel = builder
							.comment("Fang Necklace: poison inflicted level (0 means Lv.I)")
							.defineInRange("fangNecklacePoisonLevel", 2, 0, 5);

				}
				preciousNecklaceCritDmg = builder
						.comment("Precious Necklace: crit damage bonus")
						.defineInRange("preciousNecklaceCritDmg", 0.2, 0, 10);
				holyNecklaceMaxHealth = builder
						.comment("Holy Necklace: Max health boost")
						.defineInRange("holyNecklaceMaxHealth", 4d, 0, 100);
				holyNecklaceInvulTick = builder
						.comment("Holy Necklace: additional invul tick")
						.defineInRange("holyNecklaceInvulTick", 5, 0, 100);
				//hierloom necklace
				{

					hierloomNecklaceArmor = builder
							.comment("Hierloom Necklace: armor bonus")
							.defineInRange("hierloomNecklaceArmor", 2d, 0, 100);
					hierloomNecklaceSpeed = builder
							.comment("Hierloom Necklace: movement speed bonus")
							.defineInRange("hierloomNecklaceSpeed", 0.05, 0, 10);
					hierloomNecklaceExp = builder
							.comment("Hierloom Necklace: exp pickup bonus")
							.defineInRange("hierloomNecklaceExp", 0.1, 0, 10);
				}
				enderProtectorToughness = builder
						.comment("Ender Protector: armor toughness bonus")
						.defineInRange("enderProtectorToughness", 4d, 0, 100);
				redHeartNecklaceMaxHealth = builder
						.comment("Red Heart Necklace: max health bonus")
						.defineInRange("redHeartNecklaceMaxHealth", 0.05, 0, 10);
				spiritNecklaceProjectile = builder
						.comment("Spirit Necklace: projectile damage bonus")
						.defineInRange("spiritNecklaceProjectile", 0.25, 0, 10);
				treasureHunterNecklaceCooldown = builder
						.comment("Treasure Hunter Necklace: Cooldown time")
						.defineInRange("treasureHunterNecklaceCoolDown", 6000, 0, 10000000);
				treasureHunterNecklaceChance = builder
						.comment("Treasure Hunter Necklace: chance to catch a loot")
						.defineInRange("treasureHunterNecklaceChance", 0.05, 0, 1);
				builder.pop();
			}

		}

		public static class Pendant {

			public final ForgeConfigSpec.DoubleValue shadowPendantDamageHeal;
			public final ForgeConfigSpec.DoubleValue shadowPendantDamageBonus;
			public final ForgeConfigSpec.DoubleValue shadowPendantDamageReduction;
			public final ForgeConfigSpec.IntValue shadowPendantLightLevel;
			public final ForgeConfigSpec.IntValue chaoticPendantEnchantLevel;


			private Pendant(ForgeConfigSpec.Builder builder) {
				builder.push("pendant");
				shadowPendantDamageHeal = builder
						.comment("Shadow Pendant: damage heal multiplier")
						.defineInRange("shadowPendantDamageHeal", 0.25, 0, 1);
				shadowPendantLightLevel = builder
						.comment("Shadow Pendant: Starting light level to give bonus")
						.defineInRange("shadowPendantLightLevel", 7, 0, 15);
				shadowPendantDamageBonus = builder
						.comment("Shadow Pendant: damage bonus")
						.defineInRange("shadowPendantDamageBonus", 0.05, 0, 1);
				shadowPendantDamageReduction = builder
						.comment("Shadow Pendant: damage reduction")
						.defineInRange("shadowPendantDamageReduction", 0.05, 0, 1);
				chaoticPendantEnchantLevel = builder
						.comment("Chaotic Pendant: enchantment level bonus")
						.defineInRange("chaoticPendantEnchantLevel", 3, 0, 30);
				builder.pop();
			}

		}

		public static class Ring {

			public final ForgeConfigSpec.DoubleValue amethystRingDamage;
			public final ForgeConfigSpec.DoubleValue netheriteRingProtection;
			public final ForgeConfigSpec.IntValue netherFireRingFireTime;
			public final ForgeConfigSpec.IntValue ringOfLifeEffectInterval;

			private Ring(ForgeConfigSpec.Builder builder) {
				builder.push("ring");
				amethystRingDamage = builder.comment("Amethyst Ring: damage boost")
						.defineInRange("amethystRingDamage", 0.1, 0, 100);
				netheriteRingProtection = builder.comment("Netherite Ring: protection in nether")
						.defineInRange("netheriteRingProtection", 0.1, 0, 1);
				netherFireRingFireTime = builder
						.comment("Nether Fire Ring: fire burning time")
						.defineInRange("netherFireRingFireBurningTime", 5, 0, 1000);
				ringOfLifeEffectInterval = builder
						.comment("Ring Of Life: effect interval")
						.defineInRange("ringOfLifeEffectInterval", 5, 1, 100);
				builder.pop();
			}

		}

		public static class Scroll {

			public final ForgeConfigSpec.IntValue skyWalkerCooldown;
			public final ForgeConfigSpec.IntValue travelerEffectDuration;
			public final ForgeConfigSpec.DoubleValue twistedDuplicateChance;
			public final ForgeConfigSpec.DoubleValue travellerScrollSpeedBonus;


			private Scroll(ForgeConfigSpec.Builder builder) {
				builder.push("scroll");

				skyWalkerCooldown = builder
						.comment("Sky Walker Scroll: teleport cooldown in seconds")
						.defineInRange("skyWalkerCooldown", 60, 0, 600);
				travelerEffectDuration = builder
						.comment("Traveller Scroll: effect duration in seconds")
						.defineInRange("travelerEffectDuration", 15, 1, 10000);
				twistedDuplicateChance = builder
						.comment("Twisted Scroll: chance for encountered mobs to duplicate")
						.defineInRange("twistedDuplicateChance", 0.6, 0, 1);
				travellerScrollSpeedBonus = builder
						.comment("Traveller Scroll: speed bonus of all kinds")
						.defineInRange("travellerScrollSpeedBonus", 0.25, 0, 10);

				builder.pop();
			}

			public MobEffectInstance travelerScrollSpeedEffect() {
				return new MobEffectInstance(MobEffects.MOVEMENT_SPEED, travelerEffectDuration.get() * 20, 1);
			}

			public MobEffectInstance travelerScrollRegenEffect() {
				return new MobEffectInstance(MobEffects.REGENERATION, travelerEffectDuration.get() * 20, 0);
			}

		}

		public static class Body {

			public final ForgeConfigSpec.DoubleValue forestCloakDodgeChance;
			public final ForgeConfigSpec.DoubleValue forestCloakJumpPower;

			private Body(ForgeConfigSpec.Builder builder) {
				builder.push("body");

				forestCloakDodgeChance = builder.comment("forest cloak: chance to dodge projectile")
						.defineInRange("forestCloakDodgeChance", 0.2, 0, 1);
				forestCloakJumpPower = builder.comment("forest cloak: jump power bonus")
						.defineInRange("forestCloakJumpPower", 0.25, 0, 10);
				builder.pop();
			}

		}

		public static class Set {

			public final ForgeConfigSpec.DoubleValue seaGodProtect;
			public final ForgeConfigSpec.DoubleValue seaGodMelee;
			public final ForgeConfigSpec.DoubleValue seaGodThrow;
			public final ForgeConfigSpec.IntValue emeraldLuck;
			public final ForgeConfigSpec.DoubleValue emeraldCrit;
			public final ForgeConfigSpec.IntValue spiritPullDuration;
			public final ForgeConfigSpec.DoubleValue spiritBackShootBonus;
			public final ForgeConfigSpec.DoubleValue spiritInflictChance;
			public final ForgeConfigSpec.IntValue spiritEffectDuration;
			public final ForgeConfigSpec.IntValue spiritEffectAmplifier;
			public final ForgeConfigSpec.DoubleValue spiritProtect;

			private Set(ForgeConfigSpec.Builder builder) {
				builder.push("set");

				// sea god
				{
					seaGodProtect = builder.comment("Sea God Set: damage reduction of water mob damage")
							.defineInRange("seaGodProtect", 0.35, 0, 1);
					seaGodMelee = builder.comment("Sea God Set: damage bonus of trident melee")
							.defineInRange("seaGodMelee", 0.5, 0, 10);
					seaGodThrow = builder.comment("Sea God Set: damage bonus of trident throw")
							.defineInRange("seaGodThrow", 0.75, 0, 10);
				}

				// emerald
				{
					emeraldLuck = builder.comment("Emerald Set: Luck bonus")
							.defineInRange("emeraldLuck", 2, 0, 10);
					emeraldCrit = builder.comment("Emerald Set: Crit rate bonus")
							.defineInRange("emeraldCrit", 0.15, 0, 1);
				}

				// spirit
				{
					spiritPullDuration = builder.comment("Spirit Set: time in seconds after pulling bow to apply pulling bonus")
							.defineInRange("spiritPullDuration", 3, 0, 100);
					spiritBackShootBonus = builder.comment("Spirit Set: damage bonus when shooting target from behind")
							.defineInRange("spiritBackShootBonus", 0.5, 0, 10);
					spiritInflictChance = builder.comment("Spirit Set: chance to inflict slowness effect")
							.defineInRange("spiritInflictChance", 0.5, 0, 1);
					spiritEffectDuration = builder.comment("Spirit Set: slowness effect duration")
							.defineInRange("spiritEffectDuration", 3, 0, 100);
					spiritEffectAmplifier = builder.comment("Spirit Set: slowness effect level (0 means Lv.I)")
							.defineInRange("spiritEffectAmplifier", 1, 0, 10);
					spiritProtect = builder.comment("Spirit Set: projectile damage reduction")
							.defineInRange("spiritProtect", 0.2, 0, 1);
				}
				builder.pop();
			}

		}

		public static class Misc {

			public final ForgeConfigSpec.IntValue backtrackMirrorCooldown;
			public final ForgeConfigSpec.IntValue repentMirrorCooldown;
			public final ForgeConfigSpec.DoubleValue copperReinforceChance;
			public final ForgeConfigSpec.IntValue amethystReinforceEffect;

			public final ForgeConfigSpec.BooleanValue giveItemsOnStart;
			public final ForgeConfigSpec.BooleanValue catastropheScrollEquipOnStart;
			public final ForgeConfigSpec.BooleanValue catastropheScrollPreventUnequip;

			private Misc(ForgeConfigSpec.Builder builder) {
				builder.push("misc");

				backtrackMirrorCooldown = builder.defineInRange("backtrackMirrorCooldown",
						10, 0, 10000);
				repentMirrorCooldown = builder.defineInRange("repentMirrorCooldown",
						10, 0, 10000);
				copperReinforceChance = builder.defineInRange("copperReinforceChance",
						0.5, 0, 1);
				amethystReinforceEffect = builder.defineInRange("amethystReinforceEffect",
						1, 0, 100);

				giveItemsOnStart = builder
						.comment("If true, Hierloom Necklace and Catastrophe Scroll will be given to new players")
						.define("giveItemsOnStart", true);
				// catastrophe_scroll
				catastropheScrollEquipOnStart = builder
						.comment("If true, Catastrophe Scroll will be directly equipped onto new players")
						.define("catastropheScrollStart", false);
				catastropheScrollPreventUnequip = builder
						.comment("If true, Catastrophe Scroll cannot be unequipped once equipped")
						.define("catastropheScrollPreventUnequip", true);

				builder.pop();
			}

		}

		public static class Toggles {


			private final LinkedHashMap<String, ForgeConfigSpec.BooleanValue> itemToggle = new LinkedHashMap<>();

			private Toggles(ForgeConfigSpec.Builder builder) {
				builder.push("itemToggle");
				for (var e : CAItems.ALL_CURIOS) {
					itemToggle.put(e, builder.define(e, true));
				}
				builder.pop();
			}

			public ForgeConfigSpec.BooleanValue get(String item) {
				var ans = itemToggle.get(item);
				if (ans == null) {
					CelestialArtifacts.LOGGER.error("Item toggle failed to load. Toggle map size: " + itemToggle.size());
					CelestialArtifacts.LOGGER.error("Iten ID: " + item + ", containing: " + itemToggle.containsKey(item));
					for (var e : itemToggle.entrySet()) {
						CelestialArtifacts.LOGGER.error(e.getKey() + " -> " + e.getValue());
					}
					throw new IllegalStateException("Iten ID: " + item + " is not in config");
				}
				return ans;
			}

		}

	}

}
