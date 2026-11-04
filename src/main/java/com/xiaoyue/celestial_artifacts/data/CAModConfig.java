package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_invoker.invoker.config.wrapper.ConfigWrapper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.LinkedHashMap;

public class CAModConfig {

	public static class Client extends ConfigWrapper {

		Client(Builder builder) {
		}

	}

	public static class Server extends ConfigWrapper {

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

		Server(Builder builder) {
			setCelestial();
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
		}

		public static class Materials {

			public final ModConfigSpec.DoubleValue desireEtchingDropChance;
			public final ModConfigSpec.IntValue desireEtchingLootingRequirement;
			public final ModConfigSpec.DoubleValue endEtchingDropChance;
			public final ModConfigSpec.IntValue endEtchingEffectRequirement;
			public final ModConfigSpec.DoubleValue originEtchingDropChance;
			public final ModConfigSpec.IntValue originEtchingEffectRequirement;
			public final ModConfigSpec.DoubleValue lifeEtchingDropChance;
			public final ModConfigSpec.IntValue lifeEtchingHealthRequirement;
			public final ModConfigSpec.DoubleValue chaoticEtchingDropChance;
			public final ModConfigSpec.DoubleValue nihilityEtchingDropChance;
			public final ModConfigSpec.DoubleValue truthEtchingDropChance;

			public final ModConfigSpec.DoubleValue endDustDropChance;
			public final ModConfigSpec.DoubleValue charmingBraceletDropChance;
			public final ModConfigSpec.IntValue charmingBraceletReputationRequirement;
			public final ModConfigSpec.DoubleValue guardianEyeDropChance;
			public final ModConfigSpec.DoubleValue demonCurseDropChance;
			public final ModConfigSpec.DoubleValue twistedHeartDropChance;
			public final ModConfigSpec.IntValue twistedHeartHealthRequirement;
			public final ModConfigSpec.DoubleValue twistedBrainDropChance;

			private Materials(Builder builder) {
				builder.push("materials", "Materials");
				desireEtchingDropChance = builder.defineInRange("desireEtchingDropChance", 0.25, 0, 1);
				desireEtchingLootingRequirement = builder.defineInRange("desireEtchingLootingRequirement", 7, 0, 100);
				endEtchingDropChance = builder.defineInRange("endEtchingDropChance", 0.75, 0, 1);
				endEtchingEffectRequirement = builder.defineInRange("endEtchingEffectRequirement", 9, 0, 100);
				originEtchingDropChance = builder.defineInRange("originEtchingDropChance", 0.5, 0, 1);
				originEtchingEffectRequirement = builder.defineInRange("originEtchingEffectRequirement", 200, 0, 10000);
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

			public final ModConfigSpec.DoubleValue magicArrowBagBowStrength;
			public final ModConfigSpec.IntValue magicArrowBagArrowKnock;
			public final ModConfigSpec.DoubleValue flameArrowBagBowStrength;
			public final ModConfigSpec.IntValue flameArrowBagArrowKnock;
			public final ModConfigSpec.IntValue flameArrowBagTime;
			public final ModConfigSpec.DoubleValue spiritArrowBagBowStrength;
			public final ModConfigSpec.DoubleValue spiritArrowBagArrowSpeed;
			public final ModConfigSpec.IntValue spiritArrowBagArrowKnock;
			public final ModConfigSpec.DoubleValue leechScabbardHealFactor;
			public final ModConfigSpec.DoubleValue titanScabbardDamageFactor;
			public final ModConfigSpec.IntValue twistedScabbardInterval;
			public final ModConfigSpec.DoubleValue twistedScabbardAttack;
			public final ModConfigSpec.DoubleValue twistedScabbardAttackEnd;
			public final ModConfigSpec.IntValue twistedScabbardMaxAttackBonus;
			public final ModConfigSpec.DoubleValue twistedScabbardAtkKnock;
			public final ModConfigSpec.DoubleValue twistedScabbardAtkSpeed;
			public final ModConfigSpec.DoubleValue twistedScabbardHealRate;
			public final ModConfigSpec.IntValue ironScabbardBladeInterval;
			public final ModConfigSpec.IntValue leechScabbardBladeInterval;
			public final ModConfigSpec.IntValue titanScabbardBladeInterval;
			public final ModConfigSpec.IntValue twistedScabbardBladeInterval;

			private Back(Builder builder) {
				builder.push("back", "Back");

				magicArrowBagBowStrength = builder.comment("Magic Arrow Bag: bow strength")
						.defineInRange("magicArrowBagBowStrength", 0.1, 0, 10);
				magicArrowBagArrowKnock = builder.comment("Magic Arrow Bag: arrow knockback")
						.defineInRange("magicArrowBagArrowKnock", 1, 0, 100);

				flameArrowBagBowStrength = builder.comment("Flame Arrow Bag: bow strength")
						.defineInRange("flameArrowBagBowStrength", 0.1, 0, 10);
				flameArrowBagArrowKnock = builder.comment("Flame Arrow Bag: arrow knockback")
						.defineInRange("flameArrowBagArrowKnock", 1, 0, 100);
				flameArrowBagTime = builder.comment("Flame Arrow Bag: fire duration in ticks")
						.defineInRange("flameArrowBagTime", 60, 1, 36000);

				spiritArrowBagBowStrength = builder.comment("Spirit Arrow Bag: bow strength")
						.defineInRange("spiritArrowBagBowStrength", 0.2, 0, 1);
				spiritArrowBagArrowSpeed = builder.comment("Spirit Arrow Bag: arrow speed")
						.defineInRange("spiritArrowBagArrowSpeed", 0.5, 0, 10);
				spiritArrowBagArrowKnock = builder.comment("Spirit Arrow Bag: arrow knockback")
						.defineInRange("spiritArrowBagArrowKnock", 2, 0, 100);

				ironScabbardBladeInterval = builder.comment("Iron Scabbard: blade modifier effect interval in seconds")
						.defineInRange("ironScabbardBladeInterval", 10, 0, 100);

				leechScabbardBladeInterval = builder.comment("Leech Scabbard: blade modifier effect interval in seconds")
						.defineInRange("leechScabbardBladeInterval", 10, 0, 100);
				leechScabbardHealFactor = builder.comment("Leech Scabbard: healing as a percentage of damage dealt")
						.defineInRange("leechScabbardHealFactor", 0.25, 0, 100);

				titanScabbardBladeInterval = builder.comment("Titan Scabbard: blade modifier effect interval in seconds")
						.defineInRange("titanScabbardBladeInterval", 7, 0, 100);
				titanScabbardDamageFactor = builder.comment("Titan Scabbard: damage bonus while the effect is active")
						.defineInRange("titanScabbardDamageFactor", 0.75, 0, 100);

				twistedScabbardInterval = builder.comment("Twisted Scabbard: interval in seconds to lose one stack of Twist")
						.defineInRange("twistedScabbardInterval", 5, 1, 1000);
				twistedScabbardAttack = builder.comment("Twisted Scabbard: attack bonus per stack of Twist")
						.defineInRange("twistedScabbardAttack", 0.05, 0, 1);
				twistedScabbardAttackEnd = builder.comment("Twisted Scabbard: attack bonus while under the End curse")
						.defineInRange("twistedScabbardAttackEnd", 0.1, 0, 1);
				twistedScabbardMaxAttackBonus = builder.comment("Twisted Scabbard: maximum number of stacks")
						.defineInRange("twistedScabbardMaxAttackBonus", 100, 1, Integer.MAX_VALUE);
				twistedScabbardBladeInterval = builder.comment("Twisted Scabbard: blade modifier effect interval in seconds")
						.defineInRange("twistedScabbardBladeInterval", 5, 0, 100);
				twistedScabbardAtkKnock = builder.comment("Twisted Scabbard: attack knockback")
						.defineInRange("twistedScabbardAtkKnock", 1d, 0, 100);
				twistedScabbardAtkSpeed = builder.comment("Twisted Scabbard: attack speed bonus")
						.defineInRange("twistedScabbardAtkSpeed", 0.25, 0, 1);
				twistedScabbardHealRate = builder.comment("Twisted Scabbard: healing rate penalty")
						.defineInRange("twistedScabbardHealRate", 0.5, 0, 1);

				builder.pop();
			}

		}

