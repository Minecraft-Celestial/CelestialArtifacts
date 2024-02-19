package com.xiaoyue.celestial_artifacts.content.curios.etching;

import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import net.minecraft.world.damagesource.DamageSource;

public class TruthEtching extends XICurioBase {
    public TruthEtching() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }
}
