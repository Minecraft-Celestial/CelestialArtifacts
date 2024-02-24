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

		public static class Back {

			public ForgeConfigSpec.DoubleValue leechScabbardHealFactor;

			private Back(ForgeConfigSpec.Builder builder) {
				builder.push("back");
				leechScabbardHealFactor = builder.comment("Leech Scabbard healing rate as percentage of damage dealt")
						.defineInRange("leechScabbardHealFactor", 0.25, 0, 100);


				builder.pop();
			}

		}

		public static class Bracelet {

			// hidden_bracelet
			public final ForgeConfigSpec.IntValue hiddenBraceletEffectInterval;

			private Bracelet(ForgeConfigSpec.Builder builder) {
				builder.push("bracelet");

				// hidden_bracelet
				hiddenBraceletEffectInterval = builder
						.comment("This value determines how many ticks you will receive a stealth effect once")
						.defineInRange("hiddenBraceletEffectInterval", 140, 1, Integer.MAX_VALUE);

				builder.pop();
			}

		}

		public static class Charm {

			// holy_sword
			public final ForgeConfigSpec.DoubleValue holySwordMaxAddDamage;
			public final ForgeConfigSpec.DoubleValue holySwordLostLifeAddDamage;

			// angel_heart
			public final ForgeConfigSpec.IntValue angelHeartRemoveInterval;
			public final ForgeConfigSpec.IntValue angelHeartBloodInterval;
			public final ForgeConfigSpec.IntValue angelHeartHealAmount;
			// cursed_protector
			public final ForgeConfigSpec.DoubleValue cursedProtectorJudgingLife;
			public final ForgeConfigSpec.DoubleValue cursedProtectorReduceInjury;

			// cursed_talisman
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritRateAdd;
			public final ForgeConfigSpec.DoubleValue cursedTalismanCritDamageAdd;

			private Charm(ForgeConfigSpec.Builder builder) {
				builder.push("charm");

				// holy_sword
				holySwordMaxAddDamage = builder
						.comment("This value determines the maximum damage increase")
						.defineInRange("holySwordMaxAddDamage", 0.5, 0, Integer.MAX_VALUE);

				holySwordLostLifeAddDamage = builder
						.comment("This value determines how much life you lose and how much damage you inflict once")
						.defineInRange("holySwordLostLifeAddDamage", 1, 1.0, Integer.MAX_VALUE);

				// angel_heart
				angelHeartRemoveInterval = builder
						.comment("This value determines how many ticks remove negative effects once")
						.defineInRange("angelHeartRemoveInterval", 600, 1, Integer.MAX_VALUE);

				angelHeartBloodInterval = builder
						.comment("This value determines how many ticks restore health once")
						.defineInRange("angelHeartBloodInterval", 30, 1, Integer.MAX_VALUE);

				angelHeartHealAmount = builder
						.comment("This value determines how much health is restored at once")
						.defineInRange("angelHeartHealAmount", 1, 1, Integer.MAX_VALUE);

				// cursed_protector
				cursedProtectorJudgingLife = builder
						.comment("This value determines how much health is greater than the current one in order to trigger damage reduction")
						.defineInRange("cursedProtectorJudgingLife", 0.35, 0.01, 1);

				cursedProtectorReduceInjury = builder
						.comment("This value determines its damage reduction amount")
						.defineInRange("cursedProtectorReduceInjury", 0.25, 0.01, 1);

				// cursed_talisman
				cursedTalismanCritRateAdd = builder
						.comment("This value determines the critical hit rate increased by each enchantment")
						.defineInRange("cursedTalismanCritRateAdd", 0.04, 0.01, 1);

				cursedTalismanCritDamageAdd = builder
						.comment("This value determines the critical hit damage increased by each enchantment")
						.defineInRange("cursedTalismanCritDamageAdd", 0.08, 0.01, 1);

				builder.pop();
			}

		}

		public static class Curse {

			// catastrophe_scroll
			public final ForgeConfigSpec.BooleanValue catastropheScrollStart;

			public final ForgeConfigSpec.DoubleValue catastropheScrollExplosionDamage;
			public final ForgeConfigSpec.DoubleValue catastropheScrollOtherDamage;
			public final ForgeConfigSpec.DoubleValue catastropheScrollEndCurseDamage;
			public final ForgeConfigSpec.DoubleValue catastropheScrollOriginCurseDamage;


			// etching_of_life
			public final ForgeConfigSpec.IntValue etchingOfLifeDropCondition;

			private Curse(ForgeConfigSpec.Builder builder) {
				builder.push("curse");

				// catastrophe_scroll
				catastropheScrollStart = builder
						.comment("When true, when the player enters the world, the accessory will be directly worn in the accessory bar")
						.define("catastropheScrollStart", false);

				catastropheScrollExplosionDamage = builder
						.comment("This value determines how much explosive damage you will receive due to the Chaos Curse")
						.defineInRange("catastropheScrollExplosionDamage", 0.75, 0.01, Integer.MAX_VALUE);

				catastropheScrollOtherDamage = builder
						.comment("This value determines how much other damage you will receive due to the Chaos Curse")
						.defineInRange("catastropheScrollOtherDamage", 0.5, 0.01, Integer.MAX_VALUE);

				catastropheScrollEndCurseDamage = builder
						.comment("This value determines how much damage is negatively affected")
						.defineInRange("catastropheScrollEndCurseDamage", 0.25, 0.01, 1);

				catastropheScrollOriginCurseDamage = builder
						.comment("This value determines how much your damage will be reduced due to the Origin Curse")
						.defineInRange("catastropheScrollOriginCurseDamage", 0.5, 0.01, 1);


				// etching_of_life
				etchingOfLifeDropCondition = builder
						.pop()
						.comment("This value determines how many enemies with maximum health you need to defeat in order to possibly drop it")
						.defineInRange("etchingOfLifeDropCondition", 499, 1, Integer.MAX_VALUE);

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

			private Heart(ForgeConfigSpec.Builder builder) {
				builder.push("heart");


				builder.pop();
			}

		}

		public static class Necklace {

			private Necklace(ForgeConfigSpec.Builder builder) {
				builder.push("necklace");


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

			private Ring(ForgeConfigSpec.Builder builder) {
				builder.push("ring");


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
		register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static void register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = "celestial_configs/" + mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
	}

}
