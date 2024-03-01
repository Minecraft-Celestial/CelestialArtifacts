package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.HashMap;
import java.util.Locale;

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
			return CelestialArtifacts.MODID + ".tooltip." + path() + "." + entry().id();
		}

		default MutableComponent get(Component... objs) {
			if (objs.length != entry().count())
				throw new IllegalArgumentException("for " + entry().id() + ": expect " + entry().count() + " parameters, got " + objs.length);
			return translate(desc(), (Object[]) objs);
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
		PROTECT_TYPE("Reduce %s damage by %s", 2);

		final Entry entry;

		Modular(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

		public static MutableComponent curse() {
			var stack = CAItems.CATASTROPHE_SCROLL.asStack();
			return CURSE.get(stack.getHoverName().copy().withStyle(stack.getRarity().getStyleModifier()))
					.withStyle(ChatFormatting.RED);
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
		HOT_REGION("When in hot biomes:", 0),
		ATTACK_BEHIND("When attacking from behind:", 0),
		TARGET_HAS_ARMOR("When target has armor:", 0),
		REVENGE("When hurt, for the next %s seconds: ", 1),
		LUCK("When you have %s or more Luck: ", 1),
		TITAN("With %s, when you deal melee damage to mobs with higher max health than you: ", 1),
		NETHER("When you are in nether: ", 0),
		SNEAK("When you are sneaking: ", 0),
		HURT_MAGIC("When you are hurt with magic:", 0),
		;

		final Entry entry;

		Condition(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum DamageType implements Info {
		MAGIC("magic", 0),
		;

		final Entry entry;

		DamageType(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Back implements Info {
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


	static {
		putLang(Modular.class, "modular", Modular.values());
		putLang(Condition.class, "condition", Condition.values());
		putLang(DamageType.class, "damage_type", DamageType.values());
		putLang(Back.class, "back", Back.values());
		putLang(Bracelet.class, "bracelet", Bracelet.values());
	}

	public static void addLang(RegistrateLangProvider pvd) {
		for (var ent : MAP.values()) {
			for (var e : ent.info()) {
				pvd.add(e.desc(), e.entry().def());
			}
		}
	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
