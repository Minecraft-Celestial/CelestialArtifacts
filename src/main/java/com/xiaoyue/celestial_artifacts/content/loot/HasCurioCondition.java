package com.xiaoyue.celestial_artifacts.content.loot;

import com.xiaoyue.celestial_artifacts.register.CALootModifier;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class HasCurioCondition implements LootItemCondition, LootItemCondition.Builder {

	@SerialClass.SerialField
	public Item item;

	@Deprecated
	public HasCurioCondition() {

	}

	public HasCurioCondition(Item item) {
		this.item = item;
	}

	@Override
	public LootItemConditionType getType() {
		return CALootModifier.HAS_CURIO.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		if (!ctx.hasParam(LootContextParams.LAST_DAMAGE_PLAYER)) return false;
		var player = ctx.getParam(LootContextParams.LAST_DAMAGE_PLAYER);
		return CurioUtils.hasCurio(player, item);
	}

	@Override
	public LootItemCondition build() {
		return this;
	}

}