		public static class Bracelet {

			// hidden_bracelet
			public final ModConfigSpec.IntValue hiddenBraceletInterval;
			public final ModConfigSpec.DoubleValue hiddenBraceletDamageBoost;

			//charm_brace
			public final ModConfigSpec.IntValue charmingBraceletArmor;
			public final ModConfigSpec.IntValue charmingBraceletCooldown;
			public final ModConfigSpec.IntValue charmingBraceletReputationBonus;

			//scarlet_bloodrage
			public final ModConfigSpec.DoubleValue scarletBraceletMaxCost;
			public final ModConfigSpec.DoubleValue scarletBraceletDamageBonus;
			public final ModConfigSpec.DoubleValue scarletBraceletDamageLimit;

			//spirit_bracelet
			public final ModConfigSpec.IntValue spiritBraceletEffectDuration;


			public final ModConfigSpec.DoubleValue lifeBraceletRegenBonus;
			public final ModConfigSpec.DoubleValue preciousBraceletReach;
			public final ModConfigSpec.DoubleValue preciousBraceletMagicReduction;
			public final ModConfigSpec.DoubleValue redRubyBraceletFireReduction;

			// emerald_necklace
			public final ModConfigSpec.DoubleValue emeraldBraceletCritRate;
			public final ModConfigSpec.IntValue emeraldBraceletLuckReq;
			public final ModConfigSpec.DoubleValue emeraldBraceletEffChance;

