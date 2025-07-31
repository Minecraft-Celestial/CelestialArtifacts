package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.attack.SimpleListener;
import com.xiaoyue.celestial_artifacts.content.core.effect.ConditionalEffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.effect.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.effect.HurtPlayerEffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.effect.HurtTargetEffectFacet;
import com.xiaoyue.celestial_artifacts.content.core.feature.BreakSpeedFeature;
import com.xiaoyue.celestial_artifacts.content.core.feature.XpBonusFeature;
import com.xiaoyue.celestial_artifacts.content.core.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.core.modular.SlotFacet;
import com.xiaoyue.celestial_artifacts.content.core.modular.TextFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.CAAttackToken;
import com.xiaoyue.celestial_artifacts.content.core.token.InvulToken;
import com.xiaoyue.celestial_artifacts.content.core.token.SetTokenFacet;
import com.xiaoyue.celestial_artifacts.content.core.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.content.curios.back.LeechScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.back.TwistedScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.body.ForestCloak;
import com.xiaoyue.celestial_artifacts.content.curios.bracelet.CharmingBracelet;
import com.xiaoyue.celestial_artifacts.content.curios.bracelet.HiddenBracelet;
import com.xiaoyue.celestial_artifacts.content.curios.bracelet.ScarletBracelet;
import com.xiaoyue.celestial_artifacts.content.curios.bracelet.SpiritBracelet;
import com.xiaoyue.celestial_artifacts.content.curios.charm.*;
import com.xiaoyue.celestial_artifacts.content.curios.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.curios.head.*;
import com.xiaoyue.celestial_artifacts.content.curios.heart.DemonHeart;
import com.xiaoyue.celestial_artifacts.content.curios.heart.TwistedHeart;
import com.xiaoyue.celestial_artifacts.content.curios.necklace.*;
import com.xiaoyue.celestial_artifacts.content.curios.pendant.ShadowPendant;
import com.xiaoyue.celestial_artifacts.content.curios.ring.FlightRing;
import com.xiaoyue.celestial_artifacts.content.curios.ring.NetherFire;
import com.xiaoyue.celestial_artifacts.content.curios.ring.RingOfLife;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SeaGodScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SkywalkerScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.TwistedScroll;
import com.xiaoyue.celestial_artifacts.content.curios.set.EmeraldSet;
import com.xiaoyue.celestial_artifacts.content.curios.set.SeaGodSet;
import com.xiaoyue.celestial_artifacts.content.curios.set.SpiritSet;
import com.xiaoyue.celestial_artifacts.content.items.item.*;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthAxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthHoe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthPickaxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthShovel;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_artifacts.data.CAModConfig;
import com.xiaoyue.celestial_artifacts.data.CATagGen;
import com.xiaoyue.celestial_core.content.generic.CCTooltipItem;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.Curios;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

public class CAItems {

	public static final LinkedHashSet<String> ALL_CURIOS = new LinkedHashSet<>();

