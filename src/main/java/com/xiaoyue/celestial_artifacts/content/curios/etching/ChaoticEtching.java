package com.xiaoyue.celestial_artifacts.content.curios.etching;

import com.xiaoyue.celestial_artifacts.content.generic.XICurioBase;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;

public class ChaoticEtching extends XICurioBase {
    public ChaoticEtching() {
        super(new Item.Properties().stacksTo(1).fireResistant());
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }
}
