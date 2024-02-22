package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.curios.impl.back.TwistedScabbard;
import com.xiaoyue.celestial_artifacts.content.curios.modular.AttrFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.EffectFacet;
import com.xiaoyue.celestial_artifacts.content.curios.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.items.food.UnluckyPotato;
import com.xiaoyue.celestial_artifacts.content.items.item.BacktrackMirror;
import com.xiaoyue.celestial_artifacts.content.items.item.PurifiedPowder;
import com.xiaoyue.celestial_artifacts.content.items.item.RepentMirror;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthAxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthHoe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthPickaxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthShovel;
import com.xiaoyue.celestial_artifacts.content.old.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.old.curios.back.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.bracelet.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.charm.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.head.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.heart.DemonHeart;
import com.xiaoyue.celestial_artifacts.content.old.curios.heart.GreedyHeart;
import com.xiaoyue.celestial_artifacts.content.old.curios.heart.HeartOfRevenge;
import com.xiaoyue.celestial_artifacts.content.old.curios.heart.TwistedHeart;
import com.xiaoyue.celestial_artifacts.content.old.curios.necklace.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.pendant.ChaoticPendant;
import com.xiaoyue.celestial_artifacts.content.old.curios.pendant.ShadowPendant;
import com.xiaoyue.celestial_artifacts.content.old.curios.pendant.UnownedPendant;
import com.xiaoyue.celestial_artifacts.content.old.curios.ring.*;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.SeaGodScroll;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.SkywalkerScroll;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.TravelerScroll;
import com.xiaoyue.celestial_artifacts.content.old.curios.scroll.TwistedScroll;
import com.xiaoyue.celestial_core.register.CCAttributes;
import com.xiaoyue.celestial_core.register.CCEffects;
import com.xiaoyue.celestial_core.utils.IRarityUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.Curios;

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
	public static final ItemEntry<Item> HEART_OF_REVENGE = heart("heart_of_revenge", HeartOfRevenge::new);
	// 扭曲之脑
	public static final ItemEntry<Item> TWISTED_BRAIN = charm("twisted_brain", TwistedBrain::new);
	// 扭曲之心
	public static final ItemEntry<Item> TWISTED_HEART = heart("twisted_heart", TwistedHeart::new);
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
	public static final ItemEntry<Item> GREEDY_HEART = heart("greedy_heart", GreedyHeart::new);
	// 魔法马掌
	public static final ItemEntry<Item> MAGIC_HORSESHOE = charm("magic_horseshoe", MagicHorseshoe::new);
	// 生息胸花
	public static final ItemEntry<Item> BEARING_STAMEN = charm("bearing_stamen", BearingStamen::new);
	// 深渊意志徽章
	public static final ItemEntry<Item> ABYSS_WILL_BADGE = charm("abyss_will_badge", AbyssWillBadge::new);
	// 金沙护符
	public static final ItemEntry<Item> SANDS_TALISMAN = charm("sands_talisman", SandsTalisman::new);
	// 古代殉葬品
	public static final ItemEntry<Item> SACRIFICIAL_OBJECT = charm("sacrificial_object", SacrificialObject::new);
	// 恶魔之心
	public static final ItemEntry<Item> DEMON_HEART = heart("demon_heart", DemonHeart::new);

	// scroll
	// 旅者卷轴
	public static final ItemEntry<Item> TRAVELER_SCROLL = scroll("traveler_scroll", TravelerScroll::new);
	// 海神卷轴
	public static final ItemEntry<Item> SEA_GOD_SCROLL = scroll("sea_god_scroll", SeaGodScroll::new);
	// 传送卷轴
	public static final ItemEntry<Item> SKYWALKER_SCROLL = scroll("skywalker_scroll", SkywalkerScroll::new);
	// 扭曲卷轴
	public static final ItemEntry<Item> TWISTED_SCROLL = scroll("twisted_scroll", TwistedScroll::new);

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
	public static final ItemEntry<Item> SPIRIT_BRACELET = bracelet("spirit_bracelet", SpiritBracelet::new);

	// pendant
	// 无主的吊坠
	public static final ItemEntry<Item> UNOWNED_PENDANT = pendant("unowned_pendant", UnownedPendant::new);
	// 混沌吊坠
	public static final ItemEntry<Item> CHAOTIC_PENDANT = pendant("chaotic_pendant", ChaoticPendant::new);
	// 怨影吊坠
	public static final ItemEntry<Item> SHADOW_PENDANT = pendant("shadow_pendant", ShadowPendant::new);

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
	public static final ItemEntry<Item> PRECIOUS_NECKLACE = necklace("precious_necklace", PreciousNecklace::new);
	// 神圣项链
	public static final ItemEntry<Item> HOLY_NECKLACE = necklace("holy_necklace", HolyNecklace::new);
	// 家传项链
	public static final ItemEntry<Item> HEIRLOOM_NECKLACE = necklace("heirloom_necklace", HeirloomNecklace::new);
	// 绿宝石项链
	public static final ItemEntry<Item> EMERALD_NECKLACE = necklace("emerald_necklace", EmeraldNecklace::new);
	// 末影庇佑者项链
	public static final ItemEntry<Item> ENDER_PROTECTOR = necklace("ender_protector", EnderProtector::new);
	// 红心项链
	public static final ItemEntry<Item> RED_HEART_NECKLACE = necklace("red_heart_necklace", RedHeartNecklace::new);
	// 深渊之锁
	public static final ItemEntry<Item> LOCK_OF_ABYSS = necklace("lock_of_abyss", LockOfAbyss::new);
	// 精灵项链
	public static final ItemEntry<Item> SPIRIT_NECKLACE = necklace("spirit_necklace", SpiritNecklace::new);

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
	public static final ItemEntry<Item> SPIRIT_CROWN = head("spirit_crown", SpiritCrown::new);

	// back
	// 魔法箭袋
	public static final ItemEntry<Item> MAGIC_ARROW_BAG = back("magic_arrow_bag", MagicArrowBag::new);
	// 火焰箭袋
	public static final ItemEntry<Item> FLAME_ARROW_BAG = back("flame_arrow_bag", FlameArrowBag::new);
	// 精灵箭袋
	public static final ItemEntry<Item> SPIRIT_ARROW_BAG = back("spirit_arrow_bag", SpiritArrowBag::new);
	// 铁剑鞘
	public static final ItemEntry<Item> IRON_SCABBARD = back("iron_scabbard", () -> ModularCurio.of(new EffectFacet(() -> MobEffects.DAMAGE_BOOST, 2, 0, 5)));
	// 水蛭剑鞘
	public static final ItemEntry<Item> LEECH_SCABBARD = back("leech_scabbard", LeechScabbard::new);
	// 泰坦剑鞘
	public static final ItemEntry<Item> TITAN_SCABBARD = back("titan_scabbard", TitanScabbard::new);
	// 扭曲剑鞘
	public static final ItemEntry<Item> TWISTED_SCABBARD = back("twisted_scabbard", () ->
			ModularCurio.builder().rarity(IRarityUtils.DARK_PURPLE).build(
					new EffectFacet(CCEffects.BLADE_MODIFIER::get, 3, 0, 5),
					AttrFacet.multBase(() -> Attributes.ATTACK_KNOCKBACK, () -> 0.25),
					AttrFacet.multBase(() -> Attributes.ATTACK_SPEED, () -> 0.25),
					AttrFacet.multBase(CCAttributes.REPLY_POWER, () -> -0.5),
					TwistedScabbard.TOKEN
			));

	// etching
	// 混沌
	public static final ItemEntry<Item> CHAOTIC_ETCHING = etching("chaotic_etching", () -> ModularCurio.builder().immune().build());
	// 始源
	public static final ItemEntry<Item> ORIGIN_ETCHING = etching("origin_etching", () -> ModularCurio.builder().immune().build());
	// 生命
	public static final ItemEntry<Item> ETCHING_OF_LIFE = etching("etching_of_life", () -> ModularCurio.builder().immune().build());
	// 真理
	public static final ItemEntry<Item> TRUTH_ETCHING = etching("truth_etching", () -> ModularCurio.builder().immune().build());
	// 欲望
	public static final ItemEntry<Item> DESIRE_ETCHING = etching("desire_etching", () -> ModularCurio.builder().immune().build());
	// 虚无
	public static final ItemEntry<Item> NIHILITY_ETCHING = etching("nihility_etching", () -> ModularCurio.builder().immune().build());
	// 终焉
	public static final ItemEntry<Item> END_ETCHING = etching("end_etching", () -> ModularCurio.builder().immune().build());

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

	// 灾厄之册
	public static final ItemEntry<Item> CATASTROPHE_SCROLL = item("curios/", "catastrophe_scroll", CatastropheScroll::new)
			.tag(curio("c_charm")).register();

	// food
	// 厄运土豆
	public static final ItemEntry<Item> UNLUCKY_POTATO = item("food/", "unlucky_potato", UnluckyPotato::new).register();


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