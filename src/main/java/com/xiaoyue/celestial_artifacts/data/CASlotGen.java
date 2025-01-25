package com.xiaoyue.celestial_artifacts.data;

import com.xiaoyue.celestial_artifacts.CelestialArtifacts;
import dev.xkmc.l2library.compat.curios.CurioEntityBuilder;
import dev.xkmc.l2library.compat.curios.CurioSlotBuilder;
import dev.xkmc.l2library.compat.curios.SlotCondition;
import dev.xkmc.l2library.serial.config.RecordDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class CASlotGen extends RecordDataProvider {

	public CASlotGen(DataGenerator generator) {
		super(generator, "Celestial Artifact Slot Provider");
	}

	@Override
	public void add(BiConsumer<String, Record> map) {

		for (var e : Type.values()) {
			int order = 20 + e.ordinal();
			if (e == Type.CATASTROPHE) order = -5;
			map.accept(CelestialArtifacts.MODID + "/curios/slots/" + e.id(), new CurioSlotBuilder(order,
					new ResourceLocation(CelestialArtifacts.MODID, "slot/empty_" + e.id() + "_slot").toString(),
					e.slot, CurioSlotBuilder.Operation.SET));
		}

		map.accept(CelestialArtifacts.MODID + "/curios/entities/player_vanilla", new CurioEntityBuilder(
				new ArrayList<>(List.of(new ResourceLocation("player"))),
				new ArrayList<>(List.of("back", "body", "bracelet", "charm", "head", "necklace", "ring")),
				SlotCondition.of()
		));

		map.accept(CelestialArtifacts.MODID + "/curios/entities/player_celestial", new CurioEntityBuilder(
				new ArrayList<>(List.of(new ResourceLocation("player"))),
				new ArrayList<>(Stream.of(Type.values()).map(Type::id).toList()),
				SlotCondition.of()
		));
	}

	public enum Type {
		SCROLL(1),
		PENDANT(1),
		HEART(1),
		CATASTROPHE(1),
		ETCHING(0);

		private final int slot;

		Type(int slot) {
			this.slot = slot;
		}

		public String id() {
			return name().toLowerCase(Locale.ROOT);
		}


	}
}
