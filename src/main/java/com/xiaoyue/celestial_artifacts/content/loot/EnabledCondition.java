package com.xiaoyue.celestial_artifacts.content.loot;

import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.register.CALootModifier;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class EnabledCondition implements LootItemCondition, LootItemCondition.Builder {

	@SerialClass.SerialField
	public Item item;

	@Deprecated
	public EnabledCondition() {

	}

	public EnabledCondition(ModularCurio item) {
		this.item = item;
	}

	@Override
	public LootItemConditionType getType() {
		return CALootModifier.ENABLED.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		return !(item instanceof ModularCurio curio) || curio.enableConfig().get();
	}

	@Override
	public LootItemCondition build() {
		return this;
	}

}
