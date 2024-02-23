package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.curios.impl.back.LeechScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.impl.back.TitanScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.impl.back.TwistedScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.impl.bracelet.SpiritBracelet;
import com.xiaoyue.celestial_artifacts.content.curios.impl.charm.SandsTalisman;
import com.xiaoyue.celestial_artifacts.content.curios.impl.curse.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.curios.impl.head.SpiritCrown;
import com.xiaoyue.celestial_artifacts.content.curios.impl.heart.DemonHeart;
import com.xiaoyue.celestial_artifacts.content.curios.impl.heart.HeartOfRevenge;
import com.xiaoyue.celestial_artifacts.content.curios.impl.heart.TwistedHeart;
import com.xiaoyue.celestial_artifacts.content.curios.impl.necklace.SpiritNecklace;
import com.xiaoyue.celestial_artifacts.content.curios.impl.pendant.ShadowPendant;
import com.xiaoyue.celestial_artifacts.content.curios.impl.set.SpiritSet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.*;
import com.xiaoyue.celestial_artifacts.content.curios.set.SetTokenFacet;
import com.xiaoyue.celestial_artifacts.content.curios.token.TokenFacet;
import com.xiaoyue.celestial_artifacts.content.items.food.UnluckyPotato;
import com.xiaoyue.celestial_artifacts.content.items.item.BacktrackMirror;
import com.xiaoyue.celestial_artifacts.content.items.item.PurifiedPowder;
import com.xiaoyue.celestial_artifacts.content.items.item.RepentMirror;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthAxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthHoe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthPickaxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthShovel;
import com.xiaoyue.celestial_artifacts.content.old.curios.bracelet.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.charm.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.head.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.necklace.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.ring.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.SeaGodScroll;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.SkywalkerScroll;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import dev.xkmc.l2damagetracker.init.L2DamageTracker;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeMod;
import top.theillusivec4.curios.Curios;

import java.util.List;

@SuppressWarnings("unused")//TODO remove to check loot gen
public class CAItems {

	// ring
	// 金戒指
	public static final ItemEntry<Item> GOLD_RING = ring("gold_ring", GoldRing::new);
	// 紫水晶戒指
	public static final ItemEntry<Item> AMETHYST_RING = ring("amethyst_ring", AmethystRing::new);
	// 钻石戒指
	public static final ItemEntry<Item> DIAMOND_RING = ring("diamond_ring", DiamondRing::new);
	// 绿宝石戒指
	public static final ItemEntry<Item> EMERALD_RING = ring("emerald_ring", EmeraldRing::new);
	// 飞行戒指
	public static final ItemEntry<Item> FLIGHT_RING = ring("flight_ring", FlightRing::new);
	// 下界合金戒指
	public static final ItemEntry<Item> NETHERITE_RING = ring("netherite_ring", NetheriteRing::new);
	// 生息之戒
	public static final ItemEntry<Item> RING_OF_LIFE = ring("ring_of_life", RingOfLife::new);
	// 雷电之戒
	public static final ItemEntry<Item> THUNDER_RING = ring("thunder_ring", ThunderRing::new);
	// 地狱之火
	public static final ItemEntry<Item> NETHER_FIRE = ring("nether_fire", NetherFire::new);
	// 冰冻之戒
	public static final ItemEntry<Item> FREEZE_RING = ring("freeze_ring", FreezeRing::new);

