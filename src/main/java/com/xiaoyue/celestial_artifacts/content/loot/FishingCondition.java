package com.xiaoyue.celestial_artifacts.content.loot;

import com.xiaoyue.celestial_artifacts.register.CALootModifier;
import com.xiaoyue.celestial_artifacts.utils.CurioUtils;
import dev.xkmc.l2serial.serialization.SerialClass;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

@SerialClass
public class FishingCondition implements LootItemCondition, LootItemCondition.Builder {

	@SerialClass.SerialField
	public boolean open;

	@Deprecated
	public FishingCondition() {

	}

	public FishingCondition(boolean open) {
		this.open = open;
	}

	@Override
	public LootItemConditionType getType() {
		return CALootModifier.FISHING.get();
	}

	@Override
	public boolean test(LootContext ctx) {
		if (!ctx.hasParam(LootContextParams.THIS_ENTITY)) return false;
		if (ctx.getParam(LootContextParams.THIS_ENTITY) instanceof FishingHook hook){
			return hook.isOpenWaterFishing() == open;
		}
		return false;
	}

	@Override
	public LootItemCondition build() {
		return this;
	}

}