	public static final ItemEntry<CCTooltipItem> THE_END_DUST = material("the_end_dust",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false,
					() -> CALang.Tooltip.END_DUST.get(CALang.Modular.curseItem(),
							TextFacet.perc(CAModConfig.COMMON.materials.endDustDropChance.get()))));
	public static final ItemEntry<CCTooltipItem> NEBULA_CUBE = material("nebula_cube",
			p -> new CCTooltipItem(new Item.Properties().rarity(Rarity.EPIC), false, CALang.Tooltip.NEBULA_CUBE::get));

	public static final ItemEntry<ModularCurio> GOLD_RING, AMETHYST_RING, DIAMOND_RING, EMERALD_RING, FLIGHT_RING, NETHERITE_RING, RING_OF_LIFE, THUNDER_RING, NETHER_FIRE, FREEZE_RING,
			WAR_DEAD_BADGE, UNDEAD_CHARM, DESTROYER_BADGE, TWISTED_BRAIN, CORRUPT_BADGE,
			CURSED_TALISMAN, CURSED_PROTECTOR, CURSED_TOTEM, HOLY_TALISMAN, HOLY_SWORD, ANGEL_HEART,
			ANGEL_PEARL, DEMON_CURSE, KNIGHT_SHELTER, SOUL_BOX, SOLAR_MAGNET, GLUTTONY_BADGE, MAGIC_HORSESHOE,
			BEARING_STAMEN, ABYSS_WILL_BADGE, SANDS_TALISMAN, SACRIFICIAL_OBJECT, DEERS_MERCY_AMULET, DEER_INSCRIBED_AMULET,
			HEART_OF_REVENGE, TWISTED_HEART, GREEDY_HEART, DEMON_HEART,
			TRAVELER_SCROLL, SEA_GOD_SCROLL, SKYWALKER_SCROLL, TWISTED_SCROLL,
			EMERALD_BRACELET, LIFE_BRACELET, PRECIOUS_BRACELET, RED_RUBY_BRACELET, HIDDEN_BRACELET, SCARLET_BRACELET, CHARMING_BRACELET, SPIRIT_BRACELET,
			UNOWNED_PENDANT, CHAOTIC_PENDANT, SHADOW_PENDANT, FOREST_CLOAK,
			STAR_NECKLACE, CROSS_NECKLACE, GALLOP_NECKLACE, FANG_NECKLACE, PRECIOUS_NECKLACE,
			HOLY_NECKLACE, HEIRLOOM_NECKLACE, EMERALD_NECKLACE, ENDER_PROTECTOR, RED_HEART_NECKLACE, LOCK_OF_ABYSS, SPIRIT_NECKLACE, TREASURE_HUNTER_NECKLACE,
			SEA_GOD_CROWN, PRAYER_CROWN, ABYSS_CORE, GUARDIAN_EYE, EVIL_EYE, SPIRIT_CROWN, SAKURA_HAIRPIN, YELLOW_DUCK, ANGEL_DESIRE,
			MAGIC_ARROW_BAG, FLAME_ARROW_BAG, SPIRIT_ARROW_BAG, IRON_SCABBARD, LEECH_SCABBARD, TITAN_SCABBARD, TWISTED_SCABBARD,
			CATASTROPHE_SCROLL, CHAOTIC_ETCHING, ORIGIN_ETCHING, LIFE_ETCHING, TRUTH_ETCHING, DESIRE_ETCHING, NIHILITY_ETCHING, END_ETCHING;

	static {

		// ring
		{
			// 金戒指
			GOLD_RING = ring("gold_ring", () ->
					ModularCurio.of(EffectFacet.of(() -> MobEffects.LUCK)));

			// 紫水晶戒指
			AMETHYST_RING = ring("amethyst_ring", () ->
					ModularCurio.of(EffectFacet.of(() -> MobEffects.NIGHT_VISION),
							AttrFacet.multBase(() -> Attributes.ATTACK_DAMAGE,
									CAModConfig.COMMON.ring.amethystRingDamage::get)));
			// 钻石戒指
			DIAMOND_RING = ring("diamond_ring", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(() -> MobEffects.DAMAGE_BOOST)));
			// 绿宝石戒指
			EMERALD_RING = ring("emerald_ring", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
							AttrFacet.add(() -> Attributes.LUCK, () -> 1), emeraldSet()));
			// 飞行戒指
			FLIGHT_RING = ring("flight_ring", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new TokenFacet<>("flight_ring", FlightRing::new)));
			// 下界合金戒指
			NETHERITE_RING = ring("netherite_ring", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(() -> MobEffects.FIRE_RESISTANCE),
							SimpleListener.protect(
									CALang.Condition.NETHER::get,
									(p, a, c) -> p.level().dimension().equals(Level.NETHER),
									CAModConfig.COMMON.ring.netheriteRingProtection::get
							)
					));
			// 生息之戒
			RING_OF_LIFE = ring("ring_of_life", () -> ModularCurio.builder().rarity(IRarityUtils.GREEN)
					.build(new RingOfLife()));
			// 雷电之戒
			THUNDER_RING = ring("thunder_ring", () -> ModularCurio.builder().rarity(Rarity.RARE)
					.build(SimpleListener.negateType(CALang.DamageTypes.LIGHTNING)));
			// 地狱之火
			NETHER_FIRE = ring("nether_fire", () -> ModularCurio.builder().rarity(Rarity.RARE)
					.build(SimpleListener.negateType(CALang.DamageTypes.FIRE), new NetherFire()));
			// 冰冻之戒
			FREEZE_RING = ring("freeze_ring", () -> ModularCurio.builder().rarity(Rarity.RARE)
					.build(SimpleListener.negateType(CALang.DamageTypes.FREEZE)));
		}

		// charms
		{
			//战争亡者之徽
			WAR_DEAD_BADGE = charm("war_dead_badge", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE)
					.build(new TokenFacet<>("war_dead_badge", WarDeadBadge::new)));

			// 不死者护符
			UNDEAD_CHARM = charm("undead_charm", () -> ModularCurio.builder()
					.requireCS().rarity(Rarity.RARE).build(new UndeadCharm()));

			// 毁灭者徽章
			DESTROYER_BADGE = charm("destroyer_badge", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(
							AttrFacet.multTotal(() -> Attributes.ATTACK_DAMAGE, CAModConfig.COMMON.charm.destroyerBadgeAttack::get),
							AttrFacet.multTotal(L2DamageTracker.REDUCTION::get, CAModConfig.COMMON.charm.destroyerBadgeDamagePenalty::get),
							SimpleListener.hurtBonus(
									() -> CALang.Condition.LOW_HEALTH.get(TextFacet.perc(CAModConfig.COMMON.charm.destroyerBadgeThreshold.get())),
									(p, t, c) -> p.getHealth() <= CAModConfig.COMMON.charm.destroyerBadgeThreshold.get() * p.getMaxHealth(),
									CAModConfig.COMMON.charm.destroyerBadgeHurtBonus::get)));

			// 扭曲之脑
			TWISTED_BRAIN = charm("twisted_brain", () -> ModularCurio.builder()
					.requireCS().rarity(Rarity.EPIC).loot(1).build(new TwistedBrain()));

			// 噬咒护符
			CORRUPT_BADGE = charm("corrupt_badge", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE)
					.build(new TokenFacet<>("corrupt_badge", CorruptBadge::new)));

			// 腐化侵蚀徽章
			CURSED_TALISMAN = charm("cursed_talisman", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE)
					.build(new TokenFacet<>("cursed_talisman", CursedTalisman::new)));

			// 受诅咒的坚盾
			CURSED_PROTECTOR = charm("cursed_protector", () ->
					ModularCurio.builder().requireCS().rarity(Rarity.EPIC).build(
							AttrFacet.add(() -> Attributes.KNOCKBACK_RESISTANCE, () -> 1),
							SimpleListener.protect(
									CALang.Condition.FRONT_DAMAGE::get,
									(p, a, c) -> !EntityUtils.isLookingBehindTarget(p, a.getEyePosition()),
									CAModConfig.COMMON.charm.cursedProtectorFrontProtect::get
							),
							new CursedProtector()
					));

			// 负咒的灵魂图腾
			CURSED_TOTEM = charm("cursed_totem", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE).build(CursedTotem.TOKEN));

			// 神圣七彩护符
			HOLY_TALISMAN = charm("holy_talisman", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new HolyTalisman()
					));

			// 大天使之剑
			HOLY_SWORD = charm("holy_sword", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							AttrFacet.add(L2DamageTracker.CRIT_RATE::get,
									CAModConfig.COMMON.charm.holySwordCritRate::get),
							new HolySword()
					));

			// 天使之心
			ANGEL_HEART = charm("angel_heart", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new AngelHeart(),
							SimpleListener.protect(
									() -> CALang.Condition.LOW_HEALTH.get(TextFacet.perc(CAModConfig.COMMON.charm.angelHeartThreshold.get())),
									(p, a, c) -> p.getHealth() <= CAModConfig.COMMON.charm.angelHeartThreshold.get() * p.getMaxHealth(),
									CAModConfig.COMMON.charm.angelHeartProtection::get
							)
					));

			// 天使珍珠
			ANGEL_PEARL = charm("angel_pearl", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new TokenFacet<>("angel_pearl", AngelPearl::new)
					));

			// 大恶魔之咒
			DEMON_CURSE = charm("demon_curse", () -> ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE)
					.requireCS().build(new TokenFacet<>("demon_curse", DemonCurse::new)));

			// 骑士庇护
			KNIGHT_SHELTER = charm("knight_shelter", () -> ModularCurio.builder().rarity(Rarity.UNCOMMON)
					.build(new KnightShelter(),
							AttrFacet.add(() -> Attributes.ARMOR,
									CAModConfig.COMMON.charm.knightShelterArmor::get),
							AttrFacet.add(L2DamageTracker.ABSORB::get,
									CAModConfig.COMMON.charm.knightShelterDamageReduction::get)
					));

			// 魂灵匣
			SOUL_BOX = charm("soul_box", () -> ModularCurio.builder()
					.requireCS().rarity(Rarity.EPIC).build(new SoulBox()));

			// 太阳磁铁
			SOLAR_MAGNET = charm("solar_magnet", () -> ModularCurio.builder().rarity(Rarity.RARE)
					.build(new SolarMagnet(), SimpleListener.hurtBonus(
							CALang.Condition.DAY::get, (p, t, c) -> p.level().isDay(),
							CAModConfig.COMMON.charm.solarMagnetDamageBonus::get
					)));

			// 暴食徽章
			GLUTTONY_BADGE = charm("gluttony_badge", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(new GluttonyBadge(),
							EffectFacet.of(() -> MobEffects.HUNGER, () -> 3,
									CAModConfig.COMMON.charm.gluttonyBadgeHungerLevel::get)
					));

			// 魔法马掌
			MAGIC_HORSESHOE = charm("magic_horseshoe", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED,
									CAModConfig.COMMON.charm.magicHorseshoeSpeedBonus::get),
							AttrFacet.add(() -> Attributes.LUCK,
									CAModConfig.COMMON.charm.magicHorseshoeLuck::get),
							SimpleListener.protectType(CALang.DamageTypes.FALL,
									CAModConfig.COMMON.charm.magicHorseshoeFallReduction::get)
					));

			// 生息胸花
			BEARING_STAMEN = charm("bearing_stamen", () -> ModularCurio.builder().rarity(IRarityUtils.GREEN)
					.build(
							AttrFacet.add(() -> Attributes.MAX_HEALTH,
									CAModConfig.COMMON.charm.bearingStamenMaxHealth::get),
							AttrFacet.add(CCAttributes.REPLY_POWER::get,
									CAModConfig.COMMON.charm.bearingStamenRegen::get),
							EffectFacet.of(() -> MobEffects.REGENERATION, () -> 2,
									CAModConfig.COMMON.charm.bearingStamenLevel::get)
					));

			// 深渊意志徽章
			ABYSS_WILL_BADGE = charm("abyss_will_badge", () -> ModularCurio.builder().rarity(IRarityUtils.DARK_AQUA)
					.requireCS().build(AbyssWillBadge.TOKEN));

			// 金沙护符
			SANDS_TALISMAN = charm("sands_talisman", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).loot(1).build(
							XpBonusFeature.simple(CAModConfig.COMMON.charm.sandsTalismanExpBonus::get),
							SimpleListener.hurtBonus(
									CALang.Condition.HOT_REGION::get,
									(p, t, c) -> p.level().getBiome(p.blockPosition()).get().getBaseTemperature() >= 0.01,
									CAModConfig.COMMON.charm.sandsTalismanDamageBonus::get)
					));

			// 古代殉葬品
			SACRIFICIAL_OBJECT = charm("sacrificial_object", () -> ModularCurio.builder().rarity(Rarity.EPIC)
					.requireCS().loot(1).build(AttrFacet.multTotal(L2DamageTracker.REDUCTION::get,
									() -> -CAModConfig.COMMON.charm.sacrificialObjectReduction.get()),
							new SacrificialObject()));

			// 铭鹿护符
			DEERS_MERCY_AMULET = charm("deers_mercy_amulet", () -> ModularCurio.builder()
					.rarity(Rarity.RARE).fortune(1).loot(1).build(
							AttrFacet.add(() -> Attributes.MAX_HEALTH, CAModConfig.COMMON.charm.deersMercyAmuletMaxHealth::get),
							AttrFacet.add(() -> Attributes.ATTACK_DAMAGE, CAModConfig.COMMON.charm.deersMercyAmuletDamage::get)
					));

			DEER_INSCRIBED_AMULET = charm("deer_inscribed_amulet", () -> ModularCurio.builder()
					.rarity(Rarity.UNCOMMON).build(
							AttrFacet.multBase(() -> Attributes.MAX_HEALTH, CAModConfig.COMMON.charm.deerInscribedAmuletMaxHealth::get),
							AttrFacet.add(() -> Attributes.ARMOR, CAModConfig.COMMON.charm.deerInscribedAmuletArmor::get),
							AttrFacet.add(() -> Attributes.ARMOR_TOUGHNESS, CAModConfig.COMMON.charm.deerInscribedAmuletToughness::get),
							AttrFacet.add(() -> Attributes.ATTACK_DAMAGE, CAModConfig.COMMON.charm.deerInscribedAmuletDamage::get),
							new TokenFacet<>("deer_inscribed_amulet", DeerInscribedAmulet::new)));
		}

		// heart
		{
			// 复仇之心
			HEART_OF_REVENGE = heart("heart_of_revenge", () -> {
				IntSupplier dur = CAModConfig.COMMON.heart.heartOfRevengeValidTime::get;
				return ModularCurio.builder().rarity(IRarityUtils.GOLD).build(
						AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get,
								CAModConfig.COMMON.heart.heartOfRevengeBowStrength::get),
						HurtPlayerEffectFacet.of(
								EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, dur, () -> 1),
								EffectFacet.of(() -> MobEffects.DAMAGE_RESISTANCE, dur, () -> 0)
						),
						SimpleListener.hurtBonus(
								() -> CALang.Condition.REVENGE.get(TextFacet.num(dur.getAsInt())),
								(p, t, c) -> p.getLastHurtByMobTimestamp() >= p.tickCount - dur.getAsInt() * 20,
								CAModConfig.COMMON.heart.heartOfRevengeDamageBonus::get
						)
				);
			});

			// 扭曲之心
			TWISTED_HEART = heart("twisted_heart", () ->
					ModularCurio.builder().requireCS().rarity(IRarityUtils.DARK_PURPLE).immune().loot(1).build(
							new TokenFacet<>("twisted_heart", TwistedHeart::new)
					));

			// 贪婪者之心
			GREEDY_HEART = heart("greedy_heart", () ->
					ModularCurio.builder().requireCS().rarity(Rarity.EPIC).fortune(1).loot(1).build(
							XpBonusFeature.simple(CAModConfig.COMMON.heart.greedyHeartXpBonus::get)
					));

			// 恶魔之心
			DEMON_HEART = heart("demon_heart", () ->
					ModularCurio.builder().requireCS().rarity(Rarity.EPIC).requireCS()
							.build(new TokenFacet<>("demon_heart", DemonHeart::new)));
		}

		// scroll
		{
			// 旅者卷轴
			TRAVELER_SCROLL = scroll("traveler_scroll", () -> {
				DoubleSupplier sup = CAModConfig.COMMON.scroll.travellerScrollSpeedBonus::get;
				return ModularCurio.builder().rarity(Rarity.UNCOMMON).immune().build(
						AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, sup),
						AttrFacet.multBase(() -> Attributes.FLYING_SPEED, sup),
						AttrFacet.multBase(ForgeMod.SWIM_SPEED, sup),
						TextFacet.line(() -> CALang.Scroll.TRAVELER.get(
								EffectFacet.getDesc(CAModConfig.COMMON.scroll.travelerScrollSpeedEffect()),
								EffectFacet.getDesc(CAModConfig.COMMON.scroll.travelerScrollRegenEffect())
						))
				);
			});

			// 海神卷轴
			SEA_GOD_SCROLL = scroll("sea_god_scroll", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							new SeaGodScroll(),
							ConditionalEffectFacet.of(false,
									Player::isInWaterRainOrBubble,
									CALang.Condition.PLAYER_WET::get,
									EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 2, 1),
									EffectFacet.of(() -> MobEffects.DAMAGE_RESISTANCE, 2, 0)
							),
							seaGodSet()));
			// 传送卷轴
			SKYWALKER_SCROLL = scroll("skywalker_scroll", () -> ModularCurio.builder()
					.rarity(Rarity.UNCOMMON).build(SkywalkerScroll.TOKEN));

			// 扭曲卷轴
			TWISTED_SCROLL = scroll("twisted_scroll", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE).loot(1).build(new TwistedScroll()));
		}

		// bracelet
		{
			// 幸运手环
			EMERALD_BRACELET = bracelet("emerald_bracelet", () -> {
				IntSupplier luck = CAModConfig.COMMON.bracelet.emeraldBraceletLuckReq::get;
				return ModularCurio.builder().rarity(IRarityUtils.GREEN).fortune(1).build(
						AttrFacet.add(L2DamageTracker.CRIT_RATE::get,
								CAModConfig.COMMON.bracelet.emeraldBraceletCritRate::get),
						HurtPlayerEffectFacet.of(
								e -> e.getLuck() >= luck.getAsInt(),
								() -> CALang.Condition.LUCK.get(TextFacet.num(luck.getAsInt())),
								CAModConfig.COMMON.bracelet.emeraldBraceletEffChance::get,
								EffectFacet.of(() -> MobEffects.ABSORPTION, 5, 1)),
						emeraldSet());
			});

			// 生命手环
			LIFE_BRACELET = bracelet("life_bracelet", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.add(CCAttributes.REPLY_POWER::get,
									CAModConfig.COMMON.bracelet.lifeBraceletRegenBonus::get),
							EffectFacet.of(() -> MobEffects.REGENERATION, () -> 4, () -> 0)
					));

			// 珍钻手环
			PRECIOUS_BRACELET = bracelet("precious_bracelet", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).fortune(1).build(
							AttrFacet.add(ForgeMod.BLOCK_REACH, CAModConfig.COMMON.bracelet.preciousBraceletReach::get),
							SlotFacet.of("ring", 1),
							SimpleListener.protectType(CALang.DamageTypes.MAGIC,
									CAModConfig.COMMON.bracelet.preciousBraceletMagicReduction::get)
					));

			// 绯红石手环
			RED_RUBY_BRACELET = bracelet("red_ruby_bracelet", () ->
					ModularCurio.builder().rarity(IRarityUtils.RED).build(
							SimpleListener.protectType(CALang.DamageTypes.FIRE,
									CAModConfig.COMMON.bracelet.redRubyBraceletFireReduction::get),
							HurtPlayerEffectFacet.ofType(e -> e.is(DamageTypeTags.IS_FIRE), CALang.Condition.HURT_FIRE::get,
									EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 3, 0))
					));

			// 隐匿手环
			HIDDEN_BRACELET = bracelet("hidden_bracelet", () -> ModularCurio.builder().requireCS()
					.rarity(IRarityUtils.DARK_PURPLE).build(new TokenFacet<>("hidden_bracelet", HiddenBracelet::new)));

			// 猩红手环
			SCARLET_BRACELET = bracelet("scarlet_bracelet", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.RED).build(new ScarletBracelet()));

			// 魅力手环
			CHARMING_BRACELET = bracelet("charming_bracelet", () -> ModularCurio.builder().rarity(Rarity.RARE).build(
					AttrFacet.add(() -> Attributes.ARMOR, CAModConfig.COMMON.bracelet.charmingBraceletArmor::get),
					new CharmingBracelet()
			));
			// 精灵手环
			SPIRIT_BRACELET = bracelet("spirit_bracelet", () -> ModularCurio.builder().rarity(IRarityUtils.GREEN)
					.build(new SpiritBracelet(), spiritSet()));
		}

		// pendant
		{
			// 无主的吊坠
			UNOWNED_PENDANT = pendant("unowned_pendant", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build());

			// 混沌吊坠
			CHAOTIC_PENDANT = pendant("chaotic_pendant", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).requireCS().loot(1).build(
							TextFacet.line(() -> CALang.Pendant.CHAOTIC.get(TextFacet.num(
									CAModConfig.COMMON.pendant.chaoticPendantEnchantLevel.get())))
					));

			// 怨影吊坠
			SHADOW_PENDANT = pendant("shadow_pendant", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).requireCS().build(new ShadowPendant()));
		}

		// necklace
		{
			// 星星项链
			STAR_NECKLACE = necklace("star_necklace", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							SimpleListener.hurtBonus(
									CALang.Condition.ATTACK_BEHIND::get,
									(p, t, c) -> EntityUtils.isLookingBehindTarget(t, p.position()),
									CAModConfig.COMMON.necklace.starNecklaceDamageBonus::get),
							ConditionalEffectFacet.of(false,
									e -> e.level().isNight(), CALang.Condition.NIGHT::get,
									EffectFacet.of(() -> MobEffects.DAMAGE_RESISTANCE, 2, 0))
					));

			// 十字项链
			CROSS_NECKLACE = necklace("cross_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(InvulToken.of(
							CAModConfig.COMMON.necklace.crossNecklaceInvulTick::get
					)));

			// 疾行项链
			GALLOP_NECKLACE = necklace("gallop_necklace", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED,
									CAModConfig.COMMON.necklace.gallopNecklaceSpeedBonus::get),
							new GallopNecklace()
					));

			// 毒牙项链
			FANG_NECKLACE = necklace("fang_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							AttrFacet.multBase(() -> Attributes.ATTACK_SPEED,
									CAModConfig.COMMON.necklace.fangNecklaceAttack::get),
							SimpleListener.hurtBonus(
									CALang.Condition.TARGET_HAS_ARMOR::get,
									(p, t, c) -> EntityUtils.hasArmor(t),
									CAModConfig.COMMON.necklace.fangNecklaceDamageBonus::get),
							HurtTargetEffectFacet.of(
									CAModConfig.COMMON.necklace.fangNecklacePoisonChance::get,
									() -> MobEffects.POISON,
									CAModConfig.COMMON.necklace.fangNecklacePoisonDuration::get,
									CAModConfig.COMMON.necklace.fangNecklacePoisonLevel::get)
					));

			// 珍钻项链
			PRECIOUS_NECKLACE = necklace("precious_necklace", () ->
					ModularCurio.builder().rarity(Rarity.RARE).fortune(1).build(
							AttrFacet.add(L2DamageTracker.CRIT_DMG::get,
									CAModConfig.COMMON.necklace.preciousNecklaceCritDmg::get),
							SlotFacet.of("charm", 1)
					));

			// 神圣项链
			HOLY_NECKLACE = necklace("holy_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							AttrFacet.add(() -> Attributes.MAX_HEALTH,
									CAModConfig.COMMON.necklace.holyNecklaceMaxHealth::get),
							InvulToken.of(CAModConfig.COMMON.necklace.holyNecklaceInvulTick::get),
							new HolyNecklace()
					));

			// 家传项链
			HEIRLOOM_NECKLACE = necklace("heirloom_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).fortune(1).build(
							AttrFacet.add(() -> Attributes.ARMOR,
									CAModConfig.COMMON.necklace.hierloomNecklaceArmor::get),
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED,
									CAModConfig.COMMON.necklace.hierloomNecklaceSpeed::get),
							XpBonusFeature.simple(CAModConfig.COMMON.necklace.hierloomNecklaceExp::get)
					));

			// 绿宝石项链
			EMERALD_NECKLACE = necklace("emerald_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).fortune(1).build(
							new EmeraldNecklace(), emeraldSet()));

			// 末影庇佑者项链
			ENDER_PROTECTOR = necklace("ender_protector", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).requireCS().enderMask().build(
							AttrFacet.add(() -> Attributes.ARMOR_TOUGHNESS,
									CAModConfig.COMMON.necklace.enderProtectorToughness::get),
							new EnderProtector()
					));

			// 红心项链
			RED_HEART_NECKLACE = necklace("red_heart_necklace", () ->
					ModularCurio.of(AttrFacet.multBase(() -> Attributes.MAX_HEALTH,
							CAModConfig.COMMON.necklace.redHeartNecklaceMaxHealth::get)));

			// 深渊之锁
			LOCK_OF_ABYSS = necklace("lock_of_abyss", () ->
					ModularCurio.builder().requireCS().rarity(IRarityUtils.DARK_AQUA).build(new LockOfAbyss()));

			// 精灵项链
			SPIRIT_NECKLACE = necklace("spirit_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get,
									CAModConfig.COMMON.necklace.spiritNecklaceProjectile::get),
							HurtPlayerEffectFacet.of(EffectFacet.of(() -> MobEffects.MOVEMENT_SPEED, 5, 0)),
							spiritSet()));

			// 寻宝者项链
			TREASURE_HUNTER_NECKLACE = necklace("treasure_hunter_necklace", () ->
					ModularCurio.builder().requireCS().rarity(Rarity.UNCOMMON).fortune(1)
							.build(new TreasureHunterNecklace()));
		}

		// body
		{
			// 丛林披风
			FOREST_CLOAK = body("forest_cloak", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(new ForestCloak()));
		}

		// head
		{
			// 海神王冠
			SEA_GOD_CROWN = head("sea_god_crown", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							ConditionalEffectFacet.of(false,
									Player::isInWaterRainOrBubble,
									CALang.Condition.PLAYER_WET::get,
									EffectFacet.of(() -> MobEffects.WATER_BREATHING, 2, 3),
									EffectFacet.of(() -> MobEffects.NIGHT_VISION, 2, 0)
							), new SeaGodCrown(), seaGodSet()));

			// 祷告者王冠
			PRAYER_CROWN = head("prayer_crown", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							InvulToken.of(() -> 10),
							SimpleListener.protect(CALang.Condition.SNEAK::get,
									(player, attacker, cache) -> player.isCrouching(),
									CAModConfig.COMMON.head.prayerCrownProtection::get),
							new PrayerCrown()));

			// 深渊意志之核
			ABYSS_CORE = head("abyss_core", () ->
					ModularCurio.builder().requireCS().rarity(IRarityUtils.DARK_AQUA).build(new AbyssCore()));

			// 守卫者之眼
			GUARDIAN_EYE = head("guardian_eye", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.multBase(ForgeMod.SWIM_SPEED, () -> 0.15),
							SimpleListener.protect(CALang.Condition.PLAYER_WET::get,
									(player, attacker, cache) -> player.isInWaterRainOrBubble(),
									CAModConfig.COMMON.head.guardianEyeProtection::get),
							new GuardianEye()));

			// 邪恶之瞳
			EVIL_EYE = head("evil_eye", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).build(new EvilEye()));

			// 精灵花冠
			SPIRIT_CROWN = head("spirit_crown", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							new SpiritCrown(), spiritSet()));

			// 樱花发簪
			SAKURA_HAIRPIN = head("sakura_hairpin", () -> ModularCurio.builder().rarity(IRarityUtils.PINK).build(
					AttrFacet.add(() -> Attributes.MAX_HEALTH,
							CAModConfig.COMMON.head.sakuraHairpinMaxHealthAdd::get),
					AttrFacet.multBase(() -> Attributes.ARMOR,
							CAModConfig.COMMON.head.sakuraHairpinArmorBonus::get),
					new TokenFacet<>("sakura_hairpin", SakuraHairpin::new)
			));

			// 小黄鸭
			YELLOW_DUCK = head("yellow_duck", () ->
					ModularCurio.builder().rarity(IRarityUtils.YELLOW).build(new YellowDuck()));

			// 天使的祈愿
			ANGEL_DESIRE = head("angel_desire", () -> ModularCurio.builder().rarity(IRarityUtils.YELLOW).build(
					SimpleListener.hurtBonus(() ->
									CALang.Head.ANGEL_DESIRE_1.get(TextFacet.perc(CAModConfig.COMMON.head.angelDesireDamageBonus.get())),
							(p, f, c) -> p.isFallFlying(), CAModConfig.COMMON.head.angelDesireDamageBonus::get
					), new AngelDesire()
			));
		}

		// back
		{
			// 魔法箭袋
			MAGIC_ARROW_BAG = back("magic_arrow_bag", () -> ModularCurio.of(
					AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get,
							CAModConfig.COMMON.back.magicArrowBagBowStrength::get),
					AttrFacet.add(CCAttributes.ARROW_KNOCK::get,
							CAModConfig.COMMON.back.magicArrowBagArrowKnock::get)
			));
			// 火焰箭袋
			FLAME_ARROW_BAG = back("flame_arrow_bag", () -> ModularCurio.of(
					AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get,
							CAModConfig.COMMON.back.flameArrowBagBowStrength::get),
					AttrFacet.add(CCAttributes.ARROW_KNOCK::get,
							CAModConfig.COMMON.back.flameArrowBagArrowKnock::get),
					TextFacet.line(() -> CALang.Back.FLAME.get(TextFacet.num(
							CAModConfig.COMMON.back.flameArrowBagTime.get())))
			));
			// 精灵箭袋
			SPIRIT_ARROW_BAG = back("spirit_arrow_bag", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
							AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get,
									CAModConfig.COMMON.back.spiritArrowBagBowStrength::get),
							AttrFacet.add(CCAttributes.ARROW_SPEED::get,
									CAModConfig.COMMON.back.spiritArrowBagArrowSpeed::get),
							AttrFacet.add(CCAttributes.ARROW_KNOCK::get,
									CAModConfig.COMMON.back.spiritArrowBagArrowKnock::get),
							spiritSet()
					));

			// 铁剑鞘
			IRON_SCABBARD = back("iron_scabbard", () -> ModularCurio.of(
					EffectFacet.of(CCEffects.BLADE_MODIFIER::get, () -> 2, () -> 0,
							CAModConfig.COMMON.back.ironScabbardBladeInterval::get)
			));

			// 水蛭剑鞘
			LEECH_SCABBARD = back("leech_scabbard", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(CCEffects.BLADE_MODIFIER::get, () -> 3, () -> 0,
									CAModConfig.COMMON.back.leechScabbardBladeInterval::get),
							new LeechScabbard()
					));

			// 泰坦剑鞘
			TITAN_SCABBARD = back("titan_scabbard", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(CCEffects.REPLY_POWER::get, () -> 3, () -> 0,
									CAModConfig.COMMON.back.titanScabbardBladeInterval::get),
							SimpleListener.hurtBonus(
									() -> CALang.Condition.TITAN.get(TextFacet.eff(CCEffects.REPLY_POWER.get())),
									(p, t, c) -> CAAttackToken.isMelee(c) && p.hasEffect(CCEffects.BLADE_MODIFIER.get()) &&
											t.getMaxHealth() > p.getMaxHealth(),
									CAModConfig.COMMON.back.titanScabbardDamageFactor::get)
					));

			// 扭曲剑鞘
			TWISTED_SCABBARD = back("twisted_scabbard", () ->
					ModularCurio.builder().requireCS().rarity(IRarityUtils.DARK_PURPLE).loot(1).build(
							EffectFacet.of(CCEffects.BLADE_MODIFIER::get, () -> 3, () -> 0,
									CAModConfig.COMMON.back.twistedScabbardBladeInterval::get),
							AttrFacet.multBase(() -> Attributes.ATTACK_KNOCKBACK,
									CAModConfig.COMMON.back.twistedScabbardAtkKonck::get),
							AttrFacet.multBase(() -> Attributes.ATTACK_SPEED,
									CAModConfig.COMMON.back.twistedScabbardAtkSpeed::get),
							AttrFacet.multTotal(CCAttributes.REPLY_POWER::get,
									() -> -CAModConfig.COMMON.back.twistedScabbardHealRate.get()),
							TwistedScabbard.TOKEN
					));
		}

		// curses and etching
		{
			CATASTROPHE_SCROLL = curio("curios/", "catastrophe_scroll", () ->
					ModularCurio.builder().curse().immune().rarity(IRarityUtils.DARK_PURPLE).hideAttr().build(
							SlotFacet.of("etching", CAModConfig.COMMON.curse.etchingSlotSize::get),
							SlotFacet.of("charm", CAModConfig.COMMON.curse.charmSlotSize::get),
							CatastropheScroll.TOKEN
					)).tag(curio("catastrophe")).register();
			// 混沌
			CHAOTIC_ETCHING = etching("chaotic_etching", () -> ModularCurio.builder().immune().hideAttr().build());
			// 始源
			ORIGIN_ETCHING = etching("origin_etching", () -> ModularCurio.builder().immune().hideAttr().build(
					BreakSpeedFeature.simple(() -> 1 + CatastropheScroll.getOriginBonus())
			));
			// 生命
			LIFE_ETCHING = etching("life_etching", () -> ModularCurio.builder().immune().hideAttr().build());
			// 真理
			TRUTH_ETCHING = etching("truth_etching", () -> ModularCurio.builder().immune().hideAttr().build());
			// 欲望
			DESIRE_ETCHING = etching("desire_etching", () -> ModularCurio.builder().immune().fortune(1).loot(1).hideAttr().build());
			// 虚无
			NIHILITY_ETCHING = etching("nihility_etching", () -> ModularCurio.builder().immune().hideAttr().build());
			// 终焉
			END_ETCHING = etching("end_etching", () -> ModularCurio.builder().immune().hideAttr().build());
		}
	}

	// tool
	// 大地系列工具
	// 斧
	public static final ItemEntry<EarthAxe> EARTH_AXE = tool("tool/earth/", "earth_axe",
			EarthAxe::new).tag(ItemTags.AXES).register();
	// 镐
	public static final ItemEntry<EarthPickaxe> EARTH_PICKAXE = tool("tool/earth/", "earth_pickaxe",
			EarthPickaxe::new).tag(ItemTags.PICKAXES).register();
	// 铲
	public static final ItemEntry<EarthShovel> EARTH_SHOVEL = tool("tool/earth/", "earth_shovel",
			EarthShovel::new).tag(ItemTags.SHOVELS).register();
	// 锄
	public static final ItemEntry<EarthHoe> EARTH_HOE = tool("tool/earth/", "earth_hoe",
			EarthHoe::new).tag(ItemTags.HOES).register();
	// item
	// 忏悔之境
	public static final ItemEntry<RepentMirror> REPENT_MIRROR = item("items/", "repent_mirror", RepentMirror::new).register();
	// 回溯之镜
	public static final ItemEntry<BacktrackMirror> BACKTRACK_MIRROR = item("items/", "backtrack_mirror", BacktrackMirror::new).register();
	// 净化粉末
	public static final ItemEntry<PurifiedPowder> PURIFIED_POWDER = item("items/", "purified_powder", PurifiedPowder::new).register();
	// 铜加固板
	public static final ItemEntry<CopperReinforcePlate> COPPER_REINFORCE_PLATE = item("items/", "copper_reinforce_plate", CopperReinforcePlate::new).register();
	// 紫水晶加固板
	public static final ItemEntry<AmethystReinforcePlate> AMETHYST_REINFORCE_PLATE = item("items/", "amethyst_reinforce_plate", AmethystReinforcePlate::new).register();
	// 药水袋
	public static final ItemEntry<PotionsBag> POTIONS_BAG = item("items/", "potions_bag", PotionsBag::new).register();
	// 盖亚图腾
	public static final ItemEntry<GaiaTotem> GAIA_TOTEM = item("items/", "gaia_totem", GaiaTotem::new).tag(CATagGen.REQUIRE_CURSE).register();
	// 末影折跃权杖
	public static final ItemEntry<EnderJumpScepter> ENDER_JUMP_SCEPTER = item("items/", "ender_jump_scepter", EnderJumpScepter::new).register();

	// food
	// 厄运土豆 TODO unused, not obtainable
	public static final ItemEntry<UnluckyPotato> UNLUCKY_POTATO = item("food/", "unlucky_potato", UnluckyPotato::new).register();

	private static final SetTokenFacet<SpiritSet> SPIRIT_SET = new SetTokenFacet<>("spirit",
			List.of(SPIRIT_BRACELET, SPIRIT_CROWN, SPIRIT_NECKLACE, SPIRIT_ARROW_BAG),
			SpiritSet::new);

	private static final SetTokenFacet<EmeraldSet> EMERALD_SET = new SetTokenFacet<>("emerald",
			List.of(EMERALD_RING, EMERALD_NECKLACE, EMERALD_BRACELET), EmeraldSet::new);

	private static final SetTokenFacet<SeaGodSet> SEA_GOD_SET = new SetTokenFacet<>("sea_god",
			List.of(SEA_GOD_CROWN, SEA_GOD_SCROLL), SeaGodSet::new);

	public static SetTokenFacet<SpiritSet> spiritSet() {
		return SPIRIT_SET;
	}

	public static SetTokenFacet<EmeraldSet> emeraldSet() {
		return EMERALD_SET;
	}

	public static SetTokenFacet<SeaGodSet> seaGodSet() {
		return SEA_GOD_SET;
	}

	public static ItemEntry<ModularCurio> ring(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/ring/", id, factory).tag(curio("ring")).register();
	}

	public static ItemEntry<ModularCurio> charm(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/charm/", id, factory).tag(curio("charm")).register();
	}

	public static ItemEntry<ModularCurio> scroll(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/scroll/", id, factory).tag(curio("scroll")).register();
	}

	public static ItemEntry<ModularCurio> bracelet(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/bracelet/", id, factory).tag(curio("bracelet")).register();
	}

	public static ItemEntry<ModularCurio> pendant(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/pendant/", id, factory).tag(curio("pendant")).register();
	}

	public static ItemEntry<ModularCurio> necklace(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/necklace/", id, factory).tag(curio("necklace")).register();
	}

	public static ItemEntry<ModularCurio> head(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/head/", id, factory).tag(curio("head")).register();
	}

	public static ItemEntry<ModularCurio> heart(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/heart/", id, factory).tag(curio("heart")).register();
	}

	public static ItemEntry<ModularCurio> body(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/body/", id, factory).tag(curio("body")).register();
	}

	public static ItemEntry<ModularCurio> etching(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/etching/", id, factory).tag(curio("etching")).register();
	}

	public static ItemEntry<ModularCurio> back(String id, NonNullSupplier<ModularCurio> factory) {
		return curio("curios/back/", id, factory).tag(curio("back")).register();
	}

	public static ItemBuilder<ModularCurio, ?> curio(String path, String id, NonNullSupplier<ModularCurio> factory) {
		ALL_CURIOS.add(id);
		return item(path, id, factory);
	}

	public static <T extends Item> ItemBuilder<T, ?> item(String path, String id, NonNullSupplier<T> factory) {
		return CelestialArtifacts.REGISTRATE.item(id, p -> factory.get())
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/" + path + ctx.getName())));
	}

	public static <T extends Item> ItemBuilder<T, ?> tool(String path, String id, NonNullSupplier<T> factory) {
		return CelestialArtifacts.REGISTRATE.item(id, p -> factory.get())
				.model((ctx, pvd) -> pvd.handheld(ctx, pvd.modLoc("item/" + path + ctx.getName())));
	}

	private static TagKey<Item> curio(String id) {
		return ItemTags.create(new ResourceLocation(Curios.MODID, id));
	}

	public static <T extends Item> ItemEntry<T> material(String id, NonNullFunction<Item.Properties, T> factory) {
		return CelestialArtifacts.REGISTRATE.item(id, factory)
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/materials/" + ctx.getName())))
				.register();
	}

	public static void register() {

	}

}