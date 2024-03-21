package com.xiaoyue.celestial_artifacts.content.items.tool;

import com.xiaoyue.celestial_core.register.CCItems;
import dev.xkmc.l2library.util.math.MathHelper;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.UUID;

public class EarthTierUtils implements Tier {

	public static final UUID BASE_BLOCK_REACH_UUID = MathHelper.getUUIDFromString("celestial_artifacts:block_reach");
	public static final UUID BASE_ENTITY_REACH_UUID = MathHelper.getUUIDFromString("celestial_artifacts:entity_reach");

	@Override
	public int getUses() {
		return 12456;
	}

	@Override
	public float getSpeed() {
		return 20;
	}

	@Override
	public float getAttackDamageBonus() {
		return 10;
	}

	@Override
	public int getLevel() {
		return 5;
	}

	@Override
	public int getEnchantmentValue() {
		return 30;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(CCItems.EARTH_CORE.get());
	}
}
