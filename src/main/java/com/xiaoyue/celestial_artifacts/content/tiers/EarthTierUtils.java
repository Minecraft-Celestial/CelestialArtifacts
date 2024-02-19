package com.xiaoyue.celestial_artifacts.content.tiers;

import com.xiaoyue.celestial_core.register.COItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class EarthTierUtils implements Tier {
    @Override
    public int getUses() {
        return 1245;
    }

    @Override
    public float getSpeed() {
        return 4;
    }

    @Override
    public float getAttackDamageBonus() {
        return 6;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEnchantmentValue() {
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(COItems.EARTH_CORE.get());
    }
}
