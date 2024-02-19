package com.xiaoyue.celestial_artifacts.register;

import com.xiaoyue.celestial_artifacts.content.curios.CatastropheScroll;
import com.xiaoyue.celestial_artifacts.content.curios.back.*;
import com.xiaoyue.celestial_artifacts.content.curios.bracelet.*;
import com.xiaoyue.celestial_artifacts.content.curios.charm.*;
import com.xiaoyue.celestial_artifacts.content.curios.etching.*;
import com.xiaoyue.celestial_artifacts.content.curios.head.*;
import com.xiaoyue.celestial_artifacts.content.curios.heart.DemonHeart;
import com.xiaoyue.celestial_artifacts.content.curios.heart.GreedyHeart;
import com.xiaoyue.celestial_artifacts.content.curios.heart.HeartOfRevenge;
import com.xiaoyue.celestial_artifacts.content.curios.heart.TwistedHeart;
import com.xiaoyue.celestial_artifacts.content.curios.necklace.*;
import com.xiaoyue.celestial_artifacts.content.curios.pendant.ChaoticPendant;
import com.xiaoyue.celestial_artifacts.content.curios.pendant.ShadowPendant;
import com.xiaoyue.celestial_artifacts.content.curios.pendant.UnownedPendant;
import com.xiaoyue.celestial_artifacts.content.curios.ring.*;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SeaGodScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.SkywalkerScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.TravelerScroll;
import com.xiaoyue.celestial_artifacts.content.curios.scroll.TwistedScroll;
import com.xiaoyue.celestial_artifacts.content.items.TestOneItem;
import com.xiaoyue.celestial_artifacts.content.items.food.UnluckyPotato;
import com.xiaoyue.celestial_artifacts.content.items.item.BacktrackMirror;
import com.xiaoyue.celestial_artifacts.content.items.item.PurifiedPowder;
import com.xiaoyue.celestial_artifacts.content.items.item.RepentMirror;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthAxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthHoe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthPickaxe;
import com.xiaoyue.celestial_artifacts.content.items.tool.EarthShovel;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_artifacts.CelestialArtifacts.MODID;