	// charm
	public static final ItemEntry<Item> WAR_DEAD_BADGE = charm("war_dead_badge", WarDeadBadge::new);
	// 不死者护符
	public static final ItemEntry<Item> UNDEAD_CHARM = charm("undead_charm", UndeadCharm::new);
	// 毁灭者徽章
	public static final ItemEntry<Item> DESTROYER_BADGE = charm("destroyer_badge", DestroyerBadge::new);
	// 复仇之心
	public static final ItemEntry<Item> HEART_OF_REVENGE = heart("heart_of_revenge", () ->
			ModularCurio.builder().rarity(IRarityUtils.GOLD).build(
					AttrFacet.add(L2DamageTracker.BOW_STRENGTH::get, () -> 0.06),
					new HeartOfRevenge()
			));
	// 扭曲之脑
	public static final ItemEntry<Item> TWISTED_BRAIN = charm("twisted_brain", TwistedBrain::new);
	// 扭曲之心
	public static final ItemEntry<Item> TWISTED_HEART = heart("twisted_heart", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).immune().build(
					new TokenFacet<>("twisted_heart", TwistedHeart::new)
			));

	// 噬咒护符
	public static final ItemEntry<Item> CORRUPT_BADGE = charm("corrupt_badge", CorruptBadge::new);
	// 腐化侵蚀徽章
	public static final ItemEntry<Item> CURSED_TALISMAN = charm("cursed_talisman", CursedTalisman::new);
	// 受诅咒的坚盾
	public static final ItemEntry<Item> CURSED_PROTECTOR = charm("cursed_protector", CursedProtector::new);
	// 负咒的灵魂图腾
	public static final ItemEntry<Item> CURSED_TOTEM = charm("cursed_totem", CursedTotem::new);
	// 神圣七彩护符
	public static final ItemEntry<Item> HOLY_TALISMAN = charm("holy_talisman", HolyTalisman::new);
	// 大天使之剑
	public static final ItemEntry<Item> HOLY_SWORD = charm("holy_sword", HolySword::new);
	// 天使之心
	public static final ItemEntry<Item> ANGEL_HEART = charm("angel_heart", AngelHeart::new);
	// 天使珍珠
	public static final ItemEntry<Item> ANGEL_PEARL = charm("angel_pearl", AngelPearl::new);
	// 大恶魔之咒
	public static final ItemEntry<Item> DEMON_CURSE = charm("demon_curse", DemonCurse::new);
	// 骑士庇护
	public static final ItemEntry<Item> KNIGHT_SHELTER = charm("knight_shelter", KnightShelter::new);
	// 魂灵匣
	public static final ItemEntry<Item> SOUL_BOX = charm("soul_box", SoulBox::new);
	// 太阳磁铁
	public static final ItemEntry<Item> SOLAR_MAGNET = charm("solar_magnet", SolarMagnet::new);
	// 暴食徽章
	public static final ItemEntry<Item> GLUTTONY_BADGE = charm("gluttony_badge", GluttonyBadge::new);
	// 贪婪者之心
	public static final ItemEntry<Item> GREEDY_HEART = heart("greedy_heart", () ->
			ModularCurio.builder().rarity(Rarity.EPIC).fortune(1).loot(1).build(
					XpBonusFacet.simple(2)
			));

	// 魔法马掌
	public static final ItemEntry<Item> MAGIC_HORSESHOE = charm("magic_horseshoe", MagicHorseshoe::new);
	// 生息胸花
	public static final ItemEntry<Item> BEARING_STAMEN = charm("bearing_stamen", BearingStamen::new);
	// 深渊意志徽章
	public static final ItemEntry<Item> ABYSS_WILL_BADGE = charm("abyss_will_badge", AbyssWillBadge::new);
	// 金沙护符
	public static final ItemEntry<Item> SANDS_TALISMAN = charm("sands_talisman", () ->
			ModularCurio.builder().loot(1).build(
					new SandsTalisman(),
					XpBonusFacet.simple(0.5)
			));
	// 古代殉葬品
	public static final ItemEntry<Item> SACRIFICIAL_OBJECT = charm("sacrificial_object", SacrificialObject::new);
	// 恶魔之心
	public static final ItemEntry<Item> DEMON_HEART = heart("demon_heart", () ->
			ModularCurio.builder().rarity(Rarity.EPIC).requireCS()
					.build(new TokenFacet<>("demon_heart", DemonHeart::new)));

	// scroll
	// 旅者卷轴
	public static final ItemEntry<Item> TRAVELER_SCROLL = scroll("traveler_scroll", () ->
			ModularCurio.builder().rarity(Rarity.UNCOMMON).immune().build(
					AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, () -> 0.25),
					AttrFacet.multBase(() -> Attributes.FLYING_SPEED, () -> 0.25),
					AttrFacet.multBase(ForgeMod.SWIM_SPEED, () -> 0.25),
					TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.traveler_scroll.shift2"))
			));
	// 海神卷轴
	public static final ItemEntry<Item> SEA_GOD_SCROLL = scroll("sea_god_scroll", SeaGodScroll::new);
	// 传送卷轴
	public static final ItemEntry<Item> SKYWALKER_SCROLL = scroll("skywalker_scroll", SkywalkerScroll::new);
	// 扭曲卷轴
	public static final ItemEntry<Item> TWISTED_SCROLL = scroll("twisted_scroll", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).build(
					TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.twisted_scroll.shift1"))));

	// bracelet
	// 幸运手环
	public static final ItemEntry<Item> EMERALD_BRACELET = bracelet("emerald_bracelet", EmeraldBracelet::new);
	// 生命手环
	public static final ItemEntry<Item> LIFE_BRACELET = bracelet("life_bracelet", LifeBracelet::new);
	// 珍钻手环
	public static final ItemEntry<Item> PRECIOUS_BRACELET = bracelet("precious_bracelet", PreciousBracelet::new);
	// 绯红石手环
	public static final ItemEntry<Item> RED_RUBY_BRACELET = bracelet("red_ruby_bracelet", RedRubyBracelet::new);
	// 隐匿手环
	public static final ItemEntry<Item> HIDDEN_BRACELET = bracelet("hidden_bracelet", HiddenBracelet::new);
	// 猩红手环
	public static final ItemEntry<Item> SCARLET_BRACELET = bracelet("scarlet_bracelet", ScarletBracelet::new);
	// 魅力手环
	public static final ItemEntry<Item> CHARMING_BRACELET = bracelet("charming_bracelet", CharmingBracelet::new);
	// 精灵手环
	public static final ItemEntry<Item> SPIRIT_BRACELET = bracelet("spirit_bracelet", () ->
			ModularCurio.builder().rarity(IRarityUtils.GREEN).build(
					new SpiritBracelet(), spiritSet()));

	// pendant
	// 无主的吊坠
	public static final ItemEntry<Item> UNOWNED_PENDANT = pendant("unowned_pendant", () ->
			ModularCurio.builder().rarity(Rarity.RARE).build());
	// 混沌吊坠
	public static final ItemEntry<Item> CHAOTIC_PENDANT = pendant("chaotic_pendant", () ->
			ModularCurio.builder().rarity(Rarity.EPIC).requireCS().loot(1).build(
					TextFacet.line(() -> Component.translatable("tooltip.celestial_artifacts.chaotic_pendant.shift2"))
			));
	// 怨影吊坠
	public static final ItemEntry<Item> SHADOW_PENDANT = pendant("shadow_pendant", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).requireCS().build(new ShadowPendant()));

	// necklace
	// 星星项链
	public static final ItemEntry<Item> STAR_NECKLACE = necklace("star_necklace", StarNecklace::new);
	// 十字项链
	public static final ItemEntry<Item> CROSS_NECKLACE = necklace("cross_necklace", CrossNecklace::new);
	// 疾行项链
	public static final ItemEntry<Item> GALLOP_NECKLACE = necklace("gallop_necklace", GallopNecklace::new);
	// 毒牙项链
	public static final ItemEntry<Item> FANG_NECKLACE = necklace("fang_necklace", FangNecklace::new);
	// 珍钻项链
	public static final ItemEntry<Item> PRECIOUS_NECKLACE = necklace("precious_necklace", () ->
			ModularCurio.builder().rarity(Rarity.RARE).fortune(1).build(
					AttrFacet.add(L2DamageTracker.CRIT_DMG::get, () -> 0.2),
					SlotFacet.of("charm", 1)
			));
	// 神圣项链
	public static final ItemEntry<Item> HOLY_NECKLACE = necklace("holy_necklace", HolyNecklace::new);
	// 家传项链
	public static final ItemEntry<Item> HEIRLOOM_NECKLACE = necklace("heirloom_necklace", () ->
			ModularCurio.builder().rarity(Rarity.UNCOMMON).fortune(1).build(
					AttrFacet.add(() -> Attributes.ARMOR, () -> 2),
					AttrFacet.multBase(() -> Attributes.MOVEMENT_SPEED, () -> 0.05),
					XpBonusFacet.simple(0.1)
			));
	// 绿宝石项链
	public static final ItemEntry<Item> EMERALD_NECKLACE = necklace("emerald_necklace", EmeraldNecklace::new);
	// 末影庇佑者项链
	public static final ItemEntry<Item> ENDER_PROTECTOR = necklace("ender_protector", EnderProtector::new);
	// 红心项链
	public static final ItemEntry<Item> RED_HEART_NECKLACE = necklace("red_heart_necklace", RedHeartNecklace::new);
	// 深渊之锁
	public static final ItemEntry<Item> LOCK_OF_ABYSS = necklace("lock_of_abyss", LockOfAbyss::new);
	// 精灵项链
	public static final ItemEntry<Item> SPIRIT_NECKLACE = necklace("spirit_necklace", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
					new SpiritNecklace(), spiritSet()));

	// head
	// 海神王冠
	public static final ItemEntry<Item> SEA_GOD_CROWN = head("sea_god_crown", SeaGodCrown::new);
	// 祷告者王冠
	public static final ItemEntry<Item> PRAYER_CROWN = head("prayer_crown", PrayerCrown::new);
	// 深渊意志之核
	public static final ItemEntry<Item> ABYSS_CORE = head("abyss_core", AbyssCore::new);
	// 守卫者之眼
	public static final ItemEntry<Item> GUARDIAN_EYE = head("guardian_eye", GuardianEye::new);
	// 邪恶之瞳
	public static final ItemEntry<Item> EVIL_EYE = head("evil_eye", EvilEye::new);
	// 精灵花冠
	public static final ItemEntry<Item> SPIRIT_CROWN = head("spirit_crown", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_GREEN).build(
					new SpiritCrown(), spiritSet()));

	// back
	public static final ItemEntry<Item> MAGIC_ARROW_BAG, FLAME_ARROW_BAG, SPIRIT_ARROW_BAG;
	public static final ItemEntry<Item> IRON_SCABBARD, LEECH_SCABBARD, TITAN_SCABBARD, TWISTED_SCABBARD;

	static {
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
						new TitanScabbard()
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

	// 灾厄之册
	public static final ItemEntry<Item> CATASTROPHE_SCROLL;
	// etching
	public static final ItemEntry<Item> CHAOTIC_ETCHING, ORIGIN_ETCHING, ETCHING_OF_LIFE, TRUTH_ETCHING, DESIRE_ETCHING, NIHILITY_ETCHING, END_ETCHING;

	static {
		CATASTROPHE_SCROLL = item("curios/", "catastrophe_scroll", () ->
				ModularCurio.builder().curse().immune().rarity(IRarityUtils.DARK_PURPLE).hideAttr().build(
						AttrFacet.add(CCAttributes.REPLY_POWER, () -> -0.5),
						AttrFacet.multBase(() -> Attributes.MAX_HEALTH, () -> -0.25),
						new CatastropheScroll()
				)).tag(curio("c_charm")).register();
		// 混沌
		CHAOTIC_ETCHING = etching("chaotic_etching", () -> ModularCurio.builder().immune().hideAttr().build());
		// 始源
		ORIGIN_ETCHING = etching("origin_etching", () -> ModularCurio.builder().immune().hideAttr().build(BreakSpeedFacet.simple(1.25)));
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

	private static SetTokenFacet<SpiritSet> spiritSet() {
		return SPIRIT_SET;
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