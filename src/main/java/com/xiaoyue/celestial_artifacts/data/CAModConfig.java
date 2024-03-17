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
			public ForgeConfigSpec.DoubleValue magicArrowBagArrowKnock;

			public ForgeConfigSpec.DoubleValue flameArrowBagBowStrength;
			public ForgeConfigSpec.DoubleValue flameArrowBagArrowKnock;
			public ForgeConfigSpec.IntValue flameArrowBagTime;

			public ForgeConfigSpec.DoubleValue spiritArrowBagBowStrength;
			public ForgeConfigSpec.DoubleValue spiritArrowBagArrowSpeed;
			public ForgeConfigSpec.DoubleValue spiritArrowBagArrowKnock;

			public ForgeConfigSpec.DoubleValue leechScabbardHealFactor;

			private Back(ForgeConfigSpec.Builder builder) {
				builder.push("back");

				magicArrowBagBowStrength = builder.comment("Magic Arrow Bag: bow strength")
						.defineInRange("magicArrowBagBowStrength", 0.1, 0, 10);
				magicArrowBagArrowKnock = builder.comment("Magic Arrow Bag: arrow knock")
						.defineInRange("magicArrowBagBowStrength", 0.1, 0, 10);

				flameArrowBagBowStrength = builder.comment("Flame Arrow Bag: bow strength")
						.defineInRange("flameArrowBagBowStrength", 0.1, 0, 10);
				flameArrowBagArrowKnock = builder.comment("Flame Arrow Bag: arrow knock")
						.defineInRange("flameArrowBagArrowKnock", 0.1, 0, 10);
				flameArrowBagTime = builder.comment("Flame Arrow Bag: flame")
						.defineInRange("flameArrowBagTime", 60, 1, 36000);

				spiritArrowBagBowStrength = builder.comment("Spirit Arrow Bag: bow strength")
						.defineInRange("spiritArrowBagBowStrength", 0.2, 0, 1);
				spiritArrowBagArrowSpeed = builder.comment("Spirit Arrow Bag: arrow speed")
						.defineInRange("spiritArrowBagArrowSpeed", 0.5, 0, 10);
				spiritArrowBagArrowKnock = builder.comment("Flame Arrow Bag: arrow knock")
						.defineInRange("spiritArrowBagArrowKnock", 1.0, 0, 10);


				leechScabbardHealFactor = builder.comment("Leech Scabbard healing rate as percentage of damage dealt")
						.defineInRange("leechScabbardHealFactor", 0.25, 0, 100);


				builder.pop();
			}

		}

		public static class Bracelet {

			// hidden_bracelet
			public final ForgeConfigSpec.IntValue hiddenBraceletEffectDuration;

			public final ForgeConfigSpec.DoubleValue hiddenBraceletDamageimprove;

			public final ForgeConfigSpec.IntValue hiddenBraceletCD;

			//charm_brace
			public final ForgeConfigSpec.IntValue charmbraceArmor;
			public final ForgeConfigSpec.IntValue charmbracevirus;
			public final ForgeConfigSpec.IntValue charmbraceship;

			//scarlet_bloodrage
			public final ForgeConfigSpec.DoubleValue scarletheal;
			public final ForgeConfigSpec.DoubleValue scarletdamagerate;
			public final ForgeConfigSpec.DoubleValue scarletdamagelimit;

			//spirit_bracelet
			public final ForgeConfigSpec.DoubleValue spiritspeedbow;

			private Bracelet(ForgeConfigSpec.Builder builder) {
				builder.push("bracelet");

				// hidden_bracelet
				hiddenBraceletEffectDuration = builder
						.comment("This value determines how many seconds you will receive a stealth effect once")
						.defineInRange("hiddenBraceletEffectDuration", 7, 1, Integer.MAX_VALUE);
				hiddenBraceletDamageimprove = builder
			            .comment("This value determines how many damage you will improve")
						.defineInRange("hiddenBraceletEffectDuration", 0.25, 0, Integer.MAX_VALUE);
				hiddenBraceletCD = builder
						.comment("This value determines how many seconds you cant hidden")
						.defineInRange("hiddenBraceletEffectDuration", 5, 1, Integer.MAX_VALUE);

				//charm_brace
				{
					charmbraceArmor = builder
						.comment("Armor improve")
						.defineInRange("charmbraceArmor", 2, 0, Integer.MAX_VALUE);
					charmbracevirus = builder
							.comment("enemy kill one of themself")
							.defineInRange("charmbracevirus", 10, 1, Integer.MAX_VALUE);
					charmbraceship = builder
							.comment("villager be friendly when you kill zombies")
							.defineInRange("ship", 1, 0, Integer.MAX_VALUE);
				}

				//scarlet_brace
				{
					scarletheal = builder
							.comment("Armor improve")
							.defineInRange("scarlet decrease heal", 0.50, 0, Integer.MAX_VALUE);
					scarletdamagerate = builder
							.comment("scarlet bracelet damage rate")
							.defineInRange("scarlet damage rate", 0.01, 0, Integer.MAX_VALUE);
					scarletdamagelimit = builder
							.comment("the limit of scarlet bracelet damage rate")
							.defineInRange("scarlet damage limit", 0.50, 0, Integer.MAX_VALUE);

					//spirit_brace
					spiritspeedbow = builder
							.comment("speed increase after hitting with a bow or crossbow")
							.defineInRange("scarlet damage limit", 1.00, 0, Integer.MAX_VALUE);

				}
				builder.pop();
			}

            public Bracelet(ForgeConfigSpec.IntValue hiddenBraceletEffectDuration, ForgeConfigSpec.DoubleValue hiddenBraceletDamageimprove, ForgeConfigSpec.IntValue hiddenBraceletCD, ForgeConfigSpec.IntValue charmbraceArmor, ForgeConfigSpec.IntValue charmbracevirus, ForgeConfigSpec.IntValue charmbraceship, ForgeConfigSpec.DoubleValue scarletheal, ForgeConfigSpec.DoubleValue scarletdamagerate, ForgeConfigSpec.DoubleValue scarletdamagelimit, ForgeConfigSpec.DoubleValue spiritspeedbow) {
                this.hiddenBraceletEffectDuration = hiddenBraceletEffectDuration;
                this.hiddenBraceletDamageimprove = hiddenBraceletDamageimprove;
                this.hiddenBraceletCD = hiddenBraceletCD;
                this.charmbraceArmor = charmbraceArmor;
                this.charmbracevirus = charmbracevirus;
                this.charmbraceship = charmbraceship;
                this.scarletheal = scarletheal;
                this.scarletdamagerate = scarletdamagerate;
                this.scarletdamagelimit = scarletdamagelimit;
                this.spiritspeedbow = spiritspeedbow;
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

			// angel heart
			public final ForgeConfigSpec.DoubleValue angelHeartThreshold;
			public final ForgeConfigSpec.DoubleValue angelHeartProtection;
			public final ForgeConfigSpec.IntValue angelHeartRemoveInterval;
			public final ForgeConfigSpec.IntValue angelHeartBloodInterval;
			public final ForgeConfigSpec.IntValue angelHeartHealAmount;

			// cursed protector
			public final ForgeConfigSpec.DoubleValue cursedProtectorThreshold;
			public final ForgeConfigSpec.DoubleValue cursedProtectorReduction;
			public final ForgeConfigSpec.DoubleValue cursedProtectorFrontProtect;

			// cursed talisman
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritRateAdd;
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritDamageAdd;

			// magic horseshoe
			public final ForgeConfigSpec.DoubleValue magicHorseshoeSpeedBonus;
			public final ForgeConfigSpec.DoubleValue magicHorseshoeLuck;
			public final ForgeConfigSpec.DoubleValue magicHorseshoeFallReduction;

			// bearing stamen
			public final ForgeConfigSpec.DoubleValue bearingStamenMaxHealth;
			public final ForgeConfigSpec.DoubleValue bearingStamenRegen;
			public final ForgeConfigSpec.IntValue bearingStamenLevel;

			// sands talisman
			public final ForgeConfigSpec.DoubleValue sandsTalismanDamageBonus;
			public final ForgeConfigSpec.DoubleValue sandsTalismanExpBonus;

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

				// angel_heart
				{
					angelHeartThreshold = builder
							.comment("Angel Heart: health threshold for protection")
							.defineInRange("angelHeartThreshold", 0.5, 0, 1);

					angelHeartProtection = builder
							.comment("Angel Heart: damage reduction")
							.defineInRange("angelHeartProtection", 0.2, 0, 1);

					angelHeartRemoveInterval = builder
							.comment("This value determines how many ticks remove negative effects once")
							.defineInRange("angelHeartRemoveInterval", 600, 1, Integer.MAX_VALUE);

					angelHeartBloodInterval = builder
							.comment("This value determines how many ticks restore health once")
							.defineInRange("angelHeartBloodInterval", 30, 1, Integer.MAX_VALUE);

					angelHeartHealAmount = builder
							.comment("This value determines how much health is restored at once")
							.defineInRange("angelHeartHealAmount", 1, 1, Integer.MAX_VALUE);
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

			private Head(ForgeConfigSpec.Builder builder) {
				builder.push("head");


				builder.pop();
			}

		}

		public static class Heart {

			public final ForgeConfigSpec.DoubleValue heartOfRevengeBowStrength;
			public final ForgeConfigSpec.IntValue heartOfRevengeValidTime;
			public final ForgeConfigSpec.DoubleValue heartOfRevengeDamageBonus;

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

			private Pendant(ForgeConfigSpec.Builder builder) {
				builder.push("pendant");


				builder.pop();
			}

		}

		public static class Ring {

			public final ForgeConfigSpec.DoubleValue amethystRingDamage;
			public final ForgeConfigSpec.DoubleValue netheriteRingProtection;

			private Ring(ForgeConfigSpec.Builder builder) {
				builder.push("ring");
				amethystRingDamage = builder.comment("Amethyst Ring damage boost")
						.defineInRange("amethystRingDamage", 0.1, 0, 100);
				netheriteRingProtection = builder.comment("Netherite Ring protection in nether")
						.defineInRange("netheriteRingProtection", 0.1, 0, 1);
				builder.pop();
			}

		}

		public static class Scroll {

			private Scroll(ForgeConfigSpec.Builder builder) {
				builder.push("scroll");


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