			private Bracelet(Builder builder) {
				builder.push("bracelet", "Bracelet");

				// hidden_bracelet
				{
					hiddenBraceletInterval = builder
							.comment("Hidden Bracelet: hidden effect interval")
							.defineInRange("hiddenBraceletInterval", 7, 1, 100000);
					hiddenBraceletDamageBoost = builder
							.comment("Hidden Bracelet: damage bonus while hidden")
							.defineInRange("hiddenBraceletDamageBoost", 0.25, 0, 100);
				}

				//charming bracelet
				{
					charmingBraceletArmor = builder
							.comment("Charming Bracelet: armor bonus")
							.defineInRange("charmingBraceletArmor", 2, 0, Integer.MAX_VALUE);
					charmingBraceletCooldown = builder
							.comment("Charming Bracelet: cooldown in seconds for redirecting damage to a target")
							.defineInRange("charmingBraceletCooldown", 10, 1, Integer.MAX_VALUE);
					charmingBraceletReputationBonus = builder
							.comment("Charming Bracelet: reputation bonus per zombie killed")
							.defineInRange("charmingBraceletReputationBonus", 1, 0, Integer.MAX_VALUE);
				}

				//scarlet_brace
				{
					scarletBraceletMaxCost = builder
							.comment("Scarlet Bracelet: remaining HP as a percentage")
							.defineInRange("scarletBraceletMaxCost", 0.50, 0, 1);
					scarletBraceletDamageBonus = builder
							.comment("Scarlet Bracelet: damage bonus as a percentage of the target's max health per health spent")
							.defineInRange("scarletBraceletDamageBonus", 0.0001, 0, Integer.MAX_VALUE);
					scarletBraceletDamageLimit = builder
							.comment("Scarlet Bracelet: max damage bonus as a percentage of the target's max health")
							.defineInRange("scarletBraceletDamageLimit", 0.50, 0, Integer.MAX_VALUE);

				}

				//spirit_brace
				spiritBraceletEffectDuration = builder
						.comment("Spirit Bracelet: speed effect duration in seconds")
						.defineInRange("spiritBraceletEffectDuration", 5, 1, Integer.MAX_VALUE);


				// emerald bracelet
				{
					emeraldBraceletCritRate = builder
							.comment("Emerald Bracelet: critical hit rate bonus")
							.defineInRange("emeraldBraceletCritRate", 0.1, 0, 1);

					emeraldBraceletLuckReq = builder
							.comment("Emerald Bracelet: luck required to gain the effect")
							.defineInRange("emeraldBraceletLuckReq", 2, 0, 100);

					emeraldBraceletEffChance = builder
							.comment("Emerald Bracelet: absorption effect chance")
							.defineInRange("emeraldBraceletEffChance", 0.1, 0, 1);
				}

				lifeBraceletRegenBonus = builder
						.comment("Life Bracelet: regeneration rate bonus")
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
			public final ModConfigSpec.DoubleValue destroyerBadgeAttack;
			public final ModConfigSpec.DoubleValue destroyerBadgeDamagePenalty;
			public final ModConfigSpec.DoubleValue destroyerBadgeThreshold;
			public final ModConfigSpec.DoubleValue destroyerBadgeHurtBonus;

			// holy sword
			public final ModConfigSpec.DoubleValue holySwordCritRate;
			public final ModConfigSpec.DoubleValue holySwordReflect;
			public final ModConfigSpec.DoubleValue holySwordLostLifeAddDamage;

			//holy talisman
			public final ModConfigSpec.IntValue holyTalismanWeakenInterval;
			public final ModConfigSpec.DoubleValue holyTalismanProtection;
			public final ModConfigSpec.DoubleValue holyTalismanProtectionUndead;
			public final ModConfigSpec.IntValue holyTalismanEffectDuration;
			public final ModConfigSpec.IntValue holyTalismanCooldown;

			//AbyssWillBadge
			public final ModConfigSpec.IntValue abyssWillBadgeInitialLevel;
			public final ModConfigSpec.IntValue abyssWillBadgeDuration;
			public final ModConfigSpec.DoubleValue abyssWillBadgeRemainingHealth;
			public final ModConfigSpec.DoubleValue abyssWillBadgeChanceSmall;
			public final ModConfigSpec.DoubleValue abyssWillBadgeChanceLarge;
			public final ModConfigSpec.DoubleValue abyssWillBadgeBonusSmall;
			public final ModConfigSpec.DoubleValue abyssWillBadgeBonusLarge;
			public final ModConfigSpec.DoubleValue abyssWillBadgePenaltySmall;
			public final ModConfigSpec.DoubleValue abyssWillBadgePenaltyLarge;
			public final ModConfigSpec.IntValue abyssWillBadgeGrowInterval;
			public final ModConfigSpec.IntValue abyssWillBadgeLimit;
			public final ModConfigSpec.DoubleValue abyssWillBadgeDamageBonus;
			public final ModConfigSpec.DoubleValue abyssWillBadgeDamagePenalty;

			// angel heart
			public final ModConfigSpec.DoubleValue angelHeartThreshold;
			public final ModConfigSpec.DoubleValue angelHeartProtection;
			public final ModConfigSpec.IntValue angelHeartRemoveInterval;
			public final ModConfigSpec.IntValue angelHeartBloodInterval;
			public final ModConfigSpec.IntValue angelHeartHealAmount;

			// AngelPearl
			public final ModConfigSpec.DoubleValue angelPearlRegen;
			public final ModConfigSpec.IntValue angelPearlArmor;

			//corrupt badge
			public final ModConfigSpec.IntValue corruptBadgeDebuffDuration;
			public final ModConfigSpec.DoubleValue corruptBadgeDigSpeedBonus;
			public final ModConfigSpec.DoubleValue corruptBadgeAttackSpeedBonus;
			public final ModConfigSpec.DoubleValue corruptBadgeAttackBonus;
			public final ModConfigSpec.IntValue corruptBadgeCooldown;

			// cursed protector
			public final ModConfigSpec.DoubleValue cursedProtectorThreshold;
			public final ModConfigSpec.DoubleValue cursedProtectorReduction;
			public final ModConfigSpec.DoubleValue cursedProtectorFrontProtect;

			// cursed talisman
			public final ModConfigSpec.DoubleValue cursedTalismanCritRateAdd;
			public final ModConfigSpec.DoubleValue cursedTalismanCritDamageAdd;

			//cursed totem
			public final ModConfigSpec.IntValue cursedTotemMaxLevel;
			public final ModConfigSpec.IntValue cursedTotemConsumption;
			public final ModConfigSpec.IntValue cursedTotemEffectDuration;
			public final ModConfigSpec.IntValue cursedTotemEffectLevel;

			//demon curse
			public final ModConfigSpec.DoubleValue demonCurseAttackBonus;
			public final ModConfigSpec.DoubleValue demonCurseSpeedBonus;

			//gluttony badge
			public final ModConfigSpec.IntValue gluttonyBadgeHungerLevel;
			public final ModConfigSpec.DoubleValue gluttonyBadgeProtection;
			public final ModConfigSpec.IntValue gluttonyBadgeEffectDuration;

			//knight shelter
			public final ModConfigSpec.IntValue knightShelterArmor;
			public final ModConfigSpec.IntValue knightShelterDamageReduction;
			public final ModConfigSpec.IntValue knightShelterHealInterval;
			public final ModConfigSpec.DoubleValue knightShelterReflection;

			// magic horseshoe
			public final ModConfigSpec.DoubleValue magicHorseshoeSpeedBonus;
			public final ModConfigSpec.DoubleValue magicHorseshoeLuck;
			public final ModConfigSpec.DoubleValue magicHorseshoeFallReduction;

			//sacrificial object
			public final ModConfigSpec.DoubleValue sacrificialObjectReduction;
			public final ModConfigSpec.DoubleValue sacrificialObjectHeritage;
			public final ModConfigSpec.DoubleValue sacrificialObjectSacrifice;

			//soul box
			public final ModConfigSpec.DoubleValue soulBoxShatterChance;
			public final ModConfigSpec.DoubleValue soulBoxReflect;
			public final ModConfigSpec.IntValue soulBoxCooldown;
			public final ModConfigSpec.IntValue soulBoxShatterHighDuration;
			public final ModConfigSpec.IntValue soulBoxShatterHighLevel;
			public final ModConfigSpec.IntValue soulBoxShatterLowDuration;
			public final ModConfigSpec.IntValue soulBoxShatterLowLevel;

			// bearing stamen
			public final ModConfigSpec.DoubleValue bearingStamenMaxHealth;
			public final ModConfigSpec.DoubleValue bearingStamenRegen;
			public final ModConfigSpec.IntValue bearingStamenLevel;

			// sands talisman
			public final ModConfigSpec.DoubleValue sandsTalismanDamageBonus;
			public final ModConfigSpec.DoubleValue sandsTalismanExpBonus;

			//twisted brain
			public final ModConfigSpec.DoubleValue twistedbrainAvoidChance;
			public final ModConfigSpec.IntValue twistedBrainEffectDuration;

			//undead charm
			public final ModConfigSpec.IntValue undeadCharmCooldown;

			//wardead badge
			public final ModConfigSpec.DoubleValue warDeadBadgeHeal;
			public final ModConfigSpec.DoubleValue warDeadBadgeAtk;
			public final ModConfigSpec.DoubleValue warDeadBadgeArmor;
			public final ModConfigSpec.DoubleValue warDeadBadgeSpeed;
			public final ModConfigSpec.DoubleValue warDeadBadgeThreshold;

			// solar magnet
			public final ModConfigSpec.DoubleValue solarMagnetDamageBonus;

			// deers mercy amulet
			public final ModConfigSpec.IntValue deersMercyAmuletDamage;
			public final ModConfigSpec.IntValue deersMercyAmuletMaxHealth;

			public final ModConfigSpec.DoubleValue deerInscribedAmuletMaxHealth;
			public final ModConfigSpec.DoubleValue deerInscribedAmuletArmor;
			public final ModConfigSpec.DoubleValue deerInscribedAmuletToughness;
			public final ModConfigSpec.DoubleValue deerInscribedAmuletDamage;
			public final ModConfigSpec.DoubleValue deerInscribedAmuletBlessBonus;
			public final ModConfigSpec.IntValue deerInscribedAmuletStrengthInterval;


			private Charm(Builder builder) {
				builder.push("charm", "Charm");

				// destroyer badge
				{
					destroyerBadgeAttack = builder
							.comment("Destroyer Badge: attack bonus")
							.defineInRange("destroyerBadgeAttack", 0.4, 0, 100);
					destroyerBadgeDamagePenalty = builder
							.comment("Destroyer Badge: damage penalty")
							.defineInRange("destroyerBadgeDamagePenalty", 0.5, 0, 100);
					destroyerBadgeThreshold = builder
							.comment("Destroyer Badge: health threshold to trigger the damage bonus")
							.defineInRange("destroyerBadgeThreshold", 0.5, 0, 100);
					destroyerBadgeHurtBonus = builder
							.comment("Destroyer Badge: damage bonus while below the threshold")
							.defineInRange("destroyerBadgeHurtBonus", 0.2, 0, 100);
				}

				// holy_sword
				{
					holySwordCritRate = builder
							.comment("Holy Sword: critical hit rate bonus")
							.defineInRange("holySwordCritRate", 0.15, 0, 1);
					holySwordLostLifeAddDamage = builder
							.comment("Holy Sword: attack bonus per point of health lost")
							.defineInRange("holySwordLostLifeAddDamage", 0.02, 0, 100);
					holySwordReflect = builder
							.comment("Holy Sword: damage reflection rate")
							.defineInRange("holySwordReflect", 0.12, 0, 10);
				}

				//holy talisman
				{
					holyTalismanWeakenInterval = builder
							.comment("Holy Talisman: weakness effect interval in seconds")
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
							.comment("Holy Talisman: totem cooldown")
							.defineInRange("holyTalismanCooldown", 60, 1, Integer.MAX_VALUE);
				}


				//AbyssWillBadge
				{
					abyssWillBadgeInitialLevel = builder
							.comment("Abyss Will Badge: stacks granted when the skill is triggered")
							.defineInRange("abyssWillBadgeInitialLevel", 15, 0, Integer.MAX_VALUE);
					abyssWillBadgeDuration = builder
							.comment("Abyss Will Badge: skill duration in seconds")
							.defineInRange("abyssWillBadgeDuration", 15, 0, Integer.MAX_VALUE);
					abyssWillBadgeRemainingHealth = builder
							.comment("Abyss Will Badge: remaining HP as a percentage after the skill ends")
							.defineInRange("abyssWillBadgeRemainingHealth", 0.2, 0, 1.00);
					abyssWillBadgeChanceSmall = builder
							.comment("Abyss Will Badge: chance of the small damage bonus")
							.defineInRange("abyssWillBadgeChanceSmall", 0.4, 0.0, 1.0);
					abyssWillBadgeChanceLarge = builder
							.comment("Abyss Will Badge: chance of the large damage bonus")
							.defineInRange("abyssWillBadgeChanceLarge", 0.1, 0.0, 1.0);
					abyssWillBadgeBonusSmall = builder
							.comment("Abyss Will Badge: small damage bonus")
							.defineInRange("abyssWillBadgeBonusSmall", 1.5, 0.0, 10);
					abyssWillBadgeBonusLarge = builder
							.comment("Abyss Will Badge: large damage bonus")
							.defineInRange("abyssWillBadgeBonusLarge", 2.0, 0.0, 50);
					abyssWillBadgePenaltySmall = builder
							.comment("Abyss Will Badge: small damage-taken penalty")
							.defineInRange("abyssWillBadgePenaltySmall", 2.0, 0.0, 10);
					abyssWillBadgePenaltyLarge = builder
							.comment("Abyss Will Badge: large damage-taken penalty")
							.defineInRange("abyssWillBadgePenaltyLarge", 2.5, 0.0, 50);
					abyssWillBadgeGrowInterval = builder
							.comment("Abyss Will Badge: interval in seconds to gain a stack")
							.defineInRange("abyssWillBadgeGrowInterval", 20, 1, Integer.MAX_VALUE);
					abyssWillBadgeLimit = builder
							.comment("Abyss Will Badge: maximum stacks from passive growth")
							.defineInRange("abyssWillBadgeLimit", 10, 1, Integer.MAX_VALUE);
					abyssWillBadgeDamageBonus = builder
							.comment("Abyss Will Badge: damage bonus per stack")
							.defineInRange("abyssWillBadgeDamageBonus", 0.20, 0, 1.00);
					abyssWillBadgeDamagePenalty = builder
							.comment("Abyss Will Badge: damage-taken penalty per stack")
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
							.comment("Angel Heart: health restored each time")
							.defineInRange("angelHeartHealAmount", 1, 1, Integer.MAX_VALUE);
				}

				// angel_pearl
				{
					angelPearlRegen = builder
							.comment("Angel Pearl: regeneration rate per beneficial effect")
							.defineInRange("angelPearlRegen", 0.08, 0, 1.00);
					angelPearlArmor = builder
							.comment("Angel Pearl: armor bonus per beneficial effect")
							.defineInRange("angelPearlArmor", 1, 0, Integer.MAX_VALUE);
				}

				//CorruptBadge
				{
					corruptBadgeDebuffDuration = builder
							.comment("Corrupt Badge: negative effect duration in seconds")
							.defineInRange("corruptBadgeDebuffDuration", 15, 1, Integer.MAX_VALUE);
					corruptBadgeDigSpeedBonus = builder
							.comment("Corrupt Badge: mining speed bonus per negative effect")
							.defineInRange("corruptBadgeDigSpeedBonus", 0.09, 0.00, Integer.MAX_VALUE);
					corruptBadgeAttackSpeedBonus = builder
							.comment("Corrupt Badge: attack speed bonus per negative effect")
							.defineInRange("corruptBadgeAttackSpeedBonus", 0.03, 0.00, Integer.MAX_VALUE);
					corruptBadgeAttackBonus = builder
							.comment("Corrupt Badge: attack bonus per negative effect")
							.defineInRange("corruptBadgeAttackBonus", 0.14, 0.00, Integer.MAX_VALUE);
					corruptBadgeCooldown = builder
							.comment("Corrupt Badge: skill cooldown in seconds")
							.defineInRange("corruptBadgeCooldown", 60, 1, Integer.MAX_VALUE);
				}

				// cursed_protector
				{
					cursedProtectorFrontProtect = builder
							.comment("Cursed Protector: damage reduction from frontal attacks")
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
							.comment("Cursed Talisman: critical hit rate gained per enchantment")
							.defineInRange("cursedTalismanCritRateAdd", 0.04, 0, 1);

					cursedTalismanCritDamageAdd = builder
							.comment("Cursed Talisman: critical damage gained per enchantment")
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
							.comment("Cursed Totem: wither effect duration in seconds")
							.defineInRange("cursedTotemEffectDuration", 30, 0, Integer.MAX_VALUE);
					cursedTotemEffectLevel = builder
							.comment("Cursed Totem: level of wither effect (0 means Lv.I)")
							.defineInRange("cursedTotemEffectLevel", 2, 0, 5);
				}

				//demon curse
				{
					demonCurseAttackBonus = builder
							.comment("Demon Curse: attack bonus per 1% extra regeneration")
							.defineInRange("demonCurseAttackBonus", 0.02, 0.0, 10.0);
					demonCurseSpeedBonus = builder
							.comment("Demon Curse: speed bonus per 1% extra regeneration")
							.defineInRange("demonCurseSpeedBonus", 0.01, 0.0, 10.0);
				}

				//gluttony badge
				{
					gluttonyBadgeHungerLevel = builder
							.comment("Gluttony Badge: hunger effect level")
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
							.comment("Knight Shelter: healing interval in seconds for an offhand shield; a mainhand shield takes half the time")
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
							.comment("Magic Horseshoe: luck bonus")
							.defineInRange("magicHorseshoeLuck", 1.0, 0, 10);

					magicHorseshoeFallReduction = builder
							.comment("Magic Horseshoe: fall damage reduction")
							.defineInRange("magicHorseshoeFallReduction", 0.95, 0, 10);

				}

				//sacrificial object
				{
					sacrificialObjectReduction = builder
							.comment("Sacrificial Object: damage reduction")
							.defineInRange("sacrificialObjectReduction", 0.05, 0, 1);
					sacrificialObjectHeritage = builder
							.comment("Sacrificial Object: gold ingot drop chance on death")
							.defineInRange("sacrificialObjectHeritage", 0.5, 0, 1);
					sacrificialObjectSacrifice = builder
							.comment("Sacrificial Object: death sacrifice chance")
							.defineInRange("sacrificialObjectSacrifice", 0.45, 0, 1);
				}

				//soul box
				{
					soulBoxShatterChance = builder
							.comment("Soul Box: chance to inflict the Shatter effect")
							.defineInRange("soulBoxShatterChance", 0.30, 0, 1);
					soulBoxReflect = builder
							.comment("Soul Box: abyss damage as a percentage of the original damage")
							.defineInRange("soulBoxReflect", 2.50, 0, 100);
					soulBoxCooldown = builder
							.comment("Soul Box: cooldown in seconds")
							.defineInRange("soulBoxCooldown", 240, 1, Integer.MAX_VALUE);
					soulBoxShatterHighDuration = builder
							.comment("Soul Box: effect duration inflicted on a fatal hit, in seconds")
							.defineInRange("soulBoxShatterHighDuration", 60, 1, Integer.MAX_VALUE);
					soulBoxShatterHighLevel = builder
							.comment("Soul Box: effect level inflicted on a fatal hit (0 means Lv.I)")
							.defineInRange("soulBoxShatterHighLevel", 2, 0, 100);
					soulBoxShatterLowDuration = builder
							.comment("Soul Box: effect duration inflicted on a non-fatal hit, in seconds")
							.defineInRange("soulBoxShatterLowDuration", 5, 1, Integer.MAX_VALUE);
					soulBoxShatterLowLevel = builder
							.comment("Soul Box: effect level inflicted on a non-fatal hit (0 means Lv.I)")
							.defineInRange("soulBoxShatterLowLevel", 0, 0, 100);
				}

				// bearing stamen
				{
					bearingStamenMaxHealth = builder
							.comment("Bearing Stamen: max health bonus")
							.defineInRange("bearingStamenMaxHealth", 20d, 0, 1000);
					bearingStamenRegen = builder
							.comment("Bearing Stamen: regeneration bonus")
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
							.comment("Sands Talisman: XP bonus")
							.defineInRange("sandsTalismanExpBonus", 0.5, 0, 10);
				}

				//twisted brain
				{
					twistedbrainAvoidChance = builder
							.comment("Twisted Brain: chance to avoid damage")
							.defineInRange("twistedbrainAvoidChance", 0.17, 0, 1);
					twistedBrainEffectDuration = builder
							.comment("Twisted Brain: damage bonus effect duration")
							.defineInRange("twistedBrainEffectDuration", 5, 0, 100);
				}

				//undead charm
				{
					undeadCharmCooldown = builder
							.comment("Undead Charm: cooldown in seconds")
							.defineInRange("undeadCharmCooldown", 180, 1, Integer.MAX_VALUE);
				}

				//wardead badge
				{
					warDeadBadgeHeal = builder
							.comment("War Dead Badge: healing as a percentage of lost health per nearby entity")
							.defineInRange("warDeadBadgeHeal", 0.01, 0, 1);
					warDeadBadgeAtk = builder
							.comment("War Dead Badge: attack bonus per 1% lost health")
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
							.comment("Solar Magnet: damage bonus during the day")
							.defineInRange("solarMagnetDamageBonus", 0.25, 0, 10);
				}

				// deers mercy amulet
				{
					deersMercyAmuletDamage = builder
							.comment("Deer's Mercy Amulet: attack damage bonus")
							.defineInRange("deersMercyAmuletDamage", 2, 1, 100);
					deersMercyAmuletMaxHealth = builder
							.comment("Deer's Mercy Amulet: max health bonus")
							.defineInRange("deersMercyAmuletMaxHealth", 10, 1, 100);
				}

				// deer_inscribed_amulet
				{
					deerInscribedAmuletMaxHealth = builder
							.comment("Deer Inscribed Amulet: max health bonus")
							.defineInRange("deerInscribedAmuletMaxHealth", 0.15, 0.01, 10);
					deerInscribedAmuletArmor = builder
							.comment("Deer Inscribed Amulet: armor bonus")
							.defineInRange("deerInscribedAmuletArmor", 5.0, 1, 100);
					deerInscribedAmuletToughness = builder
							.comment("Deer Inscribed Amulet: armor toughness bonus")
							.defineInRange("deerInscribedAmuletToughness", 2.0, 1, 100);
					deerInscribedAmuletDamage = builder
							.comment("Deer Inscribed Amulet: attack damage bonus")
							.defineInRange("deerInscribedAmuletDamage", 5.0, 1, 100);
					deerInscribedAmuletBlessBonus = builder
							.comment("Deer Inscribed Amulet: bonus provided per reversed curse")
							.defineInRange("deerInscribedAmuletBlessBonus", 0.05, 0.01, 10);
					deerInscribedAmuletStrengthInterval = builder
							.comment("Deer Inscribed Amulet: interval in seconds to strengthen the next attack")
							.defineInRange("deerInscribedAmuletStrengthInterval", 10, 1, 100);
				}

				builder.pop();
			}

		}

