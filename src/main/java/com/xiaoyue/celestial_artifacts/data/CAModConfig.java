package com.xiaoyue.celestial_artifacts.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class CAModConfig {


	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public static class Materials {

			public final ForgeConfigSpec.DoubleValue desireEtchingDropChance;
			public final ForgeConfigSpec.IntValue desireEtchingLootingRequirement;
			public final ForgeConfigSpec.DoubleValue endEtchingDropChance;
			public final ForgeConfigSpec.IntValue endEtchingEffectRequirement;
			public final ForgeConfigSpec.DoubleValue lifeEtchingDropChance;
			public final ForgeConfigSpec.IntValue lifeEtchingHealthRequirement;
			public final ForgeConfigSpec.DoubleValue chaoticEtchingDropChance;
			public final ForgeConfigSpec.DoubleValue nihilityEtchingDropChance;

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
				lifeEtchingDropChance = builder.defineInRange("lifeEtchingDropChance", 0.15, 0, 1);
				lifeEtchingHealthRequirement = builder.defineInRange("lifeEtchingHealthRequirement", 500, 1, 1000000);
				chaoticEtchingDropChance = builder.defineInRange("chaoticEtchingDropChance", 0.3, 0, 1);
				nihilityEtchingDropChance = builder.defineInRange("nihilityEtchingDropChance", 0.5, 0, 1);

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

			public ForgeConfigSpec.DoubleValue magicArrowBagBowStrength;
			public ForgeConfigSpec.IntValue magicArrowBagArrowKnock;

			public ForgeConfigSpec.DoubleValue flameArrowBagBowStrength;
			public ForgeConfigSpec.IntValue flameArrowBagArrowKnock;
			public ForgeConfigSpec.IntValue flameArrowBagTime;

			public ForgeConfigSpec.DoubleValue spiritArrowBagBowStrength;
			public ForgeConfigSpec.DoubleValue spiritArrowBagArrowSpeed;
			public ForgeConfigSpec.IntValue spiritArrowBagArrowKnock;

			public ForgeConfigSpec.DoubleValue leechScabbardHealFactor;

			private Back(ForgeConfigSpec.Builder builder) {
				builder.push("back");

				magicArrowBagBowStrength = builder.comment("Magic Arrow Bag: bow strength")
						.defineInRange("magicArrowBagBowStrength", 0.1, 0, 10);
				magicArrowBagArrowKnock = builder.comment("Magic Arrow Bag: arrow knock")
						.defineInRange("magicArrowBagBowStrength", 1, 0, 100);

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


				leechScabbardHealFactor = builder.comment("Leech Scabbard: healing rate as percentage of damage dealt")
						.defineInRange("leechScabbardHealFactor", 0.25, 0, 100);


				builder.pop();
			}

		}

		public static class Bracelet {

			// hidden_bracelet
			public final ForgeConfigSpec.IntValue hiddenBraceletEffectDuration;

			private Bracelet(ForgeConfigSpec.Builder builder) {
				builder.push("bracelet");

				// hidden_bracelet
				hiddenBraceletEffectDuration = builder
						.comment("Hidden Bracelet: Stealth effect interval")
						.defineInRange("hiddenBraceletEffectDuration", 7, 1, Integer.MAX_VALUE);

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
			public final ForgeConfigSpec.DoubleValue holySwordMaxAddDamage;
			public final ForgeConfigSpec.DoubleValue holySwordLostLifeAddDamage;

			//holy talisman
			public final ForgeConfigSpec.IntValue holytaliweak;
			public final ForgeConfigSpec.DoubleValue holytaliprotect;
			public final ForgeConfigSpec.DoubleValue holytalideadprotect;
			public final ForgeConfigSpec.IntValue holytaliimmortal;
			public final ForgeConfigSpec.IntValue holytaliCD;

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

			//demon curse
			public final ForgeConfigSpec.DoubleValue demonCurseAttackBonus;
			public final ForgeConfigSpec.DoubleValue demonCurseSpeedBonus;

			//gluttony badge
			public final ForgeConfigSpec.IntValue gluttonyBadgeHungerLevel;
			public final ForgeConfigSpec.DoubleValue gluttonyBadgeProtection;

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
			public final ForgeConfigSpec.IntValue sacriloot;
			public final ForgeConfigSpec.DoubleValue sacriReduction;
			public final ForgeConfigSpec.IntValue sacriheritage;
			public final ForgeConfigSpec.DoubleValue sacriselfexplode;

			//soul box
			public final ForgeConfigSpec.DoubleValue soulboxbreak;
			public final ForgeConfigSpec.DoubleValue soulboxwitherblast;
			public final ForgeConfigSpec.IntValue soulboximmortalCD;

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
					holySwordMaxAddDamage = builder
							.comment("Holy Sword: maximum damage increase")
							.defineInRange("holySwordMaxAddDamage", 0.5, 0, 10000);
					holySwordLostLifeAddDamage = builder
							.comment("Holy Sword: how much life you lose and how much damage you inflict once")
							.defineInRange("holySwordLostLifeAddDamage", 1, 1.0, 10000);
				}

				//holy talisman TODO
				{
					holytaliweak = builder
							.comment("weak stance")
							.defineInRange("holytaliweak", 10, 1, Integer.MAX_VALUE);
					holytaliprotect = builder
							.comment("reduce damage under attack")
							.defineInRange("holytaliprotect", 0.25, 0, 1.00);
					holytalideadprotect = builder
							.comment("reduce damage under dead attack")
							.defineInRange("holytalizomprotect", 0.35, 0, 1.00);
					holytaliimmortal = builder
							.comment("reserve some health")
							.defineInRange("holytaliimmortal", 1, 1, Integer.MAX_VALUE);
					holytaliCD = builder
							.comment("skill cooling down")
							.defineInRange("holytaliCD", 600, 1, Integer.MAX_VALUE);
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
							.defineInRange("abyssWillBadgeRemainingHealth", 0.2, 0.01, 1.00);
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
							.defineInRange("abyssWillBadgeDamageBonus", 0.20, 0.01, 1.00);
					abyssWillBadgeDamagePenalty = builder
							.comment("AbyssWillBadge: damage penalty per level")
							.defineInRange("abyssWillBadgeDamagePenalty", 0.25, 0.01, 1.00);

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
							.defineInRange("angelPearlRegen", 0.08, 0.01, 1.00);
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
							.defineInRange("cursedProtectorThreshold", 0.35, 0.01, 1);

					cursedProtectorReduction = builder
							.comment("Cursed Protector: damage reduction percentage")
							.defineInRange("cursedProtectorReduction", 0.25, 0.01, 1);
				}

				// cursed_talisman
				{
					cursedTalismanCritRateAdd = builder
							.comment("Cursed Talisman: critical hit rate increased by each enchantment")
							.defineInRange("cursedTalismanCritRateAdd", 0.04, 0.01, 1);

					cursedTalismanCritDamageAdd = builder
							.comment("Cursed Talisman: critical hit damage increased by each enchantment")
							.defineInRange("cursedTalismanCritDamageAdd", 0.08, 0.01, 1);
				}

				//cursed_totem
				{
					cursedTotemMaxLevel = builder
							.comment("Cursed Totem: maximum level")
							.defineInRange("cursedTotemMaxLevel", 5, 0, Integer.MAX_VALUE);
					cursedTotemConsumption = builder
							.comment("Cursed Totem: levels consumed when negating fatal damage")
							.defineInRange("cursedTotemConsumption", 5, 0, Integer.MAX_VALUE);
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
							.defineInRange("knightShelterReflection", 0.30, 0.00, 1);
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

				//sacrificial object TODO
				{
					sacriloot = builder
							.comment("sacrificial: loot level improve")
							.defineInRange("sacriloot", 1, 0, Integer.MAX_VALUE);
					sacriReduction = builder
							.comment("sacrificial: damage reduction percentage")
							.defineInRange("sacriReduction", 0.05, 0.01, 1);
					sacriheritage = builder
							.comment("sacrificial:deadth drop gold ingots")
							.defineInRange("sacriheritage", 1, 0, Integer.MAX_VALUE);
					sacriselfexplode = builder
							.comment("sacrificial:death explode percentage")
							.defineInRange("sacriselfexplode", 0.45, 0.01, 1);
				}

				//soul box TODO
				{
					soulboxbreak = builder
							.comment("soulboxbreak:break the soul of attacker")
							.defineInRange("soulboxbreak", 0.30, 0.01, 1);
					soulboxwitherblast = builder
							.comment("soulboxwitherblast:strike back with wither blast")
							.defineInRange("soulboxwitherblast", 2.50, 0.01, 100);
					soulboximmortalCD = builder
							.comment("soulboximmortalCD:can die within a period of time")
							.defineInRange("soulboximmortalCD", 240, 1, Integer.MAX_VALUE);
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
							.defineInRange("warDeadBadgeHeal", 0.01, 0.01, 1);
					warDeadBadgeAtk = builder
							.comment("War Dead Badge: damage bonus per 1% lost health")
							.defineInRange("warDeadBadgeAtk", 0.02, 0.01, 1);
					warDeadBadgeArmor = builder
							.comment("War Dead Badge: armor bonus per 1% lost health")
							.defineInRange("warDeadBadgeArmor", 0.02, 0.01, 1);
					warDeadBadgeSpeed = builder
							.comment("War Dead Badge: speed bonus per 1% lost health")
							.defineInRange("warDeadBadgeSpeed", 0.01, 0.01, 1);
					warDeadBadgeThreshold = builder
							.comment("War Dead Badge: health threshold to trigger healing")
							.defineInRange("warDeadBadgeThreshold", 0.5, 0.01, 1);
				}

				builder.pop();
			}

		}

		public static class Curse {

			// catastrophe_scroll
			public final ForgeConfigSpec.BooleanValue catastropheScrollStart;

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

			public final ForgeConfigSpec.DoubleValue truthCurseThreshold;
			public final ForgeConfigSpec.DoubleValue truthBlessThreshold;

			public final ForgeConfigSpec.DoubleValue nihilityCurseDuration;
			public final ForgeConfigSpec.DoubleValue nihilityBlessReduction;
			public final ForgeConfigSpec.IntValue nihilityBlessDuration;

			public final ForgeConfigSpec.DoubleValue endCurseThreshold;
			public final ForgeConfigSpec.IntValue endCurseDuration;
			public final ForgeConfigSpec.DoubleValue endBlessHeal;

			private Curse(ForgeConfigSpec.Builder builder) {
				builder.push("curse");

				// catastrophe_scroll
				catastropheScrollStart = builder
						.comment("When true, when the player enters the world, the accessory will be directly worn in the accessory bar")
						.define("catastropheScrollStart", false);

				chaoticExplosionDamage = builder
						.comment("Chaotic Curse: Damage increment percentage for explosion")
						.defineInRange("chaoticExplosionDamage", 0.75, 0.01, 100);

				chaoticOtherDamage = builder
						.comment("Chaotic Curse: Damage increment percentage for non-explosion")
						.defineInRange("chaoticOtherDamage", 0.5, 0.01, 100);

				chaoticBlessDamageReduction = builder
						.comment("Chaotic Bless: Max damage reduction at zero health")
						.defineInRange("chaoticBlessDamageReduction", 0.5, 0.01, 1);

				originTriggerDurability = builder
						.comment("Origin Curse: Durability threshold to trigger curse ")
						.defineInRange("originTriggerDurability", 500, 0, 1000000);

				originCurseDamage = builder
						.comment("Origin Curse: Player attack damage reduction")
						.defineInRange("originCurseDamage", 0.5, 0.01, 1);

				originBlessDamage = builder
						.comment("Origin Bless: Player attack damage bonus")
						.defineInRange("originBlessDamage", 0.25, 0.01, 100);

				lifeCurseHealth = builder
						.comment("Life Curse: Player max health reduction")
						.defineInRange("lifeCurseHealth", 0.25, 0.01, 1);

				lifeCurseHeal = builder
						.comment("Life Curse: Player healing reduction")
						.defineInRange("lifeCurseHeal", 0.5, 0.01, 1);

				lifeBlessHealth = builder
						.comment("Life Bless: Player max health bonus")
						.defineInRange("lifeBlessHealth", 0.2, 0.01, 100);

				lifeBlessHeal = builder
						.comment("Life Bless: Player healing bonus")
						.defineInRange("lifeBlessHeal", 0.3, 0.01, 100);

				truthCurseThreshold = builder
						.comment("Truth Curse: minimum damage from mobs")
						.defineInRange("truthCurseThreshold", 0.4, 0.01, 1);

				truthBlessThreshold = builder
						.comment("Truth Bless: maximum damage from mobs")
						.defineInRange("truthBlessThreshold", 0.6, 0.01, 1);

				nihilityCurseDuration = builder
						.comment("Nihility Curse: negative effect duration")
						.defineInRange("nihilityCurseDuration", 1, 0.01, 100);

				nihilityBlessReduction = builder
						.comment("Nihility Bless: void damage reduction")
						.defineInRange("nihilityBlessReduction", 0.75, 0.01, 1);

				nihilityBlessDuration = builder
						.comment("Nihility Bless: effect duration inflicted")
						.defineInRange("nihilityBlessDuration", 10, 1, 1000);

				endCurseThreshold = builder
						.comment("End Curse: damage threshold for negative effects")
						.defineInRange("endCurseThreshold", 0.1, 0.01, 1);

				endCurseDuration = builder
						.comment("End Curse: negative effect duration")
						.defineInRange("endCurseDuration", 30, 1, 1000);

				endBlessHeal = builder
						.comment("End Bless: healing as percentage of health lost")
						.defineInRange("endBlessHeal", 0.2, 0.01, 1);

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
						.defineInRange("prayerCrownHealAmount", 0.25, 0.01, 10);
				prayerCrownHealChance = builder
						.comment("Prayer Crown: heal chance")
						.defineInRange("prayerCrownHealChance", 0.5, 0.01, 1);
				prayerCrownProtection = builder
						.comment("Prayer Crown: protection")
						.defineInRange("prayerCrownProtection", 0.25, 0.01, 10);
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
						.defineInRange("twistedHeartDamage", 0.33, 0.01, 1);
				twistedHeartToughness = builder
						.comment("Twisted Heart: toughness")
						.defineInRange("twistedHeartToughness", 0.2, 0.01, 1);


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
						.defineInRange("lockOfAbyssExtraDamage", 2.5, 0.01, 10);
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
				builder.pop();
			}

		}

		public static class Pendant {

			public final ForgeConfigSpec.DoubleValue shadowPendantDamageHeal;
			public final ForgeConfigSpec.DoubleValue shadowPendantDamageBonus;
			public final ForgeConfigSpec.DoubleValue shadowPendantDamageReduction;
			public final ForgeConfigSpec.IntValue shadowPendantLightLevel;

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

			private Scroll(ForgeConfigSpec.Builder builder) {
				builder.push("scroll");

				skyWalkerCooldown = builder
						.comment("Sky Walker Scroll: teleport cooldown")
						.defineInRange("skyWalkerCooldown", 60, 0, 600);

				builder.pop();
			}

		}

		public static class Set {

			private Set(ForgeConfigSpec.Builder builder) {
				builder.push("set");


				builder.pop();
			}

		}

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
		public final Set set;

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
			set = new Set(builder);
		}

	}


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

}
