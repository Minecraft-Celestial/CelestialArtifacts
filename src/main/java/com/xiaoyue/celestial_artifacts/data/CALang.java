package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class CALang {

	private static final HashMap<Class<?>, EnumEntry> MAP = new HashMap<>();

	@SafeVarargs
	private static <T extends Info> void putLang(Class<T> cls, String str, T... vals) {
		MAP.put(cls, new EnumEntry(str, vals));
	}

	public record EnumEntry(String path, Info[] info) {

	}

	public record Entry(String id, String def, int count) {
	}

	public interface Info {

		Entry entry();

		default String path() {
			return MAP.get(getClass()).path();
		}

		default String desc() {
			return CelestialArtifacts.MODID + "." + path() + "." + entry().id();
		}

		default MutableComponent get(Component... objs) {
			if (objs.length != entry().count())
				throw new IllegalArgumentException("for " + entry().id() + ": expect " + entry().count() + " parameters, got " + objs.length);
			return translate(desc(), (Object[]) objs);
		}

	}

	public enum Tooltip implements Info {
		END_DUST("%s chance to drop when players equipped with [%s] kill monsters targeting them", 2),
		NEBULA_CUBE("Dropped when player failed to get etchings while meeting conditions", 0),
		;

		final Entry entry;

		Tooltip(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		@Override
		public Entry entry() {
			return entry;
		}

	}

	public enum Modular implements Info {
		EFFECT_REFRESH("Grants wearer: ", 0),
		EFFECT_FLASH("For every %s seconds, grants wearer: ", 1),
		EFFECT_INFLICT("On hit targets, inflicts: ", 0),
		EFFECT_INFLICT_CHANCE("On hit targets, %s chance to inflict: ", 1),
		EFFECT_HURT("On hurt, grants: ", 0),
		EFFECT_HURT_CHANCE("On hurt, %s chance to grant: ", 1),
		FORTUNE("Fortune", 0),
		LOOT("Looting", 0),
		XP("Xp Gain", 0),
		ENDER_MASK("Looking at endermen will not aggravate them", 0),
		IMMUNE("This item cannot be destroyed", 0),
		CURSE("Requires %s to be equipped", 1),
		SHIFT("Press [%s] to display curio effects", 1),
		ALT("Press [%s] to display set effects", 1),
		INVUL_TIME("Increase invulnerable time by %s", 1),
		HURT_BONUS("Increase damage by %s", 1),
		PROTECT("Reduce damage by %s", 1),
		PROTECT_TYPE("Reduce incoming %s damage by %s", 2),
		AVOID_TYPE("%s chance to avoid %s damage", 2),
		NEGATE_TYPE("Negates %s damage", 1),
		COMMA(", ", 0),
		SET("Requires [%s] to take effect:", 1),
		SKILL("Active Skill: ", 0),
		SKILL_CD("Cool Down: %s seconds", 1),
		CURRENT_BONUS("Current bonus:", 0),
		DIG_SPEED("Dig Speed", 0),
		;

		final Entry entry;

		Modular(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}


		public Entry entry() {
			return entry;
		}

		public static MutableComponent comma() {
			return COMMA.get();
		}

		public static MutableComponent item(ItemStack stack) {
			return stack.getHoverName().copy().withStyle(stack.getRarity().getStyleModifier());
		}

		public static MutableComponent curseItem() {
			return item(CAItems.CATASTROPHE_SCROLL.asStack());
		}

		public static MutableComponent curse() {
			return CURSE.get(curseItem()).withStyle(ChatFormatting.RED);
		}

		public static MutableComponent shift() {
			return SHIFT.get(Component.literal("SHIFT").withStyle(ChatFormatting.YELLOW))
					.withStyle(ChatFormatting.GRAY);
		}

		public static MutableComponent alt() {
			return ALT.get(Component.literal("ALT").withStyle(ChatFormatting.YELLOW))
					.withStyle(ChatFormatting.GRAY);
		}

	}

	public enum Condition implements Info {
		PLAYER_WET("When player is in water or rain:", 0),
		NIGHT("At Night:", 0),
		DAY("At Day:", 0),
		HOT_REGION("When in hot biomes:", 0),
		ATTACK_BEHIND("When attacking from behind:", 0),
		FRONT_DAMAGE("When attacked by mobs in front:", 0),
		TARGET_HAS_ARMOR("When target has armor:", 0),
		REVENGE("When hurt, for the next %s seconds: ", 1),
		LUCK("When you have %s or more Luck: ", 1),
		TITAN("With %s, when you deal melee damage to mobs with higher max health than you: ", 1),
		NETHER("When you are in nether: ", 0),
		SNEAK("When you are sneaking: ", 0),
		HURT_FIRE("When you are hurt with fire damage:", 0),
		LOW_HEALTH("When you are at %s or lower health:", 1);

		final Entry entry;

		Condition(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum DamageTypes implements Info {
		MAGIC("magic", e -> e.is(L2DamageTypes.MAGIC)),
		FIRE("fire", e -> e.is(DamageTypeTags.IS_FIRE)),
		FALL("fall", e -> e.is(DamageTypeTags.IS_FALL)),
		FREEZE("freezing", e -> e.is(DamageTypeTags.IS_FREEZING)),
		LIGHTNING("lightning", e -> e.is(DamageTypeTags.IS_LIGHTNING)),
		PROJECTILE("projectile", e -> e.is(DamageTypeTags.IS_PROJECTILE)),
		WATER_MOB("water mob", e -> e.getEntity() instanceof Mob mob && mob.getMobType() == MobType.WATER),
		;

		final Entry entry;
		final Predicate<DamageSource> pred;

		DamageTypes(String def, Predicate<DamageSource> pred) {
			this.entry = new Entry(name().toLowerCase(Locale.ROOT), def, 0);
			this.pred = pred;
		}

		public Entry entry() {
			return entry;
		}

		public boolean pred(DamageSource source) {
			return pred.test(source);
		}
	}

	public enum Back implements Info {
		FLAME("发射的箭矢命中目标后将使目标燃烧%s秒", 1),
		LEECH("拥有%s效果时，攻击造成伤害的%s将用于治疗", 2);

		final Entry entry;

		Back(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Bracelet implements Info {
		;

		final Entry entry;

		Bracelet(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Charm implements Info {
		WAR_DEAD_BADGE_1("每损失1%生命", 0),
		WAR_DEAD_BADGE_9("若背负混沌诅咒，当生命低于最大生命的%s时:", 1),
		WAR_DEAD_BADGE_11("周围每有1只怪物，攻击后恢复%s已损失生命", 1),

		CURSED_PROTECTOR_0("副手装备的盾牌不会再进入冷却", 0),
		CURSED_PROTECTOR_1("受到伤害大于当前生命的%s时，伤害降低%s", 2),

		UNDEAD_CHARM("受到致命伤害时规避该伤害，冷却%s秒", 1),
		TWISTED_BRAIN("受到伤害时有%s的概率规避该伤害并获得%s", 2),

		SOUL_BOX_0("受到攻击时有%s概率对攻击者施加%s", 2),
		SOUL_BOX_1("受到致命攻击时规避该伤害，冷却%s秒，并且:", 1),
		SOUL_BOX_2("对攻击者施加%s", 1),
		SOUL_BOX_3("对攻击者造成1次相当于你最大生命%s的深渊伤害", 1),

		SOLAR_MAGNET_1("所处世界为白天时免疫燃烧", 0),
		SOLAR_MAGNET_2("吸引周围的物品", 0),

		SACRIFICIAL_OBJECT_1("死亡时有%s概率在原地留下1块金锭", 1),
		SACRIFICIAL_OBJECT_2("死亡时有%s概率使周围最大生命小于自身的敌对生物陪葬", 1),

		KNIGHT_SHELTER_1("副手装备盾牌时，每%s秒恢复1点生命，装备于主手时恢复速度加快", 1),
		KNIGHT_SHELTER_2("使用盾牌格挡伤害后对攻击者反伤格挡伤害的%s", 1),

		HOLY_TALISMAN_1("根据周围的怪物数量每%s秒使它们获得1次虚弱效果", 1),
		HOLY_TALISMAN_2("受到伤害降低%s，若攻击者属于亡灵生物，受到伤害则降低%s", 2),
		HOLY_TALISMAN_3("受到致命攻击时将保留1点血量并获得与最大生命相等的伤害吸收，冷却%s秒", 1),

		HOLY_SWORD_1("受到伤害时反弹12%受到伤害", 0),
		HOLY_SWORD_2("攻击亡灵生物时造成伤害全额用于治疗自身", 0),
		HOLY_SWORD_3("每损失1点生命提高%s攻击伤害", 1),

		GLUTTONY_BADGE_1("每次食用食物后获得%s与%s", 2),
		GLUTTONY_BADGE_2("每拥有1点饱食度降低%s受到的伤害", 1),

		DEMON_CURSE_0("玩家无法回血", 0),
		DEMON_CURSE_1("每1%额外治疗力:", 0),

		CURSED_TOTEM_1("使用不死图腾时给予最近的攻击者%s", 1),
		CURSED_TOTEM_2("受到非致命伤害时会积攒1层[怨念]，上限%s层", 1),
		CURSED_TOTEM_3("受到致命伤害时，则消耗%s层[怨念]并规避该伤害", 1),
		CURSED_TOTEM_4("当前已积攒的[怨念]:%s", 1),

		CURSED_TALISMAN_1("使用的武器与防具中每拥有1种诅咒附魔:", 0),

		CORRUPT_BADGE_2("获得%s, %s, %s", 3),
		CORRUPT_BADGE_3("自身每有1种负面效果:", 0),

		ANGEL_PEARL_1("持续使周围的玩家获得生命恢复效果", 0),
		ANGEL_PEARL_2("每有1种正面效果:", 0),

		ANGEL_HEART_1("每%s秒恢复%s点生命值", 2),
		ANGEL_HEART_2("每%s秒清除1次身上的负面效果", 1),

		ABYSS_WILL_BADGE_2("将[深渊呼唤]层数提高至%s层，持续%s秒", 2),
		ABYSS_WILL_BADGE_3("效果结束后将积攒的层数清0并将生命削减到当前生命的%s", 1),
		ABYSS_WILL_BADGE_4("攻击时%s概率将此伤害提高至%s，%s概率提高至%s", 4),
		ABYSS_WILL_BADGE_5("受到攻击时%s概率将此伤害提高至%s，%s概率提高至%s", 4),
		ABYSS_WILL_BADGE_6("每%s秒积攒1层[深渊呼唤]，上限%s层", 2),
		ABYSS_WILL_BADGE_7("每层[深渊呼唤]提高%s攻击伤害与%s受到伤害", 2),
		ABYSS_WILL_BADGE_8("当前已积攒的[深渊呼唤]:%s", 1);

		final Entry entry;

		Charm(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Curse implements Info {
		SCROLL_0("一旦戴上，你将无法以正常的方式将它摘下", 0),
		SCROLL_1("一些生物会产出独特的掉落物", 0),
		SCROLL_2("允许佩戴与使用一些独特的饰品", 0),
		TRIGGER("诅咒%s已生效", 1),

		CHAOS_TITLE("[混沌]", 0),
		CHAOS_TRIGGER("装备附魔盔甲时触发", 0),
		CHAOS_CURSE_0("受到的爆炸伤害提高%s", 1),
		CHAOS_CURSE_1("受到的非爆炸伤害提高%s", 1),
		CHAOS_BONUS("每损失%s生命，提高%s伤害减免", 2),

		ORIGIN_TITLE("[始源]", 0),
		ORIGIN_TRIGGER("持有%s耐久以上物品时触发", 1),
		ORIGIN_CURSE("攻击伤害降低%s", 1),
		ORIGIN_BONUS("提高%s挖掘速度与攻击伤害", 1),

		LIFE_TITLE("[生命]", 0),
		LIFE_TRIGGER("受到伤害时触发", 0),
		LIFE_CURSE("降低%s最大生命，降低%s治疗力", 2),
		LIFE_BONUS("提高%s最大生命与%s治疗力", 2),

		TRUTH_TITLE("[真理]", 0),
		TRUTH_TRIGGER("对怪物造成伤害时触发", 0),
		TRUTH_CURSE("受到生物攻击时至少会受到自身最大生命%s的伤害", 1),
		TRUTH_BONUS("受到生物攻击时伤害不会超过自身最大生命的%s", 1),

		DESIRE_TITLE("[欲望]", 0),
		DESIRE_TRIGGER("对被动生物造成伤害时触发", 0),
		DESIRE_CURSE("周围的生物将远离你，中立生物会主动攻击你", 0),
		DESIRE_BONUS("提高%s点时运与抢夺等级", 1),

		NIHILITY_TITLE("[虚无]", 0),
		NIHILITY_TRIGGER("获得增益效果时触发", 0),
		NIHILITY_CURSE("受到负面效果时长提高%s", 1),
		NIHILITY_BONUS("降低%s收到虚空伤害，受到攻击时使攻击者获得%s秒的%s级%s", 4),

		END_TITLE("[终焉]", 0),
		END_TRIGGER("午夜时未入睡触发", 0),
		END_CURSE("受到大于当前生命%s的伤害时会受到%s秒的%s级%s与%s级%s影响", 6),
		END_BONUS("攻击后恢复自身相当于已损失生命%s生命", 1),
		;

		final Entry entry;

		Curse(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Head implements Info {
		EVIL_EYE("免疫黑暗与失明效果", 0),
		ABYSS_CORE("受到伤害大于%s点时，将此伤害降低为%s点，冷却%s秒", 3),
		GUARDIAN_EYE_1("持续给予周围的生物挖掘疲劳效果", 0),
		GUARDIAN_EYE_2("处于水下时不在消耗氧气", 0),
		SEA_GOD_CROWN("所处世界处于非雨天时将天气改为雨天", 0),
		PRAYER_CROWN("潜行时受到攻击后%s概率立即恢复受到伤害%s生命", 2),
		SPIRIT_CROWN_1("周围实体数量小于%s时，射出的箭矢伤害提高%s", 2),
		SPIRIT_CROWN_2("与目标每相隔1方块的距离，射出的箭矢伤害提高%s", 1);

		final Entry entry;

		Head(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Heart implements Info {
		DEMON_HEART_1("每背负1条诅咒:", 0),
		DEMON_HEART_2("盔甲韧性提高%s", 1),
		DEMON_HEART_3("攻击伤害提高%s", 1),
		DEMON_HEART_4("受到伤害降低%s", 1),
		DEMON_HEART_5("背负大于等于3条诅咒时，阻止自身受到火焰燃烧", 0),
		DEMON_HEART_6("背负大于等于5条诅咒时，免疫缓慢与虚弱效果", 0),

		TWISTED_HEART_1("盔甲韧性降低%s", 1),
		TWISTED_HEART_2("攻击伤害降低%s", 1),
		TWISTED_HEART_3("若背负虚无诅咒，以上属性将反转为增益属性", 0);

		final Entry entry;

		Heart(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Necklace implements Info {
		GALLOP("移动时攻击会增加相当于移动速度的%s的攻击伤害", 1),
		EMERALD("击杀生物后有%s基础概率额外掉落1个绿宝石", 1),
		ENDER_PROTECTOR("使用盾牌格挡目标攻击后，有%s概率将目标传送走", 1),
		HOLY("每次受到治疗后获得%s，冷却%s秒", 2),
		LOCK_OF_ABYSS_1("攻击后给予目标%s秒缓慢效果", 1),
		LOCK_OF_ABYSS_2("若目标已有缓慢效果则将目标的缓慢效果提升1级", 0),
		LOCK_OF_ABYSS_3("攻击时若目标拥有缓慢效果且等级大于%s", 1),
		LOCK_OF_ABYSS_4("移除该效果并对目标造成原伤害%s的深渊伤害", 1);

		final Entry entry;

		Necklace(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Pendant implements Info {
		SHADOW_1("造成伤害的%s用于治疗自身", 1),
		SHADOW_2("幻翼不会再因为失眠而生成", 0),
		SHADOW_3("当处于亮度小于%s的位置时，每小于1点:", 1),
		SHADOW_4("攻击伤害提高%s", 1),
		SHADOW_5("受到伤害降低%s", 1),
		CHAOTIC("从附魔台附魔出的附魔等级加%s", 1);

		final Entry entry;

		Pendant(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Ring implements Info {
		FLIGHT("获得飞行能力", 0),
		NETHER_FIRE("受到攻击后使攻击者陷入%s秒燃烧", 1),
		RING_OF_LIFE("每%s秒随机催熟1次周围的作物", 1);

		final Entry entry;

		Ring(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Scroll implements Info {
		SEA_GOD("处于水下时挖掘速度不会再受到影响", 0),
		SKY_WALKER_2("潜行时，储存当前位置的坐标，否则将你传送到储存的坐标处", 0),
		SKY_WALKER_3("只能在同维度中传送", 0),
		SKY_WALKER_4("当前储存的坐标: %s - (%s,%s,%s)", 4),
		TRAVELER("每次进入新的维度时将获得%s与%s", 2);

		final Entry entry;

		Scroll(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Sets implements Info {
		SEA_GOD("手持三叉戟时：", 0),
		SPIRIT_0("拉弓%s秒后获得%s", 2),
		SPIRIT_1("从目标背后射出的%s伤害提高%s", 2),
		SPIRIT_2("拥有%s效果时", 1),
		SPIRIT_3("射出的箭矢有%s概率施加%s", 2);

		final Entry entry;

		Sets(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	static {
		putLang(Tooltip.class, "tooltip", Tooltip.values());
		putLang(Modular.class, "modular", Modular.values());
		putLang(Condition.class, "condition", Condition.values());
		putLang(DamageTypes.class, "damage_type", DamageTypes.values());
		putLang(Back.class, "back", Back.values());
		putLang(Bracelet.class, "bracelet", Bracelet.values());
		putLang(Charm.class, "charms", Charm.values());
		putLang(Curse.class, "curse", Curse.values());
		putLang(Head.class, "head", Head.values());
		putLang(Heart.class, "heart", Heart.values());
		putLang(Necklace.class, "necklace", Necklace.values());
		putLang(Pendant.class, "pendant", Pendant.values());
		putLang(Ring.class, "ring", Ring.values());
		putLang(Scroll.class, "scroll", Scroll.values());
		putLang(Sets.class, "sets", Sets.values());
	}

	public static void addLang(RegistrateLangProvider pvd) {
		for (var ent : MAP.values()) {
			for (var e : ent.info()) {
				pvd.add(e.desc(), e.entry().def());
			}
		}
		for (var type : CASlotGen.Type.values()) {
			pvd.add("curios.identifier." + type.id(), "Celestial - " + RegistrateLangProvider.toEnglishName(type.id()));
			pvd.add("curios.modifiers." + type.id(), "When wearing as " + RegistrateLangProvider.toEnglishName(type.id()) + ": ");
		}
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