		public static class Curse {

			public final ModConfigSpec.DoubleValue chaoticExplosionDamage;
			public final ModConfigSpec.DoubleValue chaoticOtherDamage;
			public final ModConfigSpec.DoubleValue chaoticBlessDamageReduction;

			public final ModConfigSpec.IntValue originTriggerDurability;
			public final ModConfigSpec.DoubleValue originCurseDamage;
			public final ModConfigSpec.DoubleValue originBlessDamage;

			public final ModConfigSpec.DoubleValue lifeCurseHealth;
			public final ModConfigSpec.DoubleValue lifeCurseHeal;
			public final ModConfigSpec.DoubleValue lifeBlessHealth;
			public final ModConfigSpec.DoubleValue lifeBlessHeal;

			public final ModConfigSpec.DoubleValue truthCurseMinDamage;
			public final ModConfigSpec.DoubleValue truthBlessMaxDamage;

			public final ModConfigSpec.DoubleValue nihilityCurseDuration;
			public final ModConfigSpec.DoubleValue nihilityBlessReduction;
			public final ModConfigSpec.IntValue nihilityBlessDuration;

			public final ModConfigSpec.DoubleValue endCurseThreshold;
			public final ModConfigSpec.IntValue endCurseDuration;
			public final ModConfigSpec.DoubleValue endBlessHeal;

