package com.xiaoyue.celestial_artifacts.content.effect;

import com.xiaoyue.celestial_core.content.generic.CelestialEffect;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EnfeebledLaceration extends CelestialEffect {
    public EnfeebledLaceration() {
        super(MobEffectCategory.HARMFUL, 0xffae0007);
        String uuid = MathHelper.getUUIDFromString("celestial_artifacts:enfeebled_laceration").toString();
        addAttributeModifier(Attributes.ATTACK_DAMAGE, uuid, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, uuid, -0.2, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
