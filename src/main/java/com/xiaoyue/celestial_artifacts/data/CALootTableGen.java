package com.xiaoyue.celestial_artifacts.data;

import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import com.xiaoyue.celestial_artifacts.content.core.modular.ModularCurio;
import com.xiaoyue.celestial_artifacts.content.loot.EnabledCondition;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import dev.xkmc.l2library.util.data.LootTableTemplate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public enum CALootTableGen implements CALang.Info {
	VILLAGE_PLAINS_HOUSE("Plains Village House", BuiltInLootTables.VILLAGE_PLAINS_HOUSE, 7, CAItems.CROSS_NECKLACE, CAItems.RED_HEART_NECKLACE),
	ABANDONED_MINESHAFT("Abandoned Mineshaft", BuiltInLootTables.ABANDONED_MINESHAFT, 5, CAItems.FANG_NECKLACE),
	END_CITY_TREASURE("End City", BuiltInLootTables.END_CITY_TREASURE, 5, CAItems.TRAVELER_SCROLL),
	ANCIENT_CITY("Ancient City", BuiltInLootTables.ANCIENT_CITY, 9, CAItems.UNOWNED_PENDANT, CAItems.DEMON_HEART),
	BASTION_TREASURE("Bastion Treasure Room", BuiltInLootTables.BASTION_TREASURE, 3, CAItems.HEART_OF_REVENGE),
	DESERT_PYRAMID("Desert Pyramid", BuiltInLootTables.DESERT_PYRAMID, 5, CAItems.SANDS_TALISMAN),
	JUNGLE_TEMPLE("Jungle Temple", BuiltInLootTables.JUNGLE_TEMPLE, 12,
			CAItems.SPIRIT_ARROW_BAG, CAItems.SPIRIT_NECKLACE, CAItems.SPIRIT_BRACELET, CAItems.SPIRIT_CROWN),
	NETHER_BRIDGE("Nether Fortress Bridge", BuiltInLootTables.NETHER_BRIDGE, 5, CAItems.RED_RUBY_BRACELET),
	UNDERWATER_RUIN_BIG("Big Underwater Ruin", BuiltInLootTables.UNDERWATER_RUIN_BIG, 7, CAItems.LEECH_SCABBARD, CAItems.THUNDER_RING),
	FISHING_TREASURE("Can be fished at y>300", BuiltInLootTables.FISHING, 5, CAItems.MAGIC_HORSESHOE),
	;

	public final List<Supplier<ModularCurio>> item;
	public final ResourceLocation target;
	public final int odds;
	private final CALang.Entry entry;

	@SafeVarargs
	CALootTableGen(String def, ResourceLocation target, int odds, Supplier<ModularCurio>... item) {
		this.entry = new CALang.Entry(name().toLowerCase(Locale.ROOT), def, 0);
		this.item = List.of(item);
		this.target = target;
		this.odds = odds - item.length;
	}

	public static void onLootGen(RegistrateLootTableProvider pvd) {
		pvd.addLootAction(LootContextParamSets.EMPTY, cons -> {
			for (var e : values()) {
				var pool = LootPool.lootPool();
				for (var item : e.item) {
					var entry = LootTableTemplate.getItem(item.get(), 1).setWeight(1);
					entry.when(new EnabledCondition(item.get()));
					if (item.get() == CAItems.MAGIC_HORSESHOE.get()) {
						entry.when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setY(
								MinMaxBounds.Doubles.atLeast(300))));
					}
					pool.add(entry);
				}
				pool.add(LootTableTemplate.getItem(Items.AIR, 0).setWeight(e.odds));
				cons.accept(e.id(), LootTable.lootTable().withPool(pool));
			}
		});
	}

	@Override
	public CALang.Entry entry() {
		return entry;
	}

	public ResourceLocation id() {
		return CelestialArtifacts.loc("chests/" + name().toLowerCase(Locale.ROOT));
	}


}
