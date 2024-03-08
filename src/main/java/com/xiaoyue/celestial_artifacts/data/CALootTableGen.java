package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public enum CALootTableGen {
	VILLAGE_PLAINS_HOUSE(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, 7, CAItems.CROSS_NECKLACE),
	ABANDONED_MINESHAFT(BuiltInLootTables.ABANDONED_MINESHAFT, 5, CAItems.FANG_NECKLACE),
	ANCIENT_CITY(BuiltInLootTables.ANCIENT_CITY, 12, CAItems.UNOWNED_PENDANT, CAItems.SACRIFICIAL_OBJECT),
	BASTION_TREASURE(BuiltInLootTables.BASTION_TREASURE, 5, CAItems.HEART_OF_REVENGE),
	DESERT_PYRAMID(BuiltInLootTables.DESERT_PYRAMID, 5, CAItems.SANDS_TALISMAN),
	JUNGLE_TEMPLE(BuiltInLootTables.JUNGLE_TEMPLE, 12, CAItems.BEARING_STAMEN, CAItems.SPIRIT_ARROW_BAG),
	NETHER_BRIDGE(BuiltInLootTables.NETHER_BRIDGE, 5, CAItems.RED_RUBY_BRACELET),
	UNDERWATER_RUIN_BIG(BuiltInLootTables.UNDERWATER_RUIN_BIG, 7, CAItems.LEECH_SCABBARD),
	FISHING_TREASURE(BuiltInLootTables.FISHING_TREASURE, 33, CAItems.MAGIC_HORSESHOE),
	;

	public final List<Supplier<Item>> item;
	public final ResourceLocation target;
	public final int odds;

	@SafeVarargs
	CALootTableGen(ResourceLocation target, int odds, Supplier<Item>... item) {
		this.item = List.of(item);
		this.target = target;
		this.odds = odds - item.length;
	}

	public ResourceLocation id() {
		return CelestialArtifacts.loc("chests/" + name().toLowerCase(Locale.ROOT));
	}

	public static void onLootGen(RegistrateLootTableProvider pvd) {
		pvd.addLootAction(LootContextParamSets.EMPTY, cons -> {
			for (var e : values()) {
				var pool = LootPool.lootPool();
				for (var item : e.item) {
					var entry = LootTableTemplate.getItem(item.get(), 1).setWeight(1);
					if (item.get() == CAItems.MAGIC_HORSESHOE.get())
						entry.when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(
								MinMaxBounds.Doubles.atLeast(300))));
					pool.add(entry);
				}
				pool.add(LootTableTemplate.getItem(Items.AIR, 0).setWeight(e.odds));
				cons.accept(e.id(), LootTable.lootTable().withPool(pool));
			}
		});
	}

}
