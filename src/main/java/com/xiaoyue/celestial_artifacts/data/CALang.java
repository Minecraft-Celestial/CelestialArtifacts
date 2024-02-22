package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class CALang {

	public enum IDS {
		EFFECT_REFRESH_CURIO("tooltip.misc.effect_refresh", "Grants wearer: ", 0),
		EFFECT_FLASH_CURIO("tooltip.misc.effect_refresh", "For every %s seconds, grants wearer: ", 1),
		;

		final String id, def;
		final int count;

		IDS(String id, String def, int count) {
			this.id = id;
			this.def = def;
			this.count = count;
		}

		public MutableComponent get(Object... objs) {
			if (objs.length != count)
				throw new IllegalArgumentException("for " + name() + ": expect " + count + " parameters, got " + objs.length);
			return translate(CelestialArtifacts.MODID + "." + id, objs);
		}

	}

	public static MutableComponent translate(String key, Object... objs) {
		return Component.translatable(key, objs);
	}

}