public class CAItems {

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS,MODID);

    // ring
    // 金戒指
    public static final RegistryObject<Item> GOLD_RING = ITEM.register("gold_ring", GoldRing::new);
    // 紫水晶戒指
    public static final RegistryObject<Item> AMETHYST_RING = ITEM.register("amethyst_ring", AmethystRing::new);
    // 钻石戒指
    public static final RegistryObject<Item> DIAMOND_RING = ITEM.register("diamond_ring", DiamondRing::new);
    // 绿宝石戒指
    public static final RegistryObject<Item> EMERALD_RING = ITEM.register("emerald_ring", EmeraldRing::new);
    // 飞行戒指
    public static final RegistryObject<Item> FLIGHT_RING = ITEM.register("flight_ring", FlightRing::new);
    // 下界合金戒指
    public static final RegistryObject<Item> NETHERITE_RING = ITEM.register("netherite_ring", NetheriteRing::new);
    // 生息之戒
    public static final RegistryObject<Item> RING_OF_LIFE = ITEM.register("ring_of_life", RingOfLife::new);
    // 雷电之戒
    public static final RegistryObject<Item> THUNDER_RING = ITEM.register("thunder_ring", ThunderRing::new);
    // 地狱之火
    public static final RegistryObject<Item> NETHER_FIRE = ITEM.register("nether_fire", NetherFire::new);
    // 冰冻之戒
    public static final RegistryObject<Item> FREEZE_RING = ITEM.register("freeze_ring", FreezeRing::new);

    // charm
    public static final RegistryObject<Item> WAR_DEAD_BADGE = ITEM.register("war_dead_badge", WarDeadBadge::new);
    // 不死者护符
    public static final RegistryObject<Item> UNDEAD_CHARM = ITEM.register("undead_charm", UndeadCharm::new);
    // 毁灭者徽章
    public static final RegistryObject<Item> DESTROYER_BADGE = ITEM.register("destroyer_badge", DestroyerBadge::new);
    // 复仇之心
    public static final RegistryObject<Item> HEART_OF_REVENGE = ITEM.register("heart_of_revenge", HeartOfRevenge::new);
    // 扭曲之脑
    public static final RegistryObject<Item> TWISTED_BRAIN = ITEM.register("twisted_brain", TwistedBrain::new);
    // 扭曲之心
    public static final RegistryObject<Item> TWISTED_HEART = ITEM.register("twisted_heart", TwistedHeart::new);
    // 噬咒护符
    public static final RegistryObject<Item> CORRUPT_BADGE = ITEM.register("corrupt_badge", CorruptBadge::new);
    // 腐化侵蚀徽章
    public static final RegistryObject<Item> CURSED_TALISMAN = ITEM.register("cursed_talisman", CursedTalisman::new);
    // 受诅咒的坚盾
    public static final RegistryObject<Item> CURSED_PROTECTOR = ITEM.register("cursed_protector", CursedProtector::new);
    // 负咒的灵魂图腾
    public static final RegistryObject<Item> CURSED_TOTEM = ITEM.register("cursed_totem", CursedTotem::new);
    // 神圣七彩护符
    public static final RegistryObject<Item> HOLY_TALISMAN = ITEM.register("holy_talisman", HolyTalisman::new);
    // 大天使之剑
    public static final RegistryObject<Item> HOLY_SWORD = ITEM.register("holy_sword", HolySword::new);
    // 天使之心 
    public static final RegistryObject<Item> ANGEL_HEART = ITEM.register("angel_heart", AngelHeart::new);
    // 天使珍珠
    public static final RegistryObject<Item> ANGEL_PEARL = ITEM.register("angel_pearl", AngelPearl::new);
    // 大恶魔之咒
    public static final RegistryObject<Item> DEMON_CURSE = ITEM.register("demon_curse", DemonCurse::new);
    // 骑士庇护
    public static final RegistryObject<Item> KNIGHT_SHELTER = ITEM.register("knight_shelter", KnightShelter::new);
    // 魂灵匣 
    public static final RegistryObject<Item> SOUL_BOX = ITEM.register("soul_box", SoulBox::new);
    // 太阳磁铁
    public static final RegistryObject<Item> SOLAR_MAGNET = ITEM.register("solar_magnet", SolarMagnet::new);
    // 暴食徽章
    public static final RegistryObject<Item> GLUTTONY_BADGE = ITEM.register("gluttony_badge", GluttonyBadge::new);
    // 贪婪者之心 
    public static final RegistryObject<Item> GREEDY_HEART = ITEM.register("greedy_heart", GreedyHeart::new);
    // 魔法马掌 
    public static final RegistryObject<Item> MAGIC_HORSESHOE = ITEM.register("magic_horseshoe", MagicHorseshoe::new);
    // 生息胸花
    public static final RegistryObject<Item> BEARING_STAMEN = ITEM.register("bearing_stamen", BearingStamen::new);
    // 深渊意志徽章
    public static final RegistryObject<Item> ABYSS_WILL_BADGE = ITEM.register("abyss_will_badge", AbyssWillBadge::new);
    // 金沙护符
    public static final RegistryObject<Item> SANDS_TALISMAN = ITEM.register("sands_talisman", SandsTalisman::new);
    // 古代殉葬品 
    public static final RegistryObject<Item> SACRIFICIAL_OBJECT = ITEM.register("sacrificial_object", SacrificialObject::new);
    // 恶魔之心
    public static final RegistryObject<Item> DEMON_HEART = ITEM.register("demon_heart", DemonHeart::new);

    // scroll
    // 旅者卷轴
    public static final RegistryObject<Item> TRAVELER_SCROLL = ITEM.register("traveler_scroll", TravelerScroll::new);
    // 海神卷轴
    public static final RegistryObject<Item> SEA_GOD_SCROLL = ITEM.register("sea_god_scroll", SeaGodScroll::new);
    // 传送卷轴
    public static final RegistryObject<Item> SKYWALKER_SCROLL = ITEM.register("skywalker_scroll", SkywalkerScroll::new);
    // 扭曲卷轴
    public static final RegistryObject<Item> TWISTED_SCROLL = ITEM.register("twisted_scroll", TwistedScroll::new);

    // bracelet
    // 幸运手环 
    public static final RegistryObject<Item> EMERALD_BRACELET = ITEM.register("emerald_bracelet", EmeraldBracelet::new);
    // 生命手环 
    public static final RegistryObject<Item> LIFE_BRACELET = ITEM.register("life_bracelet", LifeBracelet::new);
    // 珍钻手环
    public static final RegistryObject<Item> PRECIOUS_BRACELET = ITEM.register("precious_bracelet", PreciousBracelet::new);
    // 绯红石手环 
    public static final RegistryObject<Item> RED_RUBY_BRACELET = ITEM.register("red_ruby_bracelet", RedRubyBracelet::new);
    // 隐匿手环
    public static final RegistryObject<Item> HIDDEN_BRACELET = ITEM.register("hidden_bracelet", HiddenBracelet::new);
    // 猩红手环 
    public static final RegistryObject<Item> SCARLET_BRACELET = ITEM.register("scarlet_bracelet", ScarletBracelet::new);
    // 魅力手环 
    public static final RegistryObject<Item> CHARMING_BRACELET = ITEM.register("charming_bracelet", CharmingBracelet::new);
    // 精灵手环
    public static final RegistryObject<Item> SPIRIT_BRACELET = ITEM.register("spirit_bracelet", SpiritBracelet::new);

    // pendant
    // 无主的吊坠
    public static final RegistryObject<Item> UNOWNED_PENDANT = ITEM.register("unowned_pendant", UnownedPendant::new);
    // 混沌吊坠
    public static final RegistryObject<Item> CHAOTIC_PENDANT = ITEM.register("chaotic_pendant", ChaoticPendant::new);
    // 怨影吊坠
    public static final RegistryObject<Item> SHADOW_PENDANT = ITEM.register("shadow_pendant", ShadowPendant::new);

    // necklace
    // 星星项链 
    public static final RegistryObject<Item> STAR_NECKLACE = ITEM.register("star_necklace", StarNecklace::new);
    // 十字项链
    public static final RegistryObject<Item> CROSS_NECKLACE = ITEM.register("cross_necklace", CrossNecklace::new);
    // 疾行项链
    public static final RegistryObject<Item> GALLOP_NECKLACE = ITEM.register("gallop_necklace", GallopNecklace::new);
    // 毒牙项链
    public static final RegistryObject<Item> FANG_NECKLACE = ITEM.register("fang_necklace", FangNecklace::new);
    // 珍钻项链 
    public static final RegistryObject<Item> PRECIOUS_NECKLACE = ITEM.register("precious_necklace", PreciousNecklace::new);
    // 神圣项链
    public static final RegistryObject<Item> HOLY_NECKLACE = ITEM.register("holy_necklace", HolyNecklace::new);
    // 家传项链
    public static final RegistryObject<Item> HEIRLOOM_NECKLACE = ITEM.register("heirloom_necklace", HeirloomNecklace::new);
    // 绿宝石项链 
    public static final RegistryObject<Item> EMERALD_NECKLACE = ITEM.register("emerald_necklace", EmeraldNecklace::new);
    // 末影庇佑者项链
    public static final RegistryObject<Item> ENDER_PROTECTOR = ITEM.register("ender_protector", EnderProtector::new);
    // 红心项链 
    public static final RegistryObject<Item> RED_HEART_NECKLACE = ITEM.register("red_heart_necklace", RedHeartNecklace::new);
    // 深渊之锁
    public static final RegistryObject<Item> LOCK_OF_ABYSS = ITEM.register("lock_of_abyss", LockOfAbyss::new);
    // 精灵项链
    public static final RegistryObject<Item> SPIRIT_NECKLACE = ITEM.register("spirit_necklace", SpiritNecklace::new);

    // head
    // 海神王冠
    public static final RegistryObject<Item> SEA_GOD_CROWN = ITEM.register("sea_god_crown", SeaGodCrown::new);
    // 祷告者王冠 
    public static final RegistryObject<Item> PRAYER_CROWN = ITEM.register("prayer_crown", PrayerCrown::new);
    // 深渊意志之核
    public static final RegistryObject<Item> ABYSS_CORE = ITEM.register("abyss_core", AbyssCore::new);
    // 守卫者之眼 
    public static final RegistryObject<Item> GUARDIAN_EYE = ITEM.register("guardian_eye", GuardianEye::new);
    // 邪恶之瞳 
    public static final RegistryObject<Item> EVIL_EYE = ITEM.register("evil_eye", EvilEye::new);
    // 精灵花冠
    public static final RegistryObject<Item> SPIRIT_CROWN = ITEM.register("spirit_crown", SpiritCrown::new);

    // back
    // 魔法箭袋 
    public static final RegistryObject<Item> MAGIC_ARROW_BAG = ITEM.register("magic_arrow_bag", MagicArrowBag::new);
    // 火焰箭袋 
    public static final RegistryObject<Item> FLAME_ARROW_BAG = ITEM.register("flame_arrow_bag", FlameArrowBag::new);
    // 精灵箭袋
    public static final RegistryObject<Item> SPIRIT_ARROW_BAG = ITEM.register("spirit_arrow_bag", SpiritArrowBag::new);
    // 铁剑鞘 
    public static final RegistryObject<Item> IRON_SCABBARD = ITEM.register("iron_scabbard", IronScabbard::new);
    // 水蛭剑鞘 
    public static final RegistryObject<Item> LEECH_SCABBARD = ITEM.register("leech_scabbard", LeechScabbard::new);
    // 泰坦剑鞘 
    public static final RegistryObject<Item> TITAN_SCABBARD = ITEM.register("titan_scabbard", TitanScabbard::new);
    // 扭曲剑鞘 
    public static final RegistryObject<Item> TWISTED_SCABBARD = ITEM.register("twisted_scabbard", TwistedScabbard::new);

    // tool
    // 大地系列工具
    // 斧
    public static final RegistryObject<Item> EARTH_AXE = ITEM.register("earth_axe", EarthAxe::new);
    // 镐
    public static final RegistryObject<Item> EARTH_PICKAXE = ITEM.register("earth_pickaxe", EarthPickaxe::new);
    // 铲
    public static final RegistryObject<Item> EARTH_SHOVEL = ITEM.register("earth_shovel", EarthShovel::new);
    // 锄
    public static final RegistryObject<Item> EARTH_HOE = ITEM.register("earth_hoe", EarthHoe::new);

    // item
    // 忏悔之境
    public static final RegistryObject<Item> REPENT_MIRROR = ITEM.register("repent_mirror", RepentMirror::new);
    // 回溯之镜
    public static final RegistryObject<Item> BACKTRACK_MIRROR = ITEM.register("backtrack_mirror", BacktrackMirror::new);
    // 净化粉末
    public static final RegistryObject<Item> PURIFIED_POWDER = ITEM.register("purified_powder", PurifiedPowder::new);

    // 灾厄之册
    public static final RegistryObject<Item> CATASTROPHE_SCROLL = ITEM.register("catastrophe_scroll", CatastropheScroll::new);

    // etching
    // 混沌
    public static final RegistryObject<Item> CHAOTIC_ETCHING = ITEM.register("chaotic_etching", ChaoticEtching::new);
    // 始源
    public static final RegistryObject<Item> ORIGIN_ETCHING = ITEM.register("origin_etching", OriginEtching::new);
    // 生命
    public static final RegistryObject<Item> ETCHING_OF_LIFE = ITEM.register("etching_of_life", EtchingOfLife::new);
    // 真理
    public static final RegistryObject<Item> TRUTH_ETCHING = ITEM.register("truth_etching", TruthEtching::new);
    // 欲望
    public static final RegistryObject<Item> DESIRE_ETCHING = ITEM.register("desire_etching", DesireEtching::new);
    // 虚无
    public static final RegistryObject<Item> NIHILITY_ETCHING = ITEM.register("nihility_etching", NihilityEtching::new);
    // 终焉
    public static final RegistryObject<Item> END_ETCHING = ITEM.register("end_etching", EndEtching::new);

    // food
    // 厄运土豆
    public static final RegistryObject<Item> UNLUCKY_POTATO = ITEM.register("unlucky_potato", UnluckyPotato::new);

    // test
    // 测试物品
    public static final RegistryObject<Item> TEST_ONE_ITEM = ITEM.register("test_one_item", TestOneItem::new);
}