			public final ModConfigSpec.IntValue etchingSlotSize;
			public final ModConfigSpec.IntValue charmSlotSize;

			private Curse(Builder builder) {
				builder.push("curse", "Curse");

				chaoticExplosionDamage = builder
						.comment("Chaotic Curse: explosion damage increase")
						.defineInRange("chaoticExplosionDamage", 0.75, 0, 100);

				chaoticOtherDamage = builder
						.comment("Chaotic Curse: non-explosion damage increase")
						.defineInRange("chaoticOtherDamage", 0.5, 0, 100);

				chaoticBlessDamageReduction = builder
						.comment("Chaotic Bless: maximum damage reduction at zero health")
						.defineInRange("chaoticBlessDamageReduction", 0.5, 0, 1);

				originTriggerDurability = builder
						.comment("Origin Curse: durability threshold to trigger the curse")
						.defineInRange("originTriggerDurability", 500, 0, 1000000);

				originCurseDamage = builder
						.comment("Origin Curse: player attack damage reduction")
						.defineInRange("originCurseDamage", 0.5, 0, 1);

				originBlessDamage = builder
						.comment("Origin Bless: player attack damage bonus")
						.defineInRange("originBlessDamage", 0.25, 0, 100);

				lifeCurseHealth = builder
						.comment("Life Curse: player max health reduction")
						.defineInRange("lifeCurseHealth", 0.25, 0, 1);

				lifeCurseHeal = builder
						.comment("Life Curse: player healing reduction")
						.defineInRange("lifeCurseHeal", 0.5, 0, 1);

				lifeBlessHealth = builder
						.comment("Life Bless: player max health bonus")
						.defineInRange("lifeBlessHealth", 0.2, 0, 100);

				lifeBlessHeal = builder
						.comment("Life Bless: player healing bonus")
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
						.comment("End Bless: healing as a percentage of health lost")
						.defineInRange("endBlessHeal", 0.2, 0, 1);

				etchingSlotSize = builder
						.comment("Catastrophe Scroll: number of etching slots")
						.defineInRange("etchingSlotSize", 7, 0, 7);

				charmSlotSize = builder
						.comment("Catastrophe Scroll: number of charm slots")
						.defineInRange("charmSlotSize", 3, 0, 10);

				builder.pop();
			}

		}

