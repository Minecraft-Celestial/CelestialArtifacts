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
		END_DUST("%s chance to drop when players equipped with [%s] kill monsters targeting them", 1),
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
		PROTECT_TYPE("Reduce %s damage by %s", 2),
		AVOID_TYPE("%s chance to avoid %s damage", 2),
		NEGATE_TYPE("Negates %s damage", 1),
		COMMA(", ", 0),
		SET("Requires [%s] to take effect:", 1);

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

		HIDDENBRACE1("每%s秒获得1次隐匿效果",1),
		HIDDENBRACE2("拥有隐匿效果时攻击伤害提高%s",1),

		HIDDENBRACE4("攻击时若拥有隐匿效果则移除隐匿效果，且之后%s秒内无法再次获得",1),

		CHARMARMOR("提高%s点护甲",1),
		CHARMVIRUS("受到攻击时周围的生物会优先攻击刚才攻击你的生物，冷却%s秒",1),
		CHARMSHIP("击杀僵尸时，周围的村民会提高%s点对你的好感度",1),

		SCARLETBLOOD("攻击目标时额外消耗自身生命到最大生命的%s",1),
		SCARLETRAGE("每消耗1点生命攻击提高相当于目标最大生命%s点伤害，上限%s",2),

		SPIRITSPEEDBOW("拉弓与弩的速度提高%s",1)
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
		CURSED_PROTECTOR_0("副手装备的盾牌不会再进入冷却", 0),
		CURSED_PROTECTOR_1("受到伤害大于当前生命的%s时，伤害降低%s", 2);

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
		;

		final Entry entry;

		Head(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Heart implements Info {
		;

		final Entry entry;

		Heart(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Necklace implements Info {
		GALLOP("移动时攻击会增加相当于移动速度的%s的攻击伤害", 1);

		final Entry entry;

		Necklace(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Pendant implements Info {
		;

		final Entry entry;

		Pendant(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Ring implements Info {
		;

		final Entry entry;

		Ring(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Scroll implements Info {
		;

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
