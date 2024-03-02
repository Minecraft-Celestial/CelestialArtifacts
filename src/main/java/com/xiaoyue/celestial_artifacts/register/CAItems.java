package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
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
import com.xiaoyue.celestial_artifacts.content.curios.ring.*;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SeaGodScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SkywalkerScroll;
import com.xiaoyue.celestial_artifacts.content.curios.set.EmeraldSet;
import com.xiaoyue.celestial_artifacts.content.curios.set.SeaGodSet;
import com.xiaoyue.celestial_artifacts.content.curios.set.SpiritSet;
import com.xiaoyue.celestial_artifacts.content.items.food.UnluckyPotato;
import com.xiaoyue.celestial_artifacts.content.items.item.BacktrackMirror;
import com.xiaoyue.celestial_artifacts.content.items.item.PurifiedPowder;
import com.xiaoyue.celestial_artifacts.content.items.item.RepentMirror;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthAxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthHoe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthPickaxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthShovel;
import com.xiaoyue.celestial_artifacts.data.CALang;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.Curios;

import java.util.List;

public class CAItems {

	public static final ItemEntry<Item> GOLD_RING, AMETHYST_RING, DIAMOND_RING, EMERALD_RING, FLIGHT_RING, NETHERITE_RING, RING_OF_LIFE, THUNDER_RING, NETHER_FIRE, FREEZE_RING;
	public static final ItemEntry<Item> WAR_DEAD_BADGE, UNDEAD_CHARM, DESTROYER_BADGE, TWISTED_BRAIN, CORRUPT_BADGE,
			CURSED_TALISMAN, CURSED_PROTECTOR, CURSED_TOTEM, HOLY_TALISMAN, HOLY_SWORD, ANGEL_HEART,
			ANGEL_PEARL, DEMON_CURSE, KNIGHT_SHELTER, SOUL_BOX, SOLAR_MAGNET, GLUTTONY_BADGE, MAGIC_HORSESHOE,
			BEARING_STAMEN, ABYSS_WILL_BADGE, SANDS_TALISMAN, SACRIFICIAL_OBJECT;
	public static final ItemEntry<Item> HEART_OF_REVENGE, TWISTED_HEART, GREEDY_HEART, DEMON_HEART;
	public static final ItemEntry<Item> TRAVELER_SCROLL, SEA_GOD_SCROLL, SKYWALKER_SCROLL, TWISTED_SCROLL;
	public static final ItemEntry<Item> EMERALD_BRACELET, LIFE_BRACELET, PRECIOUS_BRACELET, RED_RUBY_BRACELET, HIDDEN_BRACELET, SCARLET_BRACELET, CHARMING_BRACELET, SPIRIT_BRACELET;
	public static final ItemEntry<Item> UNOWNED_PENDANT, CHAOTIC_PENDANT, SHADOW_PENDANT;
	public static final ItemEntry<Item> STAR_NECKLACE, CROSS_NECKLACE, GALLOP_NECKLACE, FANG_NECKLACE, PRECIOUS_NECKLACE,
			HOLY_NECKLACE, HEIRLOOM_NECKLACE, EMERALD_NECKLACE, ENDER_PROTECTOR, RED_HEART_NECKLACE, LOCK_OF_ABYSS, SPIRIT_NECKLACE;
	public static final ItemEntry<Item> SEA_GOD_CROWN, PRAYER_CROWN, ABYSS_CORE, GUARDIAN_EYE, EVIL_EYE, SPIRIT_CROWN;
	public static final ItemEntry<Item> MAGIC_ARROW_BAG, FLAME_ARROW_BAG, SPIRIT_ARROW_BAG, IRON_SCABBARD, LEECH_SCABBARD, TITAN_SCABBARD, TWISTED_SCABBARD;
	public static final ItemEntry<Item> CATASTROPHE_SCROLL, CHAOTIC_ETCHING, ORIGIN_ETCHING, ETCHING_OF_LIFE, TRUTH_ETCHING, DESIRE_ETCHING, NIHILITY_ETCHING, END_ETCHING;