		public static class Head {

			public final ModConfigSpec.IntValue abyssCoreCooldown;
			public final ModConfigSpec.IntValue abyssCoreReduce;
			public final ModConfigSpec.IntValue abyssCoreDamageJudgement;
			public final ModConfigSpec.DoubleValue guardianEyeSwimSpeedBonus;
			public final ModConfigSpec.DoubleValue guardianEyeProtection;
			public final ModConfigSpec.DoubleValue prayerCrownHealAmount;
			public final ModConfigSpec.DoubleValue prayerCrownHealChance;
			public final ModConfigSpec.DoubleValue prayerCrownProtection;
			public final ModConfigSpec.DoubleValue spiritCrownArrowDamageMultiplier;
			public final ModConfigSpec.DoubleValue spiritCrownDistanceDamage;
			public final ModConfigSpec.IntValue spiritCrownMaxEntityCount;
			public final ModConfigSpec.IntValue seaGodCrownCoolDown;
			public final ModConfigSpec.IntValue sakuraHairpinMaxHealthAdd;
			public final ModConfigSpec.DoubleValue sakuraHairpinArmorBonus;
			public final ModConfigSpec.DoubleValue sakuraHairpinCritBonusFromLuck;
			public final ModConfigSpec.DoubleValue angelDesireDamageBonus;

			private Head(Builder builder) {
				builder.push("head", "Head");
				abyssCoreCooldown = builder
						.comment("Abyss Core: effect cooldown")
						.defineInRange("abyssCoreCooldown", 60, 0, 100000);
				abyssCoreDamageJudgement = builder
						.comment("Abyss Core: damage judgment")
						.defineInRange("abyssCoreDamageJudgement", 12, 1, 100000);
				abyssCoreReduce = builder
						.comment("Abyss Core: damage reduction")
						.defineInRange("abyssCoreReduce", 12, 0, 100000);
				guardianEyeSwimSpeedBonus = builder
						.comment("Guardian Eye: swim speed bonus")
						.defineInRange("guardianEyeSwimSpeedBonus", 0.15, 0, 100);
				guardianEyeProtection = builder
						.comment("Guardian Eye: protection")
						.defineInRange("guardianEyeProtection", 0.2, 0, 1);
				prayerCrownHealAmount = builder
						.comment("Prayer Crown: healing amount")
						.defineInRange("prayerCrownHealAmount", 0.25, 0, 10);
				prayerCrownHealChance = builder
						.comment("Prayer Crown: healing chance")
						.defineInRange("prayerCrownHealChance", 0.5, 0, 1);
				prayerCrownProtection = builder
						.comment("Prayer Crown: protection")
						.defineInRange("prayerCrownProtection", 0.25, 0, 10);
				spiritCrownMaxEntityCount = builder
						.comment("Spirit Crown: max entities around the player to trigger the damage bonus")
						.defineInRange("spiritCrownMaxEntityCount", 3, 0, 100);
				spiritCrownArrowDamageMultiplier = builder
						.comment("Spirit Crown: arrow damage multiplier")
						.defineInRange("spiritCrownArrowDamageMultiplier", 0.45, 0, 100);
				spiritCrownDistanceDamage = builder
						.comment("Spirit Crown: damage bonus per block of distance")
						.defineInRange("spiritCrownDistanceDamage", 0.02, 0, 1);
				seaGodCrownCoolDown = builder
						.comment("Sea God Crown: skill cooldown")
						.defineInRange("seaGodCrownCoolDown", 30, 0, 1000);
				sakuraHairpinMaxHealthAdd = builder
						.comment("Sakura Hairpin: max health bonus")
						.defineInRange("sakuraHairpinMaxHealthAdd", 10, 0, 500);
				sakuraHairpinArmorBonus = builder
						.comment("Sakura Hairpin: armor bonus")
						.defineInRange("sakuraHairpinArmorBonus", 0.1, 0, 10);
				sakuraHairpinCritBonusFromLuck = builder
						.comment("Sakura Hairpin: critical hit rate bonus per point of luck")
						.defineInRange("sakuraHairpinCritBonusFromLuck", 0.03, 0, 1);
				angelDesireDamageBonus = builder
						.comment("Angel Desire: damage bonus when flying")
						.defineInRange("angelDesireDamageBonus", 0.5, 0, 10);
				builder.pop();
			}

		}

		public static class Heart {

			public final ModConfigSpec.DoubleValue heartOfRevengeBowStrength;
			public final ModConfigSpec.IntValue heartOfRevengeValidTime;
			public final ModConfigSpec.DoubleValue heartOfRevengeDamageBonus;
			public final ModConfigSpec.DoubleValue demonHeartDamageBonus;
			public final ModConfigSpec.DoubleValue demonHeartDamageReduction;
			public final ModConfigSpec.DoubleValue twistedHeartDamage;
			public final ModConfigSpec.DoubleValue twistedHeartToughness;
			public final ModConfigSpec.DoubleValue greedyHeartXpBonus;


			private Heart(Builder builder) {
				builder.push("heart", "Heart");
				heartOfRevengeBowStrength = builder
						.comment("Heart of Revenge: bow strength")
						.defineInRange("heartOfRevengeBowStrength", 0.06, 0, 1);
				heartOfRevengeValidTime = builder
						.comment("Heart of Revenge: revenge effect duration")
						.defineInRange("heartOfRevengeValidTime", 3, 0, 10);
				heartOfRevengeDamageBonus = builder
						.comment("Heart of Revenge: revenge damage bonus")
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
						.comment("Greedy Heart: XP pickup bonus")
						.defineInRange("greedyHeartXpBonus", 2d, 0, 100);

				builder.pop();
			}

		}

		public static class Necklace {

