package com.xiaoyue.celestial_artifacts.content.old.curios.etching;

import com.xiaoyue.celestial_artifacts.content.old.generic.XICurioBase;
import net.minecraft.world.damagesource.DamageSource;

public class DesireEtching extends XICurioBase {
    public DesireEtching() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }
}