	static {

		// ring
		{
			// 金戒指
			GOLD_RING = ring("gold_ring", () ->
					ModularCurio.of(EffectFacet.of(() -> MobEffects.LUCK, 2, 0)));
			// 紫水晶戒指
			AMETHYST_RING = ring("amethyst_ring", () ->
					ModularCurio.of(EffectFacet.of(() -> MobEffects.NIGHT_VISION, 2, 0),
							AttrFacet.multBase(() -> Attributes.ATTACK_DAMAGE, () -> 0.1)));
			// 钻石戒指
			DIAMOND_RING = ring("diamond_ring", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 2, 0)));
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
							EffectFacet.of(() -> MobEffects.FIRE_RESISTANCE, 10, 0),
							SimpleListener.protect(
									CALang.Condition.NETHER::get,
									(p, a, c) -> p.level().dimension().equals(Level.NETHER),
									() -> 0.1
							)
					));
			// 生息之戒
			RING_OF_LIFE = ring("ring_of_life", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(new RingOfLife()));
			// 雷电之戒
			THUNDER_RING = ring("thunder_ring", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(new ThunderRing()));
			// 地狱之火
			NETHER_FIRE = ring("nether_fire", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(new NetherFire()));
			// 冰冻之戒
			FREEZE_RING = ring("freeze_ring", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(new FreezeRing()));
		}

		// charms
		{
			//
			WAR_DEAD_BADGE = charm("war_dead_badge", () -> ModularCurio.builder()
					.requireCS().rarity(IRarityUtils.DARK_PURPLE)
					.build(new TokenFacet<>("war_dead_badge", WarDeadBadge::new)));
			// 不死者护符
			UNDEAD_CHARM = charm("undead_charm", () -> ModularCurio.builder()
					.rarity(Rarity.RARE).build(new UndeadCharm()));
			// 毁灭者徽章
			DESTROYER_BADGE = charm("destroyer_badge", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(
							AttrFacet.add(() -> Attributes.ATTACK_DAMAGE, () -> 0.4),
							AttrFacet.multTotal(L2DamageTracker.REDUCTION, () -> 0.5),
							SimpleListener.hurtBonus(
									() -> CALang.Condition.LOW_HEALTH.get(TextFacet.perc(0.5)),
									(p, t, c) -> p.getHealth() <= 0.5 * p.getMaxHealth(),
									() -> 0.2)));
			// 扭曲之脑
			TWISTED_BRAIN = charm("twisted_brain", () -> ModularCurio.builder()
					.requireCS().rarity(Rarity.EPIC).build(new TwistedBrain()));
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
					ModularCurio.builder().rarity(Rarity.EPIC).build(
							AttrFacet.add(() -> Attributes.KNOCKBACK_RESISTANCE, () -> 1),
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
							AttrFacet.add(L2DamageTracker.CRIT_RATE::get, () -> 0.15),
							new HolySword()
					));
			// 天使之心
			ANGEL_HEART = charm("angel_heart", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new AngelHeart(),
							SimpleListener.protect(
									() -> CALang.Condition.LOW_HEALTH.get(TextFacet.perc(0.5)),
									(p, a, c) -> p.getHealth() < p.getMaxHealth() * 0.5f,
									() -> 0.1
							)
					));
			// 天使珍珠
			ANGEL_PEARL = charm("angel_pearl", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							new TokenFacet<>("angel_pearl", AngelPearl::new)
					));
			// 大恶魔之咒
			DEMON_CURSE = charm("demon_curse", () -> ModularCurio.builder()
					.rarity(IRarityUtils.DARK_PURPLE).build(
							AttrFacet.multTotal(CCAttributes.REPLY_POWER, () -> -0.9),
							new TokenFacet<>("demon_curse", DemonCurse::new)));
			// 骑士庇护
			KNIGHT_SHELTER = charm("knight_shelter", () -> ModularCurio.builder().rarity(Rarity.UNCOMMON)
					.build(
							AttrFacet.add(() -> Attributes.ARMOR, () -> 8),
							AttrFacet.add(L2DamageTracker.ABSORB, () -> 4),
							new KnightShelter()));
			// 魂灵匣
			SOUL_BOX = charm("soul_box", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(new SoulBox()));
			// 太阳磁铁
			SOLAR_MAGNET = charm("solar_magnet", () -> ModularCurio.builder().rarity(Rarity.RARE)
					.build(new SolarMagnet(), SimpleListener.hurtBonus(
							CALang.Condition.DAY::get, (p, t, c) -> p.level().isDay(), () -> 0.25
					)));
			// 暴食徽章
			GLUTTONY_BADGE = charm("gluttony_badge", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(
							EffectFacet.of(() -> MobEffects.HUNGER, 3, 1),
							new GluttonyBadge()
					));

			// 魔法马掌
			MAGIC_HORSESHOE = charm("magic_horseshoe", () -> ModularCurio.builder()
					.rarity(Rarity.EPIC).build(
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, () -> 0.25),
							AttrFacet.add(() -> Attributes.LUCK, () -> 1),
							SimpleListener.protectType(CALang.DamageType.FALL::get,
									e -> e.is(DamageTypes.FALL), () -> 0.95)
					));

			// 生息胸花
			BEARING_STAMEN = charm("bearing_stamen", () -> ModularCurio.builder().rarity(IRarityUtils.GREEN)
					.build(
							AttrFacet.add(() -> Attributes.MAX_HEALTH, () -> 20),
							AttrFacet.add(CCAttributes.REPLY_POWER, () -> 0.25),
							EffectFacet.of(() -> MobEffects.REGENERATION, 2, 1)
					));
			// 深渊意志徽章
			ABYSS_WILL_BADGE = charm("abyss_will_badge", () -> ModularCurio.builder().rarity(IRarityUtils.DARK_AQUA)
					.build(AbyssWillBadge.TOKEN));
			// 金沙护符
			SANDS_TALISMAN = charm("sands_talisman", () ->
					ModularCurio.builder().loot(1).build(
							SimpleListener.hurtBonus(
									CALang.Condition.HOT_REGION::get,
									(p, t, c) -> p.level().getBiome(p.blockPosition()).get().getBaseTemperature() >= 0.01,
									() -> 0.3),
							XpBonusFeature.simple(0.5)
					));
			// 古代殉葬品
			SACRIFICIAL_OBJECT = charm("sacrificial_object", () -> ModularCurio.builder().rarity(Rarity.EPIC)
					.loot(1).build(AttrFacet.multTotal(L2DamageTracker.REDUCTION, () -> -0.05),
							new SacrificialObject()));
		}

		// heart
		{
			// 复仇之心
			HEART_OF_REVENGE = heart("heart_of_revenge", () ->
					ModularCurio.builder().rarity(IRarityUtils.GOLD).build(
							AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.06),
							HurtPlayerEffectFacet.of(
									EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 3, 1),
									EffectFacet.of(() -> MobEffects.DAMAGE_RESISTANCE, 3, 0)
							),
							SimpleListener.hurtBonus(
									() -> CALang.Condition.REVENGE.get(TextFacet.num(3)),
									(p, t, c) -> p.getLastHurtByMobTimestamp() >= p.tickCount - 3 * 20,
									() -> 0.25
							)
					));

			// 扭曲之心
			TWISTED_HEART = heart("twisted_heart", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).immune().build(
							new TokenFacet<>("twisted_heart", TwistedHeart::new)
					));

			// 贪婪者之心
			GREEDY_HEART = heart("greedy_heart", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).fortune(1).loot(1).build(
							XpBonusFeature.simple(2)
					));
			// 恶魔之心
			DEMON_HEART = heart("demon_heart", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).requireCS()
							.build(new TokenFacet<>("demon_heart", DemonHeart::new)));
		}

		// scroll
		{
			// 旅者卷轴
			TRAVELER_SCROLL = scroll("traveler_scroll", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).immune().build(
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, () -> 0.25),
							AttrFacet.multBase(() -> Attributes.FLYING_SPEED, () -> 0.25),
							AttrFacet.multBase(ForgeMod.SWIM_SPEED, () -> 0.25),
							TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.traveler_scroll.shift2"))
					));
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
			SKYWALKER_SCROLL = scroll("skywalker_scroll", () -> ModularCurio.builder().rarity(Rarity.UNCOMMON)
					.build(SkywalkerScroll.TOKEN));
			// 扭曲卷轴
			TWISTED_SCROLL = scroll("twisted_scroll", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).build(
							TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.twisted_scroll.shift1"))));
		}

		// bracelet
		{
			// 幸运手环
			EMERALD_BRACELET = bracelet("emerald_bracelet", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
							AttrFacet.add(L2DamageTracker.CRIT_RATE::get, () -> 0.1),
							HurtPlayerEffectFacet.of(
									e -> e.getLuck() >= 2,
									() -> CALang.Condition.LUCK.get(TextFacet.num(2)),
									() -> 0.5,
									EffectFacet.of(() -> MobEffects.ABSORPTION, 5, 1)),
							emeraldSet()));
			// 生命手环
			LIFE_BRACELET = bracelet("life_bracelet", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.add(CCAttributes.REPLY_POWER, () -> 0.15),
							EffectFacet.of(() -> MobEffects.REGENERATION, 2, 0)
					));
			// 珍钻手环
			PRECIOUS_BRACELET = bracelet("precious_bracelet", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).build(
							AttrFacet.add(ForgeMod.BLOCK_REACH, () -> 2),
							SlotFacet.of("ring", 1),
							SimpleListener.protectType(CALang.DamageType.MAGIC::get, e -> e.is(DamageTypes.MAGIC), () -> 0.4)
					));
			// 绯红石手环
			RED_RUBY_BRACELET = bracelet("red_ruby_bracelet", () ->
					ModularCurio.builder().rarity(IRarityUtils.RED).build(
							SimpleListener.protectType(CALang.DamageType.FIRE::get, e -> e.is(DamageTypeTags.IS_FIRE), () -> 0.9),
							HurtPlayerEffectFacet.ofType(e -> e.is(DamageTypeTags.IS_FIRE), CALang.Condition.HURT_FIRE::get,
									EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 3, 0))
					));


			// 隐匿手环
			HIDDEN_BRACELET = bracelet("hidden_bracelet", () -> ModularCurio.builder()
					.rarity(IRarityUtils.DARK_PURPLE).build(new HiddenBracelet()));
			// 猩红手环
			SCARLET_BRACELET = bracelet("scarlet_bracelet", () -> ModularCurio.builder()
					.rarity(IRarityUtils.RED).build(new ScarletBracelet()));
			// 魅力手环
			CHARMING_BRACELET = bracelet("charming_bracelet", () -> ModularCurio.builder()
					.rarity(Rarity.RARE).build(
							AttrFacet.add(() -> Attributes.ARMOR, () -> 2),
							new CharmingBracelet()
					));
			// 精灵手环
			SPIRIT_BRACELET = bracelet("spirit_bracelet", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
							new SpiritBracelet(), spiritSet()));
		}

		// pendant
		{
			// 无主的吊坠
			UNOWNED_PENDANT = pendant("unowned_pendant", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build());
			// 混沌吊坠
			CHAOTIC_PENDANT = pendant("chaotic_pendant", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).requireCS().loot(1).build(
							TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.chaotic_pendant.shift2"))
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
									(p, t, c) -> EntityUtils.isLookingBehindTarget(t, p.getEyePosition()),
									() -> 0.4),
							ConditionalEffectFacet.of(false,
									e -> e.level().isNight(), CALang.Condition.NIGHT::get,
									EffectFacet.of(() -> MobEffects.DAMAGE_RESISTANCE, 2, 0))
					));

			// 十字项链
			CROSS_NECKLACE = necklace("cross_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(InvulToken.of(() -> 8)));

			// 疾行项链
			GALLOP_NECKLACE = necklace("gallop_necklace", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.add(() -> Attributes.MOVEMENT_SPEED, () -> 0.08),
							new GallopNecklace()
					));

			// 毒牙项链
			FANG_NECKLACE = necklace("fang_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							AttrFacet.multBase(() -> Attributes.ATTACK_SPEED, () -> 0.1),
							SimpleListener.hurtBonus(
									CALang.Condition.TARGET_HAS_ARMOR::get,
									(p, t, c) -> EntityUtils.hasArmor(t),
									() -> 0.25),
							HurtTargetEffectFacet.of(() -> 0.5,
									() -> MobEffects.POISON, 100, 2)
					));

			// 珍钻项链
			PRECIOUS_NECKLACE = necklace("precious_necklace", () ->
					ModularCurio.builder().rarity(Rarity.RARE).fortune(1).build(
							AttrFacet.add(L2DamageTracker.CRIT_DMG::get, () -> 0.2),
							SlotFacet.of("charm", 1)
					));
			// 神圣项链
			HOLY_NECKLACE = necklace("holy_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							AttrFacet.add(() -> Attributes.MAX_HEALTH, () -> 4),
							InvulToken.of(() -> 5),
							new HolyNecklace()
					));

			// 家传项链
			HEIRLOOM_NECKLACE = necklace("heirloom_necklace", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).fortune(1).build(
							AttrFacet.add(() -> Attributes.ARMOR, () -> 2),
							AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, () -> 0.05),
							XpBonusFeature.simple(0.1)
					));
			// 绿宝石项链
			EMERALD_NECKLACE = necklace("emerald_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).fortune(1).build(
							new EmeraldNecklace(), emeraldSet()));
			// 末影庇佑者项链
			ENDER_PROTECTOR = necklace("ender_protector", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).enderMask().build(
							AttrFacet.add(() -> Attributes.ARMOR_TOUGHNESS, () -> 4),
							new EnderProtector()
					));
			// 红心项链
			RED_HEART_NECKLACE = necklace("red_heart_necklace", () ->
					ModularCurio.of(AttrFacet.multBase(() -> Attributes.MAX_HEALTH, () -> 0.05)));
			// 深渊之锁
			LOCK_OF_ABYSS = necklace("lock_of_abyss", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_AQUA).build(new LockOfAbyss()));
			// 精灵项链
			SPIRIT_NECKLACE = necklace("spirit_necklace", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.25),
							HurtPlayerEffectFacet.of(EffectFacet.of(() -> MobEffects.MOVEMENT_SPEED, 5, 0)),
							spiritSet()));
		}

		// head
		{
			// 海神王冠
			SEA_GOD_CROWN = head("sea_god_crown", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							new SeaGodCrown(),
							ConditionalEffectFacet.of(false,
									Player::isInWaterRainOrBubble,
									CALang.Condition.PLAYER_WET::get,
									EffectFacet.of(() -> MobEffects.WATER_BREATHING, 2, 3),
									EffectFacet.of(() -> MobEffects.NIGHT_VISION, 2, 0)
							), seaGodSet()));
			// 祷告者王冠
			PRAYER_CROWN = head("prayer_crown", () ->
					ModularCurio.builder().rarity(Rarity.UNCOMMON).build(
							InvulToken.of(() -> 10),
							new PrayerCrown()));
			// 深渊意志之核
			ABYSS_CORE = head("abyss_core", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_AQUA).build(new AbyssCore()));
			// 守卫者之眼
			GUARDIAN_EYE = head("guardian_eye", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							AttrFacet.multBase(ForgeMod.SWIM_SPEED, () -> 0.15),
							new GuardianEye()));
			// 邪恶之瞳
			EVIL_EYE = head("evil_eye", () ->
					ModularCurio.builder().rarity(Rarity.EPIC).build(new EvilEye()));
			// 精灵花冠
			SPIRIT_CROWN = head("spirit_crown", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
							new SpiritCrown(), spiritSet()));
		}

		// back
		{
			// 魔法箭袋
			MAGIC_ARROW_BAG = back("magic_arrow_bag", () -> ModularCurio.of(
					AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.1),
					AttrFacet.add(CCAttributes.ARROW_KNOCK, () -> 0.08)
			));
			// 火焰箭袋
			FLAME_ARROW_BAG = back("flame_arrow_bag", () -> ModularCurio.of(
					AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.12),
					AttrFacet.add(CCAttributes.ARROW_KNOCK, () -> 0.1),
					TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.flame_arrow_bag.shift2"))
			));
			// 精灵箭袋
			SPIRIT_ARROW_BAG = back("spirit_arrow_bag", () ->
					ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
							AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.18),
							AttrFacet.add(CCAttributes.ARROW_SPEED, () -> 0.5),
							AttrFacet.add(CCAttributes.ARROW_KNOCK, () -> 1),
							spiritSet()
					));

			// 铁剑鞘
			IRON_SCABBARD = back("iron_scabbard", () -> ModularCurio.of(
					EffectFacet.of(() -> MobEffects.DAMAGE_BOOST, 2, 0, 5)
			));

			// 水蛭剑鞘
			LEECH_SCABBARD = back("leech_scabbard", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(CCEffects.BLADE_MODIFIER::get, 3, 0, 10),
							new LeechScabbard()
					));

			// 泰坦剑鞘
			TITAN_SCABBARD = back("titan_scabbard", () ->
					ModularCurio.builder().rarity(Rarity.RARE).build(
							EffectFacet.of(CCEffects.BLADE_MODIFIER::get, 3, 0, 10),
							SimpleListener.hurtBonus(
									() -> CALang.Condition.TITAN.get(TextFacet.eff(CCEffects.BLADE_MODIFIER.get())),
									(p, t, c) -> CAAttackToken.isMelee(c) && p.hasEffect(CCEffects.BLADE_MODIFIER.get()) &&
											t.getMaxHealth() > p.getMaxHealth(),
									() -> 0.75)
					));

			// 扭曲剑鞘
			TWISTED_SCABBARD = back("twisted_scabbard", () ->
					ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).build(
							EffectFacet.of(CCEffects.BLADE_MODIFIER::get, 3, 0, 5),
							AttrFacet.multBase(() -> Attributes.ATTACK_KNOCKBACK, () -> 1),
							AttrFacet.multBase(() -> Attributes.ATTACK_SPEED, () -> 0.25),
							AttrFacet.multTotal(CCAttributes.REPLY_POWER, () -> -0.5),
							TwistedScabbard.TOKEN
					));
		}

		// curses and etching
		{
			CATASTROPHE_SCROLL = item("curios/", "catastrophe_scroll", () ->
					ModularCurio.builder().curse().immune().rarity(IRarityUtils.DARK_PURPLE).hideAttr().build(
							AttrFacet.add(CCAttributes.REPLY_POWER, () -> -0.5),
							AttrFacet.multBase(() -> Attributes.MAX_HEALTH, () -> -0.25),
							SlotFacet.of("etching", 7),
							new CatastropheScroll()
					)).tag(curio("c_charm")).register();
			// 混沌
			CHAOTIC_ETCHING = etching("chaotic_etching", () -> ModularCurio.builder().immune().hideAttr().build());
			// 始源
			ORIGIN_ETCHING = etching("origin_etching", () -> ModularCurio.builder().immune().hideAttr().build(BreakSpeedFeature.simple(1.25)));
			// 生命
			ETCHING_OF_LIFE = etching("etching_of_life", () -> ModularCurio.builder().immune().hideAttr().build(
					AttrFacet.add(CCAttributes.REPLY_POWER, () -> 0.6),
					AttrFacet.multBase(() -> Attributes.MAX_HEALTH, () -> 0.45)
			));
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
	public static final ItemEntry<Item> EARTH_AXE = item("tool/earth/", "earth_axe", EarthAxe::new)
			.tag(ItemTags.AXES).register();
	// 镐
	public static final ItemEntry<Item> EARTH_PICKAXE = item("tool/earth/", "earth_pickaxe", EarthPickaxe::new)
			.tag(ItemTags.PICKAXES).register();
	// 铲
	public static final ItemEntry<Item> EARTH_SHOVEL = item("tool/earth/", "earth_shovel", EarthShovel::new)
			.tag(ItemTags.SHOVELS).register();
	// 锄
	public static final ItemEntry<Item> EARTH_HOE = item("tool/earth/", "earth_hoe", EarthHoe::new)
			.tag(ItemTags.HOES).register();

	// item
	// 忏悔之境
	public static final ItemEntry<Item> REPENT_MIRROR = item("items/", "repent_mirror", RepentMirror::new).register();
	// 回溯之镜
	public static final ItemEntry<Item> BACKTRACK_MIRROR = item("items/", "backtrack_mirror", BacktrackMirror::new).register();
	// 净化粉末
	public static final ItemEntry<Item> PURIFIED_POWDER = item("items/", "purified_powder", PurifiedPowder::new).register();

	// food
	// 厄运土豆
	public static final ItemEntry<Item> UNLUCKY_POTATO = item("food/", "unlucky_potato", UnluckyPotato::new).register();

	private static final SetTokenFacet<SpiritSet> SPIRIT_SET = new SetTokenFacet<>("spirit",
			List.of(SPIRIT_BRACELET, SPIRIT_CROWN, SPIRIT_NECKLACE, SPIRIT_ARROW_BAG),
			SpiritSet::new);

	private static final SetTokenFacet<EmeraldSet> EMERALD_SET = new SetTokenFacet<>("emerald",
			List.of(EMERALD_RING, EMERALD_NECKLACE, EMERALD_BRACELET), EmeraldSet::new);

	private static final SetTokenFacet<SeaGodSet> SEA_GOD_SET = new SetTokenFacet<>("sea_god",
			List.of(SEA_GOD_CROWN, SEA_GOD_SCROLL), SeaGodSet::new);

	private static SetTokenFacet<SpiritSet> spiritSet() {
		return SPIRIT_SET;
	}

	private static SetTokenFacet<EmeraldSet> emeraldSet() {
		return EMERALD_SET;
	}

	private static SetTokenFacet<SeaGodSet> seaGodSet() {
		return SEA_GOD_SET;
	}

	public static ItemEntry<Item> ring(String id, NonNullSupplier<Item> factory) {
		return item("curios/ring/", id, factory).tag(curio("ring")).register();
	}

	public static ItemEntry<Item> charm(String id, NonNullSupplier<Item> factory) {
		return item("curios/charm/", id, factory).tag(curio("charm")).register();
	}

	public static ItemEntry<Item> scroll(String id, NonNullSupplier<Item> factory) {
		return item("curios/scroll/", id, factory).tag(curio("scroll")).register();
	}

	public static ItemEntry<Item> bracelet(String id, NonNullSupplier<Item> factory) {
		return item("curios/bracelet/", id, factory).tag(curio("bracelet")).register();
	}

	public static ItemEntry<Item> pendant(String id, NonNullSupplier<Item> factory) {
		return item("curios/pendant/", id, factory).tag(curio("pendant")).register();
	}

	public static ItemEntry<Item> necklace(String id, NonNullSupplier<Item> factory) {
		return item("curios/necklace/", id, factory).tag(curio("necklace")).register();
	}

	public static ItemEntry<Item> head(String id, NonNullSupplier<Item> factory) {
		return item("curios/head/", id, factory).tag(curio("head")).register();
	}

	public static ItemEntry<Item> heart(String id, NonNullSupplier<Item> factory) {
		return item("curios/heart/", id, factory).tag(curio("heart")).register();
	}

	public static ItemEntry<Item> etching(String id, NonNullSupplier<Item> factory) {
		return item("curios/etching/", id, factory).tag(curio("etching")).register();
	}

	public static ItemEntry<Item> back(String id, NonNullSupplier<Item> factory) {
		return item("curios/back/", id, factory).tag(curio("back")).register();
	}

	public static ItemBuilder<Item, ?> item(String path, String id, NonNullSupplier<Item> factory) {
		return CelestialArtifacts.REGISTRATE.item(id, p -> factory.get())
				.model((ctx, pvd) -> pvd.generated(ctx, pvd.modLoc("item/" + path + ctx.getName())));
	}

	private static TagKey<Item> curio(String id) {
		return ItemTags.create(new ResourceLocation(Curios.MODID, id));
	}

	public static void register() {

	}

}