			public final ModConfigSpec.DoubleValue starNecklaceDamageBonus;
			public final ModConfigSpec.IntValue crossNecklaceInvulTick;
			public final ModConfigSpec.DoubleValue gallopNecklaceSpeedBonus;
			public final ModConfigSpec.DoubleValue gallopNecklaceDamageFactor;
			public final ModConfigSpec.DoubleValue fangNecklaceAttack;
			public final ModConfigSpec.DoubleValue fangNecklaceDamageBonus;
			public final ModConfigSpec.DoubleValue fangNecklacePoisonChance;
			public final ModConfigSpec.IntValue fangNecklacePoisonDuration;
			public final ModConfigSpec.IntValue fangNecklacePoisonLevel;
			public final ModConfigSpec.DoubleValue preciousNecklaceCritDmg;
			public final ModConfigSpec.DoubleValue holyNecklaceMaxHealth;
			public final ModConfigSpec.IntValue holyNecklaceInvulTick;
			public final ModConfigSpec.DoubleValue hierloomNecklaceArmor;
			public final ModConfigSpec.DoubleValue hierloomNecklaceSpeed;
			public final ModConfigSpec.DoubleValue hierloomNecklaceExp;
			public final ModConfigSpec.DoubleValue emeraldNecklaceDrop;
			public final ModConfigSpec.DoubleValue enderProtectorChance;
			public final ModConfigSpec.IntValue holyNecklaceCooldown;
			public final ModConfigSpec.IntValue holyNecklaceDuration;
			public final ModConfigSpec.DoubleValue lockOfAbyssExtraDamage;
			public final ModConfigSpec.IntValue lockOfAbyssDuration;
			public final ModConfigSpec.IntValue lockOfAbyssThreshold;
			public final ModConfigSpec.DoubleValue enderProtectorToughness;
			public final ModConfigSpec.DoubleValue redHeartNecklaceMaxHealth;
			public final ModConfigSpec.DoubleValue spiritNecklaceProjectile;
			public final ModConfigSpec.IntValue treasureHunterNecklaceCooldown;
			public final ModConfigSpec.DoubleValue treasureHunterNecklaceChance;

			private Necklace(Builder builder) {
				builder.push("necklace", "Necklace");
				starNecklaceDamageBonus = builder
						.comment("Star Necklace: damage bonus from behind")
						.defineInRange("starNecklaceDamageBonus", 0.4, 0, 10);
				crossNecklaceInvulTick = builder
						.comment("Cross Necklace: invulnerability time bonus in ticks")
						.defineInRange("crossNecklaceInvulTick", 10, 0, 100);
				gallopNecklaceSpeedBonus = builder
						.comment("Gallop Necklace: speed bonus")
						.defineInRange("gallopNecklaceSpeedBonus", 0.08, 0, 10);
				gallopNecklaceDamageFactor = builder
						.comment("Gallop Necklace: damage multiplier based on speed")
						.defineInRange("gallopNecklaceDamageFactor", 1.5, 0, 100000);
				emeraldNecklaceDrop = builder
						.comment("Emerald Necklace: chance to drop an emerald")
						.defineInRange("emeraldNecklaceDrop", 0.02, 0, 1);
				enderProtectorChance = builder
						.comment("Ender Protector: teleport chance")
						.defineInRange("enderProtectorChance", 0.5, 0, 1);
				holyNecklaceCooldown = builder
						.comment("Holy Necklace: cooldown")
						.defineInRange("holyNecklaceCooldown", 3, 0, 60);
				holyNecklaceDuration = builder
						.comment("Holy Necklace: duration in seconds")
						.defineInRange("holyNecklaceDuration", 2, 0, 600);
				lockOfAbyssExtraDamage = builder
						.comment("Lock of Abyss: extra damage multiplier")
						.defineInRange("lockOfAbyssExtraDamage", 2.5, 0, 10);
				lockOfAbyssDuration = builder
						.comment("Lock of Abyss: slowness duration in seconds")
						.defineInRange("lockOfAbyssDuration", 10, 1, 1000);
				lockOfAbyssThreshold = builder
						.comment("Lock of Abyss: slowness level required to take effect")
						.defineInRange("lockOfAbyssThreshold", 7, 1, 10);
				// fang necklace
				{
					fangNecklaceAttack = builder
							.comment("Fang Necklace: attack bonus")
							.defineInRange("fangNecklaceAttack", 0.1, 0, 10);
					fangNecklaceDamageBonus = builder
							.comment("Fang Necklace: damage bonus from behind")
							.defineInRange("fangNecklaceDamageBonus", 0.25, 0, 10);
					fangNecklacePoisonChance = builder
							.comment("Fang Necklace: poison effect chance")
							.defineInRange("fangNecklacePoisonChance", 0.5, 0, 1);
					fangNecklacePoisonDuration = builder
							.comment("Fang Necklace: poison effect duration in seconds")
							.defineInRange("fangNecklacePoisonDuration", 5, 0, 1000);
					fangNecklacePoisonLevel = builder
							.comment("Fang Necklace: poison effect level (0 means Lv.I)")
							.defineInRange("fangNecklacePoisonLevel", 2, 0, 5);

				}
				preciousNecklaceCritDmg = builder
						.comment("Precious Necklace: critical damage bonus")
						.defineInRange("preciousNecklaceCritDmg", 0.2, 0, 10);
				holyNecklaceMaxHealth = builder
						.comment("Holy Necklace: max health bonus")
						.defineInRange("holyNecklaceMaxHealth", 4d, 0, 100);
				holyNecklaceInvulTick = builder
						.comment("Holy Necklace: additional invulnerability ticks")
						.defineInRange("holyNecklaceInvulTick", 5, 0, 100);
				//hierloom necklace
				{

					hierloomNecklaceArmor = builder
							.comment("Heirloom Necklace: armor bonus")
							.defineInRange("hierloomNecklaceArmor", 2d, 0, 100);
					hierloomNecklaceSpeed = builder
							.comment("Heirloom Necklace: movement speed bonus")
							.defineInRange("hierloomNecklaceSpeed", 0.05, 0, 10);
					hierloomNecklaceExp = builder
							.comment("Heirloom Necklace: XP pickup bonus")
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
						.comment("Treasure Hunter Necklace: cooldown")
						.defineInRange("treasureHunterNecklaceCooldown", 6000, 0, 10000000);
				treasureHunterNecklaceChance = builder
						.comment("Treasure Hunter Necklace: chance to obtain loot")
						.defineInRange("treasureHunterNecklaceChance", 0.05, 0, 1);
				builder.pop();
			}

		}

		public static class Pendant {

			public final ModConfigSpec.DoubleValue shadowPendantDamageHeal;
			public final ModConfigSpec.DoubleValue shadowPendantDamageBonus;
			public final ModConfigSpec.DoubleValue shadowPendantDamageReduction;
			public final ModConfigSpec.IntValue shadowPendantLightLevel;
			public final ModConfigSpec.IntValue chaoticPendantEnchantLevel;


			private Pendant(Builder builder) {
				builder.push("pendant", "Pendant");
				shadowPendantDamageHeal = builder
						.comment("Shadow Pendant: healing from damage dealt")
						.defineInRange("shadowPendantDamageHeal", 0.25, 0, 1);
				shadowPendantLightLevel = builder
						.comment("Shadow Pendant: light level threshold for the bonus")
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

			public final ModConfigSpec.DoubleValue amethystRingDamage;
			public final ModConfigSpec.DoubleValue netheriteRingProtection;
			public final ModConfigSpec.IntValue netherFireRingFireTime;
			public final ModConfigSpec.IntValue ringOfLifeEffectInterval;
			public final ModConfigSpec.DoubleValue nostalgicButterflyRingCritRate;
			public final ModConfigSpec.DoubleValue nostalgicButterflyRingCritDmg;

			private Ring(Builder builder) {
				builder.push("ring", "Ring");
				amethystRingDamage = builder.comment("Amethyst Ring: damage bonus")
						.defineInRange("amethystRingDamage", 0.1, 0, 100);
				netheriteRingProtection = builder.comment("Netherite Ring: protection in the Nether")
						.defineInRange("netheriteRingProtection", 0.1, 0, 1);
				netherFireRingFireTime = builder
						.comment("Nether Fire Ring: fire burn duration")
						.defineInRange("netherFireRingFireTime", 5, 0, 1000);
				ringOfLifeEffectInterval = builder
						.comment("Ring of Life: effect interval")
						.defineInRange("ringOfLifeEffectInterval", 5, 1, 100);
				nostalgicButterflyRingCritRate = builder
						.comment("Nostalgic Butterfly Ring: critical hit rate bonus")
						.defineInRange("nostalgicButterflyRingCritRate", 0.2, 0, 100);
				nostalgicButterflyRingCritDmg = builder
						.comment("Nostalgic Butterfly Ring: critical damage reduction")
						.defineInRange("nostalgicButterflyRingCritDmg", 0.2, 0, 100);
				builder.pop();
			}

		}

