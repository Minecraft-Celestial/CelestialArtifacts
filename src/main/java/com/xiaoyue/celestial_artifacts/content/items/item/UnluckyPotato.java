package com.xiaoyue.celestial_artifacts.content.items.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class UnluckyPotato extends Item {

	public UnluckyPotato() {
		super(new Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder()
				.nutrition(3).saturationMod(0.3f)
				.effect(() -> new MobEffectInstance(MobEffects.UNLUCK, 1200), 1)
				.build()));
	}

}
