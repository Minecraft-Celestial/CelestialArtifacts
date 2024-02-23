package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
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

		default MutableComponent get(Object... objs) {
			if (objs.length != entry().count())
				throw new IllegalArgumentException("for " + entry().id() + ": expect " + entry().count() + " parameters, got " + objs.length);
			return translate(desc(), objs);
		}

	}

	public enum Modular implements Info {
		EFFECT_REFRESH("Grants wearer: ", 0),
		EFFECT_FLASH("For every %s seconds, grants wearer: ", 1),
		FORTUNE("Fortune", 0),
		LOOT("Looting", 0),
		XP("Xp Gain", 0),
		;

		final Entry entry;

		Modular(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	public enum Back implements Info {
		;

		final Entry entry;

		Back(String def, int count) {
			entry = new Entry(name().toLowerCase(Locale.ROOT), def, count);
		}

		public Entry entry() {
			return entry;
		}

	}

	static {
		putLang(Modular.class, "modular", Modular.values());
		putLang(Back.class, "back", Back.values());
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