		public static class Scroll {

			public final ModConfigSpec.IntValue skyWalkerCooldown;
			public final ModConfigSpec.IntValue travelerEffectDuration;
			public final ModConfigSpec.DoubleValue twistedDuplicateChance;
			public final ModConfigSpec.DoubleValue travellerScrollSpeedBonus;


			private Scroll(Builder builder) {
				builder.push("scroll", "Scroll");

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
						.comment("Traveller Scroll: speed bonus")
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

			public final ModConfigSpec.DoubleValue forestCloakDodgeChance;
			public final ModConfigSpec.DoubleValue forestCloakJumpPower;

			private Body(Builder builder) {
				builder.push("body", "Body");

				forestCloakDodgeChance = builder.comment("Forest Cloak: chance to dodge projectiles")
						.defineInRange("forestCloakDodgeChance", 0.2, 0, 1);
				forestCloakJumpPower = builder.comment("Forest Cloak: jump power bonus")
						.defineInRange("forestCloakJumpPower", 0.25, 0, 10);
				builder.pop();
			}

		}

		public static class Set {

			public final ModConfigSpec.DoubleValue seaGodProtect;
			public final ModConfigSpec.DoubleValue seaGodMelee;
			public final ModConfigSpec.DoubleValue seaGodThrow;
			public final ModConfigSpec.IntValue emeraldLuck;
			public final ModConfigSpec.DoubleValue emeraldCrit;
			public final ModConfigSpec.IntValue spiritPullDuration;
			public final ModConfigSpec.DoubleValue spiritBackShootBonus;
			public final ModConfigSpec.DoubleValue spiritInflictChance;
			public final ModConfigSpec.IntValue spiritEffectDuration;
			public final ModConfigSpec.IntValue spiritEffectAmplifier;
			public final ModConfigSpec.DoubleValue spiritProtect;
			public final ModConfigSpec.DoubleValue deerButterFlyDmgMul;

			private Set(Builder builder) {
				builder.push("set", "Set");

				// sea god
				{
					seaGodProtect = builder.comment("Sea God Set: damage reduction against aquatic mobs")
							.defineInRange("seaGodProtect", 0.35, 0, 1);
					seaGodMelee = builder.comment("Sea God Set: trident melee damage bonus")
							.defineInRange("seaGodMelee", 0.5, 0, 10);
					seaGodThrow = builder.comment("Sea God Set: thrown trident damage bonus")
							.defineInRange("seaGodThrow", 0.75, 0, 10);
				}

				// emerald
				{
					emeraldLuck = builder.comment("Emerald Set: luck bonus")
							.defineInRange("emeraldLuck", 2, 0, 10);
					emeraldCrit = builder.comment("Emerald Set: critical hit rate bonus")
							.defineInRange("emeraldCrit", 0.15, 0, 1);
				}

				// spirit
				{
					spiritPullDuration = builder.comment("Spirit Set: bow draw time in seconds required for the bonus")
							.defineInRange("spiritPullDuration", 3, 0, 100);
					spiritBackShootBonus = builder.comment("Spirit Set: damage bonus when shooting a target from behind")
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

				deerButterFlyDmgMul = builder.comment("Deer Butterfly Set: extra damage multiplier")
						.defineInRange("deerButterFlyDmgMul", 0.15, 0, 100);
				builder.pop();
			}

		}

		public static class Misc {

			public final ModConfigSpec.IntValue backtrackMirrorCooldown;
			public final ModConfigSpec.IntValue repentMirrorCooldown;
			public final ModConfigSpec.DoubleValue copperReinforceChance;
			public final ModConfigSpec.IntValue amethystReinforceEffect;

			public final ModConfigSpec.BooleanValue giveItemsOnStart;
			public final ModConfigSpec.BooleanValue catastropheScrollEquipOnStart;
			public final ModConfigSpec.BooleanValue catastropheScrollPreventUnequip;

			private Misc(Builder builder) {
				builder.push("misc", "Misc");

				backtrackMirrorCooldown = builder.defineInRange("backtrackMirrorCooldown",
						10, 0, 10000);
				repentMirrorCooldown = builder.defineInRange("repentMirrorCooldown",
						10, 0, 10000);
				copperReinforceChance = builder.defineInRange("copperReinforceChance",
						0.5, 0, 1);
				amethystReinforceEffect = builder.defineInRange("amethystReinforceEffect",
						1, 0, 100);

				giveItemsOnStart = builder
						.comment("If true, Heirloom Necklace and Catastrophe Scroll will be given to new players")
						.define("giveItemsOnStart", true);
				// catastrophe_scroll
				catastropheScrollEquipOnStart = builder
						.comment("If true, Catastrophe Scroll will be equipped to new players automatically")
						.define("catastropheScrollEquipOnStart", false);
				catastropheScrollPreventUnequip = builder
						.comment("If true, Catastrophe Scroll cannot be unequipped once equipped")
						.define("catastropheScrollPreventUnequip", true);

				builder.pop();
			}

		}
	}

	public static class Common extends ConfigWrapper {
		public final Toggles toggles;

        public Common(Builder builder) {
			setCelestial();
            this.toggles = new Toggles(builder);
        }

        public static class Toggles {

			private final LinkedHashMap<String, ModConfigSpec.BooleanValue> itemToggle = new LinkedHashMap<>();

			private Toggles(Builder builder) {
				builder.push("itemToggle", "Item Toggle");
				for (var e : CAItems.ALL_CURIOS) {
					itemToggle.put(e, builder.define(e, true));
				}
				builder.pop();
			}

			public ModConfigSpec.BooleanValue get(String item) {
				var ans = itemToggle.get(item);
				if (ans == null) {
					CelestialArtifacts.LOGGER.error("Item toggle failed to load. Toggle map size: {}", itemToggle.size());
					CelestialArtifacts.LOGGER.error("Iten ID: {}, containing: {}", item, itemToggle.containsKey(item));
					for (var e : itemToggle.entrySet()) {
						CelestialArtifacts.LOGGER.error("{} -> {}", e.getKey(), e.getValue());
					}
					throw new IllegalStateException("Iten ID: " + item + " is not in config");
				}
				return ans;
			}

		}
	}

	public static final Server SERVER = CelestialArtifacts.EXTRA.initConfig(ModConfig.Type.SERVER, Server::new);
	public static final Common COMMON = CelestialArtifacts.EXTRA.initConfig(ModConfig.Type.COMMON, Common::new);

	public static void init() {
	}
}
