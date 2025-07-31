package com.xiaoyue.celestial_artifacts.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.effect.EnfeebledLaceration;
import net.minecraft.world.effect.MobEffect;

public class CAEffects {

    public static final RegistryEntry<EnfeebledLaceration> ENFEEBLED_LACERATION = effect("enfeebled_laceration", EnfeebledLaceration::new,
            "Reduce the movement speed, attack damage, and healing amount of organisms");

    public static <T extends MobEffect> RegistryEntry<T> effect(String name, NonNullSupplier<T> sup, String desc) {
        return CelestialArtifacts.REGISTRATE.effect(name, sup, desc).lang(MobEffect::getDescriptionId).register();
    }

    public static void register() {
    }